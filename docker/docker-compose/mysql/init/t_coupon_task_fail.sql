CREATE DATABASE IF NOT EXISTS coupon_king_rebuild_0 default character set utf8mb4 collate utf8mb4_unicode_ci;
USE coupon_king_rebuild_0;

SET NAMES utf8mb4;

CREATE TABLE `t_coupon_task_fail` (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                      `batch_id` bigint(20) DEFAULT NULL COMMENT '批次ID',
                                      `json_object` text COMMENT '失败内容',
                                      PRIMARY KEY (`id`),
                                      KEY `idx_batch_id` (`batch_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板发送任务失败记录表';