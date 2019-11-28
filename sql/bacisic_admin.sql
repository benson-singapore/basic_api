/*
 Navicat Premium Data Transfer

 Source Server         : 47.244.25.34
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 47.244.25.34:3306
 Source Schema         : bacisic_admin

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 28/11/2019 16:48:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `parent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区域编码',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_area_del_flag` (`del_flag`) USING BTREE,
  KEY `sys_area_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='地区表 ';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据值',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '父级编号',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('04536229f0634f77ac16648ce69ea481', '13222', '12322', 'kkkkte\'s\'t', '23222', 11, '62f0d54a37394a908230e3bdf8dd07be', '1', '2019-11-25 15:58:01', '1', '2019-11-25 15:58:13', NULL, '0');
INSERT INTO `sys_dict` VALUES ('16f7891e57f14bf18d249d0f32995808', NULL, '角色分类', 'role_type', '角色分类\n', 10, '0', '1', '2019-05-28 17:10:00', '1', '2019-11-26 10:36:51', NULL, '0');
INSERT INTO `sys_dict` VALUES ('1d73605ecaa34875be4fb8b55431d959', '0', '系统管理员', 'role_type', '系统管理员', 1, '16f7891e57f14bf18d249d0f32995808', '1', '2019-05-30 12:14:54', '1', '2019-11-26 18:03:19', NULL, '0');
INSERT INTO `sys_dict` VALUES ('39048dbdc62d47178fce79a6bb205ed8', '1', 'male', 'sex_type', 'male（男）', 20, '94c3e749714b456f913312431520e985', '1', '2019-05-24 11:46:51', '1', '2019-05-24 11:53:20', NULL, '0');
INSERT INTO `sys_dict` VALUES ('3ce52223df5c47cf9cbdbd84e0d2d953', '1', 'administrator', 'user_type', '系统管理员', 10, '50cc381dac214e2f9b3ad698206a0aff', '1', '2019-05-25 18:21:42', '1', '2019-05-25 20:59:56', NULL, '0');
INSERT INTO `sys_dict` VALUES ('400b7f11ee3f42358d87127af3d1a3ba', '3', 'consumer', 'user_type', '普通用户', 30, '50cc381dac214e2f9b3ad698206a0aff', '1', '2019-05-25 18:23:41', '1', '2019-05-25 18:37:57', NULL, '0');
INSERT INTO `sys_dict` VALUES ('436303d26b4647b094ff4de8d1576be7', '0', 'normal', 'sys_use_able', 'it\'s usable or not (是否可用)', 10, '45d4de3e3dc745b58c9d4af482a081f2', '1', '2019-05-22 10:11:38', '1', '2019-05-22 10:11:38', 'string', '0');
INSERT INTO `sys_dict` VALUES ('43ab7d8663784297ae76931e57bab100', '1', 'Disable', 'sys_use_able', 'it\'s usable or not (是否可用)', 20, '45d4de3e3dc745b58c9d4af482a081f2', '1', '2019-05-22 10:11:22', '1', '2019-05-24 12:45:57', 'string', '0');
INSERT INTO `sys_dict` VALUES ('45d4de3e3dc745b58c9d4af482a081f2', '', '是否可用', 'sys_use_able', '是否可用', 40, '0', '1', '2019-05-22 10:08:41', '1', '2019-11-25 13:12:07', 'string', '0');
INSERT INTO `sys_dict` VALUES ('50cc381dac214e2f9b3ad698206a0aff', NULL, '用户类型', 'user_type', '用户类型', 20, '0', '1', '2019-05-25 18:20:21', '1', '2019-11-25 13:11:55', NULL, '0');
INSERT INTO `sys_dict` VALUES ('5185c61c5dc34123808c7f78021a4ba5', '2', 'Other', 'sex_type', 'Other（其他）', 30, '94c3e749714b456f913312431520e985', '1', '2019-05-24 11:48:06', '1', '2019-05-24 11:48:06', NULL, '0');
INSERT INTO `sys_dict` VALUES ('62f0d54a37394a908230e3bdf8dd07be', NULL, '测试', 'kkkkte\'s\'t', 'test', 50, '0', '1', '2019-11-25 14:56:26', '1', '2019-11-25 15:29:29', NULL, '1');
INSERT INTO `sys_dict` VALUES ('736a2b2b73fe4e1ca67e81b55bb8a21c', NULL, '12', '123', '123', 60, '0', '1', '2019-11-25 15:13:37', '1', '2019-11-25 15:13:37', NULL, '1');
INSERT INTO `sys_dict` VALUES ('74b7f24bb05a4923a52ed681fe035a16', '0', 'Female', 'sex_type', 'Female（女）', 10, '94c3e749714b456f913312431520e985', '1', '2019-05-24 11:47:34', '1', '2019-05-24 11:53:12', NULL, '0');
INSERT INTO `sys_dict` VALUES ('77571fd2df7c4284a56a595cb2e344af', '4', '其他', 'role_type', '其他', 40, '16f7891e57f14bf18d249d0f32995808', '1', '2019-05-28 17:18:28', '1', '2019-11-26 18:03:45', NULL, '0');
INSERT INTO `sys_dict` VALUES ('94c3e749714b456f913312431520e985', NULL, '性别', 'sex_type', '性别', 30, '0', '1', '2019-05-24 10:01:33', '1', '2019-11-25 13:34:55', NULL, '0');
INSERT INTO `sys_dict` VALUES ('97793accf0f64dd59fa63b0249dc1d85', '123', 'male', 'kkkkte\'s\'t', '123', 10, '0', '1', '2019-11-25 15:22:01', '1', '2019-11-25 15:50:57', NULL, '1');
INSERT INTO `sys_dict` VALUES ('9cf0adcd10734398aaea0fec3cb4b28b', '123', '123', 'te\'s\'t', '123', 20, '62f0d54a37394a908230e3bdf8dd07be', '1', '2019-11-25 15:16:13', '1', '2019-11-25 15:16:13', NULL, '1');
INSERT INTO `sys_dict` VALUES ('adc16097bf7e4a1c92e2015753b79471', '3', '普通用户', 'role_type', '普通用户', 30, '16f7891e57f14bf18d249d0f32995808', '1', '2019-05-28 17:12:03', '1', '2019-11-26 18:03:38', NULL, '0');
INSERT INTO `sys_dict` VALUES ('c6ed936db25b484caa5977c90d91eb16', '1', '运维人员', 'role_type', '运维人员', 10, '16f7891e57f14bf18d249d0f32995808', '1', '2019-05-28 17:10:53', '1', '2019-11-26 18:03:25', NULL, '0');
INSERT INTO `sys_dict` VALUES ('d9f053418e924657aa3e3d786a9f8acb', '2', '开发人员', 'role_type', '开发人员\n', 20, '16f7891e57f14bf18d249d0f32995808', '1', '2019-05-28 17:11:09', '1', '2019-11-26 18:03:32', NULL, '0');
INSERT INTO `sys_dict` VALUES ('fef305c4395446b1be5bef177203369c', '2', 'operation', 'user_type', '运营', 20, '50cc381dac214e2f9b3ad698206a0aff', '1', '2019-05-25 18:22:19', '1', '2019-05-25 18:22:19', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '1' COMMENT '日志类型：0 正常，1 异常',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '日志标题',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求URI',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作方式',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '操作提交的数据',
  `exception` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '异常信息',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_create_date` (`create_date`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日志表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `parent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `href` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '链接',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图标',
  `is_show` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否在菜单中显示',
  `component` varchar(225) DEFAULT NULL COMMENT 'ant 路由',
  `permission` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_menu_del_flag` (`del_flag`) USING BTREE,
  KEY `sys_menu_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('828201ef732e40c2b0bb70187ece6222', 'e76e8b91d7c84990a66dcd074466f014', '0e76e8b91d7c84990a66dcd074466f014', '角色管理', 20, '/sys/role', NULL, 'team', '1', './sys/role', NULL, '1', '2019-11-27 14:28:56', '1', '2019-11-27 14:59:54', NULL, '0');
INSERT INTO `sys_menu` VALUES ('ad5af9c22c1948418892445556f86d33', 'e76e8b91d7c84990a66dcd074466f014', '0e76e8b91d7c84990a66dcd074466f014', '用户管理', 10, '/sys/user', NULL, 'user', '1', './sys/user', '', '1', '2019-11-27 14:27:11', '1', '2019-11-27 14:58:37', NULL, '0');
INSERT INTO `sys_menu` VALUES ('e3822bd5114e43e1a1fc4407896c0e02', 'e76e8b91d7c84990a66dcd074466f014', '0e76e8b91d7c84990a66dcd074466f014', '字典管理', 40, '/sys/dict', NULL, 'reconciliation', '1', './sys/dict', NULL, '1', '2019-11-27 15:00:37', '1', '2019-11-27 15:03:48', NULL, '0');
INSERT INTO `sys_menu` VALUES ('e76e8b91d7c84990a66dcd074466f014', '0', '0', '系统管理', 10, '/sys', NULL, 'setting', '1', NULL, NULL, '1', '2019-11-27 14:15:33', '1', '2019-11-27 15:39:38', NULL, '0');
INSERT INTO `sys_menu` VALUES ('ff1fe6749ce841bbac311113a14012e3', 'e76e8b91d7c84990a66dcd074466f014', '0e76e8b91d7c84990a66dcd074466f014', '菜单管理', 30, '/sys/menu', NULL, 'bars', '1', './sys/menu', NULL, '1', '2019-11-27 14:59:27', '1', '2019-11-27 15:06:22', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `parent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `area_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '归属区域',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区域编码',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '机构类型',
  `grade` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '机构等级',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '传真',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `USEABLE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否启用',
  `PRIMARY_PERSON` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '主负责人',
  `DEPUTY_PERSON` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '副负责人',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_office_del_flag` (`del_flag`) USING BTREE,
  KEY `sys_office_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_office_type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='职位表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
BEGIN;
INSERT INTO `sys_office` VALUES ('1', '2', '0,2', '研发部', 20, '4', NULL, '1', '1', '', NULL, NULL, '38389438', NULL, '38389438@email.com', NULL, '2aad35a0ceb94ce0a4c7560cfaffe11d', NULL, 'zzc', '2019-05-29 03:36:57', '1', '2019-06-11 14:56:15', 'Development', '0');
INSERT INTO `sys_office` VALUES ('171074b8d7544d658f83f94888265f69', '2', '0,2', '其他', 30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1111111', NULL, 'Other@email.com', NULL, '565387ff088f4960a5f9057fff5c2fa7', NULL, '1', '2019-06-11 13:48:50', '1', '2019-06-11 14:58:29', 'Other', '0');
INSERT INTO `sys_office` VALUES ('2', '0', '0', '新加坡公司总部', 10, '4', NULL, '1', '1', 'suntec tower 3 level 43', NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, 'zzc', '2019-05-29 10:13:03', '1', '2019-06-11 14:58:07', 'company', '0');
INSERT INTO `sys_office` VALUES ('a6de7c3b370a4c638bfe00d369811fbb', '2', '0,2', '人事部', 25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '38389438', NULL, '38389438@email.com', NULL, '2aad35a0ceb94ce0a4c7560cfaffe11d', NULL, '1', '2019-06-11 14:29:49', '1', '2019-06-11 14:58:23', 'Personnel', '0');
INSERT INTO `sys_office` VALUES ('b5b57f483da34e8f92f2782fb5421d17', '2', '0,2', '运维部', 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '12341234', NULL, 'Operation@email.com', NULL, '1', NULL, '1', '2019-06-11 11:25:05', '1', '2019-06-12 11:06:26', 'Operation', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `office_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `enname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '英文名称',
  `role_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色类型',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数据范围',
  `is_sys` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否系统数据',
  `useable` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否可用',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_role_del_flag` (`del_flag`) USING BTREE,
  KEY `sys_role_enname` (`enname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', NULL, '系统管理员', 'admin', '0', NULL, '0', '1', '1', '2019-05-28 17:36:39', '1', '2019-11-28 13:12:00', NULL, '0');
INSERT INTO `sys_role` VALUES ('58d694a04b8749e1a06591cd78d03c48', NULL, '运维人员', 'operation', '1', NULL, '1', '1', '1', '2019-05-29 10:55:45', '1', '2019-11-28 13:12:09', NULL, '0');
INSERT INTO `sys_role` VALUES ('5ed7e41e048c4c93a87ae74d3ae62666', NULL, 'Admin', '234111', '3', NULL, NULL, '1', '1', '2019-11-27 17:50:58', '1', '2019-11-27 18:02:00', NULL, '1');
INSERT INTO `sys_role` VALUES ('79e12285e3494262b47f65e73c539657', NULL, '开发人员', 'development', '2', NULL, '0', '1', '1', '2019-05-30 12:25:00', '1', '2019-11-28 13:12:16', NULL, '0');
INSERT INTO `sys_role` VALUES ('ba55ef5ba17b4af68a26faf35b5cdaec', NULL, 'Admin', 'admin1', '0', NULL, NULL, '1', '1', '2019-11-27 17:45:20', '1', '2019-11-27 17:45:20', NULL, '1');
INSERT INTO `sys_role` VALUES ('d177e6a10ef34a72ae8278ecea8fac20', NULL, 'Admin', 'asd', '2', NULL, NULL, NULL, '1', '2019-11-27 17:42:49', '1', '2019-11-27 17:42:49', NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('1', '828201ef732e40c2b0bb70187ece6222');
INSERT INTO `sys_role_menu` VALUES ('1', 'ad5af9c22c1948418892445556f86d33');
INSERT INTO `sys_role_menu` VALUES ('1', 'e3822bd5114e43e1a1fc4407896c0e02');
INSERT INTO `sys_role_menu` VALUES ('1', 'e76e8b91d7c84990a66dcd074466f014');
INSERT INTO `sys_role_menu` VALUES ('1', 'ff1fe6749ce841bbac311113a14012e3');
INSERT INTO `sys_role_menu` VALUES ('58d694a04b8749e1a06591cd78d03c48', '828201ef732e40c2b0bb70187ece6222');
INSERT INTO `sys_role_menu` VALUES ('58d694a04b8749e1a06591cd78d03c48', 'ad5af9c22c1948418892445556f86d33');
INSERT INTO `sys_role_menu` VALUES ('58d694a04b8749e1a06591cd78d03c48', 'ff1fe6749ce841bbac311113a14012e3');
INSERT INTO `sys_role_menu` VALUES ('79e12285e3494262b47f65e73c539657', '828201ef732e40c2b0bb70187ece6222');
INSERT INTO `sys_role_menu` VALUES ('79e12285e3494262b47f65e73c539657', 'ff1fe6749ce841bbac311113a14012e3');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_office`;
CREATE TABLE `sys_role_office` (
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编号',
  `office_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`office_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色职位表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `company_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '归属公司',
  `office_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '归属部门',
  `login_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机',
  `user_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户类型',
  `photo` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户头像',
  `login_ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_flag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '登录状态 : 0 正常，1 异常',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_user_company_id` (`company_id`) USING BTREE,
  KEY `sys_user_del_flag` (`del_flag`) USING BTREE,
  KEY `sys_user_login_name` (`login_name`) USING BTREE,
  KEY `sys_user_office_id` (`office_id`) USING BTREE,
  KEY `sys_user_update_date` (`update_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表 ';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('0c5dcad573504a3db21f752dbe4c45a1', NULL, 'a6de7c3b370a4c638bfe00d369811fbb', 'test011', '123456', NULL, 'test011', '123456@qq.com1', NULL, '12345671', '3', '{\"select\":true,\"key\":15}', NULL, NULL, '0', '1', '2019-11-26 16:00:28', '1', '2019-11-26 16:38:03', NULL, '1');
INSERT INTO `sys_user` VALUES ('1', '1', 'a6de7c3b370a4c638bfe00d369811fbb', 'admin', '$2a$10$dWrgEnb.TPOvVcpCLD4goegbFHdhUDtSqMrdM4lCZewyzYOscDDKO', '001', 'admin', 'admin@admin.com', NULL, '123', '1', '{\"select\":true,\"key\":4}', '223.104.3.188', '2019-11-22 09:28:40', '0', '1', '2019-05-21 09:17:01', '1', '2019-11-28 12:01:20', NULL, '0');
INSERT INTO `sys_user` VALUES ('2aad35a0ceb94ce0a4c7560cfaffe11d', NULL, '171074b8d7544d658f83f94888265f69', 'operation', '$2a$10$Rh8S4zVB2oaaS0CM9peC8uSv21vTGrW3mWqXP6YSuZd6EsVcxi/IO', NULL, 'Operation', '123456@qq.com', NULL, '1234567', '2', '{\"select\":true,\"key\":4}', NULL, NULL, '0', '1', '2019-05-30 17:57:17', '1', '2019-11-28 13:12:40', NULL, '0');
INSERT INTO `sys_user` VALUES ('565387ff088f4960a5f9057fff5c2fa7', NULL, 'b5b57f483da34e8f92f2782fb5421d17', 'zhangbiyu', '$2a$10$ZiUTp8EIOEjTxE2.rrN8huVDV2ITo/ggUiNlPyBVoPwx/cCkBZdgC', NULL, 'zhangbiyu', '123456@qq.com', NULL, '1234567', '2', '{\"select\":false,\"background\":\"#00a2ae\",\"fontColor\":\"#ffffff\",\"fontValue\":\"ZHANG\"}', '13.125.231.36', '2019-08-10 20:54:03', '0', '1', '2019-05-26 20:21:44', '1', '2019-11-28 14:34:04', NULL, '0');
INSERT INTO `sys_user` VALUES ('a7392a84c5684a5499e075479bcd8061', NULL, '1', 'asdasd', '$2a$10$f2MI7nLHU1.9Orfw7FVUtOHlG.AAOadHnevGwUCXtf3wtDYxxbGw2', NULL, 'test', '123456@qq.com', NULL, '1234567', '2', '{\"select\":true,\"key\":11}', NULL, NULL, '0', '1', '2019-11-26 16:38:54', '1', '2019-11-27 12:04:07', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('0c5dcad573504a3db21f752dbe4c45a1', '1');
INSERT INTO `sys_user_role` VALUES ('0c5dcad573504a3db21f752dbe4c45a1', '58d694a04b8749e1a06591cd78d03c48');
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2aad35a0ceb94ce0a4c7560cfaffe11d', '1');
INSERT INTO `sys_user_role` VALUES ('2aad35a0ceb94ce0a4c7560cfaffe11d', '79e12285e3494262b47f65e73c539657');
INSERT INTO `sys_user_role` VALUES ('565387ff088f4960a5f9057fff5c2fa7', '79e12285e3494262b47f65e73c539657');
INSERT INTO `sys_user_role` VALUES ('a7392a84c5684a5499e075479bcd8061', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
