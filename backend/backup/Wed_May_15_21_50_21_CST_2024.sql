-- MySQL dump 10.13  Distrib 5.7.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lab
-- ------------------------------------------------------
-- Server version	5.7.30-log

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
-- Current Database: `lab`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lab` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lab`;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '借用记录id',
  `student_id` bigint(20) NOT NULL COMMENT '学生id',
  `lab_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '实验室id，默认只是占位，已通过后（status=1）分配',
  `reason` text COMMENT '借用原因',
  `semester` varchar(255) NOT NULL COMMENT '学期，格式为year1-year2-num（year1-year2为学年，num为1或2）',
  `week` varchar(255) NOT NULL COMMENT '周，1-20',
  `session` varchar(255) NOT NULL COMMENT '节次，格式为num1-num2（表示节次为num1-num2节）',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态，0-未审核，1-通过，2-驳回，3-使用完毕',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `lab_id` (`lab_id`),
  KEY `semester` (`semester`),
  KEY `session` (`session`),
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE CASCADE,
  CONSTRAINT `borrow_ibfk_3` FOREIGN KEY (`semester`) REFERENCES `semester` (`semester`) ON DELETE CASCADE,
  CONSTRAINT `borrow_ibfk_4` FOREIGN KEY (`session`) REFERENCES `session` (`session`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='借用';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (1,4,1,'1','2023-2024-1','12','1 1-2',0,'2024-05-15 15:59:56','2024-05-15 18:51:16',0),(3,4,1,'1','2023-2024-1','11','1 1-2',0,'2024-05-15 18:40:25','2024-05-15 18:40:25',0);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `teacher_id` bigint(20) NOT NULL COMMENT '教师id',
  `lab_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '实验室id，默认只是占位，已通过后（status=1）分配',
  `type` int(11) NOT NULL COMMENT '需求实验室类别，0-软件，1-硬件，2-网络',
  `name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `semester` varchar(255) NOT NULL COMMENT '学期，格式为year1-year2-num（year1-year2为学年，num为1或2）',
  `starting_week` varchar(255) NOT NULL COMMENT '起始周',
  `ending_week` varchar(255) NOT NULL COMMENT '结束周',
  `session` varchar(255) NOT NULL COMMENT '节次，格式为num1-num2（表示节次为num1-num2节）',
  `student_num` int(11) NOT NULL COMMENT '学生人数',
  `major` varchar(255) DEFAULT NULL COMMENT '学生专业',
  `clazz` varchar(255) DEFAULT NULL COMMENT '学生班级',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '排课状态，0-未排课，1-已排课',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `lab_id` (`lab_id`),
  KEY `semester` (`semester`),
  KEY `session` (`session`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE CASCADE,
  CONSTRAINT `course_ibfk_3` FOREIGN KEY (`semester`) REFERENCES `semester` (`semester`) ON DELETE CASCADE,
  CONSTRAINT `course_ibfk_4` FOREIGN KEY (`session`) REFERENCES `session` (`session`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,3,1,2,' 计算机网络','2023-2024-1','6','11','1 1-2',30,' 计算机科学与技术',' 21计机4班',0,'2024-05-14 22:01:06','2024-05-15 18:51:04',0),(2,3,3,2,' 计算机网络','2023-2024-1','6','11','3 3-5',30,' 计算机科学与技术',' 21计机4班',1,'2024-05-15 21:07:47','2024-05-15 21:09:34',0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab`
--

DROP TABLE IF EXISTS `lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '实验室id',
  `lab_admin_id` bigint(20) NOT NULL COMMENT '实验员id',
  `type` int(11) NOT NULL COMMENT '实验室类别，0-软件，1-硬件，2-网络',
  `name` varchar(255) DEFAULT NULL COMMENT '实验室名称',
  `number` varchar(255) DEFAULT NULL COMMENT '实验室编号，约定用3位数字表示',
  `equipment_num` int(11) NOT NULL DEFAULT '0' COMMENT '设备数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`),
  KEY `lab_admin_id` (`lab_admin_id`),
  CONSTRAINT `lab_ibfk_1` FOREIGN KEY (`lab_admin_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='实验室';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
INSERT INTO `lab` VALUES (1,5,0,'软件实验室1','001',50,'2024-05-13 14:57:48','2024-05-13 14:57:48',0),(2,5,0,'软件实验室2','002',100,'2024-05-13 14:58:09','2024-05-13 14:58:09',0),(3,5,2,'网络实验室1','005',100,'2024-05-13 14:58:27','2024-05-13 14:58:27',0),(4,6,1,'硬件实验室1','003',50,'2024-05-13 14:59:41','2024-05-13 14:59:41',0),(5,6,1,'硬件实验室2','004',100,'2024-05-13 14:59:51','2024-05-13 14:59:51',0);
/*!40000 ALTER TABLE `lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintain`
--

DROP TABLE IF EXISTS `maintain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maintain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '维修记录id',
  `teacher_id` bigint(20) NOT NULL COMMENT '教师id',
  `lab_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '实验室id',
  `fault_description` text COMMENT '故障描述',
  `maintenance_description` text COMMENT '维修说明',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '维修状态，0-未维修，1-维修中，2-已维修',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `lab_id` (`lab_id`),
  CONSTRAINT `maintain_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `maintain_ibfk_2` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='维修';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintain`
--

LOCK TABLES `maintain` WRITE;
/*!40000 ALTER TABLE `maintain` DISABLE KEYS */;
INSERT INTO `maintain` VALUES (1,3,1,'投影仪损坏','投影仪已经更换。',2,'2024-05-14 20:46:03','2024-05-15 13:46:30',0);
/*!40000 ALTER TABLE `maintain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '学年id',
  `semester` varchar(255) NOT NULL COMMENT '学期，格式为year1-year2-num（year1-year2为学年，num为1或2）',
  `week` varchar(255) NOT NULL COMMENT '周',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '当前学期状态，0否1是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `semester` (`semester`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='学年';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (3,'2023-2024-1','19',1,'2024-05-09 09:42:50','2024-05-14 10:42:38',0),(4,'2023-2024-2','20',0,'2024-05-15 10:17:21','2024-05-15 10:17:21',0);
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '节次id',
  `session` varchar(255) NOT NULL COMMENT '节次，格式为day num1-num2（表示星期day，节次为num1-num2节）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `session` (`session`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='节次';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (7,'1 1-2','2024-05-15 18:40:20','2024-05-15 18:40:20',0),(8,'1 3-5','2024-05-15 18:40:20','2024-05-15 18:40:20',0),(9,'1 6-7','2024-05-15 18:40:20','2024-05-15 18:40:20',0),(10,'1 8-9','2024-05-15 18:40:20','2024-05-15 18:40:20',0),(11,'1 10-12','2024-05-15 18:40:20','2024-05-15 18:40:20',0),(12,'1 13-15','2024-05-15 18:40:20','2024-05-15 18:40:20',0),(13,'2 1-2','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(14,'2 3-5','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(15,'2 6-7','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(16,'2 8-9','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(17,'2 10-12','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(18,'2 13-15','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(19,'3 1-2','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(20,'3 3-5','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(21,'3 6-7','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(22,'3 8-9','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(23,'3 10-12','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(24,'3 13-15','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(25,'4 1-2','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(26,'4 3-5','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(27,'4 6-7','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(28,'4 8-9','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(29,'4 10-12','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(30,'4 13-15','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(31,'5 1-2','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(32,'5 3-5','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(33,'5 6-7','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(34,'5 8-9','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(35,'5 10-12','2024-05-15 18:53:21','2024-05-15 18:53:21',0),(36,'5 13-15','2024-05-15 18:53:21','2024-05-15 18:53:21',0);
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '账号，约定管理员以a开头，学生以s开头，教师以t开头，实验员以l开头',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `role` int(11) NOT NULL COMMENT '用户角色，0-管理员，1-学生，2-教师，3-实验员',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `clazz` varchar(255) DEFAULT NULL COMMENT '班级',
  `title` varchar(255) DEFAULT NULL COMMENT '职称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'admin','$2a$10$fxZsyT6OD8jHBerKZPGf.usVQQxmSs.Y0qyHurOlL.n0uAAbf7Kj2',0,NULL,NULL,NULL,NULL,'2024-04-17 14:25:52','2024-04-17 14:25:52',0),(3,'ter1','$2a$10$Vyg6VTz4yPfm/Oy4nw9T5eyljJ5hbv2.r4.sX/4StpP.amV8C0t1O',2,'chen','计算机科学与技术',NULL,'讲师','2024-04-25 21:02:07','2024-05-14 15:35:50',0),(4,'stu1','$2a$10$BwWN6grs43hN/JYY5AzXW.BBRE7HojfCCVbpf9Fnb1PjI4LB5tiby',1,NULL,NULL,NULL,NULL,'2024-04-26 11:19:04','2024-04-26 11:19:04',0),(5,'ler1','$2a$10$u8TfhQUc/KizX94Q.xb2u.j8qayp8MUgOUfKn6N7jXcYAPYXzkP3S',3,NULL,NULL,NULL,NULL,'2024-05-12 23:25:00','2024-05-12 23:25:00',0),(6,'ler2','$2a$10$PNErfnywf4UgHew6Qod/6utV3/ckAFPZLYFrTIeCCgw7LXZQj.FMm',3,NULL,NULL,NULL,NULL,'2024-05-13 14:58:57','2024-05-13 14:58:57',0),(7,'stu2','$2a$10$1TTcNfgTi60S9gEYuQNFJOGMQXoPVbwZVukmcImvvbCW0K1ysNDWe',1,'c','计算机科学与技术','21计机4班',NULL,'2024-05-14 14:23:28','2024-05-14 14:23:28',0);
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

-- Dump completed on 2024-05-15 21:50:22
