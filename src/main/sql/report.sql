/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : report

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2018-03-21 16:41:12
*/
CREATE DATABASE report CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=0;

use report;

-- ----------------------------
-- Table structure for `Article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `a_id` INT(11) NOT NULL AUTO_INCREMENT,
  `a_name` VARCHAR(2000) DEFAULT '',
  `a_path` VARCHAR(2000) DEFAULT '',
  `publish_date` VARCHAR(2000) DEFAULT '',
  `publish_reader` VARCHAR(2000) DEFAULT '',
  `upload_date` VARCHAR(2000) DEFAULT '',
  `summary` VARCHAR(2000) DEFAULT '',
  `usercode` VARCHAR(2000) DEFAULT '',
  PRIMARY KEY (`aid`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'admin', '/report_servlet/upload/Article/219.86540028969625_commons-pool-1.6.jar', '2018-03-20', 'admin', '2018-03-14', '华师北门三楼幻维奇迹网咖', '1');

-- ----------------------------
-- Table structure for `education`
-- ----------------------------
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
  `edu_id` INT(11) NOT NULL AUTO_INCREMENT,
  `edu_name` VARCHAR(2000) DEFAULT '',
  `edu_date` VARCHAR(2000) DEFAULT '',
  `edu_student` VARCHAR(2000) DEFAULT '',
  `edu_time` VARCHAR(2000) DEFAULT '',
  `work_load` VARCHAR(2000) DEFAULT '',
  `mass` VARCHAR(2000) DEFAULT '',
  `edu_score` VARCHAR(2000) DEFAULT '',
  `user_code` VARCHAR(2000) DEFAULT '',
  PRIMARY KEY (`eduid`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of education
-- ----------------------------
INSERT INTO `education` VALUES ('1', 'admin', '2018-3-15', '20', '4', '4', '4', '100', '1');

-- ----------------------------
-- Table structure for `prize`
-- ----------------------------
DROP TABLE IF EXISTS `prize`;
CREATE TABLE `prize` (
  `p_id` INT(11) NOT NULL AUTO_INCREMENT,
  `p_name` VARCHAR(2000) DEFAULT '',
  `p_date` VARCHAR(2000) DEFAULT '',
  `p_path` VARCHAR(2000) DEFAULT '',
  `diploma` VARCHAR(2000) DEFAULT '',
  `p_depart` VARCHAR(2000) DEFAULT '',
  `upload_date` VARCHAR(2000) DEFAULT '',
  `summary` VARCHAR(2000) DEFAULT '',
  `usercode` VARCHAR(2000) DEFAULT '',
  PRIMARY KEY (`pid`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prize
-- ----------------------------
INSERT INTO `prize` VALUES ('1', 'admin', '2018-03-21', '/report_servlet/upload/prize/646.1209755561002_commons-pool-1.6.jar', '华师北门三楼幻维奇迹网咖', 'admin', '2018-03-22', '4', '1');

-- ----------------------------
-- Table structure for `research`
-- ----------------------------
DROP TABLE IF EXISTS `research`;
CREATE TABLE `research` (
  `r_id` INT(11) NOT NULL AUTO_INCREMENT,
  `r_name` VARCHAR(2000) DEFAULT '',
  `r_path` VARCHAR(2000) DEFAULT '',
  `publish_date` VARCHAR(2000) DEFAULT '',
  `publish_reader` VARCHAR(2000) DEFAULT '',
  `upload_date` VARCHAR(2000) DEFAULT '',
  `summary` VARCHAR(2000) DEFAULT '',
  `funds` VARCHAR(2000) DEFAULT '',
  `usercode` VARCHAR(2000) DEFAULT '',
  PRIMARY KEY (`rid`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of research
-- ----------------------------
INSERT INTO `research` VALUES ('1', 'admin', '/report_servlet/upload/research/852.8939711189489_commons-pool-1.6.jar', '2018-03-22', 'admin', '2018-03-22', '华师北门三楼幻维奇迹网咖', '10000', '1');

-- ----------------------------
-- Table structure for `researchresult`
-- ----------------------------
DROP TABLE IF EXISTS `researchresult`;
CREATE TABLE `research_result` (
  `r_rid` INT(11) NOT NULL AUTO_INCREMENT,
  `rr_name` VARCHAR(2000) DEFAULT '',
  `rr_path` VARCHAR(2000) DEFAULT '',
  `publish_date` VARCHAR(2000) DEFAULT '',
  `publish_reader` VARCHAR(2000) DEFAULT '',
  `upload_date` VARCHAR(2000) DEFAULT '',
  `summary` VARCHAR(2000) DEFAULT '',
  `usercode` VARCHAR(2000) DEFAULT '',

  PRIMARY KEY (`rrid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of researchresult
-- ----------------------------

-- ----------------------------
-- Table structure for `teacherBean`
-- ----------------------------
DROP TABLE IF EXISTS `teacherBean`;
CREATE TABLE `teacherBean` (
  `teacher_id` INT(11) NOT NULL AUTO_INCREMENT,
  `usercode` VARCHAR(2000) DEFAULT '',
  `username` VARCHAR(2000) DEFAULT '',
  `sex` VARCHAR(2000) DEFAULT '',
  `age` INT(11) DEFAULT '0',
  `depart` VARCHAR(2000) DEFAULT '',
  `graduate_school` VARCHAR(2000) DEFAULT '',
  `curriculum` VARCHAR(2000) DEFAULT '',
  `password` VARCHAR(2000) DEFAULT '',
  `role_id` INT(11) DEFAULT '0',
  `status` INT(11) DEFAULT '0',
  PRIMARY KEY (`teacherid`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacherBean`Article`
-- ----------------------------
INSERT INTO `teacherBean` VALUES ('1', '1', 'admin', '女', '5', 'java工程师', 'huanweiqiji.com', '本科', '111111', '0', '1');
