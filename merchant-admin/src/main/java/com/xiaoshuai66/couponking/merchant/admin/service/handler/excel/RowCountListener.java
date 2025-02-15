package com.xiaoshuai66.couponking.merchant.admin.service.handler.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

/**
 * ClassName: RowCountListener
 * Package: com.xiaoshuai66.couponking.merchant.admin.service.handler.excel
 * Description: Excel 行数统计监听器
 *
 * @Author 赵帅
 * @Create 2025/2/16 01:01
 * @Version 1.0
 */
public class RowCountListener extends AnalysisEventListener<Object> {

    @Getter
    private int rowCount = 0;

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        rowCount++;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // No additional actions after all data is analyzed
    }
}
