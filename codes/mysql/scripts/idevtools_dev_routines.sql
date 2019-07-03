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
-- Temporary view structure for view `lookup_values_v`
--

DROP TABLE IF EXISTS `lookup_values_v`;
/*!50001 DROP VIEW IF EXISTS `lookup_values_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `lookup_values_v` AS SELECT 
 1 AS `type_id`,
 1 AS `type_code`,
 1 AS `type_meaning`,
 1 AS `type_description`,
 1 AS `value_id`,
 1 AS `value_code`,
 1 AS `value_meaning`,
 1 AS `value_description`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `downloads_tool_v`
--

DROP TABLE IF EXISTS `downloads_tool_v`;
/*!50001 DROP VIEW IF EXISTS `downloads_tool_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `downloads_tool_v` AS SELECT 
 1 AS `download_id`,
 1 AS `user_id`,
 1 AS `download_time`,
 1 AS `tool_id`,
 1 AS `tool_name`,
 1 AS `tool_version`,
 1 AS `code_name`,
 1 AS `description`,
 1 AS `website`,
 1 AS `download_links`,
 1 AS `usable`,
 1 AS `download_count`,
 1 AS `collect_count`,
 1 AS `last_update_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `collections_tool_v`
--

DROP TABLE IF EXISTS `collections_tool_v`;
/*!50001 DROP VIEW IF EXISTS `collections_tool_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `collections_tool_v` AS SELECT 
 1 AS `collect_id`,
 1 AS `user_id`,
 1 AS `collect_time`,
 1 AS `tool_id`,
 1 AS `tool_name`,
 1 AS `tool_version`,
 1 AS `code_name`,
 1 AS `description`,
 1 AS `website`,
 1 AS `download_links`,
 1 AS `usable`,
 1 AS `download_count`,
 1 AS `collect_count`,
 1 AS `last_update_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `lookup_values_v`
--

/*!50001 DROP VIEW IF EXISTS `lookup_values_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`idt`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `lookup_values_v` AS select `t`.`type_id` AS `type_id`,`t`.`type_code` AS `type_code`,`t`.`meaning` AS `type_meaning`,`t`.`description` AS `type_description`,`v`.`value_id` AS `value_id`,`v`.`value_code` AS `value_code`,`v`.`meaning` AS `value_meaning`,`v`.`description` AS `value_description` from (`lookup_type_t` `t` join `lookup_value_t` `v`) where (`t`.`type_id` = `v`.`type_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `downloads_tool_v`
--

/*!50001 DROP VIEW IF EXISTS `downloads_tool_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`idt`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `downloads_tool_v` AS (select `d`.`download_id` AS `download_id`,`d`.`user_id` AS `user_id`,`d`.`download_time` AS `download_time`,`d`.`tool_id` AS `tool_id`,`t`.`tool_name` AS `tool_name`,`t`.`tool_version` AS `tool_version`,`t`.`code_name` AS `code_name`,`t`.`description` AS `description`,`t`.`website` AS `website`,`t`.`download_links` AS `download_links`,`t`.`usable` AS `usable`,`t`.`download_count` AS `download_count`,`t`.`collect_count` AS `collect_count`,`t`.`last_update_time` AS `last_update_time` from (`downloads_t` `d` join `tool_t` `t` on((`d`.`tool_id` = `t`.`tool_id`)))) union (select `d`.`download_id` AS `download_id`,`d`.`user_id` AS `user_id`,`d`.`download_time` AS `download_time`,`d`.`tool_id` AS `tool_id`,`t`.`tool_name` AS `tool_name`,`t`.`tool_version` AS `tool_version`,`t`.`code_name` AS `code_name`,`t`.`description` AS `description`,`t`.`website` AS `website`,`t`.`download_links` AS `download_links`,`t`.`usable` AS `usable`,`t`.`download_count` AS `download_count`,`t`.`collect_count` AS `collect_count`,`t`.`last_update_time` AS `last_update_time` from (`downloads_t` `d` join `tool_unusable_t` `t` on((`d`.`tool_id` = `t`.`tool_id`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `collections_tool_v`
--

/*!50001 DROP VIEW IF EXISTS `collections_tool_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`idt`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `collections_tool_v` AS (select `c`.`collect_id` AS `collect_id`,`c`.`user_id` AS `user_id`,`c`.`collect_time` AS `collect_time`,`c`.`tool_id` AS `tool_id`,`t`.`tool_name` AS `tool_name`,`t`.`tool_version` AS `tool_version`,`t`.`code_name` AS `code_name`,`t`.`description` AS `description`,`t`.`website` AS `website`,`t`.`download_links` AS `download_links`,`t`.`usable` AS `usable`,`t`.`download_count` AS `download_count`,`t`.`collect_count` AS `collect_count`,`t`.`last_update_time` AS `last_update_time` from (`collections_t` `c` join `tool_t` `t` on((`c`.`tool_id` = `t`.`tool_id`)))) union (select `c`.`collect_id` AS `collect_id`,`c`.`user_id` AS `user_id`,`c`.`collect_time` AS `collect_time`,`c`.`tool_id` AS `tool_id`,`t`.`tool_name` AS `tool_name`,`t`.`tool_version` AS `tool_version`,`t`.`code_name` AS `code_name`,`t`.`description` AS `description`,`t`.`website` AS `website`,`t`.`download_links` AS `download_links`,`t`.`usable` AS `usable`,`t`.`download_count` AS `download_count`,`t`.`collect_count` AS `collect_count`,`t`.`last_update_time` AS `last_update_time` from (`collections_t` `c` join `tool_unusable_t` `t` on((`c`.`tool_id` = `t`.`tool_id`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping routines for database 'idevtools_dev'
--
/*!50003 DROP PROCEDURE IF EXISTS `PROC_INSERT_TOOL` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`idt`@`%` PROCEDURE `PROC_INSERT_TOOL`(IN TAGNAMES VARCHAR(200),TOOLNAME VARCHAR(50),SOURCE VARCHAR(200),OFFCIAL_WEBSITE VARCHAR(200),DESCRPITION VARCHAR(200))
BEGIN        
        #计数，用作插入tag的循环
        DECLARE i INT DEFAULT 1;
        #以空格为分隔符的标签名称数组长度
        DECLARE count VARCHAR(50) DEFAULT CHAR_LENGTH(TAGNAMES)-CHAR_LENGTH(REPLACE(TAGNAMES,' ',''))+1;
        #保存每个标签名称
        DECLARE tagname VARCHAR(50);
        #保存每个标签的id（待插入）
        DECLARE tagid int;
        #保存每个工具的id（贴标签）
    DECLARE toolid int;
        #开启事务
        START TRANSACTION;
        #不重复插入tool
        INSERT INTO tool_t(website,description,tool_name,usable,create_time,last_update_time) SELECT OFFCIAL_WEBSITE,DESCRPITION,TOOLNAME,1,now(),now() FROM DUAL WHERE NOT EXISTS(SELECT tool_id FROM tool_t WHERE tool_name=TOOLNAME);
        #不重复插入多个标签,当有标签时这么做
        IF CHAR_LENGTH(TAGNAMES)>0 THEN
            WHILE i<=count DO
                #SUBSTRING_INDEX(str,delim,count)表示取到第count个delim，count为负数则从右向左取，如1 2 3，delim为‘ ’，count为2则结果为1 2
                SET tagname=SUBSTRING_INDEX(SUBSTRING_INDEX(TAGNAMES,' ',i),' ',-1);
                INSERT INTO tool_tag_t(tag_meaning) SELECT tagname WHERE NOT EXISTS(SELECT tag_id FROM tool_tag_t WHERE tag_meaning=tagname);
                #不重复地给tool贴标签，即向tag_tool多对多关系表插入数据
                SELECT tag_id into tagid FROM tool_tag_t where tag_meaning=tagname;
                SELECT tool_id into toolid FROM tool_t where tool_name=TOOLNAME;
                INSERT INTO tool_tag_rel_t(tag_id,tool_id) SELECT tagid,toolid WHERE NOT EXISTS(SELECT id FROM tool_tag_rel_t WHERE tag_id=tagid AND tool_id=toolid);
                SET i=i+1;
            END WHILE;
        END IF;
        COMMIT;
    END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_recommend_tool` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`idt`@`%` PROCEDURE `proc_recommend_tool`(
	IN p_user_id INT(11),
    IN p_tool_name VARCHARACTER(45),
    IN p_website VARCHARACTER(200),
    IN p_reason VARCHARACTER(300))
BEGIN
	/* 用户推荐工具
    @author southday
    @date 2019.03.18
    */
    DECLARE v_content VARCHAR(2000) DEFAULT '';
    # 1.获取工具推荐模版
    SELECT mt.content
      FROM message_template_t mt
	 WHERE mt.tmpl_id = 1 # msg_type = 'TOOL_RECOMMENDATION'
      INTO v_content;
	# 2.替换模版中的内容，转换成message_t的消息体content
    SET v_content = REPLACE(v_content, '${toolName}', p_tool_name);
    SET v_content = REPLACE(v_content, '${website}', p_website);
    SET v_content = REPLACE(v_content, '${reason}', p_reason);
    # 3.封装到message_t中
    INSERT INTO message_t(sender_id, receiver_id, title, content, msg_type)
    VALUES(p_user_id, 1, '工具推荐', v_content, 'TOOL_RECOMMENDATION');
    # 4.返回操作结果 成功(1) 失败(0)
    IF ROW_COUNT() > 0 THEN
		SELECT 1;
	ELSE
		SELECT 0;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_select_user_page` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`idt`@`%` PROCEDURE `proc_select_user_page`(IN page_id int,IN page_limit int,OUT total BIGINT)
begin
#查询总数据
SELECT count(*) into total FROM user_t;
#查询分页数据,注意limit的起始offset是0而不是1，但页面号是从1开始的，因此要将页号减一后再传进来
set page_id=page_id-1;

SELECT *
FROM user_t
LIMIT page_id,page_limit;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_submit_suggestion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`idt`@`%` PROCEDURE `proc_submit_suggestion`(
	IN p_user_id INT(11),
	IN p_content VARCHARACTER(200))
BEGIN
	/* 用户意见反馈 procedure
    @author southay
    @date 2019.03.18
    */
    /*
    这里receiver_id固定为1，表示管理员的用户代理user_id = 1；
    管理员在其消息模块接收和发送消息，都是通过他的用户代理（user_id=1的用户）来实现的；
    */
    INSERT INTO message_t(sender_id, receiver_id, title, content, msg_type)
    VALUES(p_user_id, 1, "用户意见反馈", p_content, 'SUGGESTION');
    # 1 表示成功，0 表示失败
    IF ROW_COUNT() > 0 THEN
	    SELECT 1;
	ELSE 
		SELECT 0;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_user_join` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`idt`@`%` PROCEDURE `proc_user_join`(
		IN p_user_name VARCHARACTER(45),
        IN p_email VARCHARACTER(45),
        IN p_password VARCHARACTER(45))
BEGIN
	# 用户注册 procedure
    # @author southday
    # @date 2019.03.01
    # @deprecated
	DECLARE v_code INTEGER DEFAULT -1;
    DECLARE v_msg VARCHARACTER(60) DEFAULT '';
    DECLARE v_user_id INTEGER DEFAULT 0;
    DECLARE v_username_exists INTEGER DEFAULT 0;
    DECLARE v_email_exists INTEGER DEFAULT 0;
    # 判断用户名是否已存在
    SELECT COUNT(1) INTO v_username_exists
	  FROM user_t
	 WHERE user_name = p_user_name;
	# 判断邮箱是否已存在
    SELECT COUNT(1) INTO v_email_exists
      FROM user_t
	 WHERE email = p_email;
	# 若用户名或邮箱已存在，则返回相关提示消息
    IF v_username_exists = 1 AND v_email_exists = 0 THEN
		SET v_msg = '用户名已被注册';
	ELSEIF v_email_exists = 1 AND v_username_exists = 0 THEN
		SET v_msg = '邮箱已被注册';
	ELSEIF v_email_exists = 1 AND v_username_exists = 1 THEN
		SET v_msg = '用户名和邮箱均已被注册';
    END IF;
    # 如果用户名或邮箱都未被注册，则插入数据
    IF v_msg = '' THEN
		INSERT INTO user_t(user_name, email, password)
        VALUES(p_user_name, p_email, p_password);
        # 获取最新插入的user_id
        SELECT LAST_INSERT_ID() INTO v_user_id;
        SET v_code = 1;
        SET v_msg = '注册成功';
    END IF;
    # 返回结果
    SELECT v_code code,
		   v_msg msg,
		   v_user_id user_id
	  FROM dual;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 10:02:21
