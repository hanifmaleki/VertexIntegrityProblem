-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: backdoor
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.16.04.1

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
-- Table structure for table `SimpleFVDExperiment`
--

DROP TABLE IF EXISTS `SimpleFVDExperiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SimpleFVDExperiment` (
  `c` int(11) DEFAULT NULL,
  `finished` bit(1) DEFAULT NULL,
  `fvdSize` int(11) DEFAULT NULL,
  `k` int(11) DEFAULT NULL,
  `timeout` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `lb` int(11) NOT NULL,
  `ub` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_6m700wkjobbam050ri8wqohn4` FOREIGN KEY (`id`) REFERENCES `SimpleExpriment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SimpleFVDExperiment`
--

LOCK TABLES `SimpleFVDExperiment` WRITE;
/*!40000 ALTER TABLE `SimpleFVDExperiment` DISABLE KEYS */;
INSERT INTO `SimpleFVDExperiment` VALUES (7,_binary '',NULL,7,9000,2367,7,100),(8,_binary '',NULL,8,9000,2368,8,100),(9,_binary '',NULL,9,9000,2369,9,100),(10,_binary '',NULL,10,9000,2370,10,100),(11,_binary '',NULL,11,9000,2371,11,100),(12,_binary '',NULL,12,9000,2372,12,100),(13,_binary '',NULL,13,9000,2373,13,100),(13,_binary '',NULL,13,6000,2397,13,310),(12,_binary '',NULL,12,6000,2401,12,310),(14,_binary '',NULL,14,6000,2405,14,310),(15,_binary '\0',NULL,15,10000,2409,13,310);
/*!40000 ALTER TABLE `SimpleFVDExperiment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-23 15:41:02
