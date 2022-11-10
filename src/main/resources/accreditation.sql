-- MySQL dump 10.13  Distrib 5.6.50, for Linux (x86_64)
--
-- Host: localhost    Database: accreditation
-- ------------------------------------------------------
-- Server version	5.6.50-log

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
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `section_name` varchar(50) DEFAULT NULL,
  `project_owner` varchar(100) DEFAULT NULL,
  `client_unit` varchar(100) DEFAULT NULL,
  `compile_institution` varchar(100) DEFAULT NULL,
  `review_sort` varchar(100) DEFAULT NULL,
  `inventory_norm` varchar(100) DEFAULT NULL,
  `quota_system` varchar(100) DEFAULT NULL,
  `approval_time` date DEFAULT NULL,
  `project_condition` varchar(1000) DEFAULT NULL,
  `project_address` varchar(100) DEFAULT NULL,
  `approval_number` varchar(100) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  `client_username` varchar(100) DEFAULT NULL,
  `institution_number` varchar(100) DEFAULT NULL,
  `industry_sort` varchar(100) DEFAULT NULL,
  `price_gist` varchar(100) DEFAULT NULL,
  `approval_price` float DEFAULT NULL,
  `technical_number` varchar(100) DEFAULT NULL,
  `user_number` varchar(100) DEFAULT NULL,
  `client_number` int(11) DEFAULT NULL,
  `compile_name` varchar(100) DEFAULT NULL,
  `invest_price` float DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  `user_username` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `suggestion` varchar(1000) DEFAULT NULL,
  `date` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`user_id`,`username`),
  KEY `user` (`user_id`,`user_username`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`, `user_username`) REFERENCES `user` (`id`, `username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付',NULL,'都江堰市天马镇圣寿社区村民委员会',NULL,'深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','马福钢',NULL,'91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061','13568818730',NULL,'卓航如',649997,'2022-11-01 00:00:00',-1,'10086',1,'地方睡大床',NULL),(21,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付','42','都江堰市天马镇圣寿社区村民委员会','','深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','马福钢','','91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061',NULL,0,'卓航如',649997,'2022-11-05 00:00:00',1,'10086',1,'过过过过过过过过过过过过过过过过过过过','马福钢IMG_20211007_123123.jpg'),(23,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付','fbgf','都江堰市天马镇圣寿社区村民委员会','gn','深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','马福钢','','91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061',NULL,0,'卓航如',649997,'2022-11-05 00:00:00',1,'10086',1,'等荣誉','马福钢IMG_20211105_123515.jpg*马福钢IMG_20211105_123607.jpg*马福钢IMG_20211105_123630.jpg'),(24,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付','fbgf','都江堰市天马镇圣寿社区村民委员会','gn','深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','马福钢','','91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061',NULL,0,'卓航如',649997,'2022-11-05 00:00:00',1,'10086',1,'豆腐乳官方的','马福钢IMG_20211108_121727.jpg*马福钢IMG_20211108_121810.jpg*马福钢IMG_20211108_122230.jpg'),(27,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付','42','都江堰市天马镇圣寿社区村民委员会','','深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','马福钢','','91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061',NULL,0,'卓航如',649997,'2022-11-05 00:00:00',1,'10086',1,'cvb','马福钢7265d7f4ad058912034082371e21c055-12bce32d9579259e06555b4794617e8429f697d6.zip'),(29,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付','42','都江堰市天马镇圣寿社区村民委员会','大风刮大风刮','深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','马福','','91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061',NULL,0,'卓航如',649997,'2022-11-06 16:22:40',1,'10086',1,'呱呱呱呱呱呱呱呱呱呱呱呱呱呱呱古古怪怪\n','马福OIP-C (2).jpg*马福OIP-C (3).jfif'),(33,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付','42','都江堰市天马镇圣寿社区村民委员会','大风刮大风刮','深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','刮大','发个互粉好','91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061',NULL,0,'卓航如',649997,'2022-11-06 16:50:11',1,'10086',1,'',NULL),(37,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付','42','都江堰市天马镇圣寿社区村民委员会','大风刮大风刮','深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','123','发个互粉好','91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061',NULL,0,'卓航如',649997,'2022-11-06 16:55:16',0,'10086',1,'',NULL),(40,'都江堰市天马镇圣寿社区2022年四川财政乡村振兴转移支付','42','都江堰市天马镇圣寿社区村民委员会','大风刮大风刮','深圳市腾达工程顾问有限公司','招标控制价','清单计价规范(GB50500-2013)','四川建设工程计价定额2020','2022-07-11','该项目主要建设内容为: 1.环境提升整治:在蒲阳河圣寿社区3组改造宽2米游步道约285米，节点周边安放分类垃圾桶20个，新建河边护栏约420米，安装铁皮集装箱2套(共约50平方米) ,增设休闲设施8套; 2.乡 村旅游设施配套:在圣寿社区3组安装休闲廊亭2处，增设标识标牌10个，改造厕所1座，改造生态停车位50个，3.在圣寿社区3组公共区域环境改造及露营地打造约600平方米。','都江堰市天马镇圣寿社区3组','都发改审批[2022] 48号','123','发个互粉好','91440300596782153H','房屋建筑与市政工程','都江堰市2022年第06期信息价及现行市场价格计算',650000,'都技评南2022061',NULL,0,'卓航如',649997,'2022-11-06 17:01:38',0,'10086',1,'','123签到表.xlsx'),(42,'第三方','第三方','第三方','第三方','第三方','第三方','第三方','第三方','2022-11-10','第三方','第三方','第三方','第三方','第三方','第三方','第三方','第三方',4545,'第三方','4',144,'',45424,'2022-11-08 13:49:53',0,'123',3,'上厕所的车','第三方关于信息工程学院2022年班级团支委、班委选举工作的通知.pdf');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `position` varchar(20) NOT NULL DEFAULT 'user',
  `name` varchar(10) NOT NULL,
  `email` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'10086','123','user','马福钢',NULL),(2,'10000','123456','admin','管理员',NULL),(3,'123','123','user','新用户',NULL);
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

-- Dump completed on 2022-11-09 21:35:57
