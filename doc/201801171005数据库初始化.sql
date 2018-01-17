/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.10-log : Database - superboot
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`superboot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `superboot`;

/*Table structure for table `t_action` */

DROP TABLE IF EXISTS `t_action`;

CREATE TABLE `t_action` (
  `actionid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '接口编号',
  `actionname` varchar(32) NOT NULL COMMENT '接口名',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`actionid`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `t_action` */

insert  into `t_action`(`actionid`,`actionname`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,'/addAction','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(2,'/deleteActionByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(3,'/editActionByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(4,'/pageAction','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(5,'/getActionByActionId','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(6,'/addPermissionResourceByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(7,'/deletePermissionResourceByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(8,'/editUserkeyByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(9,'/pageUserkey','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(10,'/getUserkeyByUserkeyId','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(11,'/addAuthitemByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(12,'/editAuthitemByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(13,'/pageAuthitem','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(14,'/getAuthitemByAuthitemId','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(15,'/deleteAuthitemByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(16,'/addAuthitemmapByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(17,'/deleteAuthitemmapByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(18,'/addUserAuthitemByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(19,'/deleteUserAuthitemByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(20,'/addMenuByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(21,'/deleteMenuByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(22,'/editMenuByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(23,'/pageMenu','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(24,'/getMenuByMenuId','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(25,'/addMenuItemByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(26,'/deleteMenuItemByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(27,'/editMenuItemByList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(28,'/pageMenuItem','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(29,'/getMenuItemByMenuItemId','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(30,'/getCurrentUserMenuItemList','2017-11-15 09:49:40',1,'2017-12-01 11:07:17',NULL,0,0,0),(33,'/login','2017-11-16 10:56:52',1,'2017-12-01 11:07:17',NULL,0,0,0),(35,'/isLogin','2017-11-20 15:03:41',1,'2017-12-01 11:07:17',NULL,0,0,1),(36,'/logout','2017-11-20 15:17:15',1,'2017-12-01 11:07:17',NULL,0,0,1),(39,'/getActionPermissionList','2017-11-27 15:44:25',1,'2017-12-01 11:07:17',NULL,0,0,0),(40,'/editAction','2017-11-27 15:47:17',1,'2017-12-01 11:07:17',NULL,0,0,0),(41,'/getUserRoleList','2017-11-27 16:00:42',1,'2017-12-01 11:07:17',NULL,0,0,0),(42,'/pageRole','2017-11-27 16:10:04',1,'2017-12-01 11:07:17',NULL,0,0,0),(43,'/addRole','2017-11-27 16:20:20',1,'2017-12-01 11:07:17',NULL,0,0,0),(44,'/getRolePermissionList','2017-11-27 16:22:12',1,'2017-12-01 11:07:17',NULL,0,0,0),(45,'/editRole','2017-11-27 16:25:43',1,'2017-12-01 11:07:17',NULL,0,0,0),(46,'/pagePermission','2017-11-27 16:29:07',1,'2017-12-01 11:07:17',NULL,0,0,0),(47,'/getMenuMenuItemList','2017-11-27 17:04:58',1,'2017-12-01 11:07:17',NULL,0,0,0);

/*Table structure for table `t_authitem` */

DROP TABLE IF EXISTS `t_authitem`;

CREATE TABLE `t_authitem` (
  `authitemid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '权限条目编号',
  `authitemname` varchar(16) NOT NULL COMMENT '权限条目名',
  `authitemtype` tinyint(1) NOT NULL COMMENT '权限类型 0-角色, 1-权限',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`authitemid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_authitem` */

insert  into `t_authitem`(`authitemid`,`authitemname`,`authitemtype`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,'超级管理员',0,'2017-11-15 09:49:34',1,'2017-11-15 09:49:34',NULL,0,0,0),(2,'系统',0,'2017-11-15 09:49:34',1,'2017-11-15 09:49:34',NULL,0,0,0),(3,'登录后',1,'2017-11-15 09:49:34',1,'2017-11-15 09:49:34',NULL,0,0,0),(4,'接口管理',1,'2017-11-15 09:49:34',1,'2017-11-15 09:49:34',NULL,0,0,0),(5,'行为管理',1,'2017-11-15 09:49:34',1,'2017-11-15 09:49:34',NULL,0,0,0),(6,'角色管理',1,'2017-11-15 09:49:34',1,'2017-11-15 09:49:34',NULL,0,0,0),(7,'用户登录凭证管理',1,'2017-11-15 09:49:34',1,'2017-11-15 09:49:34',NULL,0,0,0),(8,'菜单管理',1,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0);

/*Table structure for table `t_authitemmap` */

DROP TABLE IF EXISTS `t_authitemmap`;

CREATE TABLE `t_authitemmap` (
  `authitemmapid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '权限条目父子映射编号',
  `parentid` bigint(16) NOT NULL COMMENT '父权限条目编号',
  `childid` bigint(16) NOT NULL COMMENT '子权限条目编号',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`authitemmapid`),
  KEY `t_authitemmap_ibfk_1` (`parentid`),
  KEY `t_authitemmap_ibfk_2` (`childid`),
  CONSTRAINT `t_authitemmap_ibfk_1` FOREIGN KEY (`parentid`) REFERENCES `t_authitem` (`authitemid`),
  CONSTRAINT `t_authitemmap_ibfk_2` FOREIGN KEY (`childid`) REFERENCES `t_authitem` (`authitemid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_authitemmap` */

insert  into `t_authitemmap`(`authitemmapid`,`parentid`,`childid`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,2,3,'2017-11-15 09:49:35',1,'2017-11-15 09:49:35',NULL,0,0,0),(2,1,4,'2017-11-15 09:49:35',1,'2017-11-15 09:49:35',NULL,0,0,0),(3,1,5,'2017-11-15 09:49:35',1,'2017-11-15 09:49:35',NULL,0,0,0),(4,1,6,'2017-11-15 09:49:35',1,'2017-11-15 09:49:35',NULL,0,0,0),(5,1,7,'2017-11-15 09:49:35',1,'2017-11-15 09:49:35',NULL,0,0,0),(6,1,8,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0);

/*Table structure for table `t_permissionresourcemap` */

DROP TABLE IF EXISTS `t_permissionresourcemap`;

CREATE TABLE `t_permissionresourcemap` (
  `permissionresourcemapid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '行为资源映射编号',
  `permissionid` bigint(16) NOT NULL COMMENT '行为编号',
  `resourceid` bigint(16) NOT NULL COMMENT '资源编号',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`permissionresourcemapid`),
  KEY `t_permissionresourcemap_ibfk_1` (`permissionid`),
  KEY `t_permissionresourcemap_ibfk_2` (`resourceid`),
  CONSTRAINT `t_permissionresourcemap_ibfk_1` FOREIGN KEY (`permissionid`) REFERENCES `t_authitem` (`authitemid`),
  CONSTRAINT `t_permissionresourcemap_ibfk_2` FOREIGN KEY (`resourceid`) REFERENCES `t_resource` (`resourceid`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

/*Data for the table `t_permissionresourcemap` */

insert  into `t_permissionresourcemap`(`permissionresourcemapid`,`permissionid`,`resourceid`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,4,1,'2017-11-15 09:49:40',1,'2017-11-16 10:41:02',NULL,0,0,0),(2,4,2,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(3,4,3,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(4,4,4,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(5,4,5,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(6,5,6,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(7,5,7,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(8,7,8,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(9,7,9,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(10,7,10,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(11,5,11,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(12,6,11,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(13,5,12,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(14,6,12,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(15,5,13,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(16,6,13,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(17,5,14,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(18,6,14,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(19,5,15,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(20,6,15,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(21,6,16,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(22,6,17,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(23,5,18,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(24,6,18,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(25,5,19,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(26,6,19,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(27,8,20,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(28,8,21,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(29,8,22,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(30,8,23,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(31,8,24,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(32,8,25,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(33,8,26,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(34,8,27,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(35,8,28,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(36,8,29,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(37,8,30,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(38,6,31,'2017-11-15 09:49:42',1,'2017-11-15 09:49:42',NULL,0,0,0),(39,5,31,'2017-11-15 09:49:42',1,'2017-11-15 09:49:42',NULL,0,0,0),(40,7,31,'2017-11-15 09:49:42',1,'2017-11-15 09:49:42',NULL,0,0,0),(41,4,32,'2017-11-15 09:49:42',1,'2017-11-15 09:49:42',NULL,0,0,0),(42,8,32,'2017-11-15 09:49:42',1,'2017-11-15 09:49:42',NULL,0,0,0),(43,7,33,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(44,7,34,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(45,6,35,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(46,6,36,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(47,6,37,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(48,5,38,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(49,5,39,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(50,5,40,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(51,4,41,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(52,4,42,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(53,4,43,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(54,8,44,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(55,8,45,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(56,8,46,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(57,8,47,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(58,8,48,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(59,8,49,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(64,4,58,'2017-11-27 15:44:25',1,'2017-11-27 15:44:25',NULL,0,0,0),(65,4,59,'2017-11-27 15:47:17',1,'2017-11-27 15:47:17',NULL,0,0,0),(66,7,60,'2017-11-27 16:00:42',1,'2017-11-27 16:00:42',NULL,0,0,0),(67,6,61,'2017-11-27 16:10:04',1,'2017-11-27 16:10:04',NULL,0,0,0),(68,6,62,'2017-11-27 16:20:20',1,'2017-11-27 16:20:20',NULL,0,0,0),(69,6,63,'2017-11-27 16:22:12',1,'2017-11-27 16:22:12',NULL,0,0,0),(70,6,64,'2017-11-27 16:25:43',1,'2017-11-27 16:25:43',NULL,0,0,0),(71,5,65,'2017-11-27 16:29:16',1,'2017-11-27 16:29:16',NULL,0,0,0),(72,8,66,'2017-11-27 17:04:58',1,'2017-11-27 17:04:58',NULL,0,0,0);

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `resourceid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `resourcetypeid` bigint(16) NOT NULL COMMENT '资源类型',
  `realid` bigint(16) NOT NULL COMMENT '资源实际实体编号',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`resourceid`),
  KEY `t_resource_ibfk_1` (`resourcetypeid`),
  CONSTRAINT `t_resource_ibfk_1` FOREIGN KEY (`resourcetypeid`) REFERENCES `t_resourcetype` (`resourcetypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

/*Data for the table `t_resource` */

insert  into `t_resource`(`resourceid`,`resourcetypeid`,`realid`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,1,1,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(2,1,2,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(3,1,3,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(4,1,4,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(5,1,5,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(6,1,6,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(7,1,7,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(8,1,8,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(9,1,9,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(10,1,10,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(11,1,11,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(12,1,12,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(13,1,13,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(14,1,14,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(15,1,15,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(16,1,16,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(17,1,17,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(18,1,18,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(19,1,19,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(20,1,20,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(21,1,21,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(22,1,22,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(23,1,23,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(24,1,24,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(25,1,25,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(26,1,26,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(27,1,27,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(28,1,28,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(29,1,29,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(30,1,30,'2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0),(31,2,1,'2017-11-15 09:49:42',1,'2017-11-15 09:49:42',NULL,0,0,0),(32,2,2,'2017-11-15 09:49:42',1,'2017-11-15 09:49:42',NULL,0,0,0),(33,2,3,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(34,2,4,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(35,2,5,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(36,2,6,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(37,2,7,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(38,2,8,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(39,2,9,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(40,2,10,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(41,2,11,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(42,2,12,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(43,2,13,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(44,2,14,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(45,2,15,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(46,2,16,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(47,2,17,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(48,2,18,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(49,2,19,'2017-11-15 09:56:46',1,'2017-11-15 09:56:46',NULL,0,0,0),(52,1,33,'2017-11-16 10:57:34',1,'2017-11-16 10:57:39',NULL,0,0,1),(53,1,34,'2017-11-16 15:18:25',1,'2017-11-16 15:18:25',1,1,0,1),(54,1,35,'2017-11-20 15:04:19',1,'2017-11-20 15:04:20',NULL,0,0,1),(55,1,36,'2017-11-20 15:17:28',1,'2017-11-20 15:17:28',NULL,0,0,1),(58,1,39,'2017-11-27 15:44:25',1,'2017-11-27 15:44:25',NULL,0,0,0),(59,1,40,'2017-11-27 15:47:17',1,'2017-11-27 15:47:17',NULL,0,0,0),(60,1,41,'2017-11-27 16:00:42',1,'2017-11-27 16:00:42',NULL,0,0,0),(61,1,42,'2017-11-27 16:10:04',1,'2017-11-27 16:10:04',NULL,0,0,0),(62,1,43,'2017-11-27 16:20:20',1,'2017-11-27 16:20:20',NULL,0,0,0),(63,1,44,'2017-11-27 16:22:12',1,'2017-11-27 16:22:12',NULL,0,0,0),(64,1,45,'2017-11-27 16:25:43',1,'2017-11-27 16:25:43',NULL,0,0,0),(65,1,46,'2017-11-27 16:29:07',1,'2017-11-27 16:29:07',NULL,0,0,0),(66,1,47,'2017-11-27 17:04:58',1,'2017-11-27 17:04:58',NULL,0,0,0);

/*Table structure for table `t_resourcetype` */

DROP TABLE IF EXISTS `t_resourcetype`;

CREATE TABLE `t_resourcetype` (
  `resourcetypeid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '资源类型编号',
  `resourcetypename` varchar(32) NOT NULL COMMENT '资源类型名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`resourcetypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_resourcetype` */

insert  into `t_resourcetype`(`resourcetypeid`,`resourcetypename`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,'action','2017-11-15 09:49:37',1,'2017-11-15 09:49:37',NULL,0,0,0),(2,'menuitem','2017-11-15 09:49:40',1,'2017-11-15 09:49:40',NULL,0,0,0);

/*Table structure for table `t_systemconfig` */

DROP TABLE IF EXISTS `t_systemconfig`;

CREATE TABLE `t_systemconfig` (
  `systemconfigid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '系统配置编号',
  `configname` varchar(32) NOT NULL COMMENT '配置项',
  `configvalue` varchar(215) DEFAULT NULL COMMENT '配置值',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`systemconfigid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_systemconfig` */

insert  into `t_systemconfig`(`systemconfigid`,`configname`,`configvalue`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,'realm','com.wteamfly.superW.security.realm.ActionRealm','2017-11-15 09:49:32',1,'2017-11-15 09:49:32',NULL,0,0,0),(2,'realm','com.wteamfly.superW.security.realm.LoggingRealm','2017-11-15 09:49:32',1,'2017-11-15 09:49:32',NULL,0,0,0);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userid`,`username`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,'Sysadmin','2017-11-15 09:49:31',1,'2017-11-15 09:49:31',NULL,0,0,0),(2,'test','2017-11-27 16:04:44',1,'2017-11-27 16:04:45',NULL,0,0,1);

/*Table structure for table `t_userauthitemmap` */

DROP TABLE IF EXISTS `t_userauthitemmap`;

CREATE TABLE `t_userauthitemmap` (
  `userauthitemmapid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '用户权限条目映射编号',
  `userid` bigint(16) NOT NULL COMMENT '用户编号',
  `authitemid` bigint(16) NOT NULL COMMENT '权限条目编号',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`userauthitemmapid`),
  KEY `t_userauthitemmap_ibfk_1` (`userid`),
  KEY `t_userauthitemmap_ibfk_2` (`authitemid`),
  CONSTRAINT `t_userauthitemmap_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`),
  CONSTRAINT `t_userauthitemmap_ibfk_2` FOREIGN KEY (`authitemid`) REFERENCES `t_authitem` (`authitemid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_userauthitemmap` */

insert  into `t_userauthitemmap`(`userauthitemmapid`,`userid`,`authitemid`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,1,1,'2017-11-15 09:49:36',1,'2017-11-15 09:49:36',NULL,0,0,0);

/*Table structure for table `t_userkey` */

DROP TABLE IF EXISTS `t_userkey`;

CREATE TABLE `t_userkey` (
  `userkeyid` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '用户验证信息编号',
  `userid` bigint(16) NOT NULL COMMENT '用户编号',
  `logintype` tinyint(4) NOT NULL COMMENT '登录类型 0-用户名',
  `loginmsg` varchar(64) DEFAULT NULL COMMENT '用户登录名',
  `credential` char(32) NOT NULL COMMENT '用户登录凭证',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorid` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建人',
  `editetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `editorid` bigint(16) DEFAULT NULL COMMENT '修改人',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `islockup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结',
  `version` bigint(32) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`userkeyid`),
  UNIQUE KEY `loginmsg` (`loginmsg`),
  KEY `t_userkey_ibfk_1` (`userid`),
  CONSTRAINT `t_userkey_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_userkey` */

insert  into `t_userkey`(`userkeyid`,`userid`,`logintype`,`loginmsg`,`credential`,`createtime`,`creatorid`,`editetime`,`editorid`,`isdelete`,`islockup`,`version`) values (1,1,0,'Sysadmin','e10adc3949ba59abbe56e057f20f883e','2017-11-15 09:49:33',1,'2017-11-15 09:49:33',NULL,0,0,0),(2,2,0,'test2','e10adc3949ba59abbe56e057f20f883e','2017-11-27 16:05:19',1,'2017-11-27 16:05:23',NULL,0,0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
