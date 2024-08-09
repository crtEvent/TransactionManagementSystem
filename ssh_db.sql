-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: ssh_db
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `company_idx` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(500) DEFAULT NULL,
  `company_reg_num` varchar(500) DEFAULT NULL,
  `representative` varchar(500) DEFAULT NULL,
  `address_1` varchar(500) DEFAULT NULL,
  `address_2` varchar(500) DEFAULT NULL,
  `tel` varchar(500) DEFAULT NULL,
  `fax` varchar(500) DEFAULT NULL,
  `mobile` varchar(500) DEFAULT NULL,
  `ect` varchar(500) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`company_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `file_idx` bigint NOT NULL AUTO_INCREMENT,
  `transaction_idx` bigint DEFAULT NULL,
  `file_name` varchar(500) DEFAULT NULL,
  `file_path` varchar(2000) DEFAULT NULL,
  `file_type` varchar(500) DEFAULT NULL,
  `item_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`file_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory_file`
--

DROP TABLE IF EXISTS `inventory_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_file` (
  `file_idx` bigint NOT NULL,
  `item_idx` bigint DEFAULT NULL,
  `FILE_NAME` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`file_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory_item`
--

DROP TABLE IF EXISTS `inventory_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_item` (
  `item_idx` bigint NOT NULL AUTO_INCREMENT,
  `company_idx` bigint DEFAULT NULL,
  `company_name` varchar(500) DEFAULT NULL,
  `item_code` varchar(500) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `unit_price` bigint DEFAULT NULL,
  `initial_quantity` bigint DEFAULT NULL,
  `current_quantity` bigint DEFAULT NULL,
  PRIMARY KEY (`item_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_idx` bigint NOT NULL AUTO_INCREMENT,
  `transaction_idx` bigint DEFAULT NULL,
  `inventory_item_idx` bigint DEFAULT NULL,
  `item_code` varchar(500) DEFAULT NULL,
  `content` text,
  `amount` bigint DEFAULT NULL,
  `unit_price` bigint DEFAULT NULL,
  `supply_price` bigint DEFAULT NULL,
  `tax_price` bigint DEFAULT NULL,
  `total_price` bigint DEFAULT NULL,
  PRIMARY KEY (`item_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_history`
--

DROP TABLE IF EXISTS `item_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_history` (
  `company_idx` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(500) DEFAULT NULL,
  `item_code` varchar(500) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `transaction_idx` bigint DEFAULT NULL,
  `transaction_type` varchar(500) DEFAULT NULL,
  `content` text,
  `amount` bigint DEFAULT NULL,
  `unit_price` bigint DEFAULT NULL,
  `supply_price` bigint DEFAULT NULL,
  `tax_price` bigint DEFAULT NULL,
  `total_price` bigint DEFAULT NULL,
  PRIMARY KEY (`company_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `memo`
--

DROP TABLE IF EXISTS `memo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memo` (
  `memo_idx` bigint NOT NULL AUTO_INCREMENT,
  `transaction_idx` bigint DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`memo_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `path`
--

DROP TABLE IF EXISTS `path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `path` (
  `path_idx` bigint NOT NULL AUTO_INCREMENT,
  `path_name` varchar(500) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`path_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `total_price`
--

DROP TABLE IF EXISTS `total_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `total_price` (
  `unit_price` bigint NOT NULL AUTO_INCREMENT,
  `supply_price` bigint DEFAULT NULL,
  `tax_price` bigint DEFAULT NULL,
  `total_price` bigint DEFAULT NULL,
  PRIMARY KEY (`unit_price`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_idx` bigint NOT NULL AUTO_INCREMENT,
  `company_idx` bigint DEFAULT NULL,
  `transaction_type` varchar(500) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `subject` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`transaction_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_idx` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(500) DEFAULT NULL,
  `user_password` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-09  7:34:08
