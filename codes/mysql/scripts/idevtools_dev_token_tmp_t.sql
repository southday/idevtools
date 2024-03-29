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
-- Table structure for table `token_tmp_t`
--

DROP TABLE IF EXISTS `token_tmp_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `token_tmp_t` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  `token` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='临时存储token使用的表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_tmp_t`
--

LOCK TABLES `token_tmp_t` WRITE;
/*!40000 ALTER TABLE `token_tmp_t` DISABLE KEYS */;
INSERT INTO `token_tmp_t` VALUES (1,1,'southday','admin','eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlclR5cGUiOiJhZG1pbiIsInVzZXJOYW1lIjoic291dGhkYXkiLCJuYmYiOjE1NjE3NzkyNzUsImV4cCI6MTU2MjM4NDA3NX0.7xMWukR1QFz5RjVW2VBh_xBsYKZ0JDtvAXUKFOplDl4'),(2,1,'admin01','user','eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlclR5cGUiOiJ1c2VyIiwidXNlck5hbWUiOiJ0ZXN0IiwibmJmIjoxNTUyODkxMzMxLCJleHAiOjE1NTM0OTYxMzF9.n3yYINfDAQL7O1aYcb5LWeHYP-7Ke0tJO_vbWbDQTO4'),(3,2,'test','user','eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwidXNlclR5cGUiOiJ1c2VyIiwidXNlck5hbWUiOiJ0ZXN0IiwibmJmIjoxNTUyOTE2MDI1LCJleHAiOjE1NTM1MjA4MjV9.jXpRWgs6NkYVEjjVnOOa5IEm8Be4u9gvmbgmV1Ip6sE'),(4,75,'uuu','user','eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NzUsInVzZXJUeXBlIjoidXNlciIsInVzZXJOYW1lIjoidXV1IiwibmJmIjoxNTYxNzExODczLCJleHAiOjE1NjIzMTY2NzN9.8ucM4RkNCIs7IjFVjzJBP6_qU5BmzYqKEX_m9cHVSdU');
/*!40000 ALTER TABLE `token_tmp_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 10:02:10
