-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2022 at 09:36 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
SET GLOBAL FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS skuci_se;

USE skuci_se;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `skuci_se`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `averageMark` (IN `AD_ID` INT)  BEGIN
	SELECT CASE 
    	WHEN AVG(1.0*s.mark) IS NULL THEN 0
        ELSE AVG(1.0*s.mark)
        END as `averageMark`
    FROM sightseeing s
    WHERE s.advertisment_id=AD_ID AND s.accepted=1 AND s.mark>0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findMarkedSightseeingByAdId` (IN `AD_ID` INT)  BEGIN
	SELECT * FROM sightseeing s
    WHERE s.accepted=1 AND s.advertisment_id=AD_ID AND s.mark>0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findMyAds` (IN `ID` INT)  BEGIN
	SET @id=ID;
	SELECT * FROM advertisments ad WHERE ad.owner_id=@id; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findOtherAds` (IN `ID` INT)  BEGIN
	SET @id=ID;
	SELECT * FROM advertisments ad WHERE ad.owner_id != @id; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findSightseeingByOwnerId` (IN `id` INT)  BEGIN
SELECT * FROM sightseeing s WHERE EXISTS
(
    SELECT * FROM advertisments a WHERE s.advertisment_id=a.id AND a.owner_id = id
);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findSightseeingByUserId` (IN `id` INT)  BEGIN
SELECT * FROM sightseeing s WHERE s.user_id = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `numberOfLikes` (IN `AD_ID` INT)  BEGIN
	SELECT COUNT(*) as numberOfLikes FROM likes WHERE advertisment_id=AD_ID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `toogleLike` (IN `AD_ID` INT, IN `USER_ID` INT)  BEGIN
	/*DECLARE @adId INT;
    DECLARE @userId INT;*/
	SET @adId=AD_ID;
	SET @userId=USER_ID;
    IF EXISTS(SELECT * FROM likes l WHERE l.user_id=@userId AND l.advertisment_id=@adId)
    THEN 
    	SELECT 0 as status;
    	DELETE FROM likes WHERE user_id=@userId AND advertisment_id=@adId;
    ELSE 
    	INSERT INTO likes(user_id,advertisment_id) VALUES (@userId,@adId);
    	SELECT 1 as status;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `userLikedAd` (IN `AD_ID` INT, IN `USER_ID` INT)  BEGIN
	SELECT COUNT(*) as liked FROM likes l WHERE l.user_id=USER_ID AND l.advertisment_id=AD_ID;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `advertisments`
--

CREATE TABLE `advertisments` (
  `id` int(11) NOT NULL,
  `advertisment_category_id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `description` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `area` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `advertisments`
--

INSERT INTO `advertisments` (`id`, `advertisment_category_id`, `owner_id`, `description`, `city_id`, `price`, `area`) VALUES
(4, 1, 1, '', 1, 20, 10),
(5, 2, 2, 'Korisnik ad updated', 1, 2000, 10);

-- --------------------------------------------------------

--
-- Table structure for table `advertisment_categories`
--

CREATE TABLE `advertisment_categories` (
  `id` int(11) NOT NULL,
  `title` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `advertisment_categories`
--

INSERT INTO `advertisment_categories` (`id`, `title`) VALUES
(1, 'RENT'),
(2, 'SELL');

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `country_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `name`, `country_id`) VALUES
(1, 'Beograd', 1),
(2, 'Novi Sad', 1),
(3, 'Niš', 1),
(4, 'Kragujevac', 1),
(5, 'Priština', 1),
(6, 'Subotica', 1),
(7, 'Zrenjanin', 1),
(8, 'Pančevo', 1),
(9, 'Čačak', 1),
(10, 'Kruševac', 1),
(11, 'Kraljevo', 1),
(12, 'Novi Pazar', 1),
(13, 'Smederevo', 1),
(14, 'Leskovac', 1),
(15, 'Užice', 1),
(16, 'Vranje', 1),
(17, 'Valjevo', 1),
(18, 'Šabac', 1),
(19, 'Sombor', 1),
(20, 'Požarevac', 1),
(21, 'Pirot', 1),
(22, 'Zaječar', 1),
(23, 'Kikinda', 1),
(24, 'Sremska Mitrovica', 1),
(25, 'Jagodina', 1),
(26, 'Vršac', 1),
(27, 'Bor', 1),
(28, 'Prokuplje', 1),
(29, 'Loznica', 1);

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `id` int(11) NOT NULL,
  `name` varchar(56) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`id`, `name`) VALUES
(1, 'Serbia');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(15);

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `image` mediumtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE `likes` (
  `user_id` int(11) NOT NULL,
  `advertisment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `likes`
--

INSERT INTO `likes` (`user_id`, `advertisment_id`) VALUES
(1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `title`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `sightseeing`
--

CREATE TABLE `sightseeing` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `advertisment_id` int(11) NOT NULL,
  `accepted` tinyint(1) DEFAULT NULL,
  `time` datetime NOT NULL DEFAULT current_timestamp(),
  `mark` int(11) DEFAULT 0,
  `comment` text COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sightseeing`
--

INSERT INTO `sightseeing` (`id`, `user_id`, `advertisment_id`, `accepted`, `time`, `mark`, `comment`) VALUES
(4, 1, 4, 1, '2022-09-11 16:50:44', 0, NULL),
(12, 2, 4, 1, '2026-08-18 10:15:16', 0, NULL),
(13, 2, 4, 0, '2026-08-18 10:15:16', 0, NULL),
(14, 2, 4, 1, '2026-08-18 10:15:16', 4, 'aa');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `firstname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `image_id` int(11),
  `role_id` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `image_id`, `role_id`, `username`, `password`) VALUES
(1, 'Ime', 'Prezime', 1, 1, 'user', '$2a$10$pTme2grcKbjhySjxx.24z.OJ2wwDSYuyj/WuaYofD3GVQaNRg9w.S'),
(2, 'Korisnik', 'Korisnik', 2, 2, 'korisnik', '$2a$10$zlBFTWQtI7PwoUOOjQLbQu6v49caK2bipyONl5RT5bokqj1ejuk7u');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `advertisments`
--
ALTER TABLE `advertisments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_advertisment_category` (`advertisment_category_id`),
  ADD KEY `fk_owner` (`owner_id`),
  ADD KEY `fk_city` (`city_id`);

--
-- Indexes for table `advertisment_categories`
--
ALTER TABLE `advertisment_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_counrty` (`country_id`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`user_id`,`advertisment_id`),
  ADD KEY `fk_advertisment` (`advertisment_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sightseeing`
--
ALTER TABLE `sightseeing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_1` (`user_id`),
  ADD KEY `fk_advertisment_1` (`advertisment_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `fk_role` (`role_id`),
  ADD KEY `fk_image` (`image_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `advertisments`
--
ALTER TABLE `advertisments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `advertisment_categories`
--
ALTER TABLE `advertisment_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sightseeing`
--
ALTER TABLE `sightseeing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `advertisments`
--
ALTER TABLE `advertisments`
  ADD CONSTRAINT `fk_advertisment_category` FOREIGN KEY (`advertisment_category_id`) REFERENCES `advertisment_categories` (`id`),
  ADD CONSTRAINT `fk_city` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  ADD CONSTRAINT `fk_owner` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `fk_counrty` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`);

--
-- Constraints for table `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `fk_advertisment` FOREIGN KEY (`advertisment_id`) REFERENCES `advertisments` (`id`),
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `sightseeing`
--
ALTER TABLE `sightseeing`
  ADD CONSTRAINT `fk_advertisment_1` FOREIGN KEY (`advertisment_id`) REFERENCES `advertisments` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_user_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `users`
--
--
-- Constraints for table `images`
--

ALTER TABLE `users`
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_image` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
