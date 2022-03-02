/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : sys_drug

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 02/03/2022 09:03:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_object
-- ----------------------------
DROP TABLE IF EXISTS `sys_object`;
CREATE TABLE `sys_object` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `kind_id` int NOT NULL COMMENT '类别id',
  `kind_nickname` varchar(255) DEFAULT NULL COMMENT '别称',
  `kind_product_date` datetime NOT NULL COMMENT '生产日期',
  `kind_validate_period` datetime NOT NULL COMMENT '有效期',
  `reminder_date` datetime DEFAULT NULL COMMENT '提醒日期',
  `img_path` varchar(500) NOT NULL COMMENT '图片地址',
  `stored_location` varchar(500) DEFAULT NULL COMMENT '物品存放位置',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `operator` int NOT NULL COMMENT '操作者用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_object
-- ----------------------------
BEGIN;
INSERT INTO `sys_object` VALUES (1, 1, NULL, '2022-03-01 08:00:00', '2022-03-01 08:00:00', '2022-03-01 08:00:00', 'object_null', '书房', '2022-03-01 00:00:00', '2022-03-01 00:00:00', 1);
INSERT INTO `sys_object` VALUES (2, 1, NULL, '2022-03-01 08:00:00', '2022-03-01 08:00:00', '2022-03-01 08:00:00', 'object_1', '书房', '2022-03-01 00:00:00', '2022-03-01 00:00:00', 1);
INSERT INTO `sys_object` VALUES (3, 1, NULL, '2022-03-01 08:00:00', '2022-03-01 08:00:00', '2022-03-01 08:00:00', 'object_1.jpg', '书房', '2022-03-01 00:00:00', '2022-03-01 00:00:00', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_object_kind
-- ----------------------------
DROP TABLE IF EXISTS `sys_object_kind`;
CREATE TABLE `sys_object_kind` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `kind_id` int DEFAULT NULL COMMENT '种类id',
  `kind_name` varchar(255) DEFAULT NULL COMMENT '种类名称',
  `operateor` int DEFAULT NULL COMMENT '添加者用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_object_kind
-- ----------------------------
BEGIN;
INSERT INTO `sys_object_kind` VALUES (1, 0, '食物', 1);
INSERT INTO `sys_object_kind` VALUES (2, 1, '药品', 1);
INSERT INTO `sys_object_kind` VALUES (3, 2, '其他', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `user_type` char(1) NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  `create_by` bigint DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '沈自在。', '$2a$10$Q3gCHEtUklNJ1770tdovnORGJB9InA4MpqhKU1xXzdDejU3919ZU.', '0', '1315077391@qq.com', '13947856739', '0', NULL, '1', NULL, '2022-02-25 22:07:44', NULL, '2022-02-25 22:07:58', 0);
INSERT INTO `sys_user` VALUES (4, '1315077391@qq.com', '萌新:1315077391@qq.com', '$2a$10$FrUefNmD9QTFE8G9Ac.DBuzG0kg8piNvNcmeGPQs7EsW.nTgdlFcy', '0', NULL, NULL, NULL, NULL, '1', NULL, '2022-02-27 19:21:09', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (5, '13614884595', '萌新:13614884595', '$2a$10$lTq0uKdxf4A2O8Mc767OX.lfjBPR3Vv8mmWFKg/0F9bu/1G670JLO', '0', NULL, NULL, NULL, NULL, '1', NULL, '2022-02-27 19:38:18', NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
