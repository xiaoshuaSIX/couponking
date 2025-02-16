package com.xiaoshuai66.couponking.merchant.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai66.couponking.framework.exception.ClientException;
import com.xiaoshuai66.couponking.merchant.admin.common.context.UserContext;
import com.xiaoshuai66.couponking.merchant.admin.common.enums.CouponTaskSendTypeEnum;
import com.xiaoshuai66.couponking.merchant.admin.common.enums.CouponTaskStatusEnum;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTaskDO;
import com.xiaoshuai66.couponking.merchant.admin.dao.mapper.CouponTaskMapper;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTaskCreateReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.resp.CouponTemplateQueryRespDTO;
import com.xiaoshuai66.couponking.merchant.admin.service.CouponTaskService;
import com.xiaoshuai66.couponking.merchant.admin.service.CouponTemplateService;
import com.xiaoshuai66.couponking.merchant.admin.service.handler.excel.RowCountListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * ClassName: CouponTaskServiceImpl
 * Package: com.xiaoshuai66.couponking.merchant.admin.service.impl
 * Description: 优惠券推送业务实现层
 *
 * @Author 赵帅
 * @Create 2025/2/16 00:10
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponTaskServiceImpl extends ServiceImpl<CouponTaskMapper, CouponTaskDO> implements CouponTaskService {

    private final CouponTemplateService couponTemplateService;
    private final CouponTaskMapper couponTaskMapper;
    private final RedissonClient redissonClient;

    /**
     * 为什么这里拒绝策略使用直接丢弃任务？因为在发送任务时如果遇到发送数量为空，会重新计算
     */
    private final ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() << 1,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new ThreadPoolExecutor.DiscardPolicy()
    );

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCouponTask(CouponTaskCreateReqDTO requestParam) {
        // 验证非空参数
        // 验证参数是否正确，比如文件地址是否为我们期望的格式等
        // 验证参数依赖关系，比如选择定时发送，发送后时间是否不为空等
        CouponTemplateQueryRespDTO couponTemplate = couponTemplateService.findCouponTemplateById(requestParam.getCouponTemplateId());
        if (couponTemplate == null) {
            throw new ClientException("优惠券模版不存在，请检查提交信息是否正确");
        }
        // ......

        // 构建优惠券推送任务数据库持久层实体
        CouponTaskDO couponTaskDO = BeanUtil.copyProperties(requestParam, CouponTaskDO.class);
        couponTaskDO.setBatchId(IdUtil.getSnowflakeNextId());
        couponTaskDO.setOperatorId(Long.parseLong(UserContext.getUserId()));
        couponTaskDO.setShopNumber(UserContext.getShopNumber());
        couponTaskDO.setStatus(
                Objects.equals(requestParam.getSendType(), CouponTaskSendTypeEnum.IMMEDIATE.getType())
                        ? CouponTaskStatusEnum.IN_PROGRESS.getStatus()
                        : CouponTaskStatusEnum.PENDING.getStatus()
        );

        // 保存优惠券推送任务记录到数据库
        couponTaskMapper.insert(couponTaskDO);

        // 为什么需要统计行数？因为发送后需要比对所有优惠券是否都已发送到用户账号
        // 100 万数据大概需要 4 秒才能返回前段，如果加上验证将会时间更长，所以这里将最耗时间到统计操作异步化
        JSONObject delayJsonObject = JSONObject
                .of("fileAddress", requestParam.getFileAddress(), "couponTaskId", couponTaskDO.getId());
        executorService.execute(() -> refreshCouponTaskSendNum(delayJsonObject));

        // 假设刚把消息提交到线程池，突然应用宕机了，我们通过延迟队列进行兜底 Refresh
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque("COUPON_TASK_SEND_NUM_DELAY_QUEUE");
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        // 这里延迟设置 20 秒，原因是我们笃定上面线程池 20 秒之内就能结束时间
        delayedQueue.offer(delayJsonObject, 20, TimeUnit.SECONDS);
    }

    private void refreshCouponTaskSendNum(JSONObject delayJsonObject) {
        // 通过 EasyExcel 监听获取 Excel 中所有行数
        RowCountListener listener = new RowCountListener();
        EasyExcel.read(delayJsonObject.getString("fileAddress"), listener).sheet().doRead();
        int totalRows = listener.getRowCount();

        // 刷新优惠券推送记录中发送行数
        CouponTaskDO updateCouponTaskDO = CouponTaskDO.builder()
                .id(delayJsonObject.getLong("couponTaskId"))
                .sendNum(totalRows)
                .build();
        // 刷新数据库中优惠券发送数量
        couponTaskMapper.updateById(updateCouponTaskDO);
    }

    /**
     * @PostConstruct 标记一个方法，表示该方法在依赖注入完成之后自动执行，用于初始化操作
     * 因为静态内部类的 Bean 注入有问题，所以我们这里直接 new 对象运行即可
     * 如果按照上一个版本些，refreshCouponTaskSendNum 方法中的 couponTaskMapper 为空
     */
    @PostConstruct
    public void init() {
        new RefreshCouponTaskDelayQueueRunner(this, couponTaskMapper, redissonClient).run();
    }

    /**
     * 优惠券延迟刷新发送条数兜底消费者｜这是兜底策略，一般来说不会执行这段逻辑
     * 如果延迟消息没有持久化成功，或者 Redis 挂了怎么办？后续可以人工处理
     */
    @RequiredArgsConstructor
    static class RefreshCouponTaskDelayQueueRunner{

        private final CouponTaskServiceImpl couponTaskService;
        private final CouponTaskMapper couponTaskMapper;
        private final RedissonClient redissonClient;

        public void run() {
            Executors.newSingleThreadExecutor(
                        runnable -> {
                        Thread thread = new Thread(runnable);
                        thread.setName("delay_coupon-task_send-num_consumer");
                        thread.setDaemon(Boolean.TRUE);
                        return thread;
                    })
                    .execute(() -> {
                        RBlockingDeque<JSONObject> blockingDeque = redissonClient.getBlockingDeque("COUPON_TASK_SEND_NUM_DELAY_QUEUE");
                        for (; ; ) {
                            try {
                                // 获取延迟队列已到达时间元素
                                JSONObject delayJsonObject = blockingDeque.take();
                                if (delayJsonObject != null) {
                                    // 获取优惠券推送记录，查看发送条数是否已经有值，有的话代表上面线程池已经处理完成完成，无需再处理
                                    CouponTaskDO couponTaskDO = couponTaskMapper.selectById(delayJsonObject.getLong("couponTaskId"));
                                    if (couponTaskDO.getSendNum() == null) {
                                        couponTaskService.refreshCouponTaskSendNum(delayJsonObject);
                                    }
                                }
                            } catch (Throwable ignored) {
                            }
                        }
                    });
        }
    }
}
