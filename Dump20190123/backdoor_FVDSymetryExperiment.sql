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
-- Table structure for table `FVDSymetryExperiment`
--

DROP TABLE IF EXISTS `FVDSymetryExperiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FVDSymetryExperiment` (
  `c` int(11) DEFAULT NULL,
  `finished` bit(1) DEFAULT NULL,
  `fvdSize` int(11) DEFAULT NULL,
  `k` int(11) DEFAULT NULL,
  `lb` int(11) NOT NULL,
  `timeout` int(11) DEFAULT NULL,
  `ub` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_1qv2j5wlp4f94asg15n2a343s` FOREIGN KEY (`id`) REFERENCES `SimpleExpriment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FVDSymetryExperiment`
--

LOCK TABLES `FVDSymetryExperiment` WRITE;
/*!40000 ALTER TABLE `FVDSymetryExperiment` DISABLE KEYS */;
INSERT INTO `FVDSymetryExperiment` VALUES (4,_binary '',NULL,4,5,3000,10,4741),(5,_binary '',NULL,5,6,3000,10,4742),(2,_binary '',NULL,2,3,3000,10,4743),(3,_binary '',3,3,3,3000,10,4746),(3,_binary '',NULL,3,4,3000,14,4747),(4,_binary '',NULL,4,5,3000,14,4748),(5,_binary '',5,5,5,3000,2,4749),(6,_binary '',5,6,5,3000,5,4750),(7,_binary '',NULL,7,8,3000,15,4751),(8,_binary '',7,8,7,3000,7,4752),(9,_binary '',NULL,9,10,3000,20,4753),(10,_binary '\0',10,10,9,3000,10,4754),(3,_binary '',NULL,3,4,3000,22,4755),(4,_binary '',NULL,4,5,3000,22,4756),(5,_binary '',NULL,5,6,3000,22,4757),(6,_binary '',NULL,6,6,3000,22,4758),(7,_binary '',6,7,6,3000,3,4759),(12,_binary '\0',NULL,12,8,3000,25,4760),(13,_binary '\0',12,13,8,3000,12,4761),(3,_binary '',NULL,3,4,3000,30,4762),(4,_binary '',NULL,4,5,3000,30,4763),(5,_binary '',NULL,5,6,3000,30,4764),(6,_binary '',NULL,6,7,3000,30,4765),(7,_binary '',7,7,7,3000,7,4766),(3,_binary '',NULL,3,4,3000,31,4767),(4,_binary '',4,4,4,3000,4,4768),(5,_binary '',5,5,4,3000,5,4769),(6,_binary '',5,6,4,3000,5,4770),(2,_binary '',NULL,2,3,3000,36,4771),(3,_binary '',NULL,3,4,3000,36,4772),(4,_binary '',NULL,4,5,3000,36,4773),(5,_binary '',NULL,5,6,3000,36,4774),(6,_binary '',6,6,6,3000,36,4775),(3,_binary '',NULL,3,4,3000,38,4776),(4,_binary '',NULL,4,5,3000,38,4777),(5,_binary '',NULL,5,6,3000,38,4778),(6,_binary '',NULL,6,7,3000,38,4779),(7,_binary '',NULL,7,8,3000,38,4780),(8,_binary '',4,8,4,3000,38,4781),(5,_binary '',NULL,5,6,3000,42,4782),(6,_binary '',NULL,6,7,3000,42,4783),(7,_binary '',NULL,7,8,3000,42,4784),(8,_binary '',NULL,8,9,3000,42,4785),(9,_binary '',NULL,9,10,3000,42,4786),(10,_binary '',10,10,10,3000,7,4787),(11,_binary '',10,11,10,3000,7,4788),(5,_binary '',NULL,5,6,3000,49,4789),(6,_binary '',NULL,6,7,3000,49,4790),(7,_binary '',NULL,7,8,3000,49,4791),(8,_binary '',NULL,8,9,3000,49,4792),(9,_binary '\0',NULL,9,9,3000,49,4793),(10,_binary '\0',NULL,10,9,3000,49,4794),(11,_binary '\0',NULL,11,9,3000,49,4795),(12,_binary '\0',NULL,12,8,3000,49,4796),(13,_binary '\0',NULL,13,8,3000,49,4797),(14,_binary '\0',NULL,14,8,3000,49,4798),(15,_binary '\0',15,15,8,3000,15,4799),(16,_binary '\0',15,16,7,3000,15,4800),(17,_binary '\0',12,17,7,3000,12,4801),(5,_binary '',NULL,5,6,3000,60,4802),(6,_binary '',NULL,6,7,3000,60,4803),(7,_binary '',NULL,7,8,3000,60,4804),(8,_binary '',NULL,8,9,3000,60,4805),(9,_binary '\0',NULL,9,9,3000,60,4806),(10,_binary '\0',10,10,9,3000,10,4807),(4,_binary '',NULL,4,5,3000,63,4808),(5,_binary '',NULL,5,6,3000,63,4809),(6,_binary '',NULL,6,7,3000,63,4810),(7,_binary '',4,7,4,3000,4,4811),(8,_binary '',4,8,4,3000,4,4812),(5,_binary '',NULL,5,6,3000,64,4813),(6,_binary '',NULL,6,7,3000,64,4814),(7,_binary '',NULL,7,8,3000,64,4815),(8,_binary '',NULL,8,9,3000,64,4816),(9,_binary '\0',NULL,9,9,3000,64,4817),(10,_binary '\0',NULL,10,9,3000,64,4818),(11,_binary '\0',NULL,11,9,3000,64,4819),(12,_binary '\0',NULL,12,9,3000,64,4820),(13,_binary '\0',NULL,13,8,3000,64,4821),(14,_binary '\0',NULL,14,8,3000,64,4822),(15,_binary '\0',NULL,15,8,3000,64,4823),(16,_binary '\0',NULL,16,7,3000,64,4824),(17,_binary '\0',NULL,17,7,3000,64,4825),(18,_binary '\0',NULL,18,7,3000,64,4826),(19,_binary '\0',NULL,19,7,3000,64,4827),(20,_binary '\0',NULL,20,7,3000,64,4828),(21,_binary '\0',21,21,7,3000,21,4829),(22,_binary '\0',20,22,7,3000,20,4830),(23,_binary '\0',18,23,7,3000,18,4831),(3,_binary '',NULL,3,4,3000,78,4832),(4,_binary '',NULL,4,5,3000,78,4833),(5,_binary '',NULL,5,6,3000,78,4834),(6,_binary '',NULL,6,7,3000,78,4835),(7,_binary '',NULL,7,8,3000,78,4836),(8,_binary '',NULL,8,9,3000,78,4837),(9,_binary '',9,9,9,3000,78,4838),(6,_binary '',NULL,6,7,3000,81,4839),(7,_binary '',NULL,7,8,3000,81,4840),(8,_binary '',NULL,8,9,3000,81,4841),(9,_binary '',NULL,9,10,3000,81,4842),(10,_binary '\0',NULL,10,10,3000,81,4843),(11,_binary '\0',NULL,11,9,3000,81,4844),(12,_binary '\0',NULL,12,8,3000,81,4845);
/*!40000 ALTER TABLE `FVDSymetryExperiment` ENABLE KEYS */;
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
