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
-- Table structure for table `manage_history_t`
--

DROP TABLE IF EXISTS `manage_history_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manage_history_t` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL,
  `admin_name` varchar(45) DEFAULT NULL,
  `action_type` varchar(45) DEFAULT NULL,
  `action_target` varchar(45) DEFAULT NULL,
  `action_desc` varchar(200) DEFAULT NULL,
  `proc_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manage_history_t`
--

LOCK TABLES `manage_history_t` WRITE;
/*!40000 ALTER TABLE `manage_history_t` DISABLE KEYS */;
INSERT INTO `manage_history_t` VALUES (3,NULL,'southday','NOMORL','getUserDetailWithTagById','wtf','2019-02-27 22:29:01'),(4,1,'southday','NOMORL','getUserDetailWithTagById','王无敌到此一游','2019-03-11 16:40:34'),(5,1,'southday','NOMORL','getUserDetailWithTagById','王无敌到此一游','2019-03-11 20:24:19'),(6,1,'southday','NOMORL','getUserDetailWithTagById','王无敌到此一游','2019-03-11 21:54:47'),(7,1,'southday','NOMORL','getUserDetailWithTagById','王无敌到此一游','2019-03-11 22:52:12'),(8,1,'southday','NOMORL','getUserDetailWithTagById','王无敌到此一游','2019-03-11 23:28:27');
/*!40000 ALTER TABLE `manage_history_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 10:02:11
