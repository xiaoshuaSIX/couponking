CREATE DATABASE IF NOT EXISTS coupon_king_rebuild_0 default character set utf8mb4 collate utf8mb4_unicode_ci;

USE coupon_king_rebuild_0;
SET NAMES utf8mb4;

CREATE TABLE `t_coupon_template_remind` (
                                            `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                            `coupon_template_id` bigint(20) NOT NULL COMMENT '券ID',
                                            `information` bigint(20) DEFAULT NULL COMMENT '存储信息',
                                            `shop_number` bigint(20) DEFAULT NULL COMMENT '店铺编号',
                                            `start_time` datetime DEFAULT NULL COMMENT '优惠券开抢时间',
                                            PRIMARY KEY (`user_id`,`coupon_template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户预约提醒信息存储表';