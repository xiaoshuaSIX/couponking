package com.xiaoshuai66.couponking.merchant.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

        // 通过 EasyExcel 监听获取 Excel 中所有行数
        RowCountListener listener = new RowCountListener();
        EasyExcel.read(requestParam.getFileAddress(), listener).sheet().doRead();

        // 为什么需要统计行数？因为发送后需要比对所有优惠券是否都已发送到用户账号
        int totalRows = listener.getRowCount();
        couponTaskDO.setSendNum(totalRows);

        // 保存优惠券推送任务记录到数据库
        couponTaskMapper.insert(couponTaskDO);
    }
}
