CREATE DATABASE IF NOT EXISTS coupon_king_rebuild_0 default character set utf8mb4 collate utf8mb4_unicode_ci;
USE coupon_king_rebuild_0;

SET NAMES utf8mb4;

CREATE TABLE `t_coupon_task` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                 `shop_number` bigint(20) DEFAULT NULL COMMENT '店铺编号',
                                 `batch_id` bigint(20) DEFAULT NULL COMMENT '批次ID',
                                 `task_name` varchar(128) DEFAULT NULL COMMENT '优惠券批次任务名称',
                                 `file_address` varchar(512) DEFAULT NULL COMMENT '文件地址',
                                 `fail_file_address` varchar(512) DEFAULT NULL COMMENT '发放失败用户文件地址',
                                 `send_num` int(11) DEFAULT NULL COMMENT '发放优惠券数量',
                                 `notify_type` varchar(32) DEFAULT NULL COMMENT '通知方式，可组合使用 0：站内信 1：弹框推送 2：邮箱 3：短信',
                                 `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                 `send_type` tinyint(1) DEFAULT NULL COMMENT '发送类型 0：立即发送 1：定时发送',
                                 `send_time` datetime DEFAULT NULL COMMENT '发送时间',
                                 `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：待执行 1：执行中 2：执行失败 3：执行成功 4：取消',
                                 `completion_time` datetime DEFAULT NULL COMMENT '完成时间',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `operator_id` bigint(20) DEFAULT NULL COMMENT '操作人',
                                 `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                 `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_batch_id` (`batch_id`) USING BTREE,
                                 KEY `idx_coupon_template_id` (`coupon_template_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1816672964423188483 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板发送任务表';