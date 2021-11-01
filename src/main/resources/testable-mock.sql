/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 172.16.171.6:3306
 Source Schema         : testable-mock

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 06/07/2021 15:38:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int NOT NULL,
  `book_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of book
-- ----------------------------
BEGIN;
INSERT INTO `book` VALUES (1, '多情剑客无情剑', '古龙');
INSERT INTO `book` VALUES (2, '笑傲江湖', '金庸');
INSERT INTO `book` VALUES (3, '倚天屠龙记', '金庸');
INSERT INTO `book` VALUES (4, '射雕英雄传', '金庸');
INSERT INTO `book` VALUES (5, '绝代双骄', '古龙');
COMMIT;

-- ----------------------------
-- Table structure for book_hero
-- ----------------------------
DROP TABLE IF EXISTS `book_hero`;
CREATE TABLE `book_hero` (
  `id` int NOT NULL,
  `hero_id` int NOT NULL,
  `user_comment` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`hero_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of book_hero
-- ----------------------------
BEGIN;
INSERT INTO `book_hero` VALUES (1, 1, '小李飞刀，例无虚发，夺魂索命，弹指之间');
INSERT INTO `book_hero` VALUES (2, 2, '令狐少侠留步!');
INSERT INTO `book_hero` VALUES (2, 6, '有人就有恩怨，有恩怨就有江湖，人心即是江湖，你如何退出！');
INSERT INTO `book_hero` VALUES (3, 3, '尝遍世间善恶，归来仍是少年');
INSERT INTO `book_hero` VALUES (4, 4, '我只要我的靖哥哥!');
INSERT INTO `book_hero` VALUES (5, 5, '风采儒雅亦坦荡，武艺精深兼明智。');
COMMIT;

-- ----------------------------
-- Table structure for hero
-- ----------------------------
DROP TABLE IF EXISTS `hero`;
CREATE TABLE `hero` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `hero_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `skill` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of hero
-- ----------------------------
BEGIN;
INSERT INTO `hero` VALUES (1, '李寻欢', '小李飞刀', 1);
INSERT INTO `hero` VALUES (2, '令狐冲', '独孤九剑', 2);
INSERT INTO `hero` VALUES (3, '张无忌', '九阳神功', 3);
INSERT INTO `hero` VALUES (4, '郭靖', '降龙十八掌', 4);
INSERT INTO `hero` VALUES (5, '花无缺', '移花接玉', 5);
INSERT INTO `hero` VALUES (6, '任我行', '吸星大法', 2);
COMMIT;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `account` varchar(64) NOT NULL COMMENT '账户名称',
  `bak_account_id` int NOT NULL COMMENT '备用账号id',
  `bak_account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备用账号名称',
  `nickname` varchar(256) NOT NULL COMMENT ' 商家昵称',
  `freeze_dead_time` datetime NOT NULL COMMENT '解冻时间',
  `level` tinyint NOT NULL DEFAULT '0' COMMENT '等级:默认=0,1=商户V1,2=商户V2,3=商户V3',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态:0=默认,1=已激活,2=取消激活,3=冻结',
  `authority` tinyint NOT NULL DEFAULT '0' COMMENT '资金权限:0=默认,1=已开启,2=已关闭',
  `remark` varchar(128) NOT NULL COMMENT '备注',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 否(0),是(1)',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1705 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商户';

-- ----------------------------
-- Records of merchant
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
