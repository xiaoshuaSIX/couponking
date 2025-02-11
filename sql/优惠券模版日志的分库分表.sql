USE coupon_king_rebuild_0;

CREATE TABLE `t_coupon_template_log_0`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_1`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_2`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_3`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_4`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_5`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_6`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_7`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

USE coupon_king_rebuild_1;

CREATE TABLE `t_coupon_template_log_10`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_11`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_12`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_13`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_14`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_15`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_8`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';

CREATE TABLE `t_coupon_template_log_9`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `shop_number`        bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `coupon_template_id` bigint(20) DEFAULT NULL COMMENT '优惠券模板ID',
    `operator_id`        bigint(20) DEFAULT NULL COMMENT '操作人',
    `operation_log`      text COMMENT '操作日志',
    `original_data`      varchar(1024) DEFAULT NULL COMMENT '原始数据',
    `modified_data`      varchar(1024) DEFAULT NULL COMMENT '修改后数据',
    `create_time`        datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板操作日志表';