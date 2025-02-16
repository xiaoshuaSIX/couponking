CREATE DATABASE IF NOT EXISTS coupon_king_rebuild_0;
CREATE DATABASE IF NOT EXISTS coupon_king_rebuild_1;

USE coupon_king_rebuild_0;
CREATE TABLE `t_coupon_template_0`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967816300515330 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';
CREATE TABLE `t_coupon_template_1`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967812836020227 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';
CREATE TABLE `t_coupon_template_2`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967817126793218 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';
CREATE TABLE `t_coupon_template_3`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967817122598915 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';
CREATE TABLE `t_coupon_template_4`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967797723942918 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';
CREATE TABLE `t_coupon_template_5`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967789205311493 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';
CREATE TABLE `t_coupon_template_6`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967789150785539 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';
CREATE TABLE `t_coupon_template_7`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967780615376898 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

USE coupon_king_rebuild_1;
CREATE TABLE `t_coupon_template_10`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967787024273416 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

CREATE TABLE `t_coupon_template_11`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967787062022148 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

CREATE TABLE `t_coupon_template_12`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967795496767492 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

CREATE TABLE `t_coupon_template_13`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967817328119814 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

CREATE TABLE `t_coupon_template_14`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967817407811587 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

CREATE TABLE `t_coupon_template_15`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1811614173755469826 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

CREATE TABLE `t_coupon_template_8`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967783614304261 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

CREATE TABLE `t_coupon_template_9`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`             varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    `shop_number`      bigint(20) DEFAULT NULL COMMENT '店铺编号',
    `source`           tinyint(1) DEFAULT NULL COMMENT '优惠券来源 0：店铺券 1：平台券',
    `target`           tinyint(1) DEFAULT NULL COMMENT '优惠对象 0：商品专属 1：全店通用',
    `goods`            varchar(64)  DEFAULT NULL COMMENT '优惠商品编码',
    `type`             tinyint(1) DEFAULT NULL COMMENT '优惠类型 0：立减券 1：满减券 2：折扣券',
    `valid_start_time` datetime     DEFAULT NULL COMMENT '有效期开始时间',
    `valid_end_time`   datetime     DEFAULT NULL COMMENT '有效期结束时间',
    `stock`            int(11) DEFAULT NULL COMMENT '库存',
    `receive_rule`     json         DEFAULT NULL COMMENT '领取规则',
    `consume_rule`     json         DEFAULT NULL COMMENT '消耗规则',
    `status`           tinyint(1) DEFAULT NULL COMMENT '优惠券状态 0：生效中 1：已结束',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    KEY                `idx_shop_number` (`shop_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810967778472087554 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';