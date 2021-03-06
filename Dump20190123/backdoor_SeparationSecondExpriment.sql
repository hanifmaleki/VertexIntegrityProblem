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
-- Table structure for table `SeparationSecondExpriment`
--

DROP TABLE IF EXISTS `SeparationSecondExpriment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SeparationSecondExpriment` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_713jd3subrgikkkb3jjcuxo49` FOREIGN KEY (`id`) REFERENCES `Separation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SeparationSecondExpriment`
--

LOCK TABLES `SeparationSecondExpriment` WRITE;
/*!40000 ALTER TABLE `SeparationSecondExpriment` DISABLE KEYS */;
INSERT INTO `SeparationSecondExpriment` VALUES (4886),(4979),(5094),(5176),(5199),(5224),(5340),(5343),(5348),(5351),(5354),(5357),(5360),(5429),(5453),(5481),(5581),(5584),(5587),(5590),(5593),(5618),(5643),(5668),(5700),(5718),(5731),(5769),(5811),(5843),(5862),(5880),(5903),(5918),(5938),(5996),(6028),(6067),(6090),(6168),(6180),(6257),(6310),(6381),(6455),(6530),(6607),(6840),(6888),(6918),(6951),(7930),(7965),(8061),(8100),(8152),(8241),(8322),(8402),(8435),(8465),(8501),(8522),(8550),(8624),(8654),(8682),(8722),(8746),(8787),(8842),(8860),(8877),(8935),(8964),(9017),(9046),(9093),(9138),(9168),(9214),(9261),(9288),(9369),(9415),(9441),(9451),(9491),(9538),(9585),(9634),(9662),(9697),(9874),(10007),(10131),(10209),(10368),(10432),(10498),(10562),(10688),(10739),(10789),(10896),(10978),(11051),(11199),(11360),(11517),(11628),(11729),(11884),(11993),(12085),(12231),(12342),(12434),(12590),(12701),(12783),(12789),(12801),(12943),(12996),(13050),(13099),(13210),(13298),(13362),(13423),(13478),(13530),(13570),(13613),(13746),(13793),(13847),(13885),(13943),(13987),(14037),(14070),(14183),(14221),(14260),(14308),(14353),(14451),(14485),(14521),(14559),(14600),(14634),(14714),(14775),(14819),(14869),(14926),(15045),(15087),(15141),(15185),(15295),(15367),(15444),(15558),(15648),(15736),(15849),(15940),(16019),(16130),(16218),(16295),(16407),(16497),(16564),(16611),(16625),(16644),(16662),(16673),(16693),(16738),(16768),(16785),(16812),(16844),(16876),(16903),(16929),(16960),(16991),(17028),(17059),(17222),(17275),(17339),(17404),(17462),(17590),(17684),(17753),(17804),(17859),(17901),(17965),(18017),(18071),(18221),(18276),(18309),(18372),(18411),(18478),(18572),(18633),(18679),(18800),(18844),(18893),(18941),(18991),(19024),(19129),(19168),(19209),(19248),(19288),(19327),(19366),(19458),(19533),(19588),(19640),(19688),(19746),(19925),(19975),(20039),(20093),(20211),(20252),(20338),(20415),(20547),(20593),(20650),(20704),(20816),(20921),(21050),(21094),(21150),(21260),(21353),(21483),(21528),(21586),(21698),(21794),(21924),(21971),(22028),(22081),(22191),(22287),(22419),(22465),(22522),(22574),(22683),(22772),(22903),(22909),(22915),(22921),(22927),(22933),(22939),(22945),(22951);
/*!40000 ALTER TABLE `SeparationSecondExpriment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-23 15:41:03
