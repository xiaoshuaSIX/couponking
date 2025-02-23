package com.xiaoshuai66.couponking.distribution.mq.consumer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoshuai66.couponking.distribution.common.constant.DistributionRedisConstant;
import com.xiaoshuai66.couponking.distribution.common.constant.EngineRedisConstant;
import com.xiaoshuai66.couponking.distribution.common.enums.CouponSourceEnum;
import com.xiaoshuai66.couponking.distribution.common.enums.CouponStatusEnum;
import com.xiaoshuai66.couponking.distribution.common.enums.CouponTaskStatusEnum;
import com.xiaoshuai66.couponking.distribution.dao.entity.CouponTaskDO;
import com.xiaoshuai66.couponking.distribution.dao.entity.CouponTaskFailDO;
import com.xiaoshuai66.couponking.distribution.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.distribution.dao.entity.UserCouponDO;
import com.xiaoshuai66.couponking.distribution.dao.mapper.CouponTaskFailMapper;
import com.xiaoshuai66.couponking.distribution.dao.mapper.CouponTaskMapper;
import com.xiaoshuai66.couponking.distribution.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.distribution.dao.mapper.UserCouponMapper;
import com.xiaoshuai66.couponking.distribution.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.distribution.mq.event.CouponTemplateDistributionEvent;
import com.xiaoshuai66.couponking.distribution.service.handler.excel.UserCouponTaskFailExcelObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchExecutorException;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.*;

/**
 * @ClassName: CouponExecuteDistributionConsumer.java
 * @Description: 优惠券执行分发到用户消费者
 * @author 赵帅
 * @Create 2025/2/21 10:02
 */
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = "coupon-king_distribution-service_coupon-execute-distribution_topic",
        consumerGroup = "coupon-king_distribution-service_coupon-execute-distribution_cg",
        consumeMode = ConsumeMode.ORDERLY
)
@Slf4j(topic = "CouponExecuteDistributionConsumer")
public class CouponExecuteDistributionConsumer implements RocketMQListener<MessageWrapper<CouponTemplateDistributionEvent>> {

    private final UserCouponMapper userCouponMapper;
    private final CouponTemplateMapper couponTemplateMapper;
    private final CouponTaskMapper couponTaskMapper;
    private final CouponTaskFailMapper couponTaskFailMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Lazy
    @Autowired
    private CouponExecuteDistributionConsumer couponExecuteDistributionConsumer;

    private final static  int BATCH_USER_COUPON_SIZE = 5000;
    private static final String BATCH_SAVE_USER_COUPON_LUA_PATH = "lua/batch_user_coupon_list.lua";
    private final String excelPath = Paths.get("").toAbsolutePath()+"/tmp";

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onMessage(MessageWrapper<CouponTemplateDistributionEvent> messageWrapper) {
        // 开头打印日志，平常可 Debug 看任务参数，线上可报平安（比如消息是否消费，重新投递时获取参数等）
        log.info("[消费者] 优惠券任务执行推送@分发到用户账号 - 执行消费逻辑，消息体：{}", JSON.toJSONString(messageWrapper));

        // 当保存用户优惠券集合达到批量保存数量
        CouponTemplateDistributionEvent event = messageWrapper.getMessage();
        if (!event.getDistributionEndFlag() && event.getBatchUserSetSize() % BATCH_USER_COUPON_SIZE == 0) {
            decrementCouponTemplateStockAndSaveUserCouponList(event);
            return;
        }

        // 分发任务结束标识为 TRUE，代表已经没有 Excel 记录了
        if (event.getDistributionEndFlag()) {
            String batchUserSetKey = String.format(DistributionRedisConstant.TEMPLATE_TASK_EXECUTE_BATCH_USER_KEY, event.getCouponTaskId());
            Long batchUserIdsSize = stringRedisTemplate.opsForSet().size(batchUserSetKey);
            event.setBatchUserSetSize(batchUserIdsSize.intValue());

            decrementCouponTemplateStockAndSaveUserCouponList(event);
            List<String> batchUserMaps = stringRedisTemplate.opsForSet().pop(batchUserSetKey, Integer.MAX_VALUE);
            // 此时待保存入库用户优惠券列表如果还有值，就意味着可能库存不足引起的
            if (CollUtil.isNotEmpty(batchUserMaps)) {
                // 添加到 t_coupon_task_fail 并标记错误原因，方便后续查看未成功发送的原因和记录
                List<CouponTaskFailDO> couponTaskFailDOList = new ArrayList<>(batchUserMaps.size());
                for (String batchUserMapStr : batchUserMaps) {
                    Map<Object, Object> objectMap = MapUtil.builder()
                            .put("rowNum", JSON.parseObject(batchUserMapStr).get("rowNum"))
                            .put("cause", "优惠券模板无库存")
                            .build();
                    CouponTaskFailDO couponTaskFailDO = CouponTaskFailDO.builder()
                            .batchId(event.getCouponTaskBatchId())
                            .jsonObject(com.alibaba.fastjson.JSON.toJSONString(objectMap))
                            .build();
                    couponTaskFailDOList.add(couponTaskFailDO);
                }

                // 添加到 t_coupon_task_fail 并标记错误原因
                couponTaskFailMapper.insert(couponTaskFailDOList);
            }

            long initId = 0;
            boolean isFirstIteration = true; // 用于标识是否为第一次迭代
            String failFileAddress = excelPath + "/用户分发记录失败Excel-" + event.getCouponTaskBatchId() + ".xlsx";
            // 如果 excelPath 不存在，则先创建
            if (!FileUtil.exist(excelPath)) {
                FileUtil.mkdir(excelPath);
            }

            // 这里应该上传云 OSS 或者 MinIO 等存储平台，但是增加部署成功并且不太好往简历写就仅写入本地
            try (ExcelWriter excelWriter = EasyExcel.write(failFileAddress, UserCouponTaskFailExcelObject.class).build()){
                WriteSheet writeSheet = EasyExcel.writerSheet("用户分发失败Sheet").build();
                while (true) {
                    List<CouponTaskFailDO> couponTaskFailDOList = listUserCouponTaskFail(event.getCouponTaskBatchId(), initId);
                    if (CollUtil.isEmpty(couponTaskFailDOList)) {
                        // 如果第一次迭代且集合为空，则设置 failFileAddress 为 null
                        if (isFirstIteration) {
                            failFileAddress = null;
                        }
                        break;
                    }

                    // 标记第一次迭代已经完成
                    isFirstIteration = false;

                    // 将失败行数和失败原因写入 Excel 文件
                    List<UserCouponTaskFailExcelObject> excelDataList = couponTaskFailDOList.stream()
                            .map(each -> JSONObject.parseObject(each.getJsonObject(), UserCouponTaskFailExcelObject.class))
                            .toList();
                    excelWriter.write(excelDataList, writeSheet);

                    // 查询出来的数据如果小于 BATCH_USER_COUPON_SIZE 意味着后面将不再有数据，返回即可
                    if (couponTaskFailDOList.size() < BATCH_USER_COUPON_SIZE) {
                        break;
                    }

                    // 更新 initId 当前列表中最大 ID
                    initId = couponTaskFailDOList.stream()
                            .mapToLong(CouponTaskFailDO::getId)
                            .max()
                            .orElse(initId);
                }
            }

            // 确保所有用户都已经接到优惠券后，设置优惠券推送任务完成事件
            CouponTaskDO couponTaskDO = CouponTaskDO.builder()
                    .id(event.getCouponTaskId())
                    .status(CouponTaskStatusEnum.SUCCESS.getStatus())
                    .completionTime(new Date())
                    .failFileAddress(failFileAddress)
                    .build();
            couponTaskMapper.updateById(couponTaskDO);
        }
    }

    @SneakyThrows
    private void decrementCouponTemplateStockAndSaveUserCouponList(CouponTemplateDistributionEvent event) {
        // 如果等于 0 意味着已经没有了库存，直接返回即可
        Integer couponTemplateStock = decrementCouponTemplateStock(event, event.getBatchUserSetSize());
        if (couponTemplateStock <= 0) {
            return;
        }

        // 获取 Redis 中待保存入库用户优惠券列表
        String batchUserSetKet = String.format(DistributionRedisConstant.TEMPLATE_TASK_EXECUTE_BATCH_USER_KEY, event.getCouponTaskId());
        List<String> batchUserMaps = stringRedisTemplate.opsForSet().pop(batchUserSetKet, couponTemplateStock);

        // 因为 batchUserIds 数据较多，ArrayList 会进行数次扩容，为了避免额外性能消耗，直接初始化 batchUserIds 大小的数组
        List<UserCouponDO> userCouponDOList = new ArrayList<>(batchUserMaps.size());
        Date now = new Date();

        // 构建 userCouponDOList 用户优惠券数量数组
        for (String each : batchUserMaps) {
            JSONObject userIdAndRowNumJsonObject = JSON.parseObject(each);
            //基于当前时间 now，加上 validityPeriod 小时，计算出优惠券的到期时间
            DateTime validEndTime = DateUtil.offsetHour(now, JSON.parseObject(event.getCouponTemplateConsumeRule()).getInteger("validityPeriod"));
            UserCouponDO userCouponDo = UserCouponDO.builder()
                    .id(IdUtil.getSnowflakeNextId())
                    .couponTemplateId(event.getCouponTemplateId())
                    .rowNum(userIdAndRowNumJsonObject.getInteger("rowNum"))
                    .userId(userIdAndRowNumJsonObject.getLong("userId"))
                    .receiveTime(now)
                    .receiveCount(1) // 代表第一次领取该优惠券
                    .validStartTime(now)
                    .validEndTime(validEndTime)
                    .source(CouponSourceEnum.PLATFORM.getType())
                    .status(CouponStatusEnum.EFFECTIVE.getType())
                    .createTime(new Date())
                    .updateTime(new Date())
                    .delFlag(0)
                    .build();
            userCouponDOList.add(userCouponDo);
        }

        // 平台优惠券每个用户限领一次。批量新增用户优惠券记录，底层通过递归方式知道全部新增成功
        batchSaveUserCouponList(event.getCouponTemplateId(), event.getCouponTaskBatchId(), userCouponDOList);

        // 平台优惠券添加到用户的领券记录中
        List<String> userIdList = userCouponDOList.stream()
                .map(UserCouponDO::getUserId)
                .map(String::valueOf)
                .toList();
        String userIdsJson = new ObjectMapper().writeValueAsString(userIdList);

        List<String> couponIdList = userCouponDOList.stream()
                .map(each -> StrUtil.builder()
                        .append(event.getCouponTemplateId())
                        .append("_")
                        .append(each.getId())
                        .toString())
                .map(String::valueOf)
                .toList();
        String couponIdsJson = new ObjectMapper().writeValueAsString(couponIdList);

        // 调用 Lua 脚本时，传递参数
        List<String> keys = Arrays.asList(EngineRedisConstant.USER_COUPON_TEMPLATE_LIST_KEY);
        List<String> args = Arrays.asList(userIdsJson, couponIdsJson, String.valueOf(new Date().getTime()));

        // 获取 LUA 脚本，并保存到 Hutool 的单例管理容器，下次直接获取不需要加载
        DefaultRedisScript<Void> buildLuaScript = Singleton.get(BATCH_SAVE_USER_COUPON_LUA_PATH, () -> {
            DefaultRedisScript<Void> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(BATCH_SAVE_USER_COUPON_LUA_PATH)));
            redisScript.setResultType(Void.class);
            return redisScript;
        });
        stringRedisTemplate.execute(buildLuaScript, keys, args.toArray());
    }

    private Integer decrementCouponTemplateStock(CouponTemplateDistributionEvent event, Integer decrementStockSize) {
        // 通过乐观锁机制自减优惠券库存记录
        Long couponTemplateId = event.getCouponTemplateId();
        int decremented = couponTemplateMapper.decrementCouponTemplateStock(event.getShopNumber(), couponTemplateId, decrementStockSize);

        // 如果修改记录失败，意味着优惠券库存已不足，需要重试获取到可自减的库存数值
        if (!SqlHelper.retBool(decremented)) {
            LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                    .eq(CouponTemplateDO::getShopNumber, event.getShopNumber())
                    .eq(CouponTemplateDO::getId, couponTemplateId);
            CouponTemplateDO couponTemplateDO = couponTemplateMapper.selectOne(queryWrapper);
            return decrementCouponTemplateStock(event, couponTemplateDO.getStock());
        }

        return decrementStockSize;
    }

    private void batchSaveUserCouponList(Long couponTemplateId, Long couponTaskBatchId, List<UserCouponDO> userCouponDOList) {
        // MyBatis-Plus 批量执行用户优惠券记录
        try {
            userCouponMapper.insert(userCouponDOList, userCouponDOList.size());
        } catch (Exception ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof BatchExecutorException) {
                // 添加到 t_coupon_task_fail 并发标记错误原因，方便后续查看未成功发送的原因和记录
                List<CouponTaskFailDO> couponTaskFailDOList = new ArrayList<>();
                List<UserCouponDO> toRemove = new ArrayList<>();

                // 调用批量新增失败后，为了避免大量重复失败，我们通过新增单挑记录方式执行
                userCouponDOList.forEach(each -> {
                    try {
                        userCouponMapper.insert(each);
                    } catch (Exception ignored) {
                        Boolean hasReceived = couponExecuteDistributionConsumer.hasUserReceivedCoupon(couponTemplateId, each.getUserId());
                        if (hasReceived) {
                            // 添加到 t_coupon_task_fail 并标记错误原因，方便后续查看未成功发送的原因和记录
                            Map<Object, Object> objectMap = MapUtil.builder()
                                    .put("rowNum", each.getRowNum())
                                    .put("cause", "用户已领取该优惠券")
                                    .build();
                            CouponTaskFailDO couponTaskFailDO = CouponTaskFailDO.builder()
                                    .batchId(couponTaskBatchId)
                                    .jsonObject(com.alibaba.fastjson.JSON.toJSONString(objectMap))
                                    .build();
                            couponTaskFailDOList.add(couponTaskFailDO);

                            // 从 userCouponDOList 中删除已存在的记录
                            toRemove.add(each);
                        }
                    }
                });

                // 批量新增 t_coupon_task_fail 表
                couponTaskFailMapper.insert(couponTaskFailDOList, couponTaskFailDOList.size());

                // 删除已经重复的内容
                userCouponDOList.removeAll(toRemove);
                return;
            }

            throw ex;
        }
    }

    /**
     * 查询用户是否已经领取过优惠券
     *
     * @param couponTemplateId  优惠券模板 ID
     * @param userId            用户 ID
     * @return 用户优惠券模板领取信息是否已存在
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Boolean hasUserReceivedCoupon(Long couponTemplateId, Long userId) {
        LambdaQueryWrapper<UserCouponDO> queryWrapper = Wrappers.lambdaQuery(UserCouponDO.class)
                .eq(UserCouponDO::getUserId, userId)
                .eq(UserCouponDO::getCouponTemplateId, couponTemplateId);
        return userCouponMapper.selectOne(queryWrapper) != null;
    }

    /**
     * 查询用户分发任务失败记录
     *
     * @param batchId   分发任务批次 ID
     * @param maxId     上次读取最大 ID
     * @return 用户分发任务失败记录集合
     */
    private List<CouponTaskFailDO> listUserCouponTaskFail(Long batchId, Long maxId) {
        LambdaQueryWrapper<CouponTaskFailDO> queryWrapper = Wrappers.lambdaQuery(CouponTaskFailDO.class)
                .eq(CouponTaskFailDO::getBatchId, batchId)
                .gt(CouponTaskFailDO::getId, maxId)
                .last("LIMIT " + BATCH_USER_COUPON_SIZE);
        return couponTaskFailMapper.selectList(queryWrapper);
    }
}
