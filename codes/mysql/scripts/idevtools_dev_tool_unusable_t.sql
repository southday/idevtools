-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: idevtools.cn    Database: idevtools_dev
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tool_unusable_t`
--

DROP TABLE IF EXISTS `tool_unusable_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tool_unusable_t` (
  `tool_id` int(11) NOT NULL AUTO_INCREMENT,
  `tool_name` varchar(45) DEFAULT NULL,
  `tool_version` varchar(45) DEFAULT NULL,
  `code_name` varchar(45) DEFAULT NULL COMMENT '产品代号',
  `description` varchar(200) DEFAULT NULL,
  `website` varchar(200) DEFAULT NULL,
  `download_links` varchar(5000) DEFAULT NULL,
  `usable` tinyint(1) DEFAULT '0' COMMENT '表示工具是否可用；由于要同步官网的更新信息，有些工具不再被支持；\\nusbale = 0 的记录存放在tool_unusable_t表中；\\nusbale = 1 的记录存放在tool_t表中；',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `collect_count` int(11) DEFAULT '0' COMMENT '收藏次数',
  `created_by` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` varchar(45) DEFAULT NULL,
  `last_update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `version_number` int(11) DEFAULT '1',
  PRIMARY KEY (`tool_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存放不可用的工具记录（usable=0），数据来源于：tool_t表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool_unusable_t`
--

LOCK TABLES `tool_unusable_t` WRITE;
/*!40000 ALTER TABLE `tool_unusable_t` DISABLE KEYS */;
INSERT INTO `tool_unusable_t` VALUES (4,'Eclipse C++','4.9','Habe','Package suited for development of Eclipse itself at Eclipse.org; based on the Eclipse Platform adding PDE, Git, Marketplace Client, source code and developer documentation.','https://www.eclipse.org/downloads/','{\"macos\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-macosx-cocoa-x86_64.dmg\", \"win32\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-win32.zip\", \"win64\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-win32-x86_64.zip\", \"linux32\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-linux-gtk.tar.gz\", \"linux64\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-linux-gtk-x86_64.tar.gz\"}',0,451,85,'李无敌','2019-03-07 19:52:05','李无敌','2019-03-07 19:52:05',1),(5,'Eclipse C++','4.9','Habe','Package suited for development of Eclipse itself at Eclipse.org; based on the Eclipse Platform adding PDE, Git, Marketplace Client, source code and developer documentation.','https://www.eclipse.org/downloads/','{\"macos\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-macosx-cocoa-x86_64.dmg\", \"win32\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-win32.zip\", \"win64\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-win32-x86_64.zip\", \"linux32\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-linux-gtk.tar.gz\", \"linux64\": \"http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-committers-photon-R-linux-gtk-x86_64.tar.gz\"}',0,451,85,'李无敌','2019-03-07 19:52:05','李无敌','2019-03-07 19:52:05',1);
/*!40000 ALTER TABLE `tool_unusable_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 10:02:09
