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
-- Table structure for table `message_t`
--

DROP TABLE IF EXISTS `message_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message_t` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `reply_id` int(11) DEFAULT '0' COMMENT '消息B是针对消息A进行回复，那么B.reply_id = A.msg_id，reply_id = 0时，表示根消息，不是针对某条消息进行回复；',
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `msg_type` varchar(45) DEFAULT NULL,
  `send_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `read` tinyint(1) DEFAULT '0' COMMENT '已读',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_t`
--

LOCK TABLES `message_t` WRITE;
/*!40000 ALTER TABLE `message_t` DISABLE KEYS */;
INSERT INTO `message_t` VALUES (1,1,89,1,'用户意见反馈','快点开发完成，OK？','SUGGESTION','2019-03-18 17:56:40',0),(2,0,89,1,'用户意见反馈','？？？？','SUGGESTION','2019-03-18 20:53:15',0),(3,0,89,1,'工具推荐','工具推荐\n\n工具名称：Aapowersoft\n\nWebsite：https://www.apowersoft.cn/free-online-screen-recorder?tdsourcetag=s_pctim_aiomsg\n\n推荐理由：贼好用，很方便！','TOOL_RECOMMENDATION','2019-03-18 20:53:36',0),(5,0,89,1,'用户意见反馈','路飞','SUGGESTION','2019-03-18 22:21:10',0),(6,0,2,1,'工具推荐','工具推荐\n\n工具名称：docker\n\nWebsite：https://www.docker.com/\n\n推荐理由：这个工具很好用！','TOOL_RECOMMENDATION','2019-03-18 22:22:47',0),(7,0,75,1,'用户意见反馈','哈哈哈哈哈','SUGGESTION','2019-05-17 19:15:19',0),(8,0,75,1,'用户意见反馈','哟西哟西\nhallo','SUGGESTION','2019-05-17 19:43:59',0),(9,0,75,1,'工具推荐','工具推荐\n\n工具名称：Sublime Text 3\n\nWebsite：http://www.sublimetext.com/\n\n推荐理由：极客必备，轻量级文本编辑器！','TOOL_RECOMMENDATION','2019-05-17 20:09:41',0),(10,0,75,1,'用户意见反馈','你说什么？我听不到啊啊啊啊啊','SUGGESTION','2019-05-17 20:11:10',0),(11,0,75,1,'工具推荐','工具推荐\n\n工具名称：pycharm\n\nWebsite：https://www.jetbrains.com/pycharm/?fromMenu\n\n推荐理由：好用的Python IDE','TOOL_RECOMMENDATION','2019-05-17 20:17:05',0),(12,0,75,1,'用户意见反馈','测试ing....','SUGGESTION','2019-05-17 20:17:25',0),(13,0,75,1,'工具推荐','工具推荐\n\n工具名称：Hello\n\nWebsite：https://hello.com\n\n推荐理由：Hello world','TOOL_RECOMMENDATION','2019-06-13 17:10:38',0),(14,0,75,1,'用户意见反馈','wtf?','SUGGESTION','2019-06-13 17:11:10',0),(15,0,75,1,'用户意见反馈','sdf','SUGGESTION','2019-06-14 20:26:19',0),(16,0,67,1,'用户意见反馈','?','SUGGESTION','2019-06-28 12:08:51',0),(17,0,75,1,'工具推荐','工具推荐\n\n工具名称：Pygments\n\nWebsite：http://pygments.org/\n\n推荐理由：提供各种代码高亮，可直接粘贴置word文档，方便程序员编写文档。','TOOL_RECOMMENDATION','2019-06-28 14:04:14',0),(18,0,92,1,'工具推荐','工具推荐\n\n工具名称：Pygments\n\nWebsite：Website：http://pygments.org/\n\n推荐理由：提供各种代码高亮，可直接粘贴置word文档，方便程序员编写文档。','TOOL_RECOMMENDATION','2019-06-29 11:22:42',0),(19,0,92,1,'用户意见反馈','希望加强一下搜索功能，支持模糊匹配。','SUGGESTION','2019-06-29 11:24:29',0),(20,0,93,1,'工具推荐','工具推荐\n\n工具名称：Pygments\n\nWebsite：Website：http://pygments.org/\n\n推荐理由：提供各种代码高亮，可直接粘贴置word文档，方便程序员编写文档。','TOOL_RECOMMENDATION','2019-06-29 11:39:21',0),(21,0,93,1,'用户意见反馈','希望加强一下搜索功能，支持模糊匹配。','SUGGESTION','2019-06-29 11:39:33',0),(22,0,96,1,'工具推荐','工具推荐\n\n工具名称：Pygments\n\nWebsite：Website：http://pygments.org/\n\n推荐理由：提供各种代码高亮，可直接粘贴置word文档，方便程序员编写文档。','TOOL_RECOMMENDATION','2019-06-29 13:40:36',0),(23,0,96,1,'用户意见反馈','希望加强一下搜索功能，支持模糊匹配','SUGGESTION','2019-06-29 13:40:46',0),(24,0,98,1,'工具推荐','工具推荐\n\n工具名称：Pygments\n\nWebsite：http://pygments.org/\n\n推荐理由：提供各种代码高亮，可直接粘贴置word文档，方便程序员编写文档','TOOL_RECOMMENDATION','2019-06-29 13:56:32',0),(25,0,98,1,'用户意见反馈','希望加强一下搜索功能，支持模糊匹配','SUGGESTION','2019-06-29 13:56:41',0),(26,0,99,1,'用户意见反馈','这个很好','SUGGESTION','2019-07-02 20:14:40',0),(27,0,89,1,'工具推荐','工具推荐\n\n工具名称：visio\n\nWebsite：www.visio.com\n\n推荐理由：ok','TOOL_RECOMMENDATION','2019-07-02 23:09:26',0);
/*!40000 ALTER TABLE `message_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 10:02:13
