/*
 Navicat Premium Data Transfer

 Source Server         : MyLocalHost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : cms

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 30/08/2019 18:29:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ck_wc_par_notice
-- ----------------------------
DROP TABLE IF EXISTS `ck_wc_par_notice`;
CREATE TABLE `ck_wc_par_notice`  (
  `out_trade_no` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单信息',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众账号',
  `sub_mch_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子商户号',
  `device_info` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备号(留空)',
  `result_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务结果',
  `err_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误代码',
  `err_code_des` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误代码描述',
  `openid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户标识',
  `trade_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易类型',
  `bank_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款银行',
  `total_fee` int(11) NULL DEFAULT NULL COMMENT '总订单金额 单位分',
  `fee_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货币种类',
  `cash_fee` int(11) NULL DEFAULT NULL COMMENT '现金支付',
  `cash_fee_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现金支付类型',
  `transaction_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `time_end` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付完成时间',
  `pay_way` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付途径 gzh：公众账号 qr:二维码自定义',
  PRIMARY KEY (`out_trade_no`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ml_customer
-- ----------------------------
DROP TABLE IF EXISTS `ml_customer`;
CREATE TABLE `ml_customer`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `belongs` int(2) NULL DEFAULT 0 COMMENT '-1.公共客户 0.未分配 ',
  `lasttime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分配的时间',
  `time` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(2) NULL DEFAULT 0 COMMENT '合作状态 0.联系中1.合作失败2.合作成功',
  `look` int(2) NULL DEFAULT 0 COMMENT '新客户分配给员工后员工确认否   0未确认  1确认',
  `restatus` int(1) NULL DEFAULT 0 COMMENT '是否回访 0否  1是',
  `caim` int(1) NULL DEFAULT 0 COMMENT '客户意向1-3 针创无',
  `cfrom` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户来源',
  `type` int(11) NULL DEFAULT NULL COMMENT '1~7 A+~F',
  `keyword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `etime` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近沟通时间',
  `rate` int(2) NULL DEFAULT -1 COMMENT '进度 0未联系上 1联系中 2来访签订 3来访未签订',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '沟通内容',
  `count` int(5) NULL DEFAULT 0 COMMENT '沟通次数',
  `project` int(5) NULL DEFAULT NULL COMMENT '询问的项目',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `time_index`(`time`) USING BTREE,
  FULLTEXT INDEX `id`(`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ml_customer
-- ----------------------------
INSERT INTO `ml_customer` VALUES ('1309ab32d8da4a38addf28cf94f62792', 'cmy', '美国', '123456', '12345678911', '111', 16, '2019-08-27 18:05:12', '2019-08-19 17:25:05', 0, 0, 0, 3, '123', NULL, '111', '2019-08-22 13:20:29', -1, '1111', 1, 4);
INSERT INTO `ml_customer` VALUES ('197d31a161c348e8a095979726c8a220', 'wqerqwer', 'qwerqw', NULL, 'qwerq', 'qwerqw', 16, '2019-08-27 18:04:04', '2019-08-22 09:51:28', 0, 0, 0, 0, 'qwerqw', NULL, 'qwerwq', NULL, -1, NULL, 0, 5);
INSERT INTO `ml_customer` VALUES ('27fc00ee80ce42819a320834bcc2532b', 'qwrewqr', 'qwereqwe', '1221', 'qwerqw', 'qwerqwe', 16, '2019-08-27 18:04:04', '2019-08-22 09:51:59', 2, 0, 0, 2, 'wqerqwre', NULL, 'qwer', '2019-08-27 18:08:24', 2, '1111111111', 2, 5);
INSERT INTO `ml_customer` VALUES ('35b99a63e45c4c0e9301b97c7e6cbe98', 'wqerqwer', 'qwerqw', NULL, 'qwerq', 'qwerqw', 16, '2019-08-27 18:05:12', '2019-08-22 09:51:17', 0, 0, 0, 3, 'qwerqw', NULL, 'qwerwq', '2019-08-27 18:16:06', -1, '11111111111111111111111', 1, 5);
INSERT INTO `ml_customer` VALUES ('3f0454a97b69444097e257b72b20d025', '小伙子', '大伙子', '122', '111', '1111', -1, '2019-08-19 14:51:09', '2019-08-16 09:05:16', 0, 0, 0, 0, 'qyxq', NULL, NULL, NULL, -1, NULL, 0, NULL);
INSERT INTO `ml_customer` VALUES ('4298a37030884086bb167cdc4c3f0d7b', '234214', '21341', '12341', '1234', '111111111111', 16, '2019-08-27 18:04:04', '2019-08-22 09:55:09', 2, 0, 0, 1, '1234', NULL, NULL, '2019-08-22 13:46:20', 2, '11111111111111111111111111111111111111111111111111111111111111111111111', 3, 1);
INSERT INTO `ml_customer` VALUES ('4404459f2972434d804835daf5e0c60a', '刘超', '撒旦法', '11111111', 'dh', 'lhf522626@163.com', 16, '2019-08-27 18:05:12', '2019-08-16 09:08:05', 0, 0, 0, 2, '111', 7, NULL, '2019-08-28 09:10:42', 1, '1111111111', 7, NULL);
INSERT INTO `ml_customer` VALUES ('4f314673392e4602b15fe12cfc784037', 'qwrewqr', '阿斯顿发', NULL, 'wqerqw', '二', 16, '2019-08-27 18:03:55', '2019-08-22 13:48:31', 2, 0, 0, 3, 'qwrqw', NULL, NULL, '2019-08-28 17:27:04', 2, '111111111111', 2, NULL);
INSERT INTO `ml_customer` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', '213412', '21314', '213421', '2134', '21341', 16, '2019-08-27 18:04:04', '2019-08-22 09:57:27', 2, 0, 0, 3, '1234', 2, NULL, '2019-08-27 18:06:55', 2, '111111111', 7, 1);
INSERT INTO `ml_customer` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', '大伙子', '122', '111', '1111', 4, '2019-08-27 18:05:12', '2019-08-16 09:04:55', 2, 0, 0, 3, 'qyxq', 4, NULL, '2019-08-28 09:08:37', 2, '111111111111', 17, 1);
INSERT INTO `ml_customer` VALUES ('76e9f76f01004619a4e3f0ad1cd7d267', '111111', '111111111111111', '11111', 'dddddddd', 'ddd', 16, '2019-08-27 18:03:55', '2019-08-22 13:47:50', 0, 0, 0, 0, 'ddd', NULL, NULL, NULL, -1, NULL, 0, NULL);
INSERT INTO `ml_customer` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', '广州市白云区白云湖街环滘一横路2号前栋303房', '149030544', '020-36398179', '156666666', 16, '2019-08-27 18:03:55', '2019-08-26 08:46:21', 2, 0, 0, 2, '尚渔餐饮企业管理服务有限公司', NULL, NULL, '2019-08-28 17:51:48', 1, '1111111111', 14, NULL);
INSERT INTO `ml_customer` VALUES ('8a43d536a06f4e4da264ef5fcaac58da', 'qwrewqr', 'qwereqwe', NULL, 'qwerqw', 'qwerqwe', 16, '2019-08-27 18:04:04', '2019-08-22 09:51:51', 0, 0, 0, 0, 'wqerqwre', NULL, 'qwer', NULL, -1, NULL, 0, 5);
INSERT INTO `ml_customer` VALUES ('9152a8f25da24a63bd22cfa9003e8ba9', 'dddddddd', 'dddddddddd', NULL, 'aaaa', 'ddda', 16, '2019-08-27 18:03:55', '2019-08-22 13:48:01', 0, 0, 0, 0, 'aaa', NULL, NULL, NULL, -1, NULL, 0, NULL);
INSERT INTO `ml_customer` VALUES ('935223edb0b649f199fd17d05c6fd55c', '1234124', '撒啊啊啊啊', NULL, '撒旦法', 'sadfdas', 16, '2019-08-27 18:03:55', '2019-08-22 13:48:18', 2, 0, 0, 2, 'asdf', NULL, NULL, '2019-08-28 17:19:52', 2, '111111111', 1, NULL);
INSERT INTO `ml_customer` VALUES ('9aeba9819e6d4018baddb2d03f7c1441', '111111111', '1111111111', '111111', '111111', '11111', 16, '2019-08-27 18:04:04', '2019-08-22 13:46:49', 2, 0, 0, 3, '1111111', NULL, NULL, '2019-08-27 17:46:18', 2, '111111111', 3, NULL);
INSERT INTO `ml_customer` VALUES ('bc7c262d151740038433df46ed67672a', '42314', '2314', '21341', '21341', '1234', 16, '2019-08-27 18:04:04', '2019-08-22 09:53:58', 0, 0, 0, 0, '1234', NULL, NULL, NULL, -1, NULL, 0, 1);
INSERT INTO `ml_customer` VALUES ('bef77bb05dc2498ba5825d6a6e5b17ae', '1234124', '21342134', '432141', '21341234', '12341243', 16, '2019-08-27 18:04:04', '2019-08-22 09:53:23', 0, 0, 0, 0, '123412', NULL, NULL, NULL, -1, NULL, 0, 8);
INSERT INTO `ml_customer` VALUES ('bfc2f234ef21448cb8e99548302db3e3', '111111111', '11111111', '1111111111', '111111111', '1111111111', 16, '2019-08-27 18:03:55', '2019-08-22 13:47:02', 0, 0, 0, 0, '1111111111', NULL, NULL, NULL, -1, NULL, 0, NULL);
INSERT INTO `ml_customer` VALUES ('c0ec3aaa08254ebb93de55cf47187de1', '1232132114', '234123412', '123421341', '213421421', '21341234', 16, '2019-08-27 18:04:04', '2019-08-22 09:53:12', 0, 0, 0, 3, '12341234', NULL, '1234', '2019-08-27 15:07:28', -1, '111', 1, 10);
INSERT INTO `ml_customer` VALUES ('c7f2c9afaa944fa881fb4fef2b599c94', '234234', '234234', '234', '23423', '4234', 16, '2019-08-27 18:03:55', '2019-08-27 15:11:16', 1, 0, 0, 3, '24', NULL, NULL, '2019-08-28 17:27:37', 3, '111111111111111', 4, NULL);
INSERT INTO `ml_customer` VALUES ('d9381ae4845c4065b5c1b84602a96419', '王企鹅reqw', '完全二', NULL, '玩儿', '玩儿', 16, '2019-08-27 18:03:55', '2019-08-22 13:48:41', 2, 0, 0, 3, '韦尔奇', NULL, NULL, '2019-08-28 17:19:32', 2, '11111111', 2, NULL);
INSERT INTO `ml_customer` VALUES ('d9f8acac75da45a29230c6f87d6f6a80', 'ewdew', 'werwe', 'r', 'rqw', 'ewq', 16, '2019-08-27 18:05:12', '2019-08-22 09:50:53', 0, 0, 0, 1, 'rqwe', NULL, 'qwer', '2019-08-27 18:08:04', -1, '1111111111', 2, 4);
INSERT INTO `ml_customer` VALUES ('dba21dae0323400f80d2c937f8e22711', '1234124', '21342134', '432141', '21341234', '12341243', 16, '2019-08-27 18:04:04', '2019-08-22 09:53:31', 0, 0, 0, 0, '123412', NULL, NULL, NULL, -1, NULL, 0, 8);
INSERT INTO `ml_customer` VALUES ('eb84ed2258334aba8adf66004026d278', 'flskdjfhkjds', 'fldsahf;lk', '1321321', '54113213', '1131', 16, '2019-08-27 18:03:55', '2019-08-26 08:48:44', 2, 0, 0, 3, '542444', NULL, NULL, '2019-08-28 17:23:03', 2, '111111111', 4, NULL);
INSERT INTO `ml_customer` VALUES ('f078f8449fdb4a549cdfa70947292808', '11111111', '1111111111', '111111111', '2222221111111', '1111111', 16, '2019-08-27 18:03:55', '2019-08-22 13:47:32', 0, 0, 0, 0, '111111', NULL, NULL, NULL, -1, NULL, 0, NULL);
INSERT INTO `ml_customer` VALUES ('f91df6d3c30e47d2a7d92e1c6c2c11fc', '刘超', '撒旦法', NULL, 'dh', 'lhf522626@163.com', -1, '2019-08-19 15:04:23', '2019-08-16 09:08:39', 0, 0, 0, 0, '111', NULL, NULL, NULL, -1, NULL, 0, NULL);

-- ----------------------------
-- Table structure for ml_customer_20180701
-- ----------------------------
DROP TABLE IF EXISTS `ml_customer_20180701`;
CREATE TABLE `ml_customer_20180701`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `belongs` int(2) NULL DEFAULT 0 COMMENT '-1.公共客户 0.未分配 ',
  `lasttime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分配的时间',
  `time` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(2) NULL DEFAULT 0 COMMENT '合作状态 0.联系中1.合作失败2.合作成功',
  `look` int(2) NULL DEFAULT 0 COMMENT '新客户分配给员工后员工确认否   0未确认  1确认',
  `restatus` int(1) NULL DEFAULT 0 COMMENT '是否回访 0否  1是',
  `caim` int(1) NULL DEFAULT 0 COMMENT '客户意向1-3 针创无',
  `cfrom` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户来源',
  `type` int(11) NULL DEFAULT NULL COMMENT '1~7 A+~F',
  `keyword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `etime` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近沟通时间',
  `rate` int(2) NULL DEFAULT NULL COMMENT '进度 0未联系上 1联系中 2来访签订 3来访未签订',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '沟通内容',
  `count` int(5) NULL DEFAULT NULL COMMENT '沟通次数',
  `project` int(5) NULL DEFAULT NULL COMMENT '询问的项目'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ml_customer_20181225
-- ----------------------------
DROP TABLE IF EXISTS `ml_customer_20181225`;
CREATE TABLE `ml_customer_20181225`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `belongs` int(2) NULL DEFAULT 0 COMMENT '-1.公共客户 0.未分配 ',
  `lasttime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分配的时间',
  `time` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(2) NULL DEFAULT 0 COMMENT '合作状态 0.联系中1.合作失败2.合作成功',
  `look` int(2) NULL DEFAULT 0 COMMENT '新客户分配给员工后员工确认否   0未确认  1确认',
  `restatus` int(1) NULL DEFAULT 0 COMMENT '是否回访 0否  1是',
  `caim` int(1) NULL DEFAULT 0 COMMENT '客户意向1-3 针创无',
  `cfrom` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户来源',
  `type` int(11) NULL DEFAULT NULL COMMENT '1~7 A+~F',
  `keyword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `etime` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近沟通时间',
  `rate` int(2) NULL DEFAULT -1 COMMENT '进度 0未联系上 1联系中 2来访签订 3来访未签订',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '沟通内容',
  `count` int(5) NULL DEFAULT 0 COMMENT '沟通次数',
  `project` int(5) NULL DEFAULT NULL COMMENT '询问的项目'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ml_customer_20190226
-- ----------------------------
DROP TABLE IF EXISTS `ml_customer_20190226`;
CREATE TABLE `ml_customer_20190226`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `belongs` int(2) NULL DEFAULT 0 COMMENT '-1.公共客户 0.未分配 ',
  `lasttime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分配的时间',
  `time` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(2) NULL DEFAULT 0 COMMENT '合作状态 0.联系中1.合作失败2.合作成功',
  `look` int(2) NULL DEFAULT 0 COMMENT '新客户分配给员工后员工确认否   0未确认  1确认',
  `restatus` int(1) NULL DEFAULT 0 COMMENT '是否回访 0否  1是',
  `caim` int(1) NULL DEFAULT 0 COMMENT '客户意向1-3 针创无',
  `cfrom` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户来源',
  `type` int(11) NULL DEFAULT NULL COMMENT '1~7 A+~F',
  `keyword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `etime` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近沟通时间',
  `rate` int(2) NULL DEFAULT -1 COMMENT '进度 0未联系上 1联系中 2来访签订 3来访未签订',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '沟通内容',
  `count` int(5) NULL DEFAULT 0 COMMENT '沟通次数',
  `project` int(5) NULL DEFAULT NULL COMMENT '询问的项目'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ml_customer_log
-- ----------------------------
DROP TABLE IF EXISTS `ml_customer_log`;
CREATE TABLE `ml_customer_log`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `project` int(100) NULL DEFAULT NULL COMMENT '询问的项目',
  `belongsname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理者姓名',
  `rate` int(2) NULL DEFAULT 0 COMMENT '进度 0未联系上  1联系中 2来访签订 3来访未签订',
  `question` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户纠结问题',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '沟通内容',
  `money` int(7) NULL DEFAULT 0 COMMENT '所报费用',
  `talktime` double NOT NULL DEFAULT 0 COMMENT '电话时长 单位 分',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间 精确到时分秒',
  `keyword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  PRIMARY KEY (`id`, `time`) USING BTREE,
  FULLTEXT INDEX `id`(`id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ml_customer_log
-- ----------------------------
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', NULL, NULL, NULL, 2, '初始添加客户', 'dsfhdsakhjvzd', 0, 0, '2019-08-16 09:04:56', NULL);
INSERT INTO `ml_customer_log` VALUES ('3f0454a97b69444097e257b72b20d025', NULL, NULL, NULL, 0, '初始添加客户', 'dsfhdsakhjvzd', 0, 0, '2019-08-16 09:05:16', NULL);
INSERT INTO `ml_customer_log` VALUES ('4404459f2972434d804835daf5e0c60a', NULL, NULL, NULL, 1, '初始添加客户', '为人而无法', 0, 0, '2019-08-16 09:08:05', NULL);
INSERT INTO `ml_customer_log` VALUES ('f91df6d3c30e47d2a7d92e1c6c2c11fc', NULL, NULL, NULL, 0, '初始添加客户', '为人而无法', 0, 0, '2019-08-16 09:08:39', NULL);
INSERT INTO `ml_customer_log` VALUES ('1309ab32d8da4a38addf28cf94f62792', NULL, 4, NULL, 0, '初始添加客户', '第三方', 0, 0, '2019-08-19 17:25:05', '111');
INSERT INTO `ml_customer_log` VALUES ('d9f8acac75da45a29230c6f87d6f6a80', NULL, 4, NULL, 0, '初始添加客户', 'qwerqw', 0, 0, '2019-08-22 09:50:53', 'qwer');
INSERT INTO `ml_customer_log` VALUES ('35b99a63e45c4c0e9301b97c7e6cbe98', NULL, 5, NULL, 0, '初始添加客户', 'qwerqw', 0, 0, '2019-08-22 09:51:17', 'qwerwq');
INSERT INTO `ml_customer_log` VALUES ('197d31a161c348e8a095979726c8a220', NULL, 5, NULL, 0, '初始添加客户', 'qwerqw', 0, 0, '2019-08-22 09:51:28', 'qwerwq');
INSERT INTO `ml_customer_log` VALUES ('8a43d536a06f4e4da264ef5fcaac58da', NULL, 5, NULL, 0, '初始添加客户', 'qweqreqw', 0, 0, '2019-08-22 09:51:51', 'qwer');
INSERT INTO `ml_customer_log` VALUES ('27fc00ee80ce42819a320834bcc2532b', NULL, 5, NULL, 2, '初始添加客户', 'qweqreqw', 0, 0, '2019-08-22 09:51:59', 'qwer');
INSERT INTO `ml_customer_log` VALUES ('c0ec3aaa08254ebb93de55cf47187de1', NULL, 10, NULL, 0, '初始添加客户', NULL, 0, 0, '2019-08-22 09:53:12', '1234');
INSERT INTO `ml_customer_log` VALUES ('bef77bb05dc2498ba5825d6a6e5b17ae', NULL, 8, NULL, 0, '初始添加客户', '12343214', 0, 0, '2019-08-22 09:53:23', NULL);
INSERT INTO `ml_customer_log` VALUES ('dba21dae0323400f80d2c937f8e22711', NULL, 8, NULL, 0, '初始添加客户', '12343214', 0, 0, '2019-08-22 09:53:31', NULL);
INSERT INTO `ml_customer_log` VALUES ('bc7c262d151740038433df46ed67672a', NULL, 1, NULL, 0, '初始添加客户', '12341', 0, 0, '2019-08-22 09:53:58', NULL);
INSERT INTO `ml_customer_log` VALUES ('4298a37030884086bb167cdc4c3f0d7b', NULL, 1, NULL, 2, '初始添加客户', '1243', 0, 0, '2019-08-22 09:55:09', NULL);
INSERT INTO `ml_customer_log` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', NULL, 5, NULL, 2, '初始添加客户', '21341', 0, 0, '2019-08-22 09:57:27', NULL);
INSERT INTO `ml_customer_log` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', '213412', 5, 'lc', 2, '234234', '341234', 23412, 2134, '2019-08-22 10:00:28', NULL);
INSERT INTO `ml_customer_log` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', '213412', 5, 'lc', 2, '234234', '341234', 23412, 2134, '2019-08-22 10:00:31', NULL);
INSERT INTO `ml_customer_log` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', '213412', 5, 'lc', 2, '234234', '341234', 23412, 2134, '2019-08-22 10:00:32', NULL);
INSERT INTO `ml_customer_log` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', '213412', 1, 'lc', 2, '2134312', '432143214', 0, 0, '2019-08-22 10:00:57', NULL);
INSERT INTO `ml_customer_log` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', '213412', 1, 'lc', 2, '213412', '432143214321', 0, 0, '2019-08-22 10:01:28', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', 1, 'lc', 2, '3werwer', 'werwq', 2341, 23423, '2019-08-22 10:01:53', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', 1, 'lc', 2, 'w4', 'wqerewq', 0, 0, '2019-08-22 10:03:06', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', 1, 'lc', 2, '2134', '3214', 0, 0, '2019-08-22 10:04:47', NULL);
INSERT INTO `ml_customer_log` VALUES ('4298a37030884086bb167cdc4c3f0d7b', '234214', NULL, '刘会发', 2, '1111111111', '111111111111111111111', 23412, 0, '2019-08-22 13:04:19', NULL);
INSERT INTO `ml_customer_log` VALUES ('4404459f2972434d804835daf5e0c60a', '刘超', NULL, '刘会发', 1, '111111111111111', '1111111111111111', 111, 0, '2019-08-22 13:05:20', NULL);
INSERT INTO `ml_customer_log` VALUES ('4298a37030884086bb167cdc4c3f0d7b', '234214', NULL, '刘会发', 2, '222', '222', 222, 0, '2019-08-22 13:11:02', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, '刘会发', 2, '23333', '333', 33, 0, '2019-08-22 13:11:17', NULL);
INSERT INTO `ml_customer_log` VALUES ('d9f8acac75da45a29230c6f87d6f6a80', 'ewdew', NULL, '刘会发', 0, 'rr', 'rrr', 11, 0, '2019-08-22 13:14:00', NULL);
INSERT INTO `ml_customer_log` VALUES ('1309ab32d8da4a38addf28cf94f62792', 'cmy', NULL, '刘会发', 0, '11111111', '1111', 1111, 0, '2019-08-22 13:20:29', NULL);
INSERT INTO `ml_customer_log` VALUES ('4404459f2972434d804835daf5e0c60a', '刘超', NULL, '刘会发', 1, '111', '11111', 111, 0, '2019-08-22 13:20:41', NULL);
INSERT INTO `ml_customer_log` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', '213412', NULL, '刘会发', 2, '1111', '111', 111, 0, '2019-08-22 13:21:03', NULL);
INSERT INTO `ml_customer_log` VALUES ('4298a37030884086bb167cdc4c3f0d7b', '234214', NULL, '刘会发', 2, '11111111111', '11111111111111111111111111111111111111111111111111111111111111111111111', 111111, 0, '2019-08-22 13:46:20', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, '刘会发', 2, '111111111111111', '1111111111111111111111111', 111, 0, '2019-08-22 13:46:32', NULL);
INSERT INTO `ml_customer_log` VALUES ('9aeba9819e6d4018baddb2d03f7c1441', NULL, NULL, NULL, 2, '初始添加客户', '1111111111', 0, 0, '2019-08-22 13:46:49', NULL);
INSERT INTO `ml_customer_log` VALUES ('bfc2f234ef21448cb8e99548302db3e3', NULL, NULL, NULL, 0, '初始添加客户', '111111111111', 0, 0, '2019-08-22 13:47:02', NULL);
INSERT INTO `ml_customer_log` VALUES ('f078f8449fdb4a549cdfa70947292808', NULL, NULL, NULL, 0, '初始添加客户', '2222222', 0, 0, '2019-08-22 13:47:32', NULL);
INSERT INTO `ml_customer_log` VALUES ('76e9f76f01004619a4e3f0ad1cd7d267', NULL, NULL, NULL, 0, '初始添加客户', '11', 0, 0, '2019-08-22 13:47:50', NULL);
INSERT INTO `ml_customer_log` VALUES ('9152a8f25da24a63bd22cfa9003e8ba9', NULL, NULL, NULL, 0, '初始添加客户', 'aaaaa', 0, 0, '2019-08-22 13:48:01', NULL);
INSERT INTO `ml_customer_log` VALUES ('935223edb0b649f199fd17d05c6fd55c', NULL, NULL, NULL, 2, '初始添加客户', 'sad阿斯顿发', 0, 0, '2019-08-22 13:48:18', NULL);
INSERT INTO `ml_customer_log` VALUES ('4f314673392e4602b15fe12cfc784037', NULL, NULL, NULL, 2, '初始添加客户', 'weq玩儿', 0, 0, '2019-08-22 13:48:31', NULL);
INSERT INTO `ml_customer_log` VALUES ('d9381ae4845c4065b5c1b84602a96419', NULL, NULL, NULL, 2, '初始添加客户', '请问', 0, 0, '2019-08-22 13:48:41', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, '刘会发', 2, '121 饿1e', '21饿2', 1111, 0, '2019-08-22 13:49:13', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, '刘会发', 2, '12 额1e', '12额2e', 122, 0, '2019-08-22 13:49:24', NULL);
INSERT INTO `ml_customer_log` VALUES ('27fc00ee80ce42819a320834bcc2532b', 'qwrewqr', NULL, '刘会发', 2, '12额2', '1221e', 1221, 0, '2019-08-22 13:49:42', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', NULL, NULL, NULL, 1, '初始添加客户', '132132', 0, 0, '2019-08-26 08:46:21', NULL);
INSERT INTO `ml_customer_log` VALUES ('eb84ed2258334aba8adf66004026d278', NULL, NULL, NULL, 2, '初始添加客户', 'klnfksdbfb', 0, 0, '2019-08-26 08:48:44', NULL);
INSERT INTO `ml_customer_log` VALUES ('eb84ed2258334aba8adf66004026d278', 'flskdjfhkjds', NULL, '刘会发', 2, 'qwww', 'wwww', 111, 0, '2019-08-26 10:08:01', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, '刘会发', 1, '111111111', '11111111111', 111, 0, '2019-08-26 17:24:23', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '11111111', '111111111', 0, 0, '2019-08-28 17:49:19', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, '刘会发', 1, '2', '3', 1, 0, '2019-08-26 17:24:57', NULL);
INSERT INTO `ml_customer_log` VALUES ('c0ec3aaa08254ebb93de55cf47187de1', '1232132114', NULL, '刘会发', 0, '11', '111', 1111, 0, '2019-08-27 15:07:28', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, '刘会发', 2, '111', '111', 111, 0, '2019-08-27 15:09:45', NULL);
INSERT INTO `ml_customer_log` VALUES ('c7f2c9afaa944fa881fb4fef2b599c94', NULL, NULL, NULL, 3, '初始添加客户', '242424234', 0, 0, '2019-08-27 15:11:17', NULL);
INSERT INTO `ml_customer_log` VALUES ('9aeba9819e6d4018baddb2d03f7c1441', '111111111', NULL, '刘会发', 2, 'sdfdfsdf', 'sdfsdfsdfsdfsdfsdfsdf', 2442, 0, '2019-08-27 15:11:38', NULL);
INSERT INTO `ml_customer_log` VALUES ('eb84ed2258334aba8adf66004026d278', 'flskdjfhkjds', NULL, '刘会发', 2, '1111111', '11111111', 111, 0, '2019-08-27 15:56:42', NULL);
INSERT INTO `ml_customer_log` VALUES ('eb84ed2258334aba8adf66004026d278', 'flskdjfhkjds', NULL, '刘会发', 2, '111111', '111111111', 0, 0, '2019-08-27 15:56:50', NULL);
INSERT INTO `ml_customer_log` VALUES ('9aeba9819e6d4018baddb2d03f7c1441', '111111111', NULL, '刘会发', 2, '1111', '111', 111, 0, '2019-08-27 15:58:26', NULL);
INSERT INTO `ml_customer_log` VALUES ('c7f2c9afaa944fa881fb4fef2b599c94', '234234', NULL, '刘会发', 3, '1111111', '1111111111', 11111111, 0, '2019-08-27 16:01:05', NULL);
INSERT INTO `ml_customer_log` VALUES ('d9381ae4845c4065b5c1b84602a96419', '王企鹅reqw', NULL, '刘会发', 2, '111111', '1111111111', 111, 0, '2019-08-27 16:03:04', NULL);
INSERT INTO `ml_customer_log` VALUES ('9aeba9819e6d4018baddb2d03f7c1441', '111111111', NULL, '刘会发', 2, '111111', '111111111', 0, 0, '2019-08-27 17:46:18', NULL);
INSERT INTO `ml_customer_log` VALUES ('5eaa1cde68c043b7a1364eea1838c0b2', '213412', NULL, 'D', 2, '11111', '111111111', 111111111, 0, '2019-08-27 18:06:55', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '11111111', '11111111111', 111111111, 0, '2019-08-27 18:07:53', NULL);
INSERT INTO `ml_customer_log` VALUES ('d9f8acac75da45a29230c6f87d6f6a80', 'ewdew', NULL, 'D', 0, '1111111111', '1111111111', 0, 0, '2019-08-27 18:08:04', NULL);
INSERT INTO `ml_customer_log` VALUES ('27fc00ee80ce42819a320834bcc2532b', 'qwrewqr', NULL, 'D', 2, '111111111', '1111111111', 0, 0, '2019-08-27 18:08:24', NULL);
INSERT INTO `ml_customer_log` VALUES ('4404459f2972434d804835daf5e0c60a', '刘超', NULL, 'D', 1, '11111111', '111111111', 0, 0, '2019-08-27 18:09:42', NULL);
INSERT INTO `ml_customer_log` VALUES ('4404459f2972434d804835daf5e0c60a', '刘超', NULL, 'D', 1, '1111111111', '11111111111111111', 0, 0, '2019-08-27 18:11:40', NULL);
INSERT INTO `ml_customer_log` VALUES ('35b99a63e45c4c0e9301b97c7e6cbe98', 'wqerqwer', NULL, 'D', 0, '1111111111111', '11111111111111111111111', 0, 0, '2019-08-27 18:16:06', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, 'D', 2, '11111111', '11111111111', 0, 0, '2019-08-27 18:16:48', NULL);
INSERT INTO `ml_customer_log` VALUES ('4404459f2972434d804835daf5e0c60a', '刘超', NULL, 'D', 1, '1111111111', '111111111111', 0, 0, '2019-08-27 18:19:20', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, 'D', 2, '111111111111', '111111111111111', 0, 0, '2019-08-27 18:23:23', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, 'D', 2, '1111111111', '1111111111111', 111111111, 0, '2019-08-28 08:39:10', NULL);
INSERT INTO `ml_customer_log` VALUES ('4404459f2972434d804835daf5e0c60a', '刘超', NULL, 'D', 1, '11111111', '111111111111111', 11111111, 0, '2019-08-28 08:40:24', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, 'D', 2, '1111111111', '1111111111111', 0, 0, '2019-08-28 08:41:25', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, 'D', 2, '22222222222222', '222222222222222222222222222222', 0, 0, '2019-08-28 08:45:18', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, 'D', 2, '1111111111111', '11111111111111', 1111111, 0, '2019-08-28 08:45:53', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, 'D', 2, '1111111111', '11111111111', 0, 0, '2019-08-28 08:47:46', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, 'D', 2, '111111111', '11111111111', 0, 0, '2019-08-28 08:58:44', NULL);
INSERT INTO `ml_customer_log` VALUES ('6777f5fb29ca458aa0026e7b3dbb2f6c', '小伙子', NULL, '刘会发', 2, '1111111111', '111111111111', 0, 0, '2019-08-28 09:08:37', NULL);
INSERT INTO `ml_customer_log` VALUES ('4404459f2972434d804835daf5e0c60a', '刘超', NULL, 'D', 1, '111111111', '1111111111', 0, 0, '2019-08-28 09:10:42', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '111', '1111', 0, 0, '2019-08-28 16:12:23', NULL);
INSERT INTO `ml_customer_log` VALUES ('c7f2c9afaa944fa881fb4fef2b599c94', '234234', NULL, 'D', 3, '111111', '11111111', 0, 0, '2019-08-28 17:17:54', NULL);
INSERT INTO `ml_customer_log` VALUES ('d9381ae4845c4065b5c1b84602a96419', '王企鹅reqw', NULL, 'D', 2, '11111111', '11111111', 0, 0, '2019-08-28 17:19:32', NULL);
INSERT INTO `ml_customer_log` VALUES ('935223edb0b649f199fd17d05c6fd55c', '1234124', NULL, 'D', 2, '11111111', '111111111', 0, 0, '2019-08-28 17:19:52', NULL);
INSERT INTO `ml_customer_log` VALUES ('4f314673392e4602b15fe12cfc784037', 'qwrewqr', NULL, 'D', 2, '1111111111', '111111111', 1111111, 0, '2019-08-28 17:21:36', NULL);
INSERT INTO `ml_customer_log` VALUES ('c7f2c9afaa944fa881fb4fef2b599c94', '234234', NULL, 'D', 3, '111111111', '1111111111', 0, 0, '2019-08-28 17:22:35', NULL);
INSERT INTO `ml_customer_log` VALUES ('eb84ed2258334aba8adf66004026d278', 'flskdjfhkjds', NULL, 'D', 2, '1111111111', '111111111', 0, 0, '2019-08-28 17:23:03', NULL);
INSERT INTO `ml_customer_log` VALUES ('4f314673392e4602b15fe12cfc784037', 'qwrewqr', NULL, 'D', 2, '111111111111111', '111111111111', 0, 0, '2019-08-28 17:27:04', NULL);
INSERT INTO `ml_customer_log` VALUES ('c7f2c9afaa944fa881fb4fef2b599c94', '234234', NULL, 'D', 3, '11111111111', '111111111111111', 0, 0, '2019-08-28 17:27:37', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '1111111111', '1111111111111', 0, 0, '2019-08-28 17:27:49', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, 'fffffffff', 'fffffffffffffff', 0, 0, '2019-08-28 17:29:20', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '1111111', '11111111', 0, 0, '2019-08-28 17:48:35', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '111111111', '111111111111', 0, 0, '2019-08-28 17:48:58', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '11111111', '11111111', 0, 0, '2019-08-28 17:49:36', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '111111111', '1111111111', 0, 0, '2019-08-28 17:49:47', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '11111111', '11111111111', 11111, 0, '2019-08-28 17:51:24', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '111111111', '111111111111', 0, 0, '2019-08-28 17:51:37', NULL);
INSERT INTO `ml_customer_log` VALUES ('88e8b3e2409c43b9bf1d332e34102f1e', '小刘超', NULL, 'D', 1, '1111111', '1111111111', 0, 0, '2019-08-28 17:51:48', NULL);

-- ----------------------------
-- Table structure for ml_customer_log_tmp
-- ----------------------------
DROP TABLE IF EXISTS `ml_customer_log_tmp`;
CREATE TABLE `ml_customer_log_tmp`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `project` int(100) NULL DEFAULT NULL COMMENT '询问的项目',
  `belongsname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理者姓名',
  `rate` int(2) NULL DEFAULT 0 COMMENT '进度 0未联系上  1联系中 2来访签订 3来访未签订',
  `question` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户纠结问题',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '沟通内容',
  `money` int(7) NULL DEFAULT 0 COMMENT '所报费用',
  `talktime` double NOT NULL DEFAULT 0 COMMENT '电话时长 单位 分',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间 精确到时分秒',
  PRIMARY KEY (`id`, `time`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ml_customer_tmp
-- ----------------------------
DROP TABLE IF EXISTS `ml_customer_tmp`;
CREATE TABLE `ml_customer_tmp`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `belongs` int(2) NULL DEFAULT 0 COMMENT '-1.公共客户 0.未分配 ',
  `lasttime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分配的时间',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(2) NULL DEFAULT 0 COMMENT '合作状态 0.联系中1.合作失败2.合作成功',
  `look` int(2) NULL DEFAULT 0 COMMENT '新客户分配给员工后员工确认否   0未确认  1确认',
  `restatus` int(1) NULL DEFAULT 0 COMMENT '是否回访 0否  1是',
  `caim` int(1) NULL DEFAULT 0 COMMENT '客户意向1-3 针创无',
  `cfrom` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户来源',
  `type` int(11) NULL DEFAULT NULL COMMENT '1~7 A+~F',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ml_user
-- ----------------------------
DROP TABLE IF EXISTS `ml_user`;
CREATE TABLE `ml_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` int(1) NULL DEFAULT NULL COMMENT '0.普通1.管理员',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `dept` int(5) NOT NULL DEFAULT 0 COMMENT '部门标识0.无',
  `sign` int(5) NOT NULL DEFAULT 0 COMMENT '员工表示。 0.默认',
  `leader` int(5) NULL DEFAULT 0 COMMENT '直属领导',
  `level` int(2) NULL DEFAULT 0 COMMENT '等级',
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ml_user
-- ----------------------------
INSERT INTO `ml_user` VALUES ('f36488c8df0a4d32874cc3dda0f3a72a', '1027', '1027', '3cd28ac52f415a26487d7a866139146c', 1, '2019-08-13 15:26:23', 1, 9, -1, 0);
INSERT INTO `ml_user` VALUES ('e27b9b29687c45ad8734a3e8e0cf4df1', '5159', '5159', '19d19f93a4200dca6148155b8ccc0afd', 1, '2019-08-13 15:23:35', 1, 8, -1, 0);
INSERT INTO `ml_user` VALUES ('c146209aad7f41489402a63de5e10d5d', '刘会发', 'liuhuifa', 'e10adc3949ba59abbe56e057f20f883e', 1, '2019-08-12 15:13:51', 1, 4, 1, 0);
INSERT INTO `ml_user` VALUES ('56bc2974199c454ea18c3b9656876b14', 'lc', '401', 'e10adc3949ba59abbe56e057f20f883e', 0, '2019-08-16 09:18:10', 1, 11, -1, 0);
INSERT INTO `ml_user` VALUES ('001f38efe9694e1897399740659fb18e', 'gou', '414', 'e10adc3949ba59abbe56e057f20f883e', 1, '2019-08-13 15:50:11', 1, 10, -1, 0);
INSERT INTO `ml_user` VALUES ('3f0e26f9a7ba4873a39da7171038718e', '刘会发', 'lhf', 'e10adc3949ba59abbe56e057f20f883e', 0, '2019-08-19 13:03:12', 1, 12, -1, 0);
INSERT INTO `ml_user` VALUES ('19f3b4b3a7a94143b520f76c008e6a0c', 'B', 'b', 'e10adc3949ba59abbe56e057f20f883e', 2, '2019-08-19 13:15:40', 9, 14, -1, 0);
INSERT INTO `ml_user` VALUES ('3e8d4e7b5d1742ec929bdb3a48334dfa', 'C', 'c', 'e10adc3949ba59abbe56e057f20f883e', 3, '2019-08-19 13:16:01', 19, 15, -1, 0);
INSERT INTO `ml_user` VALUES ('adb0b24b23c34eeaa633a9e8d11715e4', 'D', 'd', 'e10adc3949ba59abbe56e057f20f883e', 0, '2019-08-19 13:16:23', 10, 16, -1, 0);
INSERT INTO `ml_user` VALUES ('564c846214f44a51a6a2d33d7bee5ded', 'Q', 'q', 'e10adc3949ba59abbe56e057f20f883e', 3, '2019-08-19 13:49:34', 1, 17, -1, 0);

-- ----------------------------
-- Table structure for ml_user_tmp
-- ----------------------------
DROP TABLE IF EXISTS `ml_user_tmp`;
CREATE TABLE `ml_user_tmp`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` int(1) NULL DEFAULT NULL COMMENT '0.普通1.管理员',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `dept` int(5) NOT NULL DEFAULT 0 COMMENT '部门标识0.无',
  `sign` int(5) NOT NULL DEFAULT 0 COMMENT '员工表示。 0.默认',
  `leader` int(5) NULL DEFAULT 0 COMMENT '直属领导',
  `level` int(2) NULL DEFAULT 0 COMMENT '等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ml_visit
-- ----------------------------
DROP TABLE IF EXISTS `ml_visit`;
CREATE TABLE `ml_visit`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '回访详情',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态 1不配合 2联系失败 3成功',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录生成的时间',
  PRIMARY KEY (`id`, `time`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ml_visit
-- ----------------------------
INSERT INTO `ml_visit` VALUES ('4298a37030884086bb167cdc4c3f0d7b', '234214', '1234', '111', 2, '2019-08-22 13:37:56');
INSERT INTO `ml_visit` VALUES ('4298a37030884086bb167cdc4c3f0d7b', '234214', '1234', '111', 1, '2019-08-22 13:43:01');

SET FOREIGN_KEY_CHECKS = 1;
