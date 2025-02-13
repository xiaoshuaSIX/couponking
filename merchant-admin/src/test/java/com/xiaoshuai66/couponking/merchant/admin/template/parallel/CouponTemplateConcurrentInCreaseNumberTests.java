package com.xiaoshuai66.couponking.merchant.admin.template.parallel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.merchant.admin.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.merchant.admin.template.CouponTemplateTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ClassName: CouponTemplateConcurrentInCreaseNumberTests
 * Package: com.xiaoshuai66.couponking.merchant.admin.template.parallel
 * Description: 并行增加优惠券库存单元测试
 *
 * @Author 赵帅
 * @Create 2025/2/13 16:53
 * @Version 1.0
 */
@SpringBootTest
public class CouponTemplateConcurrentInCreaseNumberTests {

    @Autowired
    private CouponTemplateMapper couponTemplateMapper;

    private CouponTemplateTest couponTemplateTest;
    private CouponTemplateDO couponTemplateDO;

    @BeforeEach
    public void setUp() {
        couponTemplateTest = new CouponTemplateTest();
        couponTemplateDO = couponTemplateTest.buildCouponTemplateDO();
        couponTemplateMapper.insert(couponTemplateDO);
    }

    @Test
    public void testConcurrentIncreaseNumber() throws InterruptedException {
        int threadCount = 200;
        int increaseAmount = 10;
        long shopNumber = couponTemplateDO.getShopNumber();
        long couponTemplateId = couponTemplateDO.getId();

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                couponTemplateMapper.increaseNumberCouponTemplate(shopNumber, String.valueOf(couponTemplateId), increaseAmount);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getShopNumber, shopNumber)
                .eq(CouponTemplateDO::getId, couponTemplateDO.getId());
        CouponTemplateDO updateCouponTemplateDO = couponTemplateMapper.selectOne(queryWrapper);

        int expectedNumber = couponTemplateDO.getStock() + (threadCount * increaseAmount);
        assertEquals(expectedNumber, updateCouponTemplateDO.getStock(), "The stock count should match the expected value.");
    }
}
