package com.xiaoshuai66.couponking.merchant.admin.common.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UserInfoDTO
 * Package: com.xiaoshuai66.couponking.merchant.admin.context
 * Description: 登录用户信息实体
 *
 * @Author 赵帅
 * @Create 2025/2/9 22:30
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {

    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 店铺编号
     */
    private Long shopNumber;
}
