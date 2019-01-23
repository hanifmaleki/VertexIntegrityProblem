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
-- Table structure for table `LBMinorLPExpriment`
--

DROP TABLE IF EXISTS `LBMinorLPExpriment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LBMinorLPExpriment` (
  `c` int(11) DEFAULT NULL,
  `k` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `exact` bit(1) NOT NULL,
  `timeout` int(11) DEFAULT NULL,
  `vertexCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_g6sri6h9khvlcg8fnsx3vwgnj` FOREIGN KEY (`id`) REFERENCES `SimpleExpriment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LBMinorLPExpriment`
--

LOCK TABLES `LBMinorLPExpriment` WRITE;
/*!40000 ALTER TABLE `LBMinorLPExpriment` DISABLE KEYS */;
INSERT INTO `LBMinorLPExpriment` VALUES (17,17,441,_binary '',-1,100),(18,17,442,_binary '',-1,100),(19,17,443,_binary '',-1,100),(20,16,444,_binary '',-1,100),(21,16,445,_binary '',-1,100),(22,16,446,_binary '',-1,100),(23,16,447,_binary '',-1,100),(24,16,448,_binary '',-1,100),(25,16,449,_binary '',-1,100),(26,16,450,_binary '',-1,100),(38,6,451,_binary '',-1,100),(39,6,452,_binary '',-1,100),(40,6,453,_binary '',-1,100),(41,6,454,_binary '',-1,100),(42,6,455,_binary '',-1,100),(43,6,456,_binary '',-1,100),(38,7,457,_binary '',-1,150),(39,7,458,_binary '',-1,150),(40,7,459,_binary '',-1,150),(41,7,460,_binary '',-1,150),(42,7,461,_binary '',-1,150),(43,7,462,_binary '',-1,150),(39,8,463,_binary '',3000,200),(40,8,464,_binary '',3000,200),(41,6,465,_binary '\0',3000,200),(42,8,466,_binary '',3000,200),(43,8,467,_binary '',3000,200),(36,8,468,_binary '\0',3000,250),(37,5,469,_binary '\0',3000,250),(38,5,470,_binary '\0',3000,250),(39,9,471,_binary '\0',3000,250),(40,8,472,_binary '\0',3000,250),(41,7,473,_binary '\0',3000,250),(42,7,474,_binary '\0',3000,250),(43,5,475,_binary '\0',3000,250),(36,6,476,_binary '\0',3000,300),(37,5,477,_binary '\0',3000,300),(38,6,478,_binary '\0',3000,300),(39,6,479,_binary '\0',3000,300),(40,6,480,_binary '\0',3000,300),(41,6,481,_binary '\0',3000,300),(42,5,482,_binary '\0',3000,300),(43,5,483,_binary '\0',3000,300),(36,26,484,_binary '\0',3000,200),(37,26,485,_binary '\0',3000,200),(38,26,486,_binary '\0',3000,200),(39,26,487,_binary '\0',3000,200),(40,26,488,_binary '\0',3000,200),(41,26,489,_binary '\0',3000,200),(42,25,490,_binary '\0',3000,200),(43,25,491,_binary '\0',3000,200);
/*!40000 ALTER TABLE `LBMinorLPExpriment` ENABLE KEYS */;
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
