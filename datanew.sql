-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: mahasiswa
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `username` varchar(15) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('672018079','12345','student'),('672018080','12345','student'),('admin1','admin','admin'),('admin2','admin','admin');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `kode` varchar(6) NOT NULL,
  `ruang` varchar(45) NOT NULL,
  `hari` varchar(45) NOT NULL,
  `jam` varchar(5) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `sks` int(11) NOT NULL,
  `dosen` varchar(45) NOT NULL,
  `kuota` int(11) NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('IN123A','FTI400','Kamis','9-12','Rekayasa Perangkat Lunak',3,'Alan Turing',20),('IN123B','FTI400','Kamis','9-12','Rekayasa Perangkat Lunak',3,'Stephen Hawking',15),('IN123E','FTI400','Kamis','15-18','Rekayasa Perangkat Lunak',3,'Alan Turing',4),('IN124A','FTI333','Selasa','15-18','Pemrograman',6,'Albert Einstein',5),('IN124B','FTI331','Jumat','15-18','Pemrograman',6,'Albert Einstein',11),('IN125A','FTI300','Rabu','15-18','Bahasa Indonesia',3,'Angela Atik',2),('IN125B','FTI440','Senin','9-12','Bahasa Indonesia',3,'Agus',9),('IN125C','FTI120','Kamis','9-12','Bahasa Indonesia',3,'Mawar',5),('IN126A','-','-','-','Magang',12,'Metrodata',10),('IN127A','-','-','-','Tugas Akhir',12,'Ajeng',5);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `koderequest` int(11) NOT NULL AUTO_INCREMENT,
  `nim` varchar(10) NOT NULL,
  `kode` varchar(6) NOT NULL,
  `kodestatus` int(11) NOT NULL,
  `ketstudent` varchar(500) DEFAULT NULL,
  `ketadmin` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`koderequest`),
  KEY `FK_request_student_idx` (`nim`),
  KEY `FK_request_status_idx` (`kodestatus`),
  CONSTRAINT `FK_request_status` FOREIGN KEY (`kodestatus`) REFERENCES `status` (`kode_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_request_student` FOREIGN KEY (`nim`) REFERENCES `students` (`nim`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'672018079','IN125C',3,'Saya tidak suka dosennya','a'),(2,'672018080','IN124B',3,'Saya bosan','Tahun depan lebih semangat!'),(4,'672018079','IN123B',3,'aa','oke silahkan'),(6,'672018079','IN123E',2,'bosen','yg semangat'),(7,'672018079','IN123B',3,'AAA','oke'),(9,'672018079','IN125B',3,'udah males','semangat yaa'),(10,'672018079','IN124B',3,'capek','okee'),(11,'672018079','IN123E',3,'kedobelan','oke'),(12,'672018079','IN124B',3,'kedobelan','oke'),(13,'672018079','IN125B',3,'cobacoba','aa');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `kode_status` int(11) NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`kode_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Request Delete MataKuliah dari KSM'),(2,'Delete MataKuliah ditolak admin'),(3,'Delete MataKuliah diterima admin');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `nim` varchar(10) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `fakultas` varchar(45) NOT NULL,
  `progdi` varchar(45) NOT NULL,
  `bebansks` varchar(45) NOT NULL,
  PRIMARY KEY (`nim`),
  CONSTRAINT `FK_mhs_user` FOREIGN KEY (`nim`) REFERENCES `accounts` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES ('672018079','Jessi Febria','Teknologi Informasi','Teknik Informatika','21'),('672018080','Apnan Juanda','Teknologi Informasi','Teknik Informatika','21');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studycards`
--

DROP TABLE IF EXISTS `studycards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studycards` (
  `nim` varchar(10) NOT NULL,
  `kodematkul` varchar(6) NOT NULL,
  KEY `FK_matkul_kst` (`kodematkul`),
  KEY `FK_mhs_kst` (`nim`),
  CONSTRAINT `FK_matkul_kst` FOREIGN KEY (`kodematkul`) REFERENCES `courses` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_mhs_kst` FOREIGN KEY (`nim`) REFERENCES `students` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studycards`
--

LOCK TABLES `studycards` WRITE;
/*!40000 ALTER TABLE `studycards` DISABLE KEYS */;
INSERT INTO `studycards` VALUES ('672018080','IN124B'),('672018079','IN124A'),('672018079','IN123A'),('672018079','IN125B');
/*!40000 ALTER TABLE `studycards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mahasiswa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-03 22:49:00
