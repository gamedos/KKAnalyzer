/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2017-02-03 20:55:45
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tbgenre`
-- ----------------------------
DROP TABLE IF EXISTS `tbgenre`;
CREATE TABLE `tbgenre` (
  `gid` int(11) NOT NULL DEFAULT '0' COMMENT '分类编号',
  `gtitle` varchar(512) NOT NULL COMMENT 'genre分类标签',
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tbgenrehistory`
-- ----------------------------
DROP TABLE IF EXISTS `tbgenrehistory`;
CREATE TABLE `tbgenrehistory` (
  `hisid` varchar(32) NOT NULL COMMENT 'id-timeYear-timeMonth-timeday每个漫画每天记录一次',
  `gid` int(11) NOT NULL DEFAULT '0' COMMENT '分类编号',
  `topicCount` int(11) NOT NULL DEFAULT '0' COMMENT '漫画数量',
  `likesCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '赞次数',
  `hotCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '热度',
  `commentCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
  `timeYear` int(11) NOT NULL DEFAULT '2016' COMMENT '年',
  `timeMonth` int(11) NOT NULL DEFAULT '12' COMMENT '月',
  `timeDay` int(11) NOT NULL DEFAULT '1' COMMENT '日',
  `timeDate` varchar(8) NOT NULL DEFAULT '20161201',
  `timetime` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`hisid`),
  KEY `year` (`timeYear`) USING BTREE,
  KEY `month` (`timeMonth`) USING BTREE,
  KEY `day` (`timeDay`) USING BTREE,
  KEY `gid` (`gid`) USING BTREE,
  KEY `timeDate` (`timeDate`),
  KEY `timetime` (`timetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for `tbsitehistory`
-- ----------------------------
DROP TABLE IF EXISTS `tbsitehistory`;
CREATE TABLE `tbsitehistory` (
  `hisid` int(11) NOT NULL COMMENT 'yyyymmdd',
  `topicCount` int(11) NOT NULL DEFAULT '0' COMMENT '当前漫画数量',
  `likesCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '赞次数',
  `hotCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '热度',
  `commentCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
  `likesCountToday` bigint(20) NOT NULL DEFAULT '0' COMMENT '赞次数',
  `hotCountToday` bigint(20) NOT NULL DEFAULT '0' COMMENT '热度',
  `commentCountToday` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
  `timeYear` int(11) NOT NULL DEFAULT '2016' COMMENT '年',
  `timeMonth` int(11) NOT NULL DEFAULT '12' COMMENT '月',
  `timeDay` int(11) NOT NULL DEFAULT '1' COMMENT '日',
  `timeDate` varchar(8) NOT NULL DEFAULT '20161201',
  `timetime` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`hisid`),
  KEY `year` (`timeYear`) USING BTREE,
  KEY `month` (`timeMonth`) USING BTREE,
  KEY `day` (`timeDay`) USING BTREE,
  KEY `timeDate` (`timeDate`),
  KEY `timetime` (`timetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for `tbtopichistory`
-- ----------------------------
DROP TABLE IF EXISTS `tbtopichistory`;
CREATE TABLE `tbtopichistory` (
  `hisid` varchar(32) NOT NULL COMMENT 'id-timeYear-timeMonth-timeday每个漫画每天记录一次',
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '漫画编号',
  `gid` int(11) NOT NULL DEFAULT '0',
  `likesCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '赞次数',
  `hotCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '热度',
  `commentCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
  `likesCountToday` bigint(20) NOT NULL DEFAULT '0',
  `hotCountToday` bigint(20) NOT NULL DEFAULT '0',
  `commentCountToday` bigint(20) NOT NULL DEFAULT '0',
  `timeYear` int(11) NOT NULL DEFAULT '2016' COMMENT '年',
  `timeMonth` int(11) NOT NULL DEFAULT '12' COMMENT '月',
  `timeDay` int(11) NOT NULL DEFAULT '1' COMMENT '日',
  `timeDate` varchar(8) NOT NULL DEFAULT '00000000',
  `timetime` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`hisid`),
  KEY `topicid` (`id`),
  KEY `year` (`timeYear`),
  KEY `month` (`timeMonth`),
  KEY `day` (`timeDay`),
  KEY `gid` (`gid`),
  KEY `timeDate` (`timeDate`),
  KEY `timetime` (`timetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tbtopics`
-- ----------------------------
DROP TABLE IF EXISTS `tbtopics`;
CREATE TABLE `tbtopics` (
  `id` int(11) NOT NULL DEFAULT '0',
  `labelId` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(1024) DEFAULT NULL,
  `gid` int(11) NOT NULL DEFAULT '0' COMMENT '分类编号',
  `description` text,
  `comicsCount` int(11) NOT NULL DEFAULT '0',
  `verticalImageUrl` varchar(1024) DEFAULT NULL,
  `createdAt` int(11) DEFAULT NULL,
  `updatedAt` int(11) DEFAULT NULL,
  `orderNum` int(11) NOT NULL DEFAULT '0',
  `authName` varchar(512) DEFAULT NULL,
  `updateDay` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `updateDay` (`updateDay`),
  KEY `gid` (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tbtopictags`
-- ----------------------------
DROP TABLE IF EXISTS `tbtopictags`;
CREATE TABLE `tbtopictags` (
  `topicid` int(11) NOT NULL DEFAULT '0',
  `tagid` int(11) NOT NULL DEFAULT '0',
  `hkey` varchar(32) NOT NULL,
  PRIMARY KEY (`hkey`),
  KEY `topicid` (`topicid`),
  KEY `tagid` (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tbwork`
-- ----------------------------
DROP TABLE IF EXISTS `tbwork`;
CREATE TABLE `tbwork` (
  `wid` int(11) NOT NULL,
  `wstate` int(11) NOT NULL DEFAULT '0',
  `windex` int(11) NOT NULL DEFAULT '0',
  `createtime` int(11) NOT NULL DEFAULT '0',
  `endtime` int(11) NOT NULL DEFAULT '0',
  `temp` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
