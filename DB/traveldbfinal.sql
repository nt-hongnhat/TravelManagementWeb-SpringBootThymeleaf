CREATE DATABASE  IF NOT EXISTS `traveldb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `traveldb`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: traveldb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_id` int DEFAULT '1',
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login` timestamp NULL DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `avatar_url` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `reset_token` mediumtext COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_account_permission_idx` (`permission_id`),
  CONSTRAINT `FKpv0xli6jj9jun3e7vtwvtgrne` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (38,3,'admin','$2a$10$IsI4QPlx67dw/cqskf2mg..yQ7c4lBMjrFNjZORx0auUPfNklnvHW','Nhật','Nguyễn Thị Hồng','1951052145nhat@ou.edu.vn','2022-10-12 18:50:53',NULL,_binary '',NULL,NULL),(39,1,'nguyenvana','$2a$10$lQ3Cqzy6SeNLGsvpjdj.PuUjz3IyluXXB2GJVveTPkNsDEKk50xjS','Nam','Nguyễn','admin@gmail.com','2022-10-12 19:15:51',NULL,_binary '','',NULL),(40,1,'tram130101','$2a$10$PQAaDuJq0T6p/DOZSRFxPut7mxG1gkqwaZCO3mzQLISGjl0m/8KY2','Trâm','Hoàng Võ Thái','tram13012001@gmail.com','2022-10-15 08:00:15',NULL,_binary '',NULL,NULL),(44,1,'nam1132','$2a$10$hRANlTm1arRH0PLaROxZN.zBrFeFWYdw9WxSWlB/BYan3FXKYKlBO','Nam','Nguyễn','nam1132@gmail.com','2022-10-15 08:15:44',NULL,_binary '',NULL,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency`
--

DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency`
--

LOCK TABLES `agency` WRITE;
/*!40000 ALTER TABLE `agency` DISABLE KEYS */;
/*!40000 ALTER TABLE `agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `booking_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total` decimal(18,0) NOT NULL,
  `payment` bit(1) NOT NULL DEFAULT b'0',
  `customer` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FKiakypgieoaw7snawb9ynctd2f` (`customer_id`),
  CONSTRAINT `FKiakypgieoaw7snawb9ynctd2f` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_detail`
--

DROP TABLE IF EXISTS `booking_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `ticket_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_booking_detail_booking_idx` (`booking_id`),
  KEY `fk_booking_detai_ticket_idx` (`ticket_id`),
  CONSTRAINT `fk_booking_detai_ticket` FOREIGN KEY (`ticket_id`) REFERENCES `tour_ticket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_booking_detail_booking` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKk5yrq99hisgyjn8hb4wkg3954` FOREIGN KEY (`ticket_id`) REFERENCES `booking_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_detail`
--

LOCK TABLES `booking_detail` WRITE;
/*!40000 ALTER TABLE `booking_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `link_static` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci,
  `tour_group` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `link_static_UNIQUE` (`link_static`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Du lịch Miền Bắc','du-lich-mien-bac','Du lịch Miền Bắc, du lịch hè Miền bắc - Miền Bắc mang nhiều dấu ấn lịch sử lâu đời từ thời Vua Hùng dựng nước cho đến các triều đại phong kiến giữ nước. Du lịch Miền Bắc là hành trình hấp dẫn dành cho những ai yêu thích lịch sử, mong muốn tìm hiểu và trải nghiệm những điều mới mẻ mà chỉ vùng đất \"Bắc kỳ\" mới có. Đến với Tour du lịch Miền Bắc, tour du lịch hè miền bắc du khách sẽ được tham quan những di tích lịch sử lâu đời, được hóa thân vào các vai Vua chúa, quan đại thần, cung phi, hoàng hậu,....',NULL),(2,'Du lịch Miền Trung','du-lich-mien-trung','Du lịch Miền Trung, Du lịch hè Miền trung- Miền Trung hay còn gọi là Trung Bộ, trải qua tiến trình lịch sử, Miền Trung được xem như trạm trung chuyển, là cầu nối giữa hai miền Nam - Bắc. Với địa hình nhỏ hẹp, phía Tây là dãy núi Trường Sơn, phía Đông giáp biển. Tour Miền Trung, Tour Hè Miền Trung đang trở thành xu hướng du lịch nghỉ dưỡng số một tại Việt Nam bởi nơi đây có nhiều cảnh quan và bãi biển đẹp. Đến với Tour du lịch Miền Trung, Tour Du lịch hè miền trung du khách sẽ được thả hồn vào sóng nước, được tham quan hệ sinh thái động thực vật đa dạng phong phú và khám phá những bãi biển hoang sơ.',NULL),(3,'Du lịch Miền Nam','du-lich-mien-nam','Du lịch Miền Nam - Miền đất hứa là vùng đất mang nhiều hy vọng của những người dân xa quê, là vùng đất trù phú giàu có được thiên nhiên ưu ái ban tặng. Miền Nam chính vì được thiên nhiên ưu ái nên đây cũng là vùng đất được phát triển về du lịch sinh thái. Đến với tour du lịch Miền Nam du khách sẽ được nhiều trải nghiệm du lịch sông nước với chợ Nổi, tham quan vườn Quốc gia Cát Tiên, vườn Quốc gia Tràm Chim,... cùng nhau ngắm nhìn hoàng hôn tại biển Vũng Tàu,... Ngoài ra, đến với Tour Miền Nam du khách còn được trải nghiệm làm kẹo Dừa, và hái trái miệt vườn tại Bến Tre.',NULL),(4,'Chùm tour nghỉ Lễ','chum-tour-nghi-le','Tour 30/4 và 1/5/2022 hot nhất của miền Bắc',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `news_id` int NOT NULL,
  `rating` double(1,1) NOT NULL,
  `content` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `news` tinyblob,
  `user` tinyblob,
  `account` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_comment_user` (`user_id`),
  KEY `fk_comment_news` (`news_id`),
  CONSTRAINT `fk_comment_news` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `fullname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `identifiedcard` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nationality` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user` tinyblob,
  `account` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_id_UNIQUE` (`id`),
  UNIQUE KEY `identifiedcard_id_UNIQUE` (`identifiedcard`),
  KEY `fk_customer_account` (`account_id`),
  CONSTRAINT `fk_customer_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,NULL,'Nguyễn Thị Hồng Nhật','Nữ','087301016242','Việt Nam','0836479646','49/30/13 Nguyễn Văn Đậu, phường 6, quận Bình Thạnh, TPHCM',NULL,NULL),(2,NULL,'Hoàng Võ Thái Trâm','Nữ','123456789010','Việt Nam',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `first_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `identifiedcard` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birth_date` timestamp NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user` tinyblob,
  `user_id` int DEFAULT NULL,
  `account` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `identifiedcard_UNIQUE` (`identifiedcard`),
  KEY `fk_employee_account` (`account_id`),
  CONSTRAINT `fk_employee_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int NOT NULL,
  `account_id` int NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci,
  `rating` double(2,1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_feedback_account` (`account_id`),
  KEY `fk_feedback_tour` (`tour_id`),
  CONSTRAINT `fk_feedback_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_feedback_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (8,4,38,'Du lịch giá rẻ, hướng dẫn rất nhiệt tình thân thiện',5.0),(9,5,39,'Tôi rất thích',5.0);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `local_district`
--

DROP TABLE IF EXISTS `local_district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `local_district` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `province_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_district_province_idx` (`province_id`),
  CONSTRAINT `fk_district_province` FOREIGN KEY (`province_id`) REFERENCES `local_province` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=974 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local_district`
--

LOCK TABLES `local_district` WRITE;
/*!40000 ALTER TABLE `local_district` DISABLE KEYS */;
INSERT INTO `local_district` VALUES (1,'Quận Ba Đình',1),(2,'Quận Hoàn Kiếm',1),(3,'Quận Tây Hồ',1),(4,'Quận Long Biên',1),(5,'Quận Cầu Giấy',1),(6,'Quận Đống Đa',1),(7,'Quận Hai Bà Trưng',1),(8,'Quận Hoàng Mai',1),(9,'Quận Thanh Xuân',1),(16,'Huyện Sóc Sơn',1),(17,'Huyện Đông Anh',1),(18,'Huyện Gia Lâm',1),(19,'Quận Nam Từ Liêm',1),(20,'Huyện Thanh Trì',1),(21,'Quận Bắc Từ Liêm',1),(24,'Thành phố Hà Giang',2),(26,'Huyện Đồng Văn',2),(27,'Huyện Mèo Vạc',2),(28,'Huyện Yên Minh',2),(29,'Huyện Quản Bạ',2),(30,'Huyện Vị Xuyên',2),(31,'Huyện Bắc Mê',2),(32,'Huyện Hoàng Su Phì',2),(33,'Huyện Xín Mần',2),(34,'Huyện Bắc Quang',2),(35,'Huyện Quang Bình',2),(40,'Thành phố Cao Bằng',4),(42,'Huyện Bảo Lâm',4),(43,'Huyện Bảo Lạc',4),(45,'Huyện Hà Quảng',4),(47,'Huyện Trùng Khánh',4),(48,'Huyện Hạ Lang',4),(49,'Huyện Quảng Hòa',4),(51,'Huyện Hoà An',4),(52,'Huyện Nguyên Bình',4),(53,'Huyện Thạch An',4),(58,'Thành Phố Bắc Kạn',6),(60,'Huyện Pác Nặm',6),(61,'Huyện Ba Bể',6),(62,'Huyện Ngân Sơn',6),(63,'Huyện Bạch Thông',6),(64,'Huyện Chợ Đồn',6),(65,'Huyện Chợ Mới',6),(66,'Huyện Na Rì',6),(70,'Thành phố Tuyên Quang',8),(71,'Huyện Lâm Bình',8),(72,'Huyện Na Hang',8),(73,'Huyện Chiêm Hóa',8),(74,'Huyện Hàm Yên',8),(75,'Huyện Yên Sơn',8),(76,'Huyện Sơn Dương',8),(80,'Thành phố Lào Cai',10),(82,'Huyện Bát Xát',10),(83,'Huyện Mường Khương',10),(84,'Huyện Si Ma Cai',10),(85,'Huyện Bắc Hà',10),(86,'Huyện Bảo Thắng',10),(87,'Huyện Bảo Yên',10),(88,'Thị xã Sa Pa',10),(89,'Huyện Văn Bàn',10),(94,'Thành phố Điện Biên Phủ',11),(95,'Thị Xã Mường Lay',11),(96,'Huyện Mường Nhé',11),(97,'Huyện Mường Chà',11),(98,'Huyện Tủa Chùa',11),(99,'Huyện Tuần Giáo',11),(100,'Huyện Điện Biên',11),(101,'Huyện Điện Biên Đông',11),(102,'Huyện Mường Ảng',11),(103,'Huyện Nậm Pồ',11),(105,'Thành phố Lai Châu',12),(106,'Huyện Tam Đường',12),(107,'Huyện Mường Tè',12),(108,'Huyện Sìn Hồ',12),(109,'Huyện Phong Thổ',12),(110,'Huyện Than Uyên',12),(111,'Huyện Tân Uyên',12),(112,'Huyện Nậm Nhùn',12),(116,'Thành phố Sơn La',14),(118,'Huyện Quỳnh Nhai',14),(119,'Huyện Thuận Châu',14),(120,'Huyện Mường La',14),(121,'Huyện Bắc Yên',14),(122,'Huyện Phù Yên',14),(123,'Huyện Mộc Châu',14),(124,'Huyện Yên Châu',14),(125,'Huyện Mai Sơn',14),(126,'Huyện Sông Mã',14),(127,'Huyện Sốp Cộp',14),(128,'Huyện Vân Hồ',14),(132,'Thành phố Yên Bái',15),(133,'Thị xã Nghĩa Lộ',15),(135,'Huyện Lục Yên',15),(136,'Huyện Văn Yên',15),(137,'Huyện Mù Căng Chải',15),(138,'Huyện Trấn Yên',15),(139,'Huyện Trạm Tấu',15),(140,'Huyện Văn Chấn',15),(141,'Huyện Yên Bình',15),(148,'Thành phố Hòa Bình',17),(150,'Huyện Đà Bắc',17),(152,'Huyện Lương Sơn',17),(153,'Huyện Kim Bôi',17),(154,'Huyện Cao Phong',17),(155,'Huyện Tân Lạc',17),(156,'Huyện Mai Châu',17),(157,'Huyện Lạc Sơn',17),(158,'Huyện Yên Thủy',17),(159,'Huyện Lạc Thủy',17),(164,'Thành phố Thái Nguyên',19),(165,'Thành phố Sông Công',19),(167,'Huyện Định Hóa',19),(168,'Huyện Phú Lương',19),(169,'Huyện Đồng Hỷ',19),(170,'Huyện Võ Nhai',19),(171,'Huyện Đại Từ',19),(172,'Thành phố Phổ Yên',19),(173,'Huyện Phú Bình',19),(178,'Thành phố Lạng Sơn',20),(180,'Huyện Tràng Định',20),(181,'Huyện Bình Gia',20),(182,'Huyện Văn Lãng',20),(183,'Huyện Cao Lộc',20),(184,'Huyện Văn Quan',20),(185,'Huyện Bắc Sơn',20),(186,'Huyện Hữu Lũng',20),(187,'Huyện Chi Lăng',20),(188,'Huyện Lộc Bình',20),(189,'Huyện Đình Lập',20),(193,'Thành phố Hạ Long',22),(194,'Thành phố Móng Cái',22),(195,'Thành phố Cẩm Phả',22),(196,'Thành phố Uông Bí',22),(198,'Huyện Bình Liêu',22),(199,'Huyện Tiên Yên',22),(200,'Huyện Đầm Hà',22),(201,'Huyện Hải Hà',22),(202,'Huyện Ba Chẽ',22),(203,'Huyện Vân Đồn',22),(205,'Thị xã Đông Triều',22),(206,'Thị xã Quảng Yên',22),(207,'Huyện Cô Tô',22),(213,'Thành phố Bắc Giang',24),(215,'Huyện Yên Thế',24),(216,'Huyện Tân Yên',24),(217,'Huyện Lạng Giang',24),(218,'Huyện Lục Nam',24),(219,'Huyện Lục Ngạn',24),(220,'Huyện Sơn Động',24),(221,'Huyện Yên Dũng',24),(222,'Huyện Việt Yên',24),(223,'Huyện Hiệp Hòa',24),(227,'Thành phố Việt Trì',25),(228,'Thị xã Phú Thọ',25),(230,'Huyện Đoan Hùng',25),(231,'Huyện Hạ Hoà',25),(232,'Huyện Thanh Ba',25),(233,'Huyện Phù Ninh',25),(234,'Huyện Yên Lập',25),(235,'Huyện Cẩm Khê',25),(236,'Huyện Tam Nông',25),(237,'Huyện Lâm Thao',25),(238,'Huyện Thanh Sơn',25),(239,'Huyện Thanh Thuỷ',25),(240,'Huyện Tân Sơn',25),(243,'Thành phố Vĩnh Yên',26),(244,'Thành phố Phúc Yên',26),(246,'Huyện Lập Thạch',26),(247,'Huyện Tam Dương',26),(248,'Huyện Tam Đảo',26),(249,'Huyện Bình Xuyên',26),(250,'Huyện Mê Linh',1),(251,'Huyện Yên Lạc',26),(252,'Huyện Vĩnh Tường',26),(253,'Huyện Sông Lô',26),(256,'Thành phố Bắc Ninh',27),(258,'Huyện Yên Phong',27),(259,'Huyện Quế Võ',27),(260,'Huyện Tiên Du',27),(261,'Thành phố Từ Sơn',27),(262,'Huyện Thuận Thành',27),(263,'Huyện Gia Bình',27),(264,'Huyện Lương Tài',27),(268,'Quận Hà Đông',1),(269,'Thị xã Sơn Tây',1),(271,'Huyện Ba Vì',1),(272,'Huyện Phúc Thọ',1),(273,'Huyện Đan Phượng',1),(274,'Huyện Hoài Đức',1),(275,'Huyện Quốc Oai',1),(276,'Huyện Thạch Thất',1),(277,'Huyện Chương Mỹ',1),(278,'Huyện Thanh Oai',1),(279,'Huyện Thường Tín',1),(280,'Huyện Phú Xuyên',1),(281,'Huyện Ứng Hòa',1),(282,'Huyện Mỹ Đức',1),(288,'Thành phố Hải Dương',30),(290,'Thành phố Chí Linh',30),(291,'Huyện Nam Sách',30),(292,'Thị xã Kinh Môn',30),(293,'Huyện Kim Thành',30),(294,'Huyện Thanh Hà',30),(295,'Huyện Cẩm Giàng',30),(296,'Huyện Bình Giang',30),(297,'Huyện Gia Lộc',30),(298,'Huyện Tứ Kỳ',30),(299,'Huyện Ninh Giang',30),(300,'Huyện Thanh Miện',30),(303,'Quận Hồng Bàng',31),(304,'Quận Ngô Quyền',31),(305,'Quận Lê Chân',31),(306,'Quận Hải An',31),(307,'Quận Kiến An',31),(308,'Quận Đồ Sơn',31),(309,'Quận Dương Kinh',31),(311,'Huyện Thuỷ Nguyên',31),(312,'Huyện An Dương',31),(313,'Huyện An Lão',31),(314,'Huyện Kiến Thuỵ',31),(315,'Huyện Tiên Lãng',31),(316,'Huyện Vĩnh Bảo',31),(317,'Huyện Cát Hải',31),(318,'Huyện Bạch Long Vĩ',31),(323,'Thành phố Hưng Yên',33),(325,'Huyện Văn Lâm',33),(326,'Huyện Văn Giang',33),(327,'Huyện Yên Mỹ',33),(328,'Thị xã Mỹ Hào',33),(329,'Huyện Ân Thi',33),(330,'Huyện Khoái Châu',33),(331,'Huyện Kim Động',33),(332,'Huyện Tiên Lữ',33),(333,'Huyện Phù Cừ',33),(336,'Thành phố Thái Bình',34),(338,'Huyện Quỳnh Phụ',34),(339,'Huyện Hưng Hà',34),(340,'Huyện Đông Hưng',34),(341,'Huyện Thái Thụy',34),(342,'Huyện Tiền Hải',34),(343,'Huyện Kiến Xương',34),(344,'Huyện Vũ Thư',34),(347,'Thành phố Phủ Lý',35),(349,'Thị xã Duy Tiên',35),(350,'Huyện Kim Bảng',35),(351,'Huyện Thanh Liêm',35),(352,'Huyện Bình Lục',35),(353,'Huyện Lý Nhân',35),(356,'Thành phố Nam Định',36),(358,'Huyện Mỹ Lộc',36),(359,'Huyện Vụ Bản',36),(360,'Huyện Ý Yên',36),(361,'Huyện Nghĩa Hưng',36),(362,'Huyện Nam Trực',36),(363,'Huyện Trực Ninh',36),(364,'Huyện Xuân Trường',36),(365,'Huyện Giao Thủy',36),(366,'Huyện Hải Hậu',36),(369,'Thành phố Ninh Bình',37),(370,'Thành phố Tam Điệp',37),(372,'Huyện Nho Quan',37),(373,'Huyện Gia Viễn',37),(374,'Huyện Hoa Lư',37),(375,'Huyện Yên Khánh',37),(376,'Huyện Kim Sơn',37),(377,'Huyện Yên Mô',37),(380,'Thành phố Thanh Hóa',38),(381,'Thị xã Bỉm Sơn',38),(382,'Thành phố Sầm Sơn',38),(384,'Huyện Mường Lát',38),(385,'Huyện Quan Hóa',38),(386,'Huyện Bá Thước',38),(387,'Huyện Quan Sơn',38),(388,'Huyện Lang Chánh',38),(389,'Huyện Ngọc Lặc',38),(390,'Huyện Cẩm Thủy',38),(391,'Huyện Thạch Thành',38),(392,'Huyện Hà Trung',38),(393,'Huyện Vĩnh Lộc',38),(394,'Huyện Yên Định',38),(395,'Huyện Thọ Xuân',38),(396,'Huyện Thường Xuân',38),(397,'Huyện Triệu Sơn',38),(398,'Huyện Thiệu Hóa',38),(399,'Huyện Hoằng Hóa',38),(400,'Huyện Hậu Lộc',38),(401,'Huyện Nga Sơn',38),(402,'Huyện Như Xuân',38),(403,'Huyện Như Thanh',38),(404,'Huyện Nông Cống',38),(405,'Huyện Đông Sơn',38),(406,'Huyện Quảng Xương',38),(407,'Thị xã Nghi Sơn',38),(412,'Thành phố Vinh',40),(413,'Thị xã Cửa Lò',40),(414,'Thị xã Thái Hoà',40),(415,'Huyện Quế Phong',40),(416,'Huyện Quỳ Châu',40),(417,'Huyện Kỳ Sơn',40),(418,'Huyện Tương Dương',40),(419,'Huyện Nghĩa Đàn',40),(420,'Huyện Quỳ Hợp',40),(421,'Huyện Quỳnh Lưu',40),(422,'Huyện Con Cuông',40),(423,'Huyện Tân Kỳ',40),(424,'Huyện Anh Sơn',40),(425,'Huyện Diễn Châu',40),(426,'Huyện Yên Thành',40),(427,'Huyện Đô Lương',40),(428,'Huyện Thanh Chương',40),(429,'Huyện Nghi Lộc',40),(430,'Huyện Nam Đàn',40),(431,'Huyện Hưng Nguyên',40),(432,'Thị xã Hoàng Mai',40),(436,'Thành phố Hà Tĩnh',42),(437,'Thị xã Hồng Lĩnh',42),(439,'Huyện Hương Sơn',42),(440,'Huyện Đức Thọ',42),(441,'Huyện Vũ Quang',42),(442,'Huyện Nghi Xuân',42),(443,'Huyện Can Lộc',42),(444,'Huyện Hương Khê',42),(445,'Huyện Thạch Hà',42),(446,'Huyện Cẩm Xuyên',42),(447,'Huyện Kỳ Anh',42),(448,'Huyện Lộc Hà',42),(449,'Thị xã Kỳ Anh',42),(450,'Thành Phố Đồng Hới',44),(452,'Huyện Minh Hóa',44),(453,'Huyện Tuyên Hóa',44),(454,'Huyện Quảng Trạch',44),(455,'Huyện Bố Trạch',44),(456,'Huyện Quảng Ninh',44),(457,'Huyện Lệ Thủy',44),(458,'Thị xã Ba Đồn',44),(461,'Thành phố Đông Hà',45),(462,'Thị xã Quảng Trị',45),(464,'Huyện Vĩnh Linh',45),(465,'Huyện Hướng Hóa',45),(466,'Huyện Gio Linh',45),(467,'Huyện Đa Krông',45),(468,'Huyện Cam Lộ',45),(469,'Huyện Triệu Phong',45),(470,'Huyện Hải Lăng',45),(471,'Huyện Cồn Cỏ',45),(474,'Thành phố Huế',46),(476,'Huyện Phong Điền',46),(477,'Huyện Quảng Điền',46),(478,'Huyện Phú Vang',46),(479,'Thị xã Hương Thủy',46),(480,'Thị xã Hương Trà',46),(481,'Huyện A Lưới',46),(482,'Huyện Phú Lộc',46),(483,'Huyện Nam Đông',46),(490,'Quận Liên Chiểu',48),(491,'Quận Thanh Khê',48),(492,'Quận Hải Châu',48),(493,'Quận Sơn Trà',48),(494,'Quận Ngũ Hành Sơn',48),(495,'Quận Cẩm Lệ',48),(497,'Huyện Hòa Vang',48),(498,'Huyện Hoàng Sa',48),(502,'Thành phố Tam Kỳ',49),(503,'Thành phố Hội An',49),(504,'Huyện Tây Giang',49),(505,'Huyện Đông Giang',49),(506,'Huyện Đại Lộc',49),(507,'Thị xã Điện Bàn',49),(508,'Huyện Duy Xuyên',49),(509,'Huyện Quế Sơn',49),(510,'Huyện Nam Giang',49),(511,'Huyện Phước Sơn',49),(512,'Huyện Hiệp Đức',49),(513,'Huyện Thăng Bình',49),(514,'Huyện Tiên Phước',49),(515,'Huyện Bắc Trà My',49),(516,'Huyện Nam Trà My',49),(517,'Huyện Núi Thành',49),(518,'Huyện Phú Ninh',49),(519,'Huyện Nông Sơn',49),(522,'Thành phố Quảng Ngãi',51),(524,'Huyện Bình Sơn',51),(525,'Huyện Trà Bồng',51),(527,'Huyện Sơn Tịnh',51),(528,'Huyện Tư Nghĩa',51),(529,'Huyện Sơn Hà',51),(530,'Huyện Sơn Tây',51),(531,'Huyện Minh Long',51),(532,'Huyện Nghĩa Hành',51),(533,'Huyện Mộ Đức',51),(534,'Thị xã Đức Phổ',51),(535,'Huyện Ba Tơ',51),(536,'Huyện Lý Sơn',51),(540,'Thành phố Quy Nhơn',52),(542,'Huyện An Lão',52),(543,'Thị xã Hoài Nhơn',52),(544,'Huyện Hoài Ân',52),(545,'Huyện Phù Mỹ',52),(546,'Huyện Vĩnh Thạnh',52),(547,'Huyện Tây Sơn',52),(548,'Huyện Phù Cát',52),(549,'Thị xã An Nhơn',52),(550,'Huyện Tuy Phước',52),(551,'Huyện Vân Canh',52),(555,'Thành phố Tuy Hoà',54),(557,'Thị xã Sông Cầu',54),(558,'Huyện Đồng Xuân',54),(559,'Huyện Tuy An',54),(560,'Huyện Sơn Hòa',54),(561,'Huyện Sông Hinh',54),(562,'Huyện Tây Hoà',54),(563,'Huyện Phú Hoà',54),(564,'Thị xã Đông Hòa',54),(568,'Thành phố Nha Trang',56),(569,'Thành phố Cam Ranh',56),(570,'Huyện Cam Lâm',56),(571,'Huyện Vạn Ninh',56),(572,'Thị xã Ninh Hòa',56),(573,'Huyện Khánh Vĩnh',56),(574,'Huyện Diên Khánh',56),(575,'Huyện Khánh Sơn',56),(576,'Huyện Trường Sa',56),(582,'Thành phố Phan Rang-Tháp Chàm',58),(584,'Huyện Bác Ái',58),(585,'Huyện Ninh Sơn',58),(586,'Huyện Ninh Hải',58),(587,'Huyện Ninh Phước',58),(588,'Huyện Thuận Bắc',58),(589,'Huyện Thuận Nam',58),(593,'Thành phố Phan Thiết',60),(594,'Thị xã La Gi',60),(595,'Huyện Tuy Phong',60),(596,'Huyện Bắc Bình',60),(597,'Huyện Hàm Thuận Bắc',60),(598,'Huyện Hàm Thuận Nam',60),(599,'Huyện Tánh Linh',60),(600,'Huyện Đức Linh',60),(601,'Huyện Hàm Tân',60),(602,'Huyện Phú Quí',60),(608,'Thành phố Kon Tum',62),(610,'Huyện Đắk Glei',62),(611,'Huyện Ngọc Hồi',62),(612,'Huyện Đắk Tô',62),(613,'Huyện Kon Plông',62),(614,'Huyện Kon Rẫy',62),(615,'Huyện Đắk Hà',62),(616,'Huyện Sa Thầy',62),(617,'Huyện Tu Mơ Rông',62),(618,'Huyện Ia H\' Drai',62),(622,'Thành phố Pleiku',64),(623,'Thị xã An Khê',64),(624,'Thị xã Ayun Pa',64),(625,'Huyện KBang',64),(626,'Huyện Đăk Đoa',64),(627,'Huyện Chư Păh',64),(628,'Huyện Ia Grai',64),(629,'Huyện Mang Yang',64),(630,'Huyện Kông Chro',64),(631,'Huyện Đức Cơ',64),(632,'Huyện Chư Prông',64),(633,'Huyện Chư Sê',64),(634,'Huyện Đăk Pơ',64),(635,'Huyện Ia Pa',64),(637,'Huyện Krông Pa',64),(638,'Huyện Phú Thiện',64),(639,'Huyện Chư Pưh',64),(643,'Thành phố Buôn Ma Thuột',66),(644,'Thị Xã Buôn Hồ',66),(645,'Huyện Ea H\'leo',66),(646,'Huyện Ea Súp',66),(647,'Huyện Buôn Đôn',66),(648,'Huyện Cư M\'gar',66),(649,'Huyện Krông Búk',66),(650,'Huyện Krông Năng',66),(651,'Huyện Ea Kar',66),(652,'Huyện M\'Đrắk',66),(653,'Huyện Krông Bông',66),(654,'Huyện Krông Pắc',66),(655,'Huyện Krông A Na',66),(656,'Huyện Lắk',66),(657,'Huyện Cư Kuin',66),(660,'Thành phố Gia Nghĩa',67),(661,'Huyện Đăk Glong',67),(662,'Huyện Cư Jút',67),(663,'Huyện Đắk Mil',67),(664,'Huyện Krông Nô',67),(665,'Huyện Đắk Song',67),(666,'Huyện Đắk R\'Lấp',67),(667,'Huyện Tuy Đức',67),(672,'Thành phố Đà Lạt',68),(673,'Thành phố Bảo Lộc',68),(674,'Huyện Đam Rông',68),(675,'Huyện Lạc Dương',68),(676,'Huyện Lâm Hà',68),(677,'Huyện Đơn Dương',68),(678,'Huyện Đức Trọng',68),(679,'Huyện Di Linh',68),(680,'Huyện Bảo Lâm',68),(681,'Huyện Đạ Huoai',68),(682,'Huyện Đạ Tẻh',68),(683,'Huyện Cát Tiên',68),(688,'Thị xã Phước Long',70),(689,'Thành phố Đồng Xoài',70),(690,'Thị xã Bình Long',70),(691,'Huyện Bù Gia Mập',70),(692,'Huyện Lộc Ninh',70),(693,'Huyện Bù Đốp',70),(694,'Huyện Hớn Quản',70),(695,'Huyện Đồng Phú',70),(696,'Huyện Bù Đăng',70),(697,'Thị xã Chơn Thành',70),(698,'Huyện Phú Riềng',70),(703,'Thành phố Tây Ninh',72),(705,'Huyện Tân Biên',72),(706,'Huyện Tân Châu',72),(707,'Huyện Dương Minh Châu',72),(708,'Huyện Châu Thành',72),(709,'Thị xã Hòa Thành',72),(710,'Huyện Gò Dầu',72),(711,'Huyện Bến Cầu',72),(712,'Thị xã Trảng Bàng',72),(718,'Thành phố Thủ Dầu Một',74),(719,'Huyện Bàu Bàng',74),(720,'Huyện Dầu Tiếng',74),(721,'Thị xã Bến Cát',74),(722,'Huyện Phú Giáo',74),(723,'Thị xã Tân Uyên',74),(724,'Thành phố Dĩ An',74),(725,'Thành phố Thuận An',74),(726,'Huyện Bắc Tân Uyên',74),(731,'Thành phố Biên Hòa',75),(732,'Thành phố Long Khánh',75),(734,'Huyện Tân Phú',75),(735,'Huyện Vĩnh Cửu',75),(736,'Huyện Định Quán',75),(737,'Huyện Trảng Bom',75),(738,'Huyện Thống Nhất',75),(739,'Huyện Cẩm Mỹ',75),(740,'Huyện Long Thành',75),(741,'Huyện Xuân Lộc',75),(742,'Huyện Nhơn Trạch',75),(747,'Thành phố Vũng Tàu',77),(748,'Thành phố Bà Rịa',77),(750,'Huyện Châu Đức',77),(751,'Huyện Xuyên Mộc',77),(752,'Huyện Long Điền',77),(753,'Huyện Đất Đỏ',77),(754,'Thị xã Phú Mỹ',77),(755,'Huyện Côn Đảo',77),(760,'Quận 1',79),(761,'Quận 12',79),(764,'Quận Gò Vấp',79),(765,'Quận Bình Thạnh',79),(766,'Quận Tân Bình',79),(767,'Quận Tân Phú',79),(768,'Quận Phú Nhuận',79),(769,'Thành phố Thủ Đức',79),(770,'Quận 3',79),(771,'Quận 10',79),(772,'Quận 11',79),(773,'Quận 4',79),(774,'Quận 5',79),(775,'Quận 6',79),(776,'Quận 8',79),(777,'Quận Bình Tân',79),(778,'Quận 7',79),(783,'Huyện Củ Chi',79),(784,'Huyện Hóc Môn',79),(785,'Huyện Bình Chánh',79),(786,'Huyện Nhà Bè',79),(787,'Huyện Cần Giờ',79),(794,'Thành phố Tân An',80),(795,'Thị xã Kiến Tường',80),(796,'Huyện Tân Hưng',80),(797,'Huyện Vĩnh Hưng',80),(798,'Huyện Mộc Hóa',80),(799,'Huyện Tân Thạnh',80),(800,'Huyện Thạnh Hóa',80),(801,'Huyện Đức Huệ',80),(802,'Huyện Đức Hòa',80),(803,'Huyện Bến Lức',80),(804,'Huyện Thủ Thừa',80),(805,'Huyện Tân Trụ',80),(806,'Huyện Cần Đước',80),(807,'Huyện Cần Giuộc',80),(808,'Huyện Châu Thành',80),(815,'Thành phố Mỹ Tho',82),(816,'Thị xã Gò Công',82),(817,'Thị xã Cai Lậy',82),(818,'Huyện Tân Phước',82),(819,'Huyện Cái Bè',82),(820,'Huyện Cai Lậy',82),(821,'Huyện Châu Thành',82),(822,'Huyện Chợ Gạo',82),(823,'Huyện Gò Công Tây',82),(824,'Huyện Gò Công Đông',82),(825,'Huyện Tân Phú Đông',82),(829,'Thành phố Bến Tre',83),(831,'Huyện Châu Thành',83),(832,'Huyện Chợ Lách',83),(833,'Huyện Mỏ Cày Nam',83),(834,'Huyện Giồng Trôm',83),(835,'Huyện Bình Đại',83),(836,'Huyện Ba Tri',83),(837,'Huyện Thạnh Phú',83),(838,'Huyện Mỏ Cày Bắc',83),(842,'Thành phố Trà Vinh',84),(844,'Huyện Càng Long',84),(845,'Huyện Cầu Kè',84),(846,'Huyện Tiểu Cần',84),(847,'Huyện Châu Thành',84),(848,'Huyện Cầu Ngang',84),(849,'Huyện Trà Cú',84),(850,'Huyện Duyên Hải',84),(851,'Thị xã Duyên Hải',84),(855,'Thành phố Vĩnh Long',86),(857,'Huyện Long Hồ',86),(858,'Huyện Mang Thít',86),(859,'Huyện  Vũng Liêm',86),(860,'Huyện Tam Bình',86),(861,'Thị xã Bình Minh',86),(862,'Huyện Trà Ôn',86),(863,'Huyện Bình Tân',86),(866,'Thành phố Cao Lãnh',87),(867,'Thành phố Sa Đéc',87),(868,'Thành phố Hồng Ngự',87),(869,'Huyện Tân Hồng',87),(870,'Huyện Hồng Ngự',87),(871,'Huyện Tam Nông',87),(872,'Huyện Tháp Mười',87),(873,'Huyện Cao Lãnh',87),(874,'Huyện Thanh Bình',87),(875,'Huyện Lấp Vò',87),(876,'Huyện Lai Vung',87),(877,'Huyện Châu Thành',87),(883,'Thành phố Long Xuyên',89),(884,'Thành phố Châu Đốc',89),(886,'Huyện An Phú',89),(887,'Thị xã Tân Châu',89),(888,'Huyện Phú Tân',89),(889,'Huyện Châu Phú',89),(890,'Huyện Tịnh Biên',89),(891,'Huyện Tri Tôn',89),(892,'Huyện Châu Thành',89),(893,'Huyện Chợ Mới',89),(894,'Huyện Thoại Sơn',89),(899,'Thành phố Rạch Giá',91),(900,'Thành phố Hà Tiên',91),(902,'Huyện Kiên Lương',91),(903,'Huyện Hòn Đất',91),(904,'Huyện Tân Hiệp',91),(905,'Huyện Châu Thành',91),(906,'Huyện Giồng Riềng',91),(907,'Huyện Gò Quao',91),(908,'Huyện An Biên',91),(909,'Huyện An Minh',91),(910,'Huyện Vĩnh Thuận',91),(911,'Thành phố Phú Quốc',91),(912,'Huyện Kiên Hải',91),(913,'Huyện U Minh Thượng',91),(914,'Huyện Giang Thành',91),(916,'Quận Ninh Kiều',92),(917,'Quận Ô Môn',92),(918,'Quận Bình Thuỷ',92),(919,'Quận Cái Răng',92),(923,'Quận Thốt Nốt',92),(924,'Huyện Vĩnh Thạnh',92),(925,'Huyện Cờ Đỏ',92),(926,'Huyện Phong Điền',92),(927,'Huyện Thới Lai',92),(930,'Thành phố Vị Thanh',93),(931,'Thành phố Ngã Bảy',93),(932,'Huyện Châu Thành A',93),(933,'Huyện Châu Thành',93),(934,'Huyện Phụng Hiệp',93),(935,'Huyện Vị Thuỷ',93),(936,'Huyện Long Mỹ',93),(937,'Thị xã Long Mỹ',93),(941,'Thành phố Sóc Trăng',94),(942,'Huyện Châu Thành',94),(943,'Huyện Kế Sách',94),(944,'Huyện Mỹ Tú',94),(945,'Huyện Cù Lao Dung',94),(946,'Huyện Long Phú',94),(947,'Huyện Mỹ Xuyên',94),(948,'Thị xã Ngã Năm',94),(949,'Huyện Thạnh Trị',94),(950,'Thị xã Vĩnh Châu',94),(951,'Huyện Trần Đề',94),(954,'Thành phố Bạc Liêu',95),(956,'Huyện Hồng Dân',95),(957,'Huyện Phước Long',95),(958,'Huyện Vĩnh Lợi',95),(959,'Thị xã Giá Rai',95),(960,'Huyện Đông Hải',95),(961,'Huyện Hoà Bình',95),(964,'Thành phố Cà Mau',96),(966,'Huyện U Minh',96),(967,'Huyện Thới Bình',96),(968,'Huyện Trần Văn Thời',96),(969,'Huyện Cái Nước',96),(970,'Huyện Đầm Dơi',96),(971,'Huyện Năm Căn',96),(972,'Huyện Phú Tân',96),(973,'Huyện Ngọc Hiển',96);
/*!40000 ALTER TABLE `local_district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `local_province`
--

DROP TABLE IF EXISTS `local_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `local_province` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local_province`
--

LOCK TABLES `local_province` WRITE;
/*!40000 ALTER TABLE `local_province` DISABLE KEYS */;
INSERT INTO `local_province` VALUES (89,'An Giang'),(77,'Bà Rịa - Vũng Tàu'),(24,'Bắc Giang'),(6,'Bắc Kạn'),(95,'Bạc Liêu'),(27,'Bắc Ninh'),(83,'Bến Tre'),(74,'Bình Dương'),(52,'Bình Định'),(70,'Bình Phước'),(60,'Bình Thuận'),(96,'Cà Mau'),(92,'Cần Thơ'),(4,'Cao Bằng'),(48,'Đà Nẵng'),(66,'Đắk Lắk'),(67,'Đắk Nông'),(11,'Điện Biên'),(75,'Đồng Nai'),(87,'Đồng Tháp'),(64,'Gia Lai'),(2,'Hà Giang'),(35,'Hà Nam'),(1,'Hà Nội'),(42,'Hà Tĩnh'),(30,'Hải Dương'),(31,'Hải Phòng'),(93,'Hậu Giang'),(79,'Hồ Chí Minh'),(17,'Hoà Bình'),(33,'Hưng Yên'),(56,'Khánh Hòa'),(91,'Kiên Giang'),(62,'Kon Tum'),(12,'Lai Châu'),(68,'Lâm Đồng'),(20,'Lạng Sơn'),(10,'Lào Cai'),(80,'Long An'),(36,'Nam Định'),(40,'Nghệ An'),(37,'Ninh Bình'),(58,'Ninh Thuận'),(25,'Phú Thọ'),(54,'Phú Yên'),(44,'Quảng Bình'),(49,'Quảng Nam'),(51,'Quảng Ngãi'),(22,'Quảng Ninh'),(45,'Quảng Trị'),(94,'Sóc Trăng'),(14,'Sơn La'),(72,'Tây Ninh'),(34,'Thái Bình'),(19,'Thái Nguyên'),(38,'Thanh Hóa'),(46,'Thừa Thiên Huế'),(82,'Tiền Giang'),(84,'Trà Vinh'),(8,'Tuyên Quang'),(86,'Vĩnh Long'),(26,'Vĩnh Phúc'),(15,'Yên Bái');
/*!40000 ALTER TABLE `local_province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_group_id` int DEFAULT NULL,
  `title` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `images` mediumtext COLLATE utf8mb4_unicode_ci,
  `access` bit(1) NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `meta_description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `meta_keywords` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `meta_title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user` tinyblob,
  `user_id` int DEFAULT NULL,
  `tour_group` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_news_tour_group_idx` (`tour_group_id`),
  CONSTRAINT `fk_news_tour_group` FOREIGN KEY (`tour_group_id`) REFERENCES `tour_group` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (4,4,'Du lịch Sapa chiêm ngưỡng tiên cảnh nơi phố núi tại đỉnh Ô Quy Hồ','2022-10-16 07:02:42','Vốn được biết đến là một trong Tứ đại đỉnh đèo của khu vực miền núi phía Bắc, đèo Ô Quy Hồ đã trở thành một điểm đến yêu thích của hội “cuồng đi” và đam mê sống ảo. Cùng tìm hiểu xem có điều gì thú vị tại Ô Quy Hồ mà khách du lịch Sapa lại tìm đến nhiều như vậy nhé! Đối với các bạn phượt thủ, hành trình du lịch Sapa chắc chắn sẽ không bao giờ trọn vẹn nếu như chưa chinh phục được đèo Ô Quy Hồ nổi tiếng hiểm trở và chênh vênh. Cảm giác được băng qua cung đường ngoằn ngoèo với những góc cua vô cùng hiểm trở chắc chắn rất hồi hộp nhưng cũng đầy thú vị. Đặc biệt, khi đã lên được đến đỉnh đèo thì trước mắt bạn hiện ra chính là khung cảnh thiên nhiên Tây Bắc vô cùng hùng vĩ. Đèo Ô Quy Hồ - một trong tứ đại đỉnh đèo nổi tiếng của Việt Nam. Đèo Ô Quy Hồ vốn nổi tiếng là con đèo lớn và đẹp nhất tại Sapa, đồng thời cũng là con đèo dài nhất khu vực Tây Bắc. Đèo Ô Quy Hồ là  cung đường trọng yếu nối liền 2 tỉnh Lào Cai và Lai Châu. Cái tên Ô Quy Hồ tương truyền được bắt nguồn từ một câu chuyện tình bi thương của một chàng tiều phu và một nàng tiên vô cùng xinh đẹp. Vì tình yêu không thành mà nàng đã hóa thành một loài chim mãi kêu “Ô Quy Hồ” một cách tha thiết. Từ đó, người dân nơi đây đã dùng tiếng kêu này để đặt tên cho con đèo hiểm trở được khách đi tour du lich Sapa đặc biệt quan tâm. Đèo Ô Quy Hồ nằm trên tuyến đường qua dãy Hoàng Liên Sơn và cũng bởi vì thế mà đèo còn được gọi là đèo Hoàng Liên. Đèo Ô Quy Hồ sở hữu độ cao hơn 2000m so với mực nước biển với nhiều khúc uốn lượn hiểm trở, cùng chiều dài lên đến 50km. So với nhiều con đèo khác thì đèo Ô Quy Hồ được mệnh danh là “ông vua không ngai” của “tứ đại đỉnh đèo” khu vực Tây Bắc và cũng được xếp vào danh sách những địa điểm du lich Sapa “hot” nhất. Đèo Ô Quy Hồ Lào Cai nằm ngay trên quốc lộ 4D nên để có thể đến được nơi này, du khách cần phải bắt xe đi đến thị trấn Sapa. Từ đây, du khách sẽ hỏi người dân trong trấn đường ra quốc lộ và họ sẽ chỉ cho bạn đường để lên đỉnh Ô Quý Hồ Sapa ngắn nhất. Thật ra đoạn đường này cũng không quá dài bởi đèo Ô Quy Hồ chỉ cách Sapa khoảng hơn 15km (tính từ trung tâm thị trấn). Tuy nhiên, đoạn đường sẽ có nhiều khúc uốn lượn cực kỳ khó đi, hiểm trở, đặc biệt là đoạn từ Trạm Tôn đổ xuống chân đèo. Khám phá vẻ đẹp đỉnh đèo Ô Quy Hồ trong tour du lịch Sapa.  Đèo Ô Quy Hồ là con đèo dài nhất Việt Nam tính cho đến năm 2020. Ở phía hai bên đèo một bên sẽ là vực sâu không nhìn thấy đáy, bên còn lại chính là những vách đá nhọn dựng đứng hiểm trở. Biển cảnh báo nguy hiểm sẽ được dựng khắp hai bên đường. Thông thường những phượt thủ sẽ chọn cho mình lộ trình đi tour Sapa sao cho khi về qua ô Quy Hồ thì trời đã bước sang ngưỡng chiều tà.  Vào những đêm rằm, ánh sáng của trăng sẽ chếch trải xuống những vách núi sừng sững, hắt bóng về phía những khúc cua hiểm trở, khi nhìn nghiêng hệt như một bức tranh thủy mặc đậm chất oai hùng. Đây thực sự là một nét chấm phá mang đầy vẻ huyền ảo. Nếu đứng sát vào một góc cua bên cạnh vách núi dựng đứng, du khách sẽ có thể lắng nghe được hơi thở lặng im đến rợn gáy của núi rừng trùng điệp nơi đây.  Sau khi đã đi qua được những ngọn thác hùng vĩ, du khách đi tour du lịch Sapa sẽ chinh phục được đỉnh đèo Ô Quy Hồ hay còn được gọi với cái tên khác là Cổng trời Ô Quy Hồ, cổng trời Sapa. Chắc chắn bạn sẽ phải hối tiếc khi đến Sapa du lịch mà bỏ qua nơi này. Tại khu vực đỉnh đèo, bạn sẽ thấy toàn cảnh dãy Hoàng Liên Sơn kỳ vĩ đang ẩn chìm trong biển sương mù mây trắng. Hơn thế nữa, vào mỗi mùa, cổng Trời Ô Quy Hồ sẽ hiện ra một khung cảnh tuyệt đẹp để du khách có thể thỏa sức chiêm ngưỡng.  Không hổ danh khi đèo Ô Quy Hồ đã được đưa vào danh sách một trong tứ đại đỉnh đèo lớn nhất của Việt Nam. Đèo Ô Quy Hồ thực sự là một tuyệt tác của thiên nhiên quá đỗi hùng tráng, thơ mộng mà tin chắc rằng bất kỳ ai chỉ cần một lần ghé qua sẽ chẳng thể nào quên được. Vậy còn chần chờ gì mà chưa lên lịch trình cho mình một chuyến trải nghiệm tour Sapa và ghé qua đèo Ô Quy Hồ. Tin chắc rằng bạn sẽ cảm thấy thích thú với những gì mà Du Lịch Việt đã chia sẻ đấy! ',_binary '','https://dulichviet.com.vn/images/bandidau/deo-o-quy-ho-duoc-menh-danh-la-mot-trong-tu-dai-dinh-deo-tay-bac.jpg',_binary '\0','','','','',NULL,NULL,NULL),(5,NULL,'Du lịch Mùa Nước Nổi','2022-10-16 07:06:29','Mùa nước nổi, hay còn gọi là mùa lũ sông Cửu Long, là một hiện tượng lũ lụt tự nhiên, đặc trưng của vùng đồng bằng sông Cửu Long ở Việt Nam, vùng hạ lưu sông Mekong, Biển Hồ và Tonle Sap ở Campuchia. Nhưng tên gọi Mùa nước nổi là để gọi riêng cho mùa lũ ở Đồng bằng sông Cửu Long Việt Nam. Mùa nước nổi du lịch nơi nào ở miền tây?  Cứ tới tháng 7,8 ,9 âm lịch hằng năm khi con nước từ thượng nguồn sông mekong đổ về thì những vùng quê đồng bằng sông Cửu Long lại thêm chút sắc màu của mùa nước nổi nào là cảnh quan cho tới những món ăn đặc sản của miền tây nam bộ. Hàng năm vào mùa mưa  từ thượng nguồn sông mekong tràn về đồng tháp mười và tứ giác Long Xuyên tạo nên một mùa nước nổi. Nước tràn về Đồng Tháp và An Giang đầu tiên nên ở đây cảnh quan đẹp, cùng nhiều những món ăn từ mùa nước nổi mang lại. Một nơi không thể không nhắc tới ở Đồng Tháp vào mùa nước nổi đó là vườn Tràm Chim. Nếu đi một mình bạn sẽ rấ khó khắn để đi hết diện tích 7.000 ha chỉ trong một ngày vì thế bạn nên lên kế hoạch cho kỳ nghỉ của mình dài ngày để thêm thú vị.Thường người ta tới tràm chim sẽ chọn lúc bình minh để ngắm cảnh nhưng  bạn có thể bắt đầu vào lúc biểu chiều để cảm nhận cái thú vị của hoàng hồn buông. Với màu đỏ rực của mặt trời bắt đầu lặn sau những cánh rừng tràm bạt ngàn những cánh chim về tổ chao mình trong bóng hoàng hôn giữa bức tranh thiên nhiên sôi động nhưng cực kỳ yên bình. Sau đó sáng hôm sau bạn se dậy sớm để khám phá chàm chim vào buổi bình minh cùng những cánh cò trắng xóa, những con cò con diêng diêc đòi ăn... sau khi đã thăm thú xong vườn chàm chim bạn sẽ chuẩn bị hành trình tới An Giang để khám phá nét kỳ thú của Rừng Tràm Trà Sư.  Đến Trà Sư vào mùa nước nổi khi con nước lũ lên bạn như lạc vào một thế giới khác, đây là khu bảo tồn hệ sinh cảnh tự nhiên tiêu biểu và độc đáo nhất của hệ sinh thái ngập nước trong vùng đồng bằng sông Cửu Long, vào mùa nước nổi chẳng có gì thú vị bằng việc ngồi thuyền đi xuyên rừng để ngắm những sắc hoa tràm trắng tinh, bên dưới một thảm thực vật bèo tấm xanh ngắt, cùng những đàn chim đua nhau gọi bầy tạo thành bản hòa ca hùng vĩ.',_binary '','https://dulichviet.com.vn/images/bandidau/_thumbs/Images/TIN%20TUC/du-lich-an-giang-mua-nuoc-noi-du-lich-viet-1.jpg',_binary '\0','','','','',NULL,NULL,NULL),(6,13,'Kinh nghiệm du lịch Côn Đảo bằng tàu cao tốc từ Hà Nội giá rẻ nhất','2022-10-16 07:15:51','Côn Đảo từ xưa đến nay vẫn luôn nổi tiếng là một hòn đảo đẹp, hoang sơ và rất đỗi linh thiêng thu hút nhiều du khách tham quan. Trước đây để đến được Côn Đảo, bạn sẽ phải di chuyển bằng máy bay với chi phí đắt đỏ hoặc đi bằng tàu chậm hơn 13 giờ lênh đênh trên mặt biển. Tuy nhiên, hiện nay điều này đã không còn là trở ngại nữa với hình thức du lịch Côn Đảo bằng tàu cao tốc.  Trải nghiệm du lịch Côn Đảo bằng tàu cao tốc Du lịch Côn Đảo ngày nay đang từng bước khởi sắc và việc đi lại từ đất liền ra đảo đã không còn gặp nhiều trở ngại như trước đây nữa. Từ TPHCM hoặc Cần Thơ, để đến được Côn Đảo bạn cần trải qua một chặng bay. Tuy nhiên, nếu đi tour du lịch Côn Đảo từ Hà Nội, bạn phải trung chuyển qua 2 chặng là bay vào TPHCM hoặc Cần Thơ rồi mới đến được Côn Đảo. Là một mảnh đất rất hoang sơ, nằm tách biệt, Côn Đảo vốn là một điểm du lịch phải mất nhiều thời gian để di chuyển đến đó. Tùy thuộc vào địa điểm khác nhau mà bạn sẽ quyết định lựa chọn cho mình chặng đi phù hợp. So với việc phải mất hàng giờ đồng hồ lênh đênh trên biển bằng tàu chậm thì hiện nay đã có một hình thức di chuyển nhanh chóng hơn rất nhiều đó là nhờ vào tàu cao tốc. Những chuyến tàu cao tốc đi Côn Đảo sẽ xuất phát từ Vũng Tàu, Sài Gòn, Sóc Trăng và Cần Thơ bao gồm các loại sau: Tàu Superdong đi Côn Đảo (Tàu Sóc Trăng Côn Đảo) Tàu cao tốc đi Côn Đảo Express Tàu cao tốc Côn Đảo Greenlines Tàu cao tốc Vũng Tàu Côn Đảo CQ – 03 Những lưu ý dành cho bạn khi chọn tàu cao tốc đi Côn Đảo Nếu đã quyết định chọn tàu cao tốc đi Côn Đảo, bạn nhất định phải lưu ý một vài điều như sau: Nên đặt vé tàu sớm trước khi di chuyển khoảng 2 tuần và nên chọn mua loại vé khứ hồi. Giá vé tàu cao tốc sẽ có sự thay đổi theo từng thời điểm khác nhau nên bạn cần phải tìm hiểu kỹ thông tin này. Ở Cảng Trần Đề sẽ có dịch vụ nhận trông giữ xe nên bạn có thể yên tâm nếu di chuyển đến cảng bằng xe máy. Ngoài ra, tàu cao tốc cũng sẽ có dịch vụ vận chuyển xe ra Côn Đảo cho bạn. Nếu là một người bị say tàu xe và say sóng, hãy chủ động trang bị thuốc chống say tàu xe để đảm bảo sức khỏe tốt cho chuyến đi. Để chuyến đi trở nên thuận lợi, sau khi đặt vé tàu, hãy đặt ngay khách sạn hoặc homestay để tránh trường hợp không có chỗ ở khi đặt chân đến Côn Đảo. Đi du lịch Côn Đảo ngày nay đã trở nên đơn giản và dễ dàng hơn rất nhiều nhờ vào hình thức di chuyển bằng tàu cao tốc. Không những vậy, dịch vụ tại Côn Đảo cũng đang ngày một phát triển mạnh với nhiều khu nghỉ dưỡng cao cấp, chất lượng. Trong tương lai, chắc chắn Côn Đảo sẽ còn thay đổi nhiều hơn nữa nên nếu có cơ hội, hãy thử một lần ghé thăm mảnh đất thiêng liêng này bạn nhé!',_binary '',NULL,_binary '\0','','','','',NULL,NULL,NULL);
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'CUSTOMER','Khách hàng được phép đặt tour trực tuyến'),(2,'EMPLOYEE','Nhân viên được phép đặt tour cho khách hàng, quản lý thông tin khách hàng'),(3,'ADMIN','Quản trị viên quản lý tất cả dữ liệu hệ thống, có toàn quyền thêm sửa xoá');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `places`
--

DROP TABLE IF EXISTS `places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `places` (
  `id` int NOT NULL AUTO_INCREMENT,
  `district_id` int unsigned DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci,
  `images` mediumtext COLLATE utf8mb4_unicode_ci,
  `district` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`) /*!80000 INVISIBLE */,
  KEY `fk_places_district_idx` (`district_id`),
  CONSTRAINT `fk_places_district` FOREIGN KEY (`district_id`) REFERENCES `local_district` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `places`
--

LOCK TABLES `places` WRITE;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
INSERT INTO `places` VALUES (21,494,'Ngũ Hành Sơn','Phường Hoà Hải, Quận Ngũ Hành Sơn, Thành phố Đà Nẵng','Ngũ Hành Sơn không cao, sườn núi dốc đứng cheo leo, cây cỏ lơ thơ. Đá ở Ngũ Hành Sơn là loại đá cẩm thạch có nhiều màu: sáng đục, trắng sữa, hồng phấn, xám vân đỏ, nâu đen, xanh đậm..., không cứng lắm và là chất liệu rất tốt cho tạc tượng và đồ mỹ nghệ trang trí. Có nhiều truyền thuyết về sự hình thành Ngũ Hành Sơn, trong đó có truyện kể rằng: “Ngày xưa, nơi đây là một vùng biển hoang vu, chỉ có một ông già sống đơn độc trong một túp lều tranh. Một hôm, trời đang sáng bỗng nhiên tối sầm, giông bão nổi lên, một con giao long rất lớn xuất hiện vùng vẫy trên bãi cát và một quả trứng khổng lồ từ từ lăn ra ở dưới bụng. Sau đó giao long quay ra biển đi mất. Lát sau, một con rùa vàng xuất hiện, tự xưng là thần Kim Quy, đào cát vùi quả trứng xuống và giao cho ông già nhiệm vụ bảo vệ giọt máu của Long Quân. Quả trứng càng ngày càng lớn, nhô lên cao chiếm cả một vùng đất rộng lớn. Vỏ trứng ánh lên đủ mầu sắc xanh, đỏ, trắng, vàng, tím lấp lánh như một hòn gạch khổng lồ. Một hôm, ông lão vừa chợp mắt thì nghe có tiếng lửa cháy, ông cầu cứu móng rùa - vật mà thần Kim Quy đã giao lại cho ông lúc ra đi và trong lòng trứng xuất hiện một cái hang rộng rãi, mát mẻ. Ông đặt lưng xuống ngủ thiếp luôn và không biết đang xẩy ra một phép lạ: một cô gái xinh xắn bước ra từ trong trứng và nơi ông nằm là một trong năm hòn đá cẩm thạch vừa được hình thành từ năm mảnh vỏ của quả trứng...”.','http://csdl.vietnamtourism.gov.vn/uploads/images/CSDLDLVN2019/DaNang/Nguhanhson/Nguhanhson2.jpg',NULL),(22,1,'Chùa Một Cột','Phố Chùa Một Cột, Phường Đội Cấn, Quận Ba Đình, Thành phố Hà Nội','Chùa Một Cột có tên chữ là Diên Hựu (phúc lành dài lâu) được xây dựng vào năm 1049 thời vua Lý Thái Tông. Tương truyền khi ấy vua Lý Thái Tông đã cao tuổi mà chưa có con trai nên nhà vua thường đến các chùa để cầu tự. Một đêm ông chiêm bao thấy Đức Phật Quan Âm hiện lên đài hoa sen ở một hồ nước hình vuông phía tây thành Thăng Long, tay bế đứa con trai trao cho nhà vua. Ít lâu sau hoàng hậu sinh con trai. Nhà vua cho dựng chùa Một Cột có dáng dấp như đã thấy trong giấc mơ để thờ Phật Quan Âm.','http://csdl.vietnamtourism.gov.vn/uploads/images/CSDLDLVN2019/HaNoi/Chuamotcot/Chuamotcot04.jpg',NULL),(23,6,'Văn Miếu - Quốc Tử Giám','58 Quốc Tử Giám, Phường Quốc Tử Giám, Quận Đống Đa, Thành phố Hà Nội','Văn Miếu được xây dựng từ năm 1070 (tức năm Thần Vũ thứ hai đời Vua Lý Thánh Tông) để thờ các bậc Tiên thánh, Tiên sư của đạo Nho, đồng thời mang chức năng của một trường học Hoàng gia mà học trò đầu tiên là Thái tử Lý Càn Đức, con trai Vua Lý Thánh Tông. Năm 1076, Vua Lý Nhân Tông cho lập Quốc Tử Giám ở bên cạnh Văn Miếu với vai trò là trường học dành riêng cho con vua và con các bậc đại quyền quý trong triều đình. Năm 1253, Vua Trần Thái Tông đổi Quốc Tử Giám thành Quốc học viện, mở rộng và thu nhận cả con cái các nhà thường dân có học lực xuất sắc. Đến đời Vua Trần Minh Tông (1314 – 1329), nhà giáo Chu Văn An được cử làm quan Quốc Tử Giám Tư nghiệp (hiệu trưởng) và thầy dạy trực tiếp cho các hoàng tử. Năm 1370, sau khi ông mất, Vua Trần Nghệ Tông đã cho thờ ở Văn Miếu bên cạnh Khổng Tử. Năm 1785, Vua Lê Hiển Tông đổi Quốc Tử Giám thành nhà Thái học. Đến đầu thời Nguyễn, Quốc Tử Giám được lập tại Huế, nhà Thái học đổi thành nhà Khải Thánh.','http://csdl.vietnamtourism.gov.vn/uploads/images/CSDLDLVN2019/HaNoi/VMQTGiam/VanmieuQTG09JPG.jpg',NULL),(24,2,'Hồ Hoàn Kiếm (Hồ Gươm)','Phường Hàng Trống, Quận Hoàn Kiếm, Thành phố Hà Nội','Hồ Hoàn Kiếm, hay còn gọi là Hồ Gươm được mệnh danh là viên ngọc sáng của Thủ đô Hà Nội. Nơi đây đã trở thành điểm đến nổi tiếng thu hút du khách, gắn liền với đời sống văn hóa của người dân Hà Thành bao đời nay.','http://csdl.vietnamtourism.gov.vn/uploads/images/CSDLDLVN2019/HaNoi/Hohoankiem/HoGuom05JPg.jpg',NULL);
/*!40000 ALTER TABLE `places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surcharge`
--

DROP TABLE IF EXISTS `surcharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surcharge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `surcharge` double(1,1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surcharge`
--

LOCK TABLES `surcharge` WRITE;
/*!40000 ALTER TABLE `surcharge` DISABLE KEYS */;
INSERT INTO `surcharge` VALUES (1,'Phụ thu Nước Ngoài',0.0);
/*!40000 ALTER TABLE `surcharge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_destination`
--

DROP TABLE IF EXISTS `tour_destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_destination` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int NOT NULL,
  `places_id` int NOT NULL,
  `places` tinyblob,
  `tour` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_schedule_itinerary_idx` (`tour_id`),
  KEY `fk_tour_destination_place_idx` (`places_id`),
  CONSTRAINT `fk_tour_destination_place` FOREIGN KEY (`places_id`) REFERENCES `places` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_destination_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_destination`
--

LOCK TABLES `tour_destination` WRITE;
/*!40000 ALTER TABLE `tour_destination` DISABLE KEYS */;
INSERT INTO `tour_destination` VALUES (4,4,22,NULL,NULL),(5,4,23,NULL,NULL),(6,4,24,NULL,NULL);
/*!40000 ALTER TABLE `tour_destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_group`
--

DROP TABLE IF EXISTS `tour_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `link_static` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` mediumtext COLLATE utf8mb4_unicode_ci,
  `category` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_tour_group_category_idx` (`category_id`),
  CONSTRAINT `fk_tour_group_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_group`
--

LOCK TABLES `tour_group` WRITE;
/*!40000 ALTER TABLE `tour_group` DISABLE KEYS */;
INSERT INTO `tour_group` VALUES (1,3,'Du lịch Đồng Tháp','du-lich-dong-thap',NULL,NULL),(2,3,'Du lịch Tiền Giang','du-lich-tien-giang','Du lịch Tiền Giang - Không chỉ là một trong những vựa cây trái lớn nhất nhì cả nước, Khi đến với Du lịch Mỹ Tho - Tiền Giang còn được biết đến là nơi có phong cảnh hữu tình và đặc sản độc đáo. Chắc chắn đây sẽ là một trong những sự lựa chọn thú vị cho du khách khám phá Du lịch Mỹ Tho - Tiền Giang thông qua các tour Du lịch Tiền Giang.',NULL),(3,2,'Du lịch Phú Yên','du-lich-phu-yen','Du lịch Phú Yên - Là một điểm đến mới thú vị hấp dẫn nhất hiện nay, Phú Yên sẽ mang đến cho du khách kỳ nghỉ dưỡng ấn tượng. Đến với tour du lịch Phú Yên du khách không những có dịp thưởng ngoạn những danh lam thắng cảnh đẹp nhất Phú Yên như: Vực Phun, Vũng Rô, Hòn Nưa, Hải đăng Đại Lãnh, Đảo hòn Chùa,... mà còn được tận hưởng bầu khí hậu ôn đới nên quanh năm mát mẻ, dễ chịu.',NULL),(4,1,'Du lịch Sapa','du-lich-sapa','Du lịch Sapa - \"Thành phố trong sương\" nổi tiếng với với cảnh quan núi non hùng vĩ, khí hậu trong lành hoang sơ, quanh năm mát mẻ sẽ giúp bạn có được những giờ phút nghỉ dưỡng đúng nghĩa. Đến với tour Sapa bạn sẽ được trải nghiệm ngắm tuyết rơi vào mùa Đông, trèo lên đỉnh núi Fansipan - nơi được mệnh danh là nóc nhà Đông Dương, và được dạo quanh thung lũng Mường Hoa làm say lòng người. Thông qua tour du lịch Sapa du khách còn có dịp được trải nghiệm nhiều nét văn hóa độc đáo với cuộc sống của đồng bào dân tộc thiểu số vùng Miền núi phía Bắc, thưởng thức các món ăn truyền thống đặc sắc nơi đây.',NULL),(5,2,'Du lịch Phan Thiết','du-lich-phan-thiet','Du lịch Phan Thiết - Thành phố biển Phan Thiết không chỉ nổi tiếng với những bãi biển xanh biếc, những hàng dừa cao vút, mà nơi đây còn thu hút du khách với những danh thắng như: Lầu Ông Hoàng, tháp Chàm Pôshanư, bãi đá Ông Địa, rạn dừa Hàm Tiến... Đến với tour Phan Thiết, du khách sẽ có dịp chiêu ngưỡn nét đẹp hoang sơ từ phong cảnh đến những bãi biển chưa được khám phá, được thưởng thức những món ăn đậm chất miền biển,... ',NULL),(6,1,'Du lịch Hạ Long','du-lich-ha-long','Du lịch Hạ Long - Vịnh Hạ Long một trong Bảy kỳ quan thiên nhiên thế giới mới, và được Thế giới công nhận là di sản thiên nhiên thế giới. Chính bởi vẻ đẹp hùng vĩ và nguyên sơ, tour du lịch Hạ Long luôn là một sự lựa chọn hàng đầu được nhiều du khách cả trong và ngoài nước tìm đến để khám phá tham quan và du lịch. Vịnh Hạ Long Không chỉ nổi tiếng bởi vẻ đẹp của một bức tranh thủy mạc đẹp vô vàn, từ các đảo lớn nhỏ cho đến những hang động đá vôi huyền bí, Vịnh Hạ Long hiện lên sừng sững trên tờ tiền Polime 200.000đ mang nét đẹp văn hóa Việt Nam.',NULL),(7,3,'Du lịch Châu Đốc','du-lich-chau-doc','Du lịch Châu Đốc - Châu Đốc là một thành phố trực thuộc tỉnh An Giang, Việt Nam, nằm ở đồng bằng sông Cửu Long, sát biên giới Việt Nam với Campuchia. Vào mùa lễ hội hằng năm, về đồng bằng sông Cửu Long, du khách đến Châu Đốc (An Giang) sẽ là một chuyến đi với nhiều khám phá. Nếu như du khách là một người có tâm nguyện, cầu mong sự tốt lành cho người thân, thì tour du lịch Châu Đốc hứa hẹn sẽ đưa du khách đến các điểm du lịch nổi tiếng linh thiêng là Miếu Bà Chùa Xứ, hoặc Tây An Cổ Tự hay Lăng Thoại Ngọc Hầu,...',NULL),(8,4,'Tour du lịch mùa thu','tour-du-lich-mua-thu','Du lịch mùa thu - Thời khắc giao mùa Hè sang Thu là khoảng thời gian thật đẹp trong năm, mùa hạ vẫn chưa hết oi nồng nhưng đã phản phất hương Thu, cùng những cơn mưa mát dịu. Đó là khoảng thời gian được thi ca nhắc đến nhiều. Mùa Thu đến, hàng loạt cảnh quan khắp nơi trên Thế Giới khoác lên mình một màu áo mới như để mời gọi khách đi tour du lịch mùa thu để đến khám phá và thưởng thức vẻ đẹp của đất trời, của cây cối và con người ở từng địa điểm.',NULL),(9,4,'Tour du lịch mùa lúa chín','tour-du-lich-mua-lua-chin',NULL,NULL),(10,4,'Chùm tour khám phá mùa hoa','chum-tour-kham-pha-mua-hoa',NULL,NULL),(11,2,'Du lịch Hội An','du-lich-hoi-an','Du lịch Hội An - Hội An Nằm trên con đường di sản Miền Trung nổi tiếng, phố cổ Hội An thu hút được rất đông đảo du khách cả trong và ngoài nước đến tham quan nghỉ dưỡng. Tour Du lịch Hội An sẽ đưa du khách đi thưởng ngoạn vẻ đẹp của phố cổ, qua những công trình kiến trúc đặc trưng lâu đời rất đỗi mộc mạc, giản dị và nên thơ. Dù là ngày hay đêm, nơi đây vẫn mang trong mình những vẻ đẹp lôi cuốn, hấp dẫn người đối diện.',NULL),(12,1,'Du lịch Tây Bắc','du-lich-tay-bac','Du lịch Tây Bắc - Tây Bắc nổi tiếng với cảnh quan thiên nhiên kỳ vĩ, nền văn hóa đặc sắc và lịch sử hào hùng trong thời kỳ kháng chiến dân tộc. Đến với Tour du lịch Tây Bắc, du khách sẽ được hòa mình vào thiên nhiên Tây Bắc đẹp nên thơ, hữu tình. Mùa Xuân khi mà hoa Mơ, hoa Mận nở trắng một vùng trời Tây Bắc thì hoa Đào, hoa Tam Giác Mạch lại đem về một hương sắc thật tươi mới, mộng mơ. Chuyến du lịch Tây Bắc hứa hẹn sẽ là một hành trình khám phá nghỉ dưỡng thú vị, đem lại những kiến thức, những cảm nhận mới về khu di tích tạo nên lịch sử. ',NULL),(13,3,'Du lịch Côn Đảo','du-lich-con-dao','Du lịch Côn Đảo xuất phát từ HN, Những tour du lịch Xuất phát từ HCM, đưa Quý khách đến với Côn Đảo, nổi tiếng với vẻ đẹp hoang sơ, Côn Đảo ngày nay là một trong những điểm đến du lịch hấp dẫn du khách nhất ở Việt Nam cũng như quốc tế. Du khách yêu thiên nhiên biển đảo tham gia tour du lịch Côn Đảo tại Du Lịch Việt để tận hưởng những giờ phút nghỉ ngơi, thư giãn, trải nghiệm đầy thú vị cho kỳ nghỉ của mình.',NULL);
/*!40000 ALTER TABLE `tour_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_info`
--

DROP TABLE IF EXISTS `tour_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_group_id` int DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `itinerary` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duration` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `unit_price` decimal(18,0) NOT NULL,
  `max_slot` tinyint NOT NULL,
  `transfer` tinytext COLLATE utf8mb4_unicode_ci NOT NULL,
  `departure_place` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `destination_place` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` longtext COLLATE utf8mb4_unicode_ci,
  `description` longtext COLLATE utf8mb4_unicode_ci,
  `tour_group` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_tour_group_idx` (`tour_group_id`),
  CONSTRAINT `fk_tour_group` FOREIGN KEY (`tour_group_id`) REFERENCES `tour_group` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_info`
--

LOCK TABLES `tour_info` WRITE;
/*!40000 ALTER TABLE `tour_info` DISABLE KEYS */;
INSERT INTO `tour_info` VALUES (4,4,'Du lịch Tết Âm lịch - Tour Hà Nội - Yên Tử - Hạ Long - Bắc Ninh - Đền Đồ - Ninh Binh - Tràng An - Sapa từ Sài Gòn 2023','Hà Nội - Yên Tử - Hạ Long - Bắc Ninh - Đền Đồ - Ninh Binh - Chùa Bái Đính - Tràng An - Sapa - Chinh Phục Đỉnh Fansipan','6 ngày 5 đêm',15499000,30,'Xe du lịch, Máy bay  ','Hồ Chí Minh','Sa Pa',NULL,'Du lịch Tết Âm lịch - Tour Hà Nội - Yên Tử - Hạ Long - Bắc Ninh - Đền Đồ - Ninh Binh - Tràng An - Sapa từ Sài Gòn 2023. Miền Bắc là nơi khởi nguồn văn hóa ngàn năm văn hiến của dân tộc Việt Nam. Du lịch miền Bắc du khách sẽ được khám phá những thắng cảnh thiên nhiên đẹp mê hồn cùng nhiều công trình kiến trúc ấn tượng được tạo nên bởi bàn tay khéo léo của con người. Điểm du lịch Tràng An là nơi du khách sẽ được khám phá một trong những địa điểm du lịch đẹp nhất Ninh Bình. Tạo hóa đã vô cùng ưu ái ban tặng cho nơi đây một cảnh quan thiên nhiên tuyệt đẹp với các dãy núi uốn lượn bao quanh các dòng Suối nước tự nhiên, tạo nên vô vàn các hang động kỳ ảo, huyền bí....',NULL),(5,8,'Du lịch mùa Thu - Tour Du lịch Thanh Hóa - Pù Luông - Vịnh Hạ Long - Yên Từ từ Sài Gòn 2022','Pù Luông – Vịnh Hạ Long – Yên Tử - Tràng An - Bái Đính - Ninh Bình','4 ngày 3 đêm',8999000,20,'Xe du lịch, Máy bay  ','Hồ Chí Minh','Thanh Hoá','https://dulichviet.com.vn/images/bandidau/NOI-DIA/Ha-Long/du-lich-ha-long-mua-thu-thien-cung-du-lich-viet.jpg','Du lịch mùa Thu - Tour Du lịch Thanh Hóa - Pù Luông - Vịnh Hạ Long - Yên Từ từ Sài Gòn 2022. Không cần phải đi đến những vùng đất Tây Bắc xa xôi, chỉ cần tới Pù Luông Thanh Hóa, bạn đã có thể đắm chìm trong một thiên nhiên hoang sơ của núi rừng, hít thở bầu không khí trong lành mát dịu và chiêm ngưỡng tất cả nét đẹp dung dị hòa quyện với mây trời. Pù Luông trong tiếng Thái là đỉnh núi cao nhất. Khu bảo tồn thiên nhiên Pù Luông thuộc hai huyện Bá Thước và Quan Hóa, cách thành phố Thanh Hóa 130 km về phía Tây Bắc. Với diện tích hơn 17.600 ha cùng hệ động thực vật phong phú, Pù Luông gây ấn tượng với du khách bằng vẻ đẹp hoang sơ của những khu rừng rậm nguyên sinh, những thửa ruộng bậc thang cùng với cuộc sống yên bình của đồng bào dân tộc miền núi. Đây là điểm du lịch hấp dẫn dành cho những ai yêu thiên nhiên và muốn khám phá những vùng đất mới.',NULL);
/*!40000 ALTER TABLE `tour_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_price`
--

DROP TABLE IF EXISTS `tour_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_price` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int DEFAULT NULL,
  `group_price` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `unit_price` decimal(18,0) NOT NULL,
  `tour` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_tour_price_tour` (`tour_id`),
  CONSTRAINT `fk_tour_price_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour_info` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_price`
--

LOCK TABLES `tour_price` WRITE;
/*!40000 ALTER TABLE `tour_price` DISABLE KEYS */;
INSERT INTO `tour_price` VALUES (1,4,'Người lớn(Trên 11 tuổi)',15499000,NULL),(2,4,'Trẻ em(5 - 11 tuổi)',11500000,NULL),(3,4,'Trẻ nhỏ(2 - 5 tuổi)',6900000,NULL),(4,4,'Sơ sinh( < 2 tuổi)',300000,NULL),(9,5,'Người lớn(Trên 11 tuổi)',8999000,NULL),(10,5,'Trẻ em(5 - 11 tuổi)',6900000,NULL),(11,5,'Trẻ nhỏ(2 - 5 tuổi)',2700000,NULL),(12,5,'Sơ sinh( < 2 tuổi)',300000,NULL);
/*!40000 ALTER TABLE `tour_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_schedule`
--

DROP TABLE IF EXISTS `tour_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int DEFAULT NULL,
  `ordinal_date` tinyint(1) NOT NULL,
  `itinerary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `tour` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_timetable_tour_idx` (`tour_id`),
  CONSTRAINT `fk_timetable_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_schedule`
--

LOCK TABLES `tour_schedule` WRITE;
/*!40000 ALTER TABLE `tour_schedule` DISABLE KEYS */;
INSERT INTO `tour_schedule` VALUES (7,4,1,'TP.HCM – HÀ NỘI (Ăn trưa, chiều)','Sáng: Quý khách có mặt tại ga quốc nội, sân bay Tân Sơn Nhất trước giờ bay ít nhất ba tiếng. Đại diện công ty Du Lịch Việt đón và hỗ trợ Quý Khách làm thủ tục đón chuyến bay đi Hà Nội. Đến sân bay Nội Bài, Hướng Dẫn Viên đón đoàn, Tham quan thủ đô với: Phủ Chủ Tịch, ao cá, nhà sàn Bác Hồ, Chùa Một Cột, Bảo Tàng Hồ Chí Minh. Trưa: Dùng bữa trưa. Tham quan Văn Miếu-Quốc Tử Giám, chùa Trấn Quốc, Hồ Tây, Hồ Trúc Bạch, Hồ Hoàn Kiếm, Đền Ngọc Sơn. Tối: Dùng bữa tối. Nghỉ đêm tại Hà Nội. Quý khách có thể tham gia phố đi bộ, chợ đêm, chợ Đồng Xuân, thưởng thức đặc sản và mua quà lưu niệm.',NULL),(8,4,2,'HÀ NỘI – LÀO CAI - SAPA (Ăn sáng, trưa, chiều)','Sáng: Dùng buffet sáng tại khách sạn. Đoàn khởi hành đến Lào Cai trên con đường cao tốc dài nhất Việt Nam - nối liền giữa Hà Nội và các tỉnh Tây Bắc. Trưa: Dùng bữa trưa. Đoàn tiếp tục đến thị trấn vùng cao Sapa, tận hưởng cảnh sắc núi rừng như tranh vẽ và khám phá cuộc sống của đồng bào dân tộc ít người miền Tây Bắc. Thăm bản Cát Cát, tìm hiểu nghề dệt nhuộm của dân tộc H’Mông và trạm thủy điện Cát Cát thời Pháp – nơi có 3 dòng nước hợp nhau thành dòng suối Mường Hoa. Tối: Dùng bữa tối. Nghỉ đêm tại Sapa.  Tự do dạo phố, tham quan nhà thờ đá Sapa, tham dự đêm chợ Tình (nếu đi vào tối thứ 7).',NULL),(9,4,3,'SAPA – FANSIPAN – HÀ NỘI (Ăn sáng, trưa, chiều)','Sáng: Dùng buffet sáng tại khách sạn. Đoàn khởi hành tham quan chinh phục Fansipan, ngọn núi cao nhất Việt Nam (3.143m) thuộc dãy núi Hoàng Liên Sơn, cách thị trấn Sa Pa khoảng 9km về phía tây nam.Du khách chinh phục \"Nóc nhà Đông Dương\" với hệ thống cáp treo Fansipan SaPa dài 6,2km đạt 2 kỷ lục Guinness thế giới: cáp treo ba dây có độ chênh giữa ga đi và ga đến lớn nhất thế giới: 1410m và cáp treo ba dây dài nhất thế giới: 6292.5m. Từ tuyến cáp treo, du khách có thể cảm nhận được thiên nhiên hùng vĩ, chiêm ngưỡng khung cảnh thung lũng Mường Hoa và vườn quốc gia Hoàng Liên từ trên cao. Ngoài ra, du khách còn có thể đến hành hương tại Khu du lịch tâm linh – Chùa Trình, Chùa Hạ tại ga đến (chi phí cáp treo tự túc). Trưa: Dùng bữa trưa. Đoàn trở về Hà Nội Tối: Dùng bữa tối. Nghỉ đêm tại Hà Nội.',NULL),(10,4,4,'HÀ NỘI – HẠ LONG (Ăn sáng, trưa, chiều)','Sáng: Dùng buffet sáng tại khách sạn. Đoàn Viếng Lăng Bác (trừ thứ 2, thứ 6 hàng tuần bảo trì Lăng). Trưa: Dùng cơm trưa. Quý khách lên núi bằng cáp treo (chi phí cáp treo tự túc), tham quan chùa Hoa Yên, Bảo Tháp, Trúc Lâm Tam Tổ, Hàng Tùng 700 tuổi…xuống núi tham quan Thiền Viện Trúc Lâm với quả cầu Như Ý nặng 6 tấn được xếp kỷ lục guiness Việt Nam. Đoàn khởi hành đến Hạ Long.',NULL),(11,4,5,'HẠ LONG – NINH BÌNH (Ăn sáng, trưa, chiều)','Sáng: Dùng buffet sáng tại khách sạn. Quý khách xuống thuyền ngoạn cảnh Vịnh Hạ Long – Di sản thiên nhiên thế giới với hàng ngàn đảo đá có hình dạng kỳ vị - chiêm ngưỡng vẻ đẹp trau chuốt, lộng lẫy của động Thiên Cung, vẻ đẹp siêu nhiên của hòn Đỉnh Hương, Gà Chọi, Chó Đá,… Trưa: Dùng cơm trưa. Khởi hành đi Ninh Bình, nơi có danh thắng Tràng An nơi được UNESCO công nhận là di sản hỗn hợp văn hóa và thiên nhiên của thế giới.  Tối: Dùng bữa tối. Nghỉ đêm tại Ninh Bình. Quý khách có thể tự do dạo phố, thưởng thức các món đặc sản Ninh Binh như thịt dê núi, ốc núi, nem Yên Mạc, cơm cháy,...',NULL),(12,4,6,'NINH BÌNH – HÀ NỘI – TP.HCM (Ăn sáng, trưa)','Sáng: Dùng buffet sáng tại khách sạn. Đoàn xuôi thuyền đi dọc theo suối giữa cánh đồng lúa thăm Khu du lịch Tràng An nơi những dải đá vôi, thung lũng và những sông ngòi đan xen tạo nên một không gian huyền ảo, kỳ bí, quý khách đi đò thăm Hang sáng, Hang tối, Hang nấu rượu và tìm hiểu văn hóa lịch sử nơi đây. Trưa: Dùng bữa trưa. Tham quan chùa Bái Đính – ngôi chùa có nhiều kỷ lục nhất Việt Nam (ngôi chùa có diện tích rộng nhất – Tượng Phật bằng đồng lớn nhất Việt Nam). Hướng dẫn viên tiễn đoàn ra sân bay Nội Bài đón chuyến bay về  TP.HCM. Kết thúc chương trình tham quan, chia tay và hẹn gặp lại.',NULL),(14,5,1,'TP.HCM – HÀ NỘI – YÊN TỬ (Ăn trưa, chiều)','Sáng:  Quý khách có mặt tại ga quốc nội, sân bay Tân Sơn Nhất trước giờ bay ít nhất ba tiếng.Đại diện công ty Du Lịch Việt đón và hỗ trợ Quý Khách làm thủ tục đón chuyến bay đi Hà Nội.Đến sân bay Nội Bài, Hướng dẫn viên đón đoàn khởi hành đến Hạ Long, trên đường dừng chân tham quan núi Yên Tử - ngọn núi cao 1068 m so với mực nước biển, một thắng cảnh thiên nhiên còn lưu giữ hệ thống các di tích lịch sử văn hóa gắn với sự ra đời, hình thành và phát triển của thiền phái Trúc Lâm Yên Tử, được mệnh danh là “đất tổ Phật giáo Việt Nam”.  Trưa: Dùng cơm trưa. Quý khách lên núi bằng cáp treo (chi phí cáp treo tự túc), tham quan chùa Hoa Yên, Bảo Tháp, Trúc Lâm Tam Tổ, Hàng Tùng 700 tuổi…xuống núi tham quan Thiền Viện Trúc Lâm với quả cầu Như Ý nặng 6 tấn được xếp kỷ lục guiness Việt Nam. Đoàn khởi hành về thành phố Hạ Long. Chiều: Dùng bữa chiều. Nghỉ đêm tại Hạ Long. Quý khách tự do dạo phố, mua sắm tại chợ đêm hoặc tham gia khu Sunworld Hạ Long Park với tất cả khu trò chơi trong nhà, ngoài trời hoành tráng có các khu Công viên Rồng, Cáp treo Nữ Hoàng vòng quay Sun wheel…(chi phí tự túc).',NULL),(15,5,2,'VỊNH HẠ LONG – NINH BÌNH – CHÙA BÁI ĐÍNH (Ăn sáng, trưa, chiều)','Sáng: Dùng buffet sáng tại khách sạn. Đoàn xuống thuyền ngoạn cảnh Vịnh Hạ Long – Di sản thiên nhiên thế giới với hàng ngàn đảo đá có hình dạng kỳ vị - chiêm ngưỡng vẻ đẹp trau chuốt, lộng lẫy của động Thiên Cung, vẻ đẹp siêu nhiên của hòn Đỉnh Hương, Gà Chọi, Chó Đá… Trưa: Dùng bữa trưa. Khởi hành đi Ninh Bình, nơi có danh thắng Tràng An nơi được UNESCO công nhận là di sản hỗn hợp văn hóa và thiên nhiên của thế giới. Tham quan chùa Bái Đính – ngôi chùa có nhiều kỷ lục nhất Việt Nam (ngôi chùa có diện tích rộng nhất – Tượng Phật bằng đồng lớn nhất Việt Nam).  Tối: Dùng bữa tối. Nghỉ đêm tại Ninh Bình. Quý khách có thể tự do dạo phố, thưởng thức các món đặc sản Ninh Binh như thịt dê núi, ốc núi, nem Yên Mạc, cơm cháy,...​​​​​​ ',NULL),(16,5,3,'TRÀNG AN – PÙ LUÔNG (Ăn sáng, trưa, chiều)','Sáng:  Dùng buffet sáng tại khách sạn. Đoàn xuôi thuyền đi dọc theo suối giữa cánh đồng lúa thăm Khu du lịch Tràng An nơi những dải đá vôi, thung lũng và những sông ngòi đan xen tạo nên một không gian huyền ảo, kỳ bí, quý khách đi đò thăm Hang sáng, Hang tối, Hang nấu rượu và tìm hiểu văn hóa lịch sử nơi đây. Trưa: Dùng bữa trưa. Khởi hành đi Pù Luông, trên đường đi, đoàn sẽ chiêm ngưỡng những thửa ruộng bậc thang đặc trưng của núi rừng Tây Bắc, ngắm nhìn khu bảo tồn thiên nhiên Pù Luông hùng vĩ và nguyên sơ. Tối:  Dùng bữa tối. Nghỉ đêm tại Pù Luông.',NULL),(17,5,4,'PÙ LUÔNG – THÁC HIÊU – TP.HCM (Ăn sáng, trưa)','Sáng: Dùng bữa sáng tại khách sạn. Đoàn khám phá Pù Luông, xuyên qua những cánh đồng ruộng bậc thang, băng qua con suối đến với Chợ Phiên Phố Đoàn để hiểu thêm về cuộc sống của người dân địa phương, tham quan Thác Hiêu, Guồng Quay Nước. Trưa: Dùng bữa trưa. Đoàn khởi hành về Hà Nội, Hướng dẫn viên tiễn đoàn ra sân bay Nội Bài đón chuyến bay về TP.HCM. Kết thúc chuyến tham quan, chia tay đoàn và hẹn gặp lại.',NULL);
/*!40000 ALTER TABLE `tour_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_ticket`
--

DROP TABLE IF EXISTS `tour_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `agency_id` int DEFAULT NULL,
  `tour_id` int DEFAULT NULL,
  `surcharge_id` int DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(18,0) NOT NULL,
  `quantity` tinyint NOT NULL,
  `agency` tinyblob,
  `surcharge` tinyblob,
  `tour` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_ticket_tour_idx` (`tour_id`),
  KEY `fk_ticket_agency_idx` (`agency_id`),
  KEY `fk_ticket_surcharge_idx` (`surcharge_id`),
  CONSTRAINT `fk_ticket_agency` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_surcharge` FOREIGN KEY (`surcharge_id`) REFERENCES `surcharge` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour_info` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_ticket`
--

LOCK TABLES `tour_ticket` WRITE;
/*!40000 ALTER TABLE `tour_ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `tour_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_for`
--

DROP TABLE IF EXISTS `work_for`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_for` (
  `id` int NOT NULL AUTO_INCREMENT,
  `agency_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `hire_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `agency` tinyblob,
  `employee` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_work_for_agency_idx` (`agency_id`),
  KEY `fk_work_for_employee_idx` (`employee_id`),
  CONSTRAINT `fk_work_for_agency` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_work_for_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_for`
--

LOCK TABLES `work_for` WRITE;
/*!40000 ALTER TABLE `work_for` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_for` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-17 11:47:22
