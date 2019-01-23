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
-- Table structure for table `CoveredVerticesExperiment`
--

DROP TABLE IF EXISTS `CoveredVerticesExperiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CoveredVerticesExperiment` (
  `c` int(11) DEFAULT NULL,
  `finished` bit(1) DEFAULT NULL,
  `fvdSize` int(11) DEFAULT NULL,
  `k` int(11) DEFAULT NULL,
  `lb` int(11) NOT NULL,
  `timeout` int(11) DEFAULT NULL,
  `ub` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_fuhhnf1i2lm09n7fu4qg1ouys` FOREIGN KEY (`id`) REFERENCES `SimpleExpriment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CoveredVerticesExperiment`
--

LOCK TABLES `CoveredVerticesExperiment` WRITE;
/*!40000 ALTER TABLE `CoveredVerticesExperiment` DISABLE KEYS */;
INSERT INTO `CoveredVerticesExperiment` VALUES (7,_binary '',NULL,7,7,3000,100,2381),(8,_binary '',NULL,8,8,3000,100,2382),(9,_binary '',NULL,9,9,3000,100,2383),(10,_binary '',NULL,10,10,3000,100,2384),(11,_binary '',NULL,11,11,3000,100,2385),(12,_binary '',NULL,12,12,3000,100,2386),(13,_binary '',NULL,13,13,6000,100,2387),(13,_binary '',NULL,13,13,6000,310,2395),(12,_binary '',NULL,12,12,6000,310,2399),(14,_binary '',NULL,14,14,6000,310,2403),(15,_binary '',NULL,15,15,10000,310,2407);
/*!40000 ALTER TABLE `CoveredVerticesExperiment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-23 15:41:01
