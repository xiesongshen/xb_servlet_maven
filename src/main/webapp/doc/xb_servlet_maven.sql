/*
 Navicat Premium Data Transfer

 Source Server         : mysql_master
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : xb_servlet_maven

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 24/03/2020 15:50:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `content` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章内容',
  `browse_count` int(11) NULL DEFAULT NULL COMMENT '浏览次数',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `publish_real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人名称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发布人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '如何做一名合格的Java工程师？', '如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师如何做一名合格的Java工程师', 1059, '2019-07-11 16:30:21', '超级管理员', 1);
INSERT INTO `article` VALUES (2, '学会自立', '学会自立', 269, '2019-01-11 21:30:29', '超级管理员', 1);
INSERT INTO `article` VALUES (3, '在逆境中生长', '在逆境中生长', 748, '2018-01-11 16:14:11', '东方标准', 6);
INSERT INTO `article` VALUES (4, '阳光总在风雨后', '阳光总在风雨后', 147, '2019-07-11 16:30:21', '小标', 4);
INSERT INTO `article` VALUES (5, '我真的很不错', '我真的很不错', 260, '2019-07-11 16:30:21', '小东', 2);
INSERT INTO `article` VALUES (6, '你若安好便是晴天', '你若安好便是晴天', 159, '2019-06-08 16:12:21', '小准', 5);
INSERT INTO `article` VALUES (8, '入门到精通', '删库跑路(rm /rf /*)', 2589545, '2019-07-24 10:52:31', '小准', 5);
INSERT INTO `article` VALUES (9, 'Java入门到精通', 'Java入门到精通', 114, '2019-07-14 10:52:31', '小准', 5);
INSERT INTO `article` VALUES (10, 'Spring入门到精通', 'Java入门到精通', 543, '2019-04-24 10:52:31', '小准', 5);
INSERT INTO `article` VALUES (11, 'SpringMVC入门到精通', 'Java入门到精通', 25, '2019-02-24 10:52:31', '小准', 5);
INSERT INTO `article` VALUES (12, 'SpringBoot入门到精通', 'Java入门到精通', 51, '2019-01-24 10:52:31', '小准', 5);
INSERT INTO `article` VALUES (13, 'MyBatis入门到精通', 'Java入门到精通', 232, '2019-07-24 10:52:31', '小准', 5);
INSERT INTO `article` VALUES (14, '23423424', '23423', 21, '2019-12-03 10:47:54', '超级管理员', 1);
INSERT INTO `article` VALUES (15, '东方标准', '东方标准真好', 23, '2019-07-25 10:55:06', '超级管理员', 1);
INSERT INTO `article` VALUES (16, '今天天气好晴朗', '今天天气好晴朗，处处好风光，好风光', 0, '2019-07-25 12:48:34', '超级管理员', 1);
INSERT INTO `article` VALUES (17, '不要抱怨', '不要抱怨啊', 0, '2019-12-05 18:26:34', '超级管理员', 1);
INSERT INTO `article` VALUES (18, '如何生存', '如何生存', 0, '2019-12-05 18:27:11', '超级管理员', 1);
INSERT INTO `article` VALUES (19, '学会自律', '学会自律', 0, '2019-12-05 18:28:49', '超级管理员', 1);
INSERT INTO `article` VALUES (20, '如何稳定情绪', '如何稳定情绪', 1, '2019-12-05 18:29:09', '超级管理员', 1);
INSERT INTO `article` VALUES (21, '关于升级问题标题', 'aaa', 0, '2019-12-06 20:17:51', '超级管理员', 1);

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `u_id` int(11) NOT NULL COMMENT '收藏用户id',
  `a_id` int(11) NOT NULL COMMENT '文章id',
  PRIMARY KEY (`u_id`, `a_id`) USING BTREE,
  INDEX `u_id_favorite_rk`(`u_id`) USING BTREE,
  INDEX `a_id_favorite_rk`(`a_id`) USING BTREE,
  CONSTRAINT `c` FOREIGN KEY (`u_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d` FOREIGN KEY (`a_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 1);
INSERT INTO `favorite` VALUES (1, 5);
INSERT INTO `favorite` VALUES (1, 11);
INSERT INTO `favorite` VALUES (2, 1);
INSERT INTO `favorite` VALUES (3, 1);
INSERT INTO `favorite` VALUES (5, 1);
INSERT INTO `favorite` VALUES (7, 1);

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议内容',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `status` int(1) NULL DEFAULT NULL COMMENT '会议状态 0:未开始 1:进行中 2:已结束',
  `make_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抄送人id，以逗号分隔',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `del_flag` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dept_id_conference_rk`(`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meeting
-- ----------------------------
INSERT INTO `meeting` VALUES (1, 1, '关于小标交友部门功能升级', '小标交友有如下问题：1、会议模块需要升级，2、文章模块需要升级，3、用户模块需要升级', '2019-12-04 00:00:00', '2019-12-16 21:07:00', '2019-12-17 12:43:00', 2, '[1,2,3]', NULL, NULL, NULL);
INSERT INTO `meeting` VALUES (2, 2, '客户需求变更', '客户需求变更', '2019-12-05 00:00:00', '2019-12-05 20:23:28', '2019-12-06 20:23:31', 2, '[1,2,3,4]', NULL, NULL, NULL);
INSERT INTO `meeting` VALUES (30, 5, '今天下午五点临时会议', '客户来了新需求，Java和产品留下讨论需求', '2019-12-06 00:00:00', '2019-12-06 17:00:50', '2019-12-06 18:30:50', 2, '[3, 5, 6]', NULL, NULL, NULL);
INSERT INTO `meeting` VALUES (31, 1, '测试会议', 'aa', '2019-12-06 00:00:00', '2019-12-06 16:55:02', '2019-12-06 16:56:10', 2, '[2, 3, 4, 5, 6, 7]', NULL, NULL, NULL);
INSERT INTO `meeting` VALUES (32, 1, '会议标题', 'asdasd', '2019-12-06 00:00:00', '2020-01-04 15:15:29', '2020-02-07 06:50:29', 2, '[2, 3, 4, 5]', NULL, NULL, NULL);
INSERT INTO `meeting` VALUES (33, 1, 'Java1908班会议', 'asdasd', '2019-12-06 00:00:00', '2019-12-06 20:28:29', '2019-12-12 20:29:00', 2, '[2, 3, 4]', NULL, NULL, NULL);
INSERT INTO `meeting` VALUES (34, 1, '哈哈哈哈哈', '哈哈哈哈哈', '2019-12-06 20:29:30', '2019-12-04 09:45:00', '2019-12-06 16:56:10', 2, '[2, 4, 5, 6]', NULL, NULL, NULL);
INSERT INTO `meeting` VALUES (35, 1, '研发部留下开会', '讨论研发部加薪问题', '2020-01-03 11:47:15', '2020-01-03 15:15:29', '2020-01-03 15:55:29', 2, '[1, 4, 5, 6]', NULL, NULL, NULL);
INSERT INTO `meeting` VALUES (46, 1, '123', '123', '2020-03-23 16:38:16', '2020-03-23 16:39:00', '2020-03-23 16:41:00', 2, '[2]', '2020-03-23 16:38:16', 1, NULL);

-- ----------------------------
-- Table structure for meeting_join
-- ----------------------------
DROP TABLE IF EXISTS `meeting_join`;
CREATE TABLE `meeting_join`  (
  `meeting_id` int(11) NULL DEFAULT NULL COMMENT '会议id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '参加人id',
  INDEX `u_id_con_join_rk`(`user_id`) USING BTREE,
  INDEX `c_id_con_join_rk`(`meeting_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `stock` int(11) NULL DEFAULT NULL COMMENT '库存',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'iphone11', 100, 1);
INSERT INTO `product` VALUES (2, '华为', 100, 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `del_flag` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '研发部', '2020-03-18 22:21:49', 1, NULL);
INSERT INTO `sys_dept` VALUES (2, '推广部', '2020-03-20 22:21:57', 1, NULL);
INSERT INTO `sys_dept` VALUES (3, '行政部', NULL, 1, NULL);
INSERT INTO `sys_dept` VALUES (4, '财务部', NULL, 1, NULL);
INSERT INTO `sys_dept` VALUES (5, '总裁办公室', '2020-03-11 22:22:01', 1, NULL);
INSERT INTO `sys_dept` VALUES (7, '秘书部', NULL, 1, NULL);
INSERT INTO `sys_dept` VALUES (8, '市场部', NULL, 1, NULL);
INSERT INTO `sys_dept` VALUES (9, '宣传部', NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(5) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `order_by` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (2, 0, '其他管理', NULL, NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (3, 1, '用户管理', '/user/list', NULL, 1, 1);
INSERT INTO `sys_menu` VALUES (4, 1, '部门管理', '/dept/list', NULL, 1, 5);
INSERT INTO `sys_menu` VALUES (5, 2, '会议管理', '/meeting/list', NULL, 1, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `qq_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ登录标识符',
  `wx_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信登录标识符',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别 1:男 0:女',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `register_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `look` int(11) NULL DEFAULT NULL COMMENT '查看数',
  `is_secret` int(2) NULL DEFAULT 1 COMMENT '是否私密 0：私密 1：公开',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `del_flag` int(2) NULL DEFAULT 0 COMMENT '是否删除 1是，0否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 5, 'admin', '8ead19ac784929f84da7a71f30de2049', 'zijun1024@aliyun.com', NULL, NULL, '马云3', 50, NULL, 0, NULL, '2019-12-01 10:51:50', '2020-03-19 17:00:37', '417af801-f991-4f21-87e5-71767d4c105c.jpg', 48, 0, '2020-03-18 15:31:07', 1, NULL);
INSERT INTO `sys_user` VALUES (2, 1, 'xiaodong', 'admin', 'xiaobiao@dfbz.com', NULL, NULL, '小东', 18, '110', 1, '我很帅啊', '2019-12-02 12:35:10', '2019-11-20 10:51:53', 'http://localhost:8080/upload/def.png', 32, 1, '2020-03-18 15:31:11', 1, NULL);
INSERT INTO `sys_user` VALUES (3, 1, 'xiaofang', 'admin', 'xiaofang@dfbz.com', NULL, NULL, '小方', 18, '110', 1, '我很帅啊', '2019-12-02 12:35:10', '2019-11-20 10:51:53', 'http://localhost:8080/upload/def.png', 29, 1, NULL, 1, NULL);
INSERT INTO `sys_user` VALUES (4, 1, 'xiaobiao', 'admin', 'xiaobiao@dfbz.com', NULL, NULL, '小标', 18, '110', 1, '我很帅啊', '2019-12-02 12:35:10', '2019-11-20 10:51:53', 'http://localhost:8080/upload/def.png', 27, 1, '2020-03-17 15:31:15', 1, NULL);
INSERT INTO `sys_user` VALUES (5, 1, 'xiaozhun', 'admin', 'xiaozhun@dfbz.com', NULL, NULL, '小准', 18, '110', 1, '我很帅啊', '2019-12-02 12:35:10', '2019-11-20 10:51:53', 'http://localhost:8080/upload/def.png', 31, 1, NULL, 1, NULL);
INSERT INTO `sys_user` VALUES (6, 1, 'dfbz', 'admin', 'dfbz@dfbz.com', NULL, NULL, '东方标准', 18, '110', 1, '我很帅啊', '2019-11-28 11:30:24', '2019-11-20 10:51:53', 'http://localhost:8080/upload/def.png', 20, 1, NULL, 1, NULL);
INSERT INTO `sys_user` VALUES (7, 1, 'xiaoming', 'admin', 'xm@dfbz.com', NULL, NULL, '小明', 18, '110', 1, '我很帅啊', '2019-12-04 07:30:28', '2019-11-20 10:51:53', 'http://localhost:8080/upload/def.png', 21, 1, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (8, 3, 'root', 'admin', 'root@dfbz.com', NULL, NULL, '管理员', 28, '110', 0, NULL, '2019-12-06 12:33:41', '2019-12-06 00:00:00', 'http://localhost:8080/upload/22892836-ed70-4039-8558-69dc81dd676b.png', 3, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (9, NULL, 'root_1', 'admin', 'root_1@dfbz.com', NULL, NULL, '管理员1号', 18, '119', 0, NULL, '2019-12-06 12:37:29', '2019-12-06 00:00:00', 'http://localhost:8080/upload/def.png', 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (17, 7, 'cc-475fd36f42ef', NULL, NULL, '3584F8F99BE67A5254A122D123B2BE24', NULL, 'Cool', 3332, '111', NULL, NULL, '2019-12-27 12:07:54', '2019-12-27 00:00:00', 'http://localhost:8080/upload/f1a3139d-d51a-4376-9ad7-90a4f2aa28de.png', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (18, 4, 'af-845c0bfa13d5', NULL, NULL, '80303D26E1091C58F41FFAB08123F427', NULL, 'qq', 11, '110', NULL, NULL, '2019-12-27 15:30:53', '2020-01-03 00:00:00', 'http://thirdqq.qlogo.cn/g?b=oidb&k=7ywAfU4U8EOKpibrQ2UXibkw&s=100&t=1556885708', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (19, NULL, 'a9-4b9ef510e8a9', NULL, NULL, NULL, 'oQ4QeuPW7WL6ZwKt4Dc9wlc8WjRY', 'For', NULL, NULL, 1, NULL, '2019-12-31 10:49:37', '2020-01-03 00:00:00', 'http://thirdwx.qlogo.cn/mmopen/vi_32/icxlG7XRNqvQ4gUhgicXlz0oH4icR2E2MH8T0qic2X2KdbIiaKGlp9FQ91qnHjr2KW2uBBOZyfia7WOasvgIjgh3yK4A/132', 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (20, NULL, '7f-3ea444301d8d', NULL, NULL, '01CC173B24CFE00ABD613E6AB58CED54', NULL, '嘿嘿嘿', NULL, NULL, NULL, NULL, '2019-12-31 16:45:47', '2019-12-31 00:00:00', 'http://thirdqq.qlogo.cn/g?b=oidb&k=IMauKBZYyia9kUL43DEiaMOQ&s=100&t=1557174653', 0, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (21, 4, '8a-6e107a887a55', NULL, NULL, NULL, 'oQ4QeuNRR9S_4IyRzkJbNRoPNSH4', '嘿嘿嘿', 1, '234234234', NULL, NULL, '2019-12-31 16:46:20', '2019-12-31 00:00:00', 'http://localhost:8080/upload/a3fb2503-b1b1-46e1-854a-8051c7368b41.jpg', 1, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for userfocus
-- ----------------------------
DROP TABLE IF EXISTS `userfocus`;
CREATE TABLE `userfocus`  (
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `user_focus_id` int(10) NOT NULL COMMENT '用户关注的朋友id',
  PRIMARY KEY (`user_id`, `user_focus_id`) USING BTREE,
  INDEX `fk_userFocus_user_1`(`user_id`) USING BTREE,
  INDEX `fk_userFocus_user_2`(`user_focus_id`) USING BTREE,
  CONSTRAINT `a` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `b` FOREIGN KEY (`user_focus_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userfocus
-- ----------------------------
INSERT INTO `userfocus` VALUES (1, 5);
INSERT INTO `userfocus` VALUES (1, 7);
INSERT INTO `userfocus` VALUES (1, 8);
INSERT INTO `userfocus` VALUES (1, 9);
INSERT INTO `userfocus` VALUES (2, 1);
INSERT INTO `userfocus` VALUES (7, 1);
INSERT INTO `userfocus` VALUES (18, 1);
INSERT INTO `userfocus` VALUES (18, 9);
INSERT INTO `userfocus` VALUES (18, 17);

-- ----------------------------
-- Event structure for auto_update_status
-- ----------------------------
DROP EVENT IF EXISTS `auto_update_status`;
delimiter ;;
CREATE EVENT `auto_update_status`
ON SCHEDULE
EVERY '1' SECOND STARTS '2019-12-13 12:36:21'
DO begin
	-- 修改使用子查询,否则报错(You can't specify target table 'meeting' for update in FROM clause) 不可以在update语句中使用本表查询
	update meeting set status = 1 where id in (
	
		select a.id from (
			select id from meeting where DATE_FORMAT(start_time,'%Y-%m-%d %H:%i')=DATE_FORMAT(now(),'%Y-%m-%d %H:%i')
		
		) a
	
	);
	update meeting set status = 2 where id in (
	
		select a.id from (
			select id from meeting where DATE_FORMAT(end_time,'%Y-%m-%d %H:%i')=DATE_FORMAT(now(),'%Y-%m-%d %H:%i')
		) a
	
	);
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
