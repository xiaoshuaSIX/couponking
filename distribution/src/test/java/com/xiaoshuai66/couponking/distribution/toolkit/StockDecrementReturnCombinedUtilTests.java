package com.xiaoshuai66.couponking.distribution.toolkit;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author 赵帅
 * @Description: 测试位移和字符串 split 速度，位移完胜
 * @ClassName: StockDecrementReturnCombinedUtilTests.java
 * @Create 2025/2/21 16:16
 */
@Slf4j
public class StockDecrementReturnCombinedUtilTests {

    @Test
    public void stockDecrementReturnCombinedUtilTest() {
        boolean firstField = true;
        int secondField = 5000;

        int combined = StockDecrementReturnCombinedUtil.combineFields(firstField, secondField);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            StockDecrementReturnCombinedUtil.extractFirstField(combined);
            StockDecrementReturnCombinedUtil.extractSecondField(combined);
        }
        long endTime = System.currentTimeMillis();

        long startTime2 = System.currentTimeMillis();
        String str = "1,1234";
        for (int i = 0; i < 100000; i++) {
            StrUtil.split(str, ",");
        }
        long endTime2 = System.currentTimeMillis();

        log.info("位移程序执行时间：{}", endTime - startTime);
        log.info("split程序执行时间：{}", endTime2 - startTime2);
        /**
         * 位移程序执行时间：2
         * split程序执行时间：40
         */
    }
}
