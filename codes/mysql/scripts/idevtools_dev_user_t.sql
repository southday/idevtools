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
-- Table structure for table `user_t`
--

DROP TABLE IF EXISTS `user_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_t` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `intro` varchar(200) DEFAULT NULL,
  `img_path` varchar(200) DEFAULT NULL,
  `actived` tinyint(1) DEFAULT '0' COMMENT '账号是否激活',
  `valid` tinyint(1) DEFAULT '1' COMMENT '账号是否“有效”，管理员通过设置valid为0来删除用户',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_t`
--

LOCK TABLES `user_t` WRITE;
/*!40000 ALTER TABLE `user_t` DISABLE KEYS */;
INSERT INTO `user_t` VALUES (1,'admin01','912417@575.com','119df9','no into','',1,1),(2,'test','ead785@1f8.com','67e1f6','no into','',0,0),(3,'9bcf29','bcfb20@3b4.com','e96a6e','no into','',0,1),(4,'a48487','018aec@a93.com','ebab1f','no into','',0,1),(5,'517965','c4f829@9aa.com','94f164','no into','',0,1),(6,'f1f9d5','4c765c@747.com','d25927','no into','',0,1),(7,'bf7bd1','0357b5@a19.com','db7ed6','no into','',0,1),(8,'d597ce','a58597@40f.com','cbe36b','no into','',0,1),(9,'b978ba','51f123@76b.com','41d6fa','no into','',0,1),(10,'fd6d72','441b3f@3d0.com','a24aaa','no into','',0,1),(11,'7743de','4b7f27@2da.com','8a82ff','no into','',0,1),(12,'2c5666','10e6fb@254.com','aeb19f','no into','',0,1),(13,'18adfa','ca8282@871.com','0e594e','no into','',0,1),(14,'4b2168','bca527@326.com','e93faf','no into','',0,1),(15,'a7df60','353cca@969.com','0ab176','no into','',0,1),(16,'db54b7','c886a1@a0c.com','0429f0','no into','',0,1),(17,'801e94','ed31fb@646.com','d8c3de','no into','',0,1),(18,'a009b6','1c3dbe@6f0.com','4fd42e','no into','',0,1),(19,'84d3f5','e3ef38@830.com','05f2ed','no into','',0,1),(20,'97c903','41032a@464.com','ae3803','no into','',0,1),(21,'e63aae','5c272a@abf.com','ca9d97','no into','',0,1),(22,'e05fcd','b3e6fc@1b2.com','58f79e','no into','',0,1),(23,'62cce2','d7a57c@e66.com','e42c02','no into','',0,1),(24,'9d46a8','bcb20b@08b.com','530acc','no into','',0,1),(25,'f7f1bb','cbb921@87f.com','627f2d','no into','',0,1),(26,'ce3307','7206d6@17f.com','42bfa8','no into','',0,1),(27,'e893cf','9a7b07@fac.com','554903','no into','',0,1),(28,'de3771','67f926@53c.com','fb7c40','no into','',0,1),(29,'11be24','130433@e0c.com','34fa10','no into','',0,1),(30,'19e312','1561d4@e16.com','73ed37','no into','',0,1),(31,'f47a01','e064c9@086.com','ba16ca','no into','',0,1),(32,'198853','937d56@f47.com','4ae6b2','no into','',0,1),(33,'52e181','1f9242@5f4.com','3c0ec0','no into','',0,1),(34,'986f65','1a3586@751.com','d67816','no into','',0,1),(35,'5daa37','460520@478.com','0011b5','no into','',0,1),(36,'ebf398','068ed6@49d.com','3b8aa4','no into','',0,1),(37,'19ae62','195079@658.com','32324e','no into','',0,1),(38,'06450e','4711f5@07f.com','f12240','no into','',0,1),(39,'59edbe','bbd0a0@b65.com','10ca2a','no into','',0,1),(40,'2273e2','4d40c3@559.com','5e0eb5','no into','',0,1),(41,'56cf93','6bc5da@4ad.com','9ec09f','no into','',0,1),(42,'ec5c3a','89d2bb@4f0.com','1254d0','no into','',0,1),(43,'4a061d','cde2fe@72a.com','d88fec','no into','',0,1),(44,'ec472d','c6ed25@140.com','c28617','no into','',0,1),(45,'0c115e','c18340@a35.com','f1b8ab','no into','',0,1),(46,'816586','7f9741@ed1.com','bd61c7','no into','',0,1),(47,'8c76fa','78e5fb@333.com','d02e80','no into','',0,1),(48,'86e92d','7fd8d3@685.com','6c5556','no into','',0,1),(49,'600391','5fda55@cf5.com','1f6d67','no into','',0,1),(50,'c919eb','f7d615@d0b.com','cca806','no into','',0,1),(51,'fd0358','f2b23e@c70.com','3dbf0c','no into','',0,1),(52,'719060','b2fb7f@9e8.com','5b3261','no into','',0,1),(53,'8ebada','cb73c6@1cf.com','1ec384','no into','',0,1),(54,'5b1df0','eb8ab9@738.com','061631','no into','',0,1),(55,'83f56b','a92f5b@849.com','2948ff','no into','',0,1),(56,'b47851','126c5e@494.com','25c076','no into','',0,1),(57,'693d5b','ef4a4f@6bc.com','23d133','no into','',0,1),(58,'fed413','923709@0ec.com','b6a4cf','no into','',0,1),(59,'656486','7d5638@6d3.com','b62cfe','no into','',0,1),(60,'50a7f6','1ab4c0@78e.com','20dd0a','no into','',0,1),(61,'5efd7e','68bb88@2af.com','fdb103','no into','',0,1),(62,'abb923','227d27@ffa.com','62c970','no into','',0,1),(63,'3f4a0f','a0a0ec@2cd.com','600de9','no into','',0,1),(64,'5be04a','2b0d90@eaa.com','a950ba','no into','',0,1),(65,'39e0bd','6933a3@a52.com','aec79f','no into','',0,1),(68,'lis','sdf@23s.com','cded313e2f92145a4f50a99d18eabed5',NULL,NULL,0,1),(69,'haha','123@312.com','8ba5e0c9ee54f8682a63d9c8fb10dbc9',NULL,NULL,0,1),(70,'wawa','123@123','c27407d633c24cad2499f91c17a1e3ae',NULL,NULL,0,1),(71,'23','23@qq.com','a3d7f92730ffa2892dfc7a96458ff8b2',NULL,NULL,0,1),(72,'pig','pig@qq.com','d87b39b52279ae40012a03cdb5c87ab3',NULL,NULL,0,1),(73,'www','www@mail.com','600a384b186da2664548ceb05a2b9d88',NULL,NULL,0,1),(74,'qqq','qqq@qq.com','dc30b840c7ce42164591b41b207d801a',NULL,NULL,0,1),(75,'uuu','uuu@qq.com','efdf3c6aa8a7de92d39a29e60ae28c4f',NULL,NULL,0,1),(76,'rrr','rrr@qq.com','15a6a5b1ecae66eaa87973246c44ce69',NULL,NULL,0,1),(77,'ccc','ccc@cc.com','25f27dad62be27eaa7757238027d0ec1',NULL,NULL,0,1),(86,'ww?','lichaoxis7@qq.com','1c6fac801ce3f1dc53afd54baedb01f4',NULL,NULL,1,1),(89,'wqk','wangqinkuan@qq.com','4122cb13c7a474c1976c9706ae36521d',NULL,NULL,1,1),(90,'jy','995797051@qq.com','5a100f2007d7ae5b722edace034f3aa8',NULL,NULL,0,1),(91,'z1152z','747830165@qq.com','4e07bf33e3b0f98e98e31a8a6120f855',NULL,NULL,0,1),(92,'eee','eee@qq.com','0f4238735916dea9bac9b6a79824223b',NULL,NULL,0,1),(93,'yyy','yyy@qq.com','06073159310c7acdb36f3076b4c3efa4',NULL,NULL,0,1),(98,'lcx','lichaoxi7@qq.com','c89648e3bad71370a580029fc1a8b4d4',NULL,NULL,1,1),(99,'ZLL','1807480053@qq.com','a8e00d3fce9815c37bd42aa80280c4ff',NULL,NULL,0,1),(100,'southday','1345244694@qq.com','4122cb13c7a474c1976c9706ae36521d',NULL,NULL,1,1);
/*!40000 ALTER TABLE `user_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 10:02:17
