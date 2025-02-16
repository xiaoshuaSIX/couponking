package com.xiaoshuai66.couponking.merchant.admin.job;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoshuai66.couponking.merchant.admin.common.enums.CouponTaskStatusEnum;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTaskDO;
import com.xiaoshuai66.couponking.merchant.admin.dao.mapper.CouponTaskMapper;
import com.xiaoshuai66.couponking.merchant.admin.mq.event.CouponTaskExecuteEvent;
import com.xiaoshuai66.couponking.merchant.admin.mq.producer.CouponTaskActualExecuteProducer;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * ClassName: CouponTaskJobHandler
 * Package: com.xiaoshuai66.couponking.merchant.admin.job
 * Description: 优惠券推送任务扫描定时发送记录 XXL-Job 处理器
 *
 * @Author 赵帅
 * @Create 2025/2/17 01:49
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
public class CouponTaskJobHandler extends IJobHandler {

    private final CouponTaskMapper couponTaskMapper;
    private final CouponTaskActualExecuteProducer couponTaskActualExecuteProducer;

    private static final int MAX_LIMIT = 100;

    @XxlJob(value = "couponTemplateTask")
    public void execute() throws Exception {
        // 用于分页查询中，记录上次查询的结束位置，也是下次查询的开始位置
        long initId = 0;
        Date now = new Date();

        while (true) {
            // 获取已到执行事件待执行的优惠券定时分发任务
            List<CouponTaskDO> couponTaskDOList = fetchPendingTasks(initId, now);

            if (CollUtil.isEmpty(couponTaskDOList)) {
                break;
            }

            // 调用分发服务对用户发送优惠券
            for (CouponTaskDO each : couponTaskDOList) {
                distributeCoupon(each);
            }

            // 查询出来的数据如果小于 MAX_LIMIT 意味着后面不再有数据，返回即可
            if (couponTaskDOList.size() < MAX_LIMIT) {
                break;
            }

            // 更新 initId 为当前列表中最大 ID
            initId = couponTaskDOList.stream()
                    .mapToLong(CouponTaskDO::getId)
                    .max()
                    .orElse(initId);
        }
    }

    private void distributeCoupon(CouponTaskDO couponTask) {
        // 修改延时执行推送任务状态为执行中
        CouponTaskDO couponTaskDO = CouponTaskDO.builder()
                .id(couponTask.getId())
                .status(CouponTaskStatusEnum.IN_PROGRESS.getStatus())
                .build();
        couponTaskMapper.updateById(couponTaskDO);
        // 通过消息队列发送消息，由分发服务消费者消费该逻辑
        CouponTaskExecuteEvent couponTaskExecuteEvent = CouponTaskExecuteEvent.builder()
                .couponTaskId(couponTask.getId())
                .build();
        couponTaskActualExecuteProducer.sendMessage(couponTaskExecuteEvent);
    }

    private List<CouponTaskDO> fetchPendingTasks(long initId, Date now) {
        LambdaQueryWrapper<CouponTaskDO> queryWrapper = Wrappers.lambdaQuery(CouponTaskDO.class)
                .eq(CouponTaskDO::getStatus, CouponTaskStatusEnum.PENDING.getStatus())
                .le(CouponTaskDO::getSendTime, now)
                .gt(CouponTaskDO::getId, initId)
                .last("LIMIT " + MAX_LIMIT);
        return couponTaskMapper.selectList(queryWrapper);
    }
}
