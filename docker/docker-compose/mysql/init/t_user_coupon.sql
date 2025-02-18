CREATE DATABASE IF NOT EXISTS coupon_king_rebuild_0 default character set utf8mb4 collate utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS coupon_king_rebuild_1 default character set utf8mb4 collate utf8mb4_unicode_ci;

USE coupon_king_rebuild_0;
SET NAMES utf8mb4;

CREATE TABLE `t_user_coupon_0` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_1` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_2` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_3` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_4` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_5` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_6` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_7` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_8` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_9` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                   `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                   `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                   `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                   `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                   `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                   KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_10` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_11` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_12` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_13` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_14` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_15` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

USE coupon_king_rebuild_1;
SET NAMES utf8mb4;

CREATE TABLE `t_user_coupon_16` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_17` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_18` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_19` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_20` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_21` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_22` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_23` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_24` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_25` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_26` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_27` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_28` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_29` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_30` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

CREATE TABLE `t_user_coupon_31` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
                                    `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                    `receive_count` int(3) DEFAULT NULL COMMENT '领取次数',
                                    `valid_start_time` datetime DEFAULT NULL COMMENT '有效期开始时间',
                                    `valid_end_time` datetime DEFAULT NULL COMMENT '有效期结束时间',
                                    `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                    `source` tinyint(1) DEFAULT NULL COMMENT '券来源 0：领券中心 1：平台发放 2：店铺领取',
                                    `status` tinyint(1) DEFAULT NULL COMMENT '状态 0：未使用 1：锁定 2：已使用 3：已过期 4：已撤回',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_user_id_coupon_template_receive_count` (`user_id`,`coupon_template_id`,`receive_count`) USING BTREE,
                                    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1815640588360376337 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';