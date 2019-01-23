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
-- Table structure for table `SimpleLPDecisionExpriment`
--

DROP TABLE IF EXISTS `SimpleLPDecisionExpriment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SimpleLPDecisionExpriment` (
  `c` int(11) NOT NULL,
  `k` int(11) NOT NULL,
  `lb` int(11) DEFAULT NULL,
  `timeout` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  `finished` bit(1) DEFAULT NULL,
  `solutionSize` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_2ndyrauhn4damfo9pbl50ri4` FOREIGN KEY (`id`) REFERENCES `SimpleExpriment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SimpleLPDecisionExpriment`
--

LOCK TABLES `SimpleLPDecisionExpriment` WRITE;
/*!40000 ALTER TABLE `SimpleLPDecisionExpriment` DISABLE KEYS */;
INSERT INTO `SimpleLPDecisionExpriment` VALUES (5,5,0,3000,2489,_binary '',5),(4,4,0,3000,2490,_binary '',NULL),(3,3,-4,3000,2491,_binary '',3),(2,2,0,3000,2492,_binary '',NULL),(6,6,-2,3000,2493,_binary '',6),(5,5,-4,3000,2494,_binary '',5),(4,4,0,3000,2495,_binary '',NULL),(3,3,0,3000,2496,_binary '',NULL),(8,8,1,3000,2497,_binary '',8),(7,7,0,3000,2498,_binary '',NULL),(10,10,0,3000,2499,_binary '',10),(9,9,0,3000,2500,_binary '',NULL),(7,7,-8,3000,2501,_binary '',7),(6,6,0,3000,2502,_binary '',NULL),(5,5,0,3000,2503,_binary '',NULL),(4,4,0,3000,2504,_binary '',NULL),(3,3,0,3000,2505,_binary '',NULL),(13,13,1,3000,2506,_binary '',13),(12,12,0,3000,2507,_binary '',NULL),(7,7,-16,3000,2508,_binary '',7),(6,6,0,3000,2509,_binary '',NULL),(5,5,0,3000,2510,_binary '',NULL),(4,4,0,3000,2511,_binary '',NULL),(3,3,0,3000,2512,_binary '',NULL),(6,6,-24,3000,2513,_binary '',6),(61,-188,-188,6000,2514,NULL,NULL),(60,-190,-190,6000,2515,NULL,NULL),(59,-192,-192,6000,2516,NULL,NULL),(58,-194,-194,6000,2517,NULL,NULL),(57,-196,-196,6000,2518,NULL,NULL),(56,-198,-198,6000,2519,NULL,NULL),(55,-200,-200,6000,2520,NULL,NULL),(54,-202,-202,6000,2521,NULL,NULL),(53,-204,-204,6000,2522,NULL,NULL),(52,-206,-206,6000,2523,NULL,NULL),(51,-208,-208,6000,2524,NULL,NULL),(50,-210,-210,6000,2525,NULL,NULL),(49,-212,-212,6000,2526,NULL,NULL),(48,-214,-214,6000,2527,NULL,NULL),(47,-216,-216,6000,2528,NULL,NULL),(46,-218,-218,6000,2529,NULL,NULL),(45,0,0,6000,2530,NULL,NULL),(9,0,0,6000,2569,NULL,NULL),(10,0,0,6000,2570,NULL,NULL),(11,0,0,6000,2571,NULL,NULL),(12,0,0,6000,2572,NULL,NULL),(13,0,0,6000,2573,NULL,NULL),(14,0,0,6000,2574,NULL,NULL),(15,0,0,6000,2575,NULL,NULL),(16,0,0,6000,2576,NULL,NULL),(17,0,0,6000,2577,NULL,NULL),(18,0,0,6000,2578,NULL,NULL),(19,0,0,6000,2579,NULL,NULL),(20,0,0,6000,2580,NULL,NULL),(21,0,0,6000,2581,NULL,NULL),(7,7,0,3001,3663,_binary '',NULL),(8,8,0,3001,3664,_binary '',NULL),(9,9,0,3001,3665,_binary '\0',NULL),(10,10,0,3001,3666,_binary '\0',NULL),(11,11,0,3001,3667,_binary '\0',NULL),(12,12,0,3001,3668,_binary '\0',NULL),(13,13,0,3001,3669,_binary '\0',NULL),(14,14,0,3001,3670,_binary '\0',NULL),(15,15,0,3001,3671,_binary '\0',NULL),(16,16,0,3001,3672,_binary '\0',NULL),(17,17,0,3001,3673,_binary '\0',NULL),(18,18,0,3001,3674,_binary '\0',NULL),(19,19,0,3001,3675,_binary '',19),(20,20,-60,3001,3676,_binary '',20),(21,21,-58,3001,3677,_binary '',21),(22,22,-56,3001,3678,_binary '',22),(23,23,-54,3001,3679,_binary '',23),(24,24,-52,3001,3680,_binary '',24),(25,25,-50,3001,3681,_binary '',25),(26,26,-48,3001,3682,_binary '',26),(27,27,-46,3001,3683,_binary '',27),(28,28,-44,3001,3684,_binary '',28),(29,29,-42,3001,3685,_binary '',29),(30,30,-40,3001,3686,_binary '',30),(31,31,-38,3001,3687,_binary '',31),(32,32,-36,3001,3688,_binary '',32),(33,33,-34,3001,3689,_binary '',33),(34,34,-32,3001,3690,_binary '',34),(35,35,-30,3001,3691,_binary '',35),(36,36,-28,3001,3692,_binary '',36),(37,37,-26,3001,3693,_binary '',37),(6,6,NULL,3000,3700,_binary '',NULL),(7,7,NULL,3000,3701,_binary '',NULL),(8,8,NULL,3000,3702,_binary '',NULL),(5,5,NULL,3000,3709,_binary '',NULL),(6,6,NULL,3000,3710,_binary '',NULL),(5,5,NULL,3000,3711,_binary '',NULL),(6,6,NULL,3000,3712,_binary '',NULL),(7,7,NULL,3000,3713,_binary '',NULL),(8,8,NULL,3000,3714,_binary '',NULL),(9,9,NULL,3000,3715,_binary '',NULL),(10,10,NULL,3000,3716,_binary '',NULL),(11,11,NULL,3000,3717,_binary '',11),(12,12,NULL,3000,3718,_binary '',12),(13,13,NULL,3000,3719,_binary '',13),(14,14,NULL,3000,3720,_binary '',14),(15,15,NULL,3000,3721,_binary '',15),(16,16,NULL,3000,3722,_binary '',16),(17,17,NULL,3000,3723,_binary '',17),(7,7,NULL,3000,3737,_binary '',NULL),(8,8,NULL,3000,3738,_binary '',NULL),(9,9,NULL,3000,3739,_binary '',NULL),(10,10,NULL,3000,3740,_binary '\0',NULL),(11,11,NULL,3000,3741,_binary '\0',NULL),(12,12,NULL,3000,3742,_binary '\0',NULL),(13,13,NULL,3000,3743,_binary '',13),(14,14,NULL,3000,3744,_binary '',14),(15,15,NULL,3000,3745,_binary '',15),(16,16,NULL,3000,3746,_binary '',16),(17,17,NULL,3000,3747,_binary '',17),(18,18,NULL,3000,3748,_binary '',18),(19,19,NULL,3000,3749,_binary '',19),(20,20,NULL,3000,3750,_binary '',20),(21,21,NULL,3000,3751,_binary '',21),(22,22,NULL,3000,3752,_binary '',22),(23,23,NULL,3000,3753,_binary '',23),(9,9,NULL,3000,3773,_binary '\0',NULL),(10,10,NULL,3000,3774,_binary '\0',NULL),(11,11,NULL,3000,3775,_binary '\0',NULL),(12,12,NULL,3000,3776,_binary '\0',NULL),(13,13,NULL,3000,3777,_binary '\0',NULL),(14,14,NULL,3000,3778,_binary '\0',NULL),(15,15,NULL,3000,3779,_binary '\0',NULL),(16,16,NULL,3000,3780,_binary '',16),(17,17,NULL,3000,3781,_binary '',17),(18,18,NULL,3000,3782,_binary '',18),(19,19,NULL,3000,3783,_binary '',19),(20,20,NULL,3000,3784,_binary '',20),(21,21,NULL,3000,3785,_binary '',21),(22,22,NULL,3000,3786,_binary '',22),(23,23,NULL,3000,3787,_binary '',23),(24,24,NULL,3000,3788,_binary '',24),(25,25,NULL,3000,3789,_binary '',25),(26,26,NULL,3000,3790,_binary '',26),(27,27,NULL,3000,3791,_binary '',27),(28,28,NULL,3000,3792,_binary '',28),(29,29,NULL,3000,3793,_binary '',29),(30,30,NULL,3000,3794,_binary '',30),(8,8,NULL,3000,3865,_binary '\0',NULL),(9,9,NULL,3000,3866,_binary '\0',NULL),(10,10,NULL,3000,3867,_binary '\0',NULL),(11,11,NULL,3000,3868,_binary '\0',NULL),(12,12,NULL,3000,3869,_binary '\0',NULL),(13,13,NULL,3000,3870,_binary '\0',NULL),(14,14,NULL,3000,3871,_binary '\0',NULL),(15,15,NULL,3000,3872,_binary '\0',NULL),(16,16,NULL,3000,3873,_binary '\0',NULL),(17,17,NULL,3000,3874,_binary '\0',NULL),(18,18,NULL,3000,3875,_binary '\0',NULL),(19,19,NULL,3000,3876,_binary '\0',NULL),(20,20,NULL,3000,3877,_binary '\0',NULL),(21,21,NULL,3000,3878,_binary '',21),(22,22,NULL,3000,3879,_binary '\0',NULL),(23,23,NULL,3000,3880,_binary '',23),(24,24,NULL,3000,3881,_binary '',24),(25,25,NULL,3000,3882,_binary '',25),(26,26,NULL,3000,3883,_binary '',26),(27,27,NULL,3000,3884,_binary '',27),(28,28,NULL,3000,3885,_binary '',28),(29,29,NULL,3000,3886,_binary '',29),(30,30,NULL,3000,3887,_binary '',30),(31,31,NULL,3000,3888,_binary '',31),(32,32,NULL,3000,3889,_binary '',32),(33,33,NULL,3000,3890,_binary '',33),(34,34,NULL,3000,3891,_binary '',34),(35,35,NULL,3000,3892,_binary '',35),(36,36,NULL,3000,3893,_binary '',36),(37,37,NULL,3000,3894,_binary '',37),(38,38,NULL,3000,3895,_binary '',38),(39,39,NULL,3000,3896,_binary '',39),(40,40,NULL,3000,3897,_binary '',40),(41,41,NULL,3000,3898,_binary '',41),(42,42,NULL,3000,3899,_binary '',42),(43,43,NULL,3000,3900,_binary '',43),(44,44,NULL,3000,3901,_binary '',44),(45,45,NULL,3000,3902,_binary '',45),(2,2,NULL,3000,4483,_binary '',NULL),(3,3,NULL,3000,4484,_binary '',NULL),(4,4,NULL,3000,4485,_binary '',NULL),(5,5,NULL,3000,4486,_binary '',NULL),(3,3,NULL,3000,4487,_binary '',NULL),(4,4,NULL,3000,4488,_binary '',NULL),(5,5,NULL,3000,4489,_binary '',NULL),(6,6,NULL,3000,4490,_binary '',NULL),(7,7,NULL,3000,4491,_binary '',NULL),(8,8,NULL,3000,4492,_binary '',8),(5,5,NULL,3000,4493,_binary '',NULL),(6,6,NULL,3000,4494,_binary '',NULL),(7,7,NULL,3000,4495,_binary '',NULL),(8,8,NULL,3000,4496,_binary '',NULL),(9,9,NULL,3000,4497,_binary '',NULL),(10,10,NULL,3000,4498,_binary '',10),(11,11,NULL,3000,4499,_binary '',11),(5,5,NULL,3000,4500,_binary '',NULL),(6,6,NULL,3000,4501,_binary '',NULL),(7,7,NULL,3000,4502,_binary '',NULL),(8,8,NULL,3000,4503,_binary '',NULL),(9,9,NULL,3000,4504,_binary '',NULL),(10,10,NULL,3000,4505,_binary '',10),(3,3,NULL,3000,4506,_binary '',NULL),(4,4,NULL,3000,4507,_binary '',NULL),(5,5,NULL,3000,4508,_binary '',NULL),(6,6,NULL,3000,4509,_binary '',NULL),(7,7,NULL,3000,4510,_binary '',NULL),(8,8,NULL,3000,4511,_binary '',NULL),(9,9,NULL,3000,4512,_binary '',9),(4,4,NULL,3000,4513,_binary '\0',NULL),(5,5,NULL,3000,4514,_binary '\0',NULL),(6,6,NULL,3000,4515,_binary '',NULL),(7,7,NULL,3000,4516,_binary '',NULL),(8,8,NULL,3000,4517,_binary '',NULL),(9,9,NULL,3000,4518,_binary '',NULL),(10,10,NULL,3000,4519,_binary '',NULL),(11,11,NULL,3000,4520,_binary '',NULL),(12,12,NULL,3000,4521,_binary '',NULL),(13,13,NULL,3000,4522,_binary '',13),(7,7,NULL,3000,4523,_binary '\0',NULL),(8,8,NULL,3000,4524,_binary '\0',NULL),(9,9,NULL,3000,4525,_binary '\0',NULL),(10,10,NULL,3000,4526,_binary '',NULL),(11,11,NULL,3000,4527,_binary '',NULL),(12,12,NULL,3000,4528,_binary '',NULL),(13,13,NULL,3000,4529,_binary '',NULL),(14,14,NULL,3000,4530,_binary '',14),(4,4,NULL,3000,4531,_binary '\0',NULL),(5,5,NULL,3000,4532,_binary '\0',NULL),(6,6,NULL,3000,4533,_binary '\0',NULL),(7,7,NULL,3000,4534,_binary '\0',NULL),(8,8,NULL,3000,4535,_binary '',NULL),(9,9,NULL,3000,4536,_binary '',NULL),(6,6,NULL,3000,4576,_binary '',NULL),(3,3,NULL,3000,6990,_binary '',NULL),(4,4,NULL,3000,6991,_binary '',4),(5,5,NULL,3000,6992,_binary '',5),(6,6,NULL,3000,6993,_binary '',6);
/*!40000 ALTER TABLE `SimpleLPDecisionExpriment` ENABLE KEYS */;
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
