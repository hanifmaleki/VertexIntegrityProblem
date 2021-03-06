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
-- Table structure for table `SimilarVerticesExperiment`
--

DROP TABLE IF EXISTS `SimilarVerticesExperiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SimilarVerticesExperiment` (
  `bagCount` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_51pfxk5yitd2bn3xwq640qijq` FOREIGN KEY (`id`) REFERENCES `SimpleExpriment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SimilarVerticesExperiment`
--

LOCK TABLES `SimilarVerticesExperiment` WRITE;
/*!40000 ALTER TABLE `SimilarVerticesExperiment` DISABLE KEYS */;
INSERT INTO `SimilarVerticesExperiment` VALUES (1,1898),(9,1899),(8,1900),(1,1901),(1,1902),(12,1903),(1,1904),(16,1905),(30,1906),(20,1907),(41,1908),(35,1909),(63,1910),(45,1911),(100,1912),(49,1913),(108,1914),(118,1915),(180,1916),(165,1918),(70,1919),(225,1920),(210,1921),(289,1922),(400,1923),(302,1924),(40,1925),(507,1926),(395,1927),(588,1928),(600,1929),(50,1930),(698,1931),(757,1932),(800,1933),(60,1934),(410,1935),(1081,1936),(673,1937),(1185,1938),(29,1939),(927,1940),(1185,1941),(2032,1942),(2109,1943),(2655,1944),(2778,1945),(2725,1946),(2915,1947),(3193,1948),(3126,1949),(3251,1950),(3382,1951),(2813,1952),(3854,1953),(3931,1954),(4170,1955),(4250,1956),(4363,1957),(3526,1958),(4548,1959),(3681,1960),(4716,1961),(5145,1962),(3817,1963),(6844,1964),(4482,1965),(7170,1966),(4869,1967),(7324,1968),(7062,1969),(7938,1970),(6300,1971),(8168,1972),(8499,1973),(6774,1974),(5428,1975),(9259,1976),(9686,1977),(1377,1978),(9114,1979),(14619,1980),(11605,1981),(14389,1982),(17469,1983),(17779,1984),(8998,1985),(19532,1986),(19336,1987),(14636,1988),(3633,1989),(26497,1990),(8874,1991),(28126,1992),(28399,1993),(11499,1994),(22128,1995),(33816,1996),(32859,1997),(29320,1998);
/*!40000 ALTER TABLE `SimilarVerticesExperiment` ENABLE KEYS */;
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
