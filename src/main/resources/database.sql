-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: bmt_database
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `bookingID` int NOT NULL AUTO_INCREMENT,
  `numberOfSeats` int NOT NULL,
  `timeStamp` datetime NOT NULL,
  `status` int NOT NULL,
  `userID` int NOT NULL,
  `showID` int NOT NULL,
  PRIMARY KEY (`bookingID`),
  KEY `showID_idx` (`showID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `booked_showID` FOREIGN KEY (`showID`) REFERENCES `show` (`showID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cinema`
--

DROP TABLE IF EXISTS `cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema` (
  `cinemaID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `totalCinemalHalls` int NOT NULL,
  PRIMARY KEY (`cinemaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cinema_hall`
--

DROP TABLE IF EXISTS `cinema_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema_hall` (
  `hallID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `totalSeats` int NOT NULL,
  `cinemaID` int NOT NULL,
  PRIMARY KEY (`hallID`),
  KEY `cinemaID_idx` (`cinemaID`),
  CONSTRAINT `cinemaID` FOREIGN KEY (`cinemaID`) REFERENCES `cinema` (`cinemaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cinema_seat`
--

DROP TABLE IF EXISTS `cinema_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema_seat` (
  `seatID` int NOT NULL AUTO_INCREMENT,
  `seatNumber` varchar(10) NOT NULL,
  `type` int NOT NULL,
  `cinemaHallID` int NOT NULL,
  PRIMARY KEY (`seatID`),
  KEY `hallID_idx` (`cinemaHallID`),
  CONSTRAINT `hallID` FOREIGN KEY (`cinemaHallID`) REFERENCES `cinema_hall` (`hallID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movieID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `description` mediumtext NOT NULL,
  `duration` time DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `releaseDate` date DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `genre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`movieID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `paymentId` int NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `timeStamp` datetime NOT NULL,
  `discountCouponID` varchar(20) DEFAULT NULL,
  `paymentMethod` int NOT NULL,
  `bookingID` int NOT NULL,
  PRIMARY KEY (`paymentId`),
  KEY `payment_bookingID_idx` (`bookingID`),
  CONSTRAINT `payment_bookingID` FOREIGN KEY (`bookingID`) REFERENCES `booking` (`bookingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `show`
--

DROP TABLE IF EXISTS `show`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show` (
  `showID` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `movieID` int NOT NULL,
  `cinemaHallID` int NOT NULL,
  PRIMARY KEY (`showID`),
  KEY `movieId_idx` (`movieID`),
  KEY `cinemaHallId_idx` (`cinemaHallID`),
  CONSTRAINT `cinemaHallId` FOREIGN KEY (`cinemaHallID`) REFERENCES `cinema_hall` (`hallID`),
  CONSTRAINT `movieId` FOREIGN KEY (`movieID`) REFERENCES `movie` (`movieID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `show_seat`
--

DROP TABLE IF EXISTS `show_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_seat` (
  `showSeatID` int NOT NULL AUTO_INCREMENT,
  `status` int NOT NULL,
  `price` float NOT NULL,
  `cinema_seatID` int NOT NULL,
  `showID` int NOT NULL,
  `bookingID` int DEFAULT NULL,
  PRIMARY KEY (`showSeatID`),
  KEY `cinema_seatID_idx` (`cinema_seatID`),
  KEY `showID_idx` (`showID`),
  KEY `booking_idx` (`bookingID`),
  CONSTRAINT `booking` FOREIGN KEY (`bookingID`) REFERENCES `booking` (`bookingID`),
  CONSTRAINT `cinema_seatID` FOREIGN KEY (`cinema_seatID`) REFERENCES `cinema_seat` (`seatID`),
  CONSTRAINT `showID` FOREIGN KEY (`showID`) REFERENCES `show` (`showID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `birth` date NOT NULL,
  `userName` varchar(64) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(70) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `userType` int NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-19 20:36:28
