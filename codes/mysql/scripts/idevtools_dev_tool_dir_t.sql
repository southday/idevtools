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
-- Table structure for table `tool_dir_t`
--

DROP TABLE IF EXISTS `tool_dir_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tool_dir_t` (
  `dir_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_dir_id` int(11) DEFAULT NULL COMMENT '父目录ID',
  `dir_name` varchar(45) DEFAULT NULL,
  `tool_id` int(11) DEFAULT NULL,
  `tool_name` varchar(45) DEFAULT NULL COMMENT '允许部分冗余(tool_name)',
  `file_type` varchar(20) DEFAULT NULL COMMENT '文件类型：目录（DIR）或工具（TOOL）',
  `website` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` varchar(45) DEFAULT NULL,
  `last_update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `version_number` int(11) DEFAULT '1',
  PRIMARY KEY (`dir_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工具目录，一个工具只能存在于一个目录下面，一个目录下可能包含子目录和工具';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool_dir_t`
--

LOCK TABLES `tool_dir_t` WRITE;
/*!40000 ALTER TABLE `tool_dir_t` DISABLE KEYS */;
INSERT INTO `tool_dir_t` VALUES (1,0,'默认目录',NULL,NULL,'DIR',NULL,NULL,'southday','2019-03-07 20:00:09',NULL,'2019-03-07 20:00:09',1),(2,0,'E',NULL,NULL,'DIR',NULL,NULL,'southday','2019-03-07 20:00:09',NULL,'2019-03-07 20:00:09',1),(3,16,'JDK',NULL,NULL,'DIR','https://www.oracle.com/technetwork/java/javase/downloads/index.html','Java Development Kit','southday','2019-03-07 20:00:09',NULL,'2019-03-07 20:00:09',1),(4,3,NULL,1,'JDK 8','TOOL',NULL,NULL,'southday','2019-03-07 20:00:09',NULL,'2019-03-07 20:00:09',1),(5,2,'Eclipse',NULL,NULL,'DIR','https://www.eclipse.org/','The Eclipse Foundation provides our global community of individuals and organizations with a mature, scalable and commercially-friendly environment for open source software collaboration and innovation.','southday','2019-03-07 20:00:09',NULL,'2019-03-07 20:00:09',1),(6,5,'Eclipse J2EE',NULL,NULL,'DIR','https://www.eclipse.org/downloads/','Get Eclipse IDE 2019‑03 Install your favorite desktop IDE packages.','southday','2019-03-07 20:00:09',NULL,'2019-03-07 20:00:09',1),(7,6,NULL,2,'Eclipse J2EE','TOOL',NULL,NULL,'southday','2019-03-07 20:00:09',NULL,'2019-03-07 20:00:09',1),(8,5,NULL,3,'Eclipse C++','TOOL',NULL,NULL,'southday','2019-03-07 20:00:09',NULL,'2019-03-07 20:00:09',1),(9,14,'MySQL',NULL,NULL,'DIR','https://www.mysql.com/','MySQL是一个关系型数据库管理系统，由瑞典MySQL AB 公司开发，目前属于 Oracle 旗下产品。','southday','2019-05-13 17:09:08',NULL,'2019-05-13 17:09:08',1),(10,9,'MySQL Workbench',NULL,NULL,'DIR','https://dev.mysql.com/downloads/workbench/','MySQL Workbench provides DBAs and developers an integrated tools environment for: 1.Database Design & Modeling; 2.SQL Development; 3.Database Administration; 4.Database Migration.','southday','2019-05-13 17:14:28',NULL,'2019-05-13 17:14:28',1),(11,9,'MySQL Community Server',NULL,NULL,'DIR','https://dev.mysql.com/downloads/mysql/','MySQL Community Edition is a freely downloadable version of the world\'s most popular open source database that is supported by an active community of open source developers and enthusiasts.','southday','2019-05-13 17:14:28',NULL,'2019-05-13 17:14:28',1),(12,10,'',20,'MySQL Workbench','TOOL',NULL,NULL,'southday','2019-05-13 17:59:28',NULL,'2019-05-13 17:59:28',1),(13,11,NULL,21,'MySQL Community Server','TOOL',NULL,NULL,'southday','2019-05-13 17:59:28',NULL,'2019-05-13 17:59:28',1),(14,0,'M',NULL,NULL,'DIR',NULL,NULL,'southday','2019-05-15 12:33:30',NULL,'2019-05-15 12:33:30',1),(15,0,'S',NULL,NULL,'DIR',NULL,NULL,'southday','2019-05-16 21:35:48',NULL,'2019-05-16 21:35:48',1),(16,0,'J',NULL,NULL,'DIR',NULL,NULL,'southday','2019-05-17 21:15:13',NULL,'2019-05-17 21:15:13',1);
/*!40000 ALTER TABLE `tool_dir_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 10:02:07
