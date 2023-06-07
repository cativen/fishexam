/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : zhuyu_pets

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 29/05/2020 10:05:42
*/

DROP DATABASE IF EXISTS `zhuyu_pets`;

CREATE DATABASE  `zhuyu_pets` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

use `zhuyu_pets`;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `doctor_id` int(0) NOT NULL AUTO_INCREMENT,
  `doctor_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_post` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_age` int(0) NULL DEFAULT NULL,
  `doctor_gender` int(0) NULL DEFAULT NULL COMMENT '1 men 0 women',
  `doctor_level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_demo1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_demo2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor_demo3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`doctor_id`) USING BTREE,
  INDEX `doctor_name`(`doctor_name`) USING BTREE,
  INDEX `doctor_number`(`doctor_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES (1, '511478', '王啦啦', NULL, '骨科', '1766722034@qq.com', '13734010086', 21, 1, '主任', '中国中医科学院主任医师，1939年2月起从事中医临床工作，为全国老中医药专家学术经验继承工作指导老师、“首都国医名师”，国家级非物质文化遗产传统医药项目代表性传承人。擅长中医内科、针灸，对妇科、儿科等亦很有深造诣', NULL, NULL);
INSERT INTO `doctor` VALUES (2, '361744', '吕小布', NULL, '骨科', 'wzy000302@qq.com', '13945687432', 30, 1, '医生', '皖南医学院附属弋矶山医院主任医师、终身教授。首届“国医大师”、首批“全国500名老中医”、首批国家名老中医学术经验继承人指导老师、首批中国百年百名中医临床家、首批国务院特殊津贴获得者。精擅内、妇科疑难杂症，尤擅痹病、痿病、肿瘤等顽疾治疗，有《济仁医录》等专著10余部，论文百余篇，并参编《内经》、《中医基础理论》等高等学校规划教材。', NULL, NULL);
INSERT INTO `doctor` VALUES (3, '817423', '唐玉浩', NULL, '泌尿科', 'ty3307@qq.com', '17558471422', 20, 0, '护士', '与汪逢春、萧龙友、施今墨并称北京四大名医。学自家传。早年任北京外城官医院医官。1929年被选为全国医药团体联合会临时主席，率请愿团赴南京，迫使国民党政府收回“取缔中医”的成命。后与萧龙友合办北京国医学院并任院长。建国后，任卫生部顾问、中华医学会中西医学术交流委员会副主任。是第二届全国政协委员。学术上，主张病必求其本，临证注重湿与热。以善治温病著名，更以善用石膏一药，为医林所景仰。著有《时斋医话》、《传染病八种证治晰疑》。有《孔伯华医集》。', NULL, NULL);
INSERT INTO `doctor` VALUES (4, '844713', '王世英', NULL, '肛门科', '1161001321@qq.com', '15753589632', 20, 0, '实习护士', '著名肝胆外科专家，中国科学院院士，中国肝脏外科的开拓者和主要创始人之一，李庄同济医院终身名誉院长，被誉为“中国肝胆外科之父”和有可能获得诺贝尔生理学或医学奖的中国大陆学者之一。皖南医学院附属弋矶', NULL, NULL);
INSERT INTO `doctor` VALUES (5, '336871', '王鑫', NULL, '毛发科', '1373628651@qq.com', '15154226321', 20, 1, '实习医生', '1997年学成回国创办国际合作生物信号转导研究中心和综合治疗病区，形成基础与临床结合的创新基地。在分子诊断方面，研发了新的肝癌诊断标志物及血清检测单克隆抗体，获国家专利；克隆新的肝癌相关基因4个并阐明功能；首次发现新的抑制性受体对肝癌细胞生长、凋亡的调控机制和癌基因P28在肝癌的异常信号通路，为肝癌防治提供了新的靶标；分离新的磷酸酶3种，提出新的酶分类法；发现了磷酸酶PCP-2调控β-catenin介导的肿瘤信号通路，与同行合作提出新的抑制性受体调控机制在多器官存在的新概念。', NULL, NULL);
INSERT INTO `doctor` VALUES (6, '358774', '杨思琦', NULL, '毛发科', '12679962@qq.com', '198442876', 20, 0, '医生助理', '著名医学家、 [1-2]  中国现代普通外科的主要开拓者、肝胆外科和器官移植外科的主要创始人和奠基人之一、晚期血吸虫病外科治疗的开创者、中国科学院资深院士，被誉为“中国外科之父”。其刀法以精准见长，被医学界称为“裘氏刀法”。', NULL, NULL);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'message表主键ID',
  `message_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `message_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `message_daytime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `message_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示的消息',
  `message_doctorid` int(0) NULL DEFAULT NULL,
  `message_status` int(0) NULL DEFAULT 0 COMMENT '1已读，0未读',
  `message_datetime` date NULL DEFAULT NULL,
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '王医生', 'nullphoto', '下午3点', '哈士奇奥特曼绝育手术', 101, 0, '2020-05-22');
INSERT INTO `message` VALUES (2, '刘护士', 'nullphoto', '上午10点', '金毛多多打针', 102, 1, '2020-05-23');

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation`  (
  `operation_id` int(0) NOT NULL AUTO_INCREMENT,
  `operation_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_10` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_11` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_12` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_3` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_4` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_5` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_6` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_date` date NULL DEFAULT NULL,
  `operation_what` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`operation_id`) USING BTREE,
  INDEX `op10`(`operation_10`) USING BTREE,
  INDEX `op11`(`operation_11`) USING BTREE,
  INDEX `op12`(`operation_12`) USING BTREE,
  INDEX `op2`(`operation_2`) USING BTREE,
  INDEX `op3`(`operation_3`) USING BTREE,
  INDEX `op4`(`operation_4`) USING BTREE,
  INDEX `op5`(`operation_5`) USING BTREE,
  INDEX `op6`(`operation_6`) USING BTREE,
  CONSTRAINT `op10` FOREIGN KEY (`operation_10`) REFERENCES `doctor` (`doctor_name`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `op11` FOREIGN KEY (`operation_11`) REFERENCES `doctor` (`doctor_name`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `op12` FOREIGN KEY (`operation_12`) REFERENCES `doctor` (`doctor_name`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `op2` FOREIGN KEY (`operation_2`) REFERENCES `doctor` (`doctor_name`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `op3` FOREIGN KEY (`operation_3`) REFERENCES `doctor` (`doctor_name`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `op4` FOREIGN KEY (`operation_4`) REFERENCES `doctor` (`doctor_name`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `op5` FOREIGN KEY (`operation_5`) REFERENCES `doctor` (`doctor_name`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `op6` FOREIGN KEY (`operation_6`) REFERENCES `doctor` (`doctor_name`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation
-- ----------------------------
INSERT INTO `operation` VALUES (1, '621745', '王鑫', NULL, '王啦啦', NULL, '杨思琦', '唐玉浩', NULL, '吕小布', '2020-05-20', NULL);
INSERT INTO `operation` VALUES (2, '691138', '王啦啦', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-05-21', NULL);
INSERT INTO `operation` VALUES (3, '685178', '王世英', NULL, '吕小布', NULL, NULL, NULL, '王鑫', NULL, '2020-05-22', NULL);
INSERT INTO `operation` VALUES (4, '698137', '王鑫', NULL, '吕小布', NULL, NULL, NULL, NULL, '唐玉浩', '2020-05-23', NULL);
INSERT INTO `operation` VALUES (5, '687151', NULL, '王世英', NULL, '王世英', '王啦啦', '杨思琦', NULL, NULL, '2020-05-24', NULL);
INSERT INTO `operation` VALUES (6, '613844', NULL, NULL, NULL, '唐玉浩', NULL, NULL, NULL, NULL, '2020-05-25', NULL);
INSERT INTO `operation` VALUES (7, '698137', '唐玉浩', NULL, '王世英', NULL, NULL, NULL, '吕小布', NULL, '2020-05-26', NULL);

-- ----------------------------
-- Table structure for operationplus
-- ----------------------------
DROP TABLE IF EXISTS `operationplus`;
CREATE TABLE `operationplus`  (
  `operationplus_id` int(0) NOT NULL AUTO_INCREMENT,
  `operationplus_number` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operationplus_date` datetime(0) NULL DEFAULT NULL,
  `operationplus_doctor` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operationplus_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operationplus_demo1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operationplus_demo2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operationplus_demo3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`operationplus_id`) USING BTREE,
  INDEX `doctor_operationplus`(`operationplus_doctor`) USING BTREE,
  CONSTRAINT `doctor_operationplus` FOREIGN KEY (`operationplus_doctor`) REFERENCES `doctor` (`doctor_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operationplus
-- ----------------------------
INSERT INTO `operationplus` VALUES (2, '85612384', '2020-05-26 10:00:00', '唐玉浩', '金毛绝育手术', '金大毛', '', NULL);
INSERT INTO `operationplus` VALUES (3, '86661759', '2020-05-24 11:00:00', '王世英', '边牧耳朵立不起来', '王美丽', '', NULL);
INSERT INTO `operationplus` VALUES (4, '51359742', '2020-05-20 10:00:00', '王鑫', '哈小二手术', '哈小二', '', NULL);
INSERT INTO `operationplus` VALUES (5, '26135187', '2020-05-20 12:00:00', '王啦啦', '金大毛手术', '金大毛', '', NULL);
INSERT INTO `operationplus` VALUES (6, '84613517', '2020-05-20 03:00:00', '杨思琦', '边小二手术', '边小二', '', NULL);
INSERT INTO `operationplus` VALUES (7, '65153174', '2020-05-20 04:00:00', '唐玉浩', '哈小三手术', '哈小三', NULL, NULL);
INSERT INTO `operationplus` VALUES (8, '31513184', '2020-05-20 06:00:00', '吕小布', '阿拉三手术', '阿拉三', '', NULL);
INSERT INTO `operationplus` VALUES (9, '55174437', '2020-05-21 10:00:00', '王啦啦', '哈小四手术', '哈小四', NULL, NULL);
INSERT INTO `operationplus` VALUES (10, '15615187', '2020-05-22 10:00:00', '王世英', '金小三手术', '金小三', NULL, NULL);
INSERT INTO `operationplus` VALUES (11, '81653217', '2020-05-22 12:00:00', '吕小布', '边小三手术', '边小三', NULL, NULL);
INSERT INTO `operationplus` VALUES (12, '58315157', '2020-05-22 05:00:00', '王鑫', '金小四手术', '金小四', NULL, NULL);
INSERT INTO `operationplus` VALUES (13, '84615517', '2020-05-23 10:00:00', '王鑫', '阿拉四手术', '阿拉四', NULL, NULL);
INSERT INTO `operationplus` VALUES (14, '56138774', '2020-05-23 12:00:00', '吕小布', '萨摩一手术', '萨摩一', NULL, NULL);
INSERT INTO `operationplus` VALUES (15, '51387412', '2020-05-23 06:00:00', '唐玉浩', '萨摩二手术', '萨摩二', NULL, NULL);
INSERT INTO `operationplus` VALUES (16, '51315844', '2020-05-24 02:00:00', '王世英', '二哈一手术', '二哈一', NULL, NULL);
INSERT INTO `operationplus` VALUES (17, '61315131', '2020-05-24 03:00:00', '王啦啦', '边小四手术', '边小四', NULL, NULL);
INSERT INTO `operationplus` VALUES (18, '68451681', '2020-05-24 04:00:00', '杨思琦', '二哈二手术', '二哈二', NULL, NULL);
INSERT INTO `operationplus` VALUES (19, '56134216', '2020-05-25 02:00:00', '唐玉浩', '金小五手术', '金小五', NULL, NULL);
INSERT INTO `operationplus` VALUES (20, '56181817', '2020-05-26 12:00:00', '王世英', '哈小六手术', '哈小六', NULL, NULL);
INSERT INTO `operationplus` VALUES (21, '51315138', '2020-05-26 05:00:00', '吕小布', '萨摩三手术', '萨摩三', NULL, NULL);

-- ----------------------------
-- Table structure for person_user
-- ----------------------------
DROP TABLE IF EXISTS `person_user`;
CREATE TABLE `person_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'user表主键',
  `user_username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'user表用户名',
  `user_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'user表密码',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'user表电话',
  `user_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'user表Email',
  `user_post` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'user表职位',
  `user_purview` int(0) NOT NULL COMMENT '权限',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person_user
-- ----------------------------
INSERT INTO `person_user` VALUES (1, 'zhuyu', '243ae1a02342aa598bea7cb9043c228c', '13734010087', '1766722033@qq.com', '科长', 3);
INSERT INTO `person_user` VALUES (2, 'admin', '243ae1a02342aa598bea7cb9043c228c', '18635594225', '905806985@qq.com', '科长', 3);

-- ----------------------------
-- Table structure for pets_user
-- ----------------------------
DROP TABLE IF EXISTS `pets_user`;
CREATE TABLE `pets_user`  (
  `pets_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '宠物id',
  `pets_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宠物编号',
  `pets_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宠物名字',
  `pets_gender` int(0) NOT NULL COMMENT '1为公 0为母',
  `pets_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '健康状况',
  `pets_datatime` date NOT NULL COMMENT '问诊日期',
  `pets_bed` int(0) NOT NULL COMMENT '是否住院',
  `pets_age` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pets_names` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`pets_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pets_user
-- ----------------------------
INSERT INTO `pets_user` VALUES (1, '138871', '金毛寻回犬', 1, '绝育手术', '2020-05-13', 0, '1岁', '多多');
INSERT INTO `pets_user` VALUES (2, '158427', '西伯利亚雪橇犬', 1, '绝育手术', '2020-05-28', 0, '9个月', '奥特曼');
INSERT INTO `pets_user` VALUES (3, '384917', '边境牧羊犬', 0, '频繁掉毛', '2020-05-05', 1, '3岁', '七天');
INSERT INTO `pets_user` VALUES (4, '236411', '阿拉斯加', 0, '腿被咬伤', '2020-05-18', 1, '2岁', '憨憨');
INSERT INTO `pets_user` VALUES (5, '268817', '德国牧羊犬', 1, '耳朵立不起来', '2020-05-03', 1, '4个月', '大宝');
INSERT INTO `pets_user` VALUES (6, '314716', '泰迪', 0, '乱叫被打伤', '2020-05-02', 0, '4岁', '巧克力');
INSERT INTO `pets_user` VALUES (7, '251799', '萨摩耶', 0, '泪痕严重', '2020-05-19', 0, '1岁半', '风扇');
INSERT INTO `pets_user` VALUES (8, '388417', '金毛寻回犬', 0, '怀孕待产', '2020-05-17', 1, '2岁', '年年');
INSERT INTO `pets_user` VALUES (9, '123884', '小唐', 1, '蛋蛋卡死', '2020-05-25', 0, '6', '法国斗牛犬');
INSERT INTO `pets_user` VALUES (10, '157884', '王世英', 1, '肛裂', '2020-05-25', 0, '六岁半', '中华田园犬');

SET FOREIGN_KEY_CHECKS = 1;
