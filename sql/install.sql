-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: cms
-- ------------------------------------------------------
-- Server version	5.6.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminId` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `email` varchar(45) DEFAULT NULL COMMENT '邮件地址',
  `name` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `password` varchar(32) DEFAULT NULL COMMENT 'MD5(密码+email) ',
  `status` varchar(20) DEFAULT NULL COMMENT '状态:init(初始化), normal(正常),freeze(冻结)',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`adminId`),
  UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'cmstest@kee.com','cmstest111','4a3981e0b096eb5a273f07f2b82b7eab','init','2014-02-17 10:59:07'),(2,'chenyu@qq.com','chenyu123','chenyu123','init','2014-02-28 13:30:32'),(3,'123456@qq.com','chenyu','a5448feba86325c747c8ccd635d5d314','init','2014-02-28 13:33:42'),(4,'chengong@qq.com','chenyu','191868dcbc99c8c123740010f6f67d0e','init','2014-02-28 14:27:42');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `articleId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `firstFolderId` bigint(20) NOT NULL DEFAULT '1' COMMENT '一级目录Id',
  `secondFolderId` bigint(20) NOT NULL DEFAULT '0' COMMENT '二级目录Id',
  `thirdFolderId` bigint(20) NOT NULL DEFAULT '0' COMMENT '三级目录Id',
  `fourthFolderId` bigint(20) NOT NULL DEFAULT '0' COMMENT '四级目录Id',
  `adminId` bigint(20) DEFAULT '0' COMMENT '管理员ID',
  `name` varchar(200) DEFAULT '' COMMENT '文件名称',
  `title` varchar(200) DEFAULT '' COMMENT '标题',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '价格，报价',
  `realPrice` decimal(10,2) DEFAULT '0.00' COMMENT '实际价格',
  `copyCount` int(11) DEFAULT '0' COMMENT '文件被copy的次数，相当于是商品的库存',
  `content` text COMMENT '文件内容',
  `description` text COMMENT '文件的描述',
  `viewCount` int(11) DEFAULT '0' COMMENT '浏览数',
  `commentCount` int(11) DEFAULT '0' COMMENT '评论数',
  `owner` varchar(20) DEFAULT 'app' COMMENT ' 创建方式  （system）系统创建 ，（app）应用创建\n		\n',
  `type` varchar(20) DEFAULT 'article' COMMENT '文件类型：0 文章 1 照片 2 下载 3 商品',
  `status` varchar(20) DEFAULT 'init' COMMENT '状态：0 隐藏 1 显示',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `expireTime` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`articleId`,`thirdFolderId`,`fourthFolderId`,`secondFolderId`),
  KEY `idx_folder` (`status`,`firstFolderId`,`secondFolderId`,`thirdFolderId`,`fourthFolderId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,3,5,0,0,0,'美丽','',0.00,0.00,0,'<p>美丽<br/></p>','',0,0,'app','article','display','2014-02-28 11:28:27','2014-02-28 11:29:14','2014-02-28 11:28:27'),(2,1,0,0,0,0,'','',0.00,0.00,0,'','',0,0,'app','article','init','2014-02-28 13:30:03','2014-02-28 13:30:03','2014-02-28 13:30:03'),(3,3,7,0,0,0,'','',0.00,0.00,0,'','',0,0,'app','article','init','2014-02-28 13:42:17','2014-02-28 13:42:17','2014-02-28 13:42:17'),(4,3,7,0,0,0,'Java','',0.00,0.00,0,'Java 是一门很容易学的计算机语言。<br/><p><br/></p>','',0,0,'app','article','display','2014-02-28 13:57:04','2014-02-28 13:57:21','2014-02-28 13:57:04'),(5,1,0,0,0,0,'','',0.00,0.00,0,'','',0,0,'app','article','init','2014-02-28 14:17:37','2014-02-28 14:17:37','2014-02-28 14:17:37'),(6,12,13,0,0,0,'这是一篇新闻','',0.00,0.00,0,'<p>这是一篇新闻<br/></p>','',0,0,'app','article','display','2014-02-28 17:03:15','2014-02-28 17:03:30','2014-02-28 17:03:15');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `attachmentId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件Id',
  `kindId` bigint(20) DEFAULT '0' COMMENT '种类Id',
  `name` varchar(200) DEFAULT NULL COMMENT '名字',
  `path` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `description` varchar(500) DEFAULT NULL COMMENT '附件描述',
  `size` int(11) DEFAULT NULL COMMENT '附件大小',
  `type` varchar(45) DEFAULT NULL COMMENT '附件类型   相册（photo）  文件（ file）',
  `link` varchar(500) DEFAULT NULL COMMENT '链接',
  `kind` varchar(20) DEFAULT NULL COMMENT '种类 （folder）目录, （article）文章',
  `status` varchar(45) DEFAULT NULL COMMENT '状态  隐藏是嵌入文章的（hidden）,没有嵌入文章的（display）',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`attachmentId`),
  KEY `idx_kind` (`kind`,`kindId`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=ucs2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (4,2,'QQ图片20140213105017.jpg','/upload/2014/02/13/1392260770067.jpg','',61295,'photo','javascript:void(0);','article','display','2014-02-13 11:06:10'),(5,2,'QQ图片20140213110522.jpg','/upload/2014/02/13/1392260781991.jpg','',46566,'photo','javascript:void(0);','article','display','2014-02-13 11:06:22'),(6,2,'QQ图片20140213104934.jpg','/upload/2014/02/13/1392260792329.jpg','',66696,'photo','javascript:void(0);','article','display','2014-02-13 11:06:32'),(7,1,'145548_94d8.jpg','upload/2014/02/28/1393559430495.jpg','',13647,'photo','javascript:void(0);','article','display','2014-02-28 11:50:30'),(8,9,'2014_02_13_44b6a313275f4e08b52d9fcb9db531de.jpg','upload/2014/02/28/1393568904989.jpg','',4565,'photo','javascript:void(0);','folder','display','2014-02-28 14:28:25'),(10,9,'杨海波 - 师说.png','upload/2014/02/28/1393568983646.png','',196227,'photo','javascript:void(0);','folder','display','2014-02-28 14:29:44'),(11,9,'2014_02_13_44b6a313275f4e08b52d9fcb9db531de.jpg','upload/2014/02/28/1393569295965.jpg','',4565,'photo','javascript:void(0);','folder','display','2014-02-28 14:34:56'),(12,9,'course.jpg','upload/2014/02/28/1393569296024.jpg','',54449,'photo','javascript:void(0);','folder','display','2014-02-28 14:34:56'),(13,9,'2014_02_13_44b6a313275f4e08b52d9fcb9db531de.jpg','upload/2014/02/28/1393569688470.jpg','',4565,'photo','javascript:void(0);','folder','display','2014-02-28 14:41:28'),(14,9,'course.jpg','upload/2014/02/28/1393569688509.jpg','',54449,'photo','javascript:void(0);','folder','display','2014-02-28 14:41:29'),(15,9,'145548_94d8.jpg','upload/2014/02/28/1393569688586.jpg','',13647,'photo','javascript:void(0);','folder','display','2014-02-28 14:41:29'),(16,9,'杨海波 - 师说.png','upload/2014/02/28/1393569688617.png','',196227,'photo','javascript:void(0);','folder','display','2014-02-28 14:41:29'),(17,9,'1111-1.png','upload/2014/02/28/1393569688700.png','',423433,'photo','javascript:void(0);','folder','display','2014-02-28 14:41:29'),(18,9,'swdjz1.jpg','upload/2014/02/28/1393569688737.jpg','',947707,'photo','javascript:void(0);','folder','display','2014-02-28 14:41:29'),(19,11,'ad-2.jpg','upload/2014/02/28/1393580065580.jpg','',184305,'photo','javascript:void(0);','folder','display','2014-02-28 17:34:26'),(20,11,'ad-3.jpg','upload/2014/02/28/1393580065631.jpg','',117973,'photo','javascript:void(0);','folder','display','2014-02-28 17:34:26'),(21,11,'ad-1.jpg','upload/2014/02/28/1393580065670.jpg','',133064,'photo','javascript:void(0);','folder','display','2014-02-28 17:34:26');
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `fatherId` bigint(20) DEFAULT NULL COMMENT '父评论ID',
  `kindId` bigint(20) DEFAULT NULL COMMENT '种类Id',
  `kind` varchar(45) DEFAULT NULL COMMENT '评论类型   隐藏 （article） 显示（folder）\n		',
  `name` varchar(45) DEFAULT NULL COMMENT '评论者',
  `email` varchar(45) DEFAULT NULL COMMENT '评论者邮件地址',
  `url` varchar(200) DEFAULT NULL COMMENT '评论者网址',
  `phone` varchar(45) DEFAULT NULL COMMENT '评论者电话',
  `content` text COMMENT '内容',
  `ip` varchar(45) DEFAULT NULL COMMENT 'Ip',
  `status` varchar(20) DEFAULT NULL COMMENT '状态       隐藏 hidden 显示 display 垃圾trash',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`commentId`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,0,0,11,'folder','sdv','sdfv',NULL,'','dfbtgebergvergfv','localhost:8080','hidden','2014-02-21 14:26:31'),(2,0,0,11,'folder','efsfds','dfegregregr@qq.com',NULL,'','fovnonoenvolwlkcfnweoifbow','localhost:8080','hidden','2014-02-21 14:27:43'),(3,0,0,11,'folder','sdfwe','ewfdsf',NULL,'','sdfvgfrbvc','localhost:8080','hidden','2014-02-21 15:01:15'),(4,0,0,11,'folder','sdfsw','sdfwef',NULL,'','fdsbedfbr','localhost:8080','hidden','2014-02-21 15:02:06'),(5,0,0,11,'folder','sdv','asf',NULL,'','fdgbergbvervger','localhost:8080','hidden','2014-02-21 15:11:58'),(6,0,0,11,'folder','wegrg','dsvfdeve@s.com',NULL,'','sdvfr','localhost:8080','hidden','2014-02-21 15:13:09'),(7,0,0,4,'folder','杨海波','cms@kee.com','','','哇哈哈哈哈','127.0.0.1:8080','hidden','2014-03-01 20:46:17'),(8,0,0,4,'folder','啊哈哈','kee.com@mail.clm','','','ddasdfasdfadf','127.0.0.1:8080','hidden','2014-03-01 20:47:01');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `key` varchar(45) NOT NULL COMMENT 'Key',
  `value` varchar(45) DEFAULT NULL COMMENT '值',
  `description` text COMMENT '描述',
  `createTime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES ('sys_sitedesc','师者,传道授业解惑者也','网站描述','2012-08-08 00:00:00'),('sys_sitename','师说','网站名称','2012-08-08 00:00:00'),('sys_theme','cms','网站模板名称','2012-08-08 00:00:00');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `folderId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '目录ID',
  `fatherId` bigint(20) NOT NULL DEFAULT '0' COMMENT '父亲Id，用于构建目录树',
  `ename` varchar(45) NOT NULL COMMENT '英文名',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '中文名',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '路径',
  `content` text COMMENT '内容',
  `level` tinyint(4) DEFAULT '1' COMMENT '层级',
  `sort` tinyint(4) DEFAULT '0' COMMENT '排序',
  `count` int(11) DEFAULT '0' COMMENT '文件数',
  `rank` varchar(20) DEFAULT 'everyone' COMMENT '等级  任何人 everyone 登录用户 login,vip用户 vip 管理员admin',
  `type` varchar(45) DEFAULT 'folder' COMMENT '种类  页面page  列表list  目录folder  所有all',
  `status` varchar(20) DEFAULT 'hidden' COMMENT '状态：0 隐藏 1 现实',
  `owner` varchar(45) DEFAULT 'app' COMMENT '创建方式   系统创建  system ,  应用创建 app',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`folderId`),
  UNIQUE KEY `idx_ename` (`ename`),
  KEY `idx_status` (`fatherId`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='目录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (1,0,'default','默认','1','',1,0,0,'everyone','folder','hidden','app','2014-02-28 15:33:53',NULL),(2,0,'service','服务','2','',1,1,0,'everyone','folder','display','app','2014-02-12 14:42:17',NULL),(3,0,'blog','博客','3','',1,2,0,'everyone','folder','display','app','2014-02-12 14:43:52',NULL),(4,0,'contact','联系我们','3','',1,4,0,'everyone','folder','display','app','2014-02-27 17:34:33',NULL),(5,3,'life','生活','3#5','',2,1,0,'everyone','article','display','app','2014-02-28 11:21:16',NULL),(6,3,'work','工作','3#6','',2,1,0,'everyone','article','display','app','2014-02-28 11:21:31',NULL),(7,3,'java','Java','3#7','',2,1,0,'everyone','article','display','app','2014-02-28 11:21:58',NULL),(8,3,'movie','电影','3#8','',2,1,0,'everyone','article','display','app','2014-02-28 11:22:29',NULL),(9,3,'photo','相册','3#9','',2,1,0,'everyone','photo','display','app','2014-02-28 13:56:45',NULL),(11,1,'top','首页头图','1#11','',2,1,0,'everyone','photo','hidden','app','2014-02-28 15:38:03',NULL),(12,0,'news','新闻','12','',1,3,0,'everyone','folder','display','app','2014-02-28 15:56:06',NULL),(13,12,'corp','公司新闻','12#13','',2,1,0,'everyone','article','display','app','2014-02-28 15:57:45',NULL),(14,12,'industry','行业新闻','12#14','',2,1,0,'everyone','article','display','app','2014-02-28 15:58:12',NULL);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `openId` bigint(20) DEFAULT NULL COMMENT '公共用户ID，只有是师说，QQ，微博等其它网站登录时才有。',
  `type` varchar(20) DEFAULT NULL COMMENT '帐号类型：0 本站 1 师说 2 QQ 3 微博',
  `name` varchar(45) DEFAULT NULL COMMENT '用户名',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-02 19:28:37
