package com.xiaoshuai66.couponking.merchant.admin.template.lock;

import com.alibaba.fastjson2.JSON;
import com.xiaoshuai66.couponking.merchant.admin.common.context.UserContext;
import com.xiaoshuai66.couponking.merchant.admin.common.context.UserInfoDTO;
import com.xiaoshuai66.couponking.merchant.admin.controller.CouponTemplateController;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: CouponTemplateCreateDuplicateSubmitTests
 * Package: com.xiaoshuai66.couponking.merchant.admin.template.lock
 * Description: 优惠券模版防重复提交测试
 *
 * @Author 赵帅
 * @Create 2025/2/12 23:22
 * @Version 1.0
 */
@Slf4j
@SpringBootTest
public class CouponTemplateCreateDuplicateSubmitTests {

    @Autowired
    private CouponTemplateController couponTemplateController;

    @SneakyThrows
    @Test
    public void testDuplicateSubmit(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        String paramJSONStr = """
                {
                  "name": "用户下单满10减3特大优惠",
                  "source": 0,
                  "target": 1,
                  "goods": "",
                  "type": 0,
                  "validStartTime": "2025-02-23 00:45:24",
                  "validEndTime": "2025-02-28 00:45:24",
                  "stock": 200,
                  "receiveRule": "{\\"limitPerPerson\\":1,\\"usageInstructions\\":\\"3\\"}",
                  "consumeRule": "{\\"termsOfUse\\":10,\\"maximumDiscountAmount\\":3,\\"explanationOfUnmetConditions\\":\\"3\\",\\"validityPeriod\\":\\"48\\"}"
                }
                """;
        // 模拟一个HTTP请求
        MockHttpServletRequest request = new MockHttpServletRequest();

        ServletRequestAttributes attributes = new ServletRequestAttributes(request);

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                RequestContextHolder.setRequestAttributes(attributes); // 将 ServletRequestAttributes 绑定到当前线程
                UserInfoDTO userInfoDTO = new UserInfoDTO("1810518709471555585", "pdd45305558318", 1810714735922956666L);
                UserContext.setUser(userInfoDTO);
                try {
                    couponTemplateController.createCouponTemplate(JSON.parseObject(paramJSONStr, CouponTemplateSaveReqDTO.class));
                } catch (Exception ex) {
                    log.error("新增优惠券模版异常", ex);
                } finally {
                    RequestContextHolder.resetRequestAttributes();
                }
            });
        }

        executorService.shutdown();
        // executorService.isTerminated(): 检查线程池是否已经完全终止，如果仍有任务在执行或线程池未关闭，返回false
        while (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }
    }
}
