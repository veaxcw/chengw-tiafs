/*
Navicat MySQL Data Transfer

Source Server         : 本地学习
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : cw_tiafs

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-31 16:31:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(2000) DEFAULT '',
  `a_path` varchar(2000) DEFAULT '',
  `publish_date` varchar(2000) DEFAULT '',
  `publish_reader` varchar(2000) DEFAULT '',
  `upload_date` varchar(2000) DEFAULT '',
  `summary` varchar(2000) DEFAULT '',
  `usercode` varchar(2000) DEFAULT '',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'admin', '/report_servlet/upload/Article/219.86540028969625_commons-pool-1.6.jar', '2018-03-20', 'admin', '2018-03-14', '华师北门三楼幻维奇迹网咖', '1');

-- ----------------------------
-- Table structure for education
-- ----------------------------
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
  `edu_id` int(11) NOT NULL AUTO_INCREMENT,
  `edu_name` varchar(2000) DEFAULT '',
  `edu_date` varchar(2000) DEFAULT '',
  `edu_student` varchar(2000) DEFAULT '',
  `edu_time` varchar(2000) DEFAULT '',
  `work_load` varchar(2000) DEFAULT '',
  `mass` varchar(2000) DEFAULT '',
  `edu_score` varchar(2000) DEFAULT '',
  `user_code` varchar(2000) DEFAULT '',
  PRIMARY KEY (`edu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of education
-- ----------------------------
INSERT INTO `education` VALUES ('1', 'admin', '2018-3-15', '20', '4', '4', '4', '100', '1');

-- ----------------------------
-- Table structure for prize
-- ----------------------------
DROP TABLE IF EXISTS `prize`;
CREATE TABLE `prize` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(2000) DEFAULT '',
  `p_date` varchar(2000) DEFAULT '',
  `p_path` varchar(2000) DEFAULT '',
  `diploma` varchar(2000) DEFAULT '',
  `p_depart` varchar(2000) DEFAULT '',
  `upload_date` varchar(2000) DEFAULT '',
  `summary` varchar(2000) DEFAULT '',
  `usercode` varchar(2000) DEFAULT '',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prize
-- ----------------------------
INSERT INTO `prize` VALUES ('1', 'admin', '2018-03-21', '/report_servlet/upload/prize/646.1209755561002_commons-pool-1.6.jar', '华师北门三楼幻维奇迹网咖', 'admin', '2018-03-22', '4', '1');

-- ----------------------------
-- Table structure for research
-- ----------------------------
DROP TABLE IF EXISTS `research`;
CREATE TABLE `research` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(2000) DEFAULT '',
  `r_path` varchar(2000) DEFAULT '',
  `publish_date` varchar(2000) DEFAULT '',
  `publish_reader` varchar(2000) DEFAULT '',
  `upload_date` varchar(2000) DEFAULT '',
  `summary` varchar(2000) DEFAULT '',
  `funds` varchar(2000) DEFAULT '',
  `usercode` varchar(2000) DEFAULT '',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of research
-- ----------------------------
INSERT INTO `research` VALUES ('1', 'admin', '/report_servlet/upload/research/852.8939711189489_commons-pool-1.6.jar', '2018-03-22', 'admin', '2018-03-22', '华师北门三楼幻维奇迹网咖', '10000', '1');

-- ----------------------------
-- Table structure for research_result
-- ----------------------------
DROP TABLE IF EXISTS `research_result`;
CREATE TABLE `research_result` (
  `rr_id` int(11) NOT NULL AUTO_INCREMENT,
  `rr_name` varchar(2000) DEFAULT '',
  `rr_path` varchar(2000) DEFAULT '',
  `publish_date` varchar(2000) DEFAULT '',
  `publish_reader` varchar(2000) DEFAULT '',
  `upload_date` varchar(2000) DEFAULT '',
  `summary` varchar(2000) DEFAULT '',
  `usercode` varchar(2000) DEFAULT '',
  PRIMARY KEY (`rr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of research_result
-- ----------------------------

-- ----------------------------
-- Table structure for teacherbean
-- ----------------------------
DROP TABLE IF EXISTS `teacherbean`;
CREATE TABLE `teacherbean` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `usercode` varchar(2000) DEFAULT '',
  `username` varchar(2000) DEFAULT '',
  `sex` varchar(2000) DEFAULT '',
  `age` int(11) DEFAULT '0',
  `depart` varchar(2000) DEFAULT '',
  `graduate_school` varchar(2000) DEFAULT '',
  `curriculum` varchar(2000) DEFAULT '',
  `password` varchar(2000) DEFAULT '',
  `role_id` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacherbean
-- ----------------------------
INSERT INTO `teacherbean` VALUES ('1', '1', 'admin', '女', '5', 'java工程师', 'huanweiqiji.com', '本科', '111111', '0', '1');
