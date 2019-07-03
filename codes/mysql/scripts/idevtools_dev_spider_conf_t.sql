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
-- Table structure for table `spider_conf_t`
--

DROP TABLE IF EXISTS `spider_conf_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `spider_conf_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `rules` longtext,
  `name` varchar(255) DEFAULT NULL,
  `setting` longtext,
  `offical_website_xpath` varchar(255) DEFAULT NULL,
  `tool_name_xpath` varchar(255) DEFAULT NULL,
  `description_xpath` varchar(255) DEFAULT NULL,
  `tag_xpath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spider_conf_t`
--

LOCK TABLES `spider_conf_t` WRITE;
/*!40000 ALTER TABLE `spider_conf_t` DISABLE KEYS */;
INSERT INTO `spider_conf_t` VALUES (1,'https://sdk.cn/datas','(Rule(LinkExtractor(allow=\'/datas/[0-9]+\',restrict_xpaths=\'//*[@id=\"data-index\"]/div[3]\'),callback=\'pares_item\', follow=True),Rule(LinkExtractor(restrict_xpaths=\'//*[@id=\"data-index\"]/div[4]/ul/li[8]/a\')))','sdk','{\r\n        \'ITEM_PIPELINES\':{\r\n            \'toolspider.pipelines.ToolItemPipline\':300\r\n        }\r\n    }','//*[@id=\"data-view\"]/header/div[2]/div[1]/a/@href','//*[@id=\"data-view\"]/header/div[1]/div[2]/h1/text()','//*[@id=\"data-view\"]/header/div[1]/div[2]/p/text()','//*[@id=\"data-view\"]/nav/a/text()');
/*!40000 ALTER TABLE `spider_conf_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 10:02:05
