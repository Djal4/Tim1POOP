-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2022 at 07:24 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

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
CREATE DEFINER=`root`@`localhost` PROCEDURE `findMyAds` (IN `ID` INT)   BEGIN
	SET @id=ID;
	SELECT * FROM advertisments ad WHERE ad.owner_id=@id; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findOtherAds` (IN `ID` INT)   BEGIN
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
  `location_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `area` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `advertisments`
--

INSERT INTO `advertisments` (`id`, `advertisment_category_id`, `owner_id`, `description`, `location_id`, `price`, `area`) VALUES
(2, 1, 1, 'opis', 1, 1000, 50);

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
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(4);

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE `likes` (
  `user_id` int(11) NOT NULL,
  `advertisment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE `locations` (
  `id` int(11) NOT NULL,
  `city` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `street` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `flat_number` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `country` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`id`, `city`, `street`, `flat_number`, `country`) VALUES
(1, 'Kragujevac', 'Ulica', '11/10', 'Srbija');

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
  `accepted` tinyint(1) NOT NULL DEFAULT 0,
  `time` datetime NOT NULL DEFAULT current_timestamp(),
  `mark` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sightseeing`
--

INSERT INTO `sightseeing` (`id`, `user_id`, `advertisment_id`, `accepted`, `time`, `mark`) VALUES
(1, 1, 2, 0, '2022-06-05 16:12:07', 0),
(3, 1, 2, 0, '2022-06-05 16:12:07', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `firstname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `image_id` int(11) NOT NULL,
  `image_path` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `image_id`, `image_path`, `role_id`, `username`, `password`) VALUES
(1, 'Ime', 'Prezime', 1, 'nsn\'mdgdd', 1, 'user', '$2a$10$pTme2grcKbjhySjxx.24z.OJ2wwDSYuyj/WuaYofD3GVQaNRg9w.S');

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
  ADD KEY `fk_location` (`location_id`);

--
-- Indexes for table `advertisment_categories`
--
ALTER TABLE `advertisment_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`user_id`,`advertisment_id`),
  ADD KEY `fk_advertisment` (`advertisment_id`);

--
-- Indexes for table `locations`
--
ALTER TABLE `locations`
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `fk_role` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `advertisments`
--
ALTER TABLE `advertisments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `advertisment_categories`
--
ALTER TABLE `advertisment_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `advertisments`
--
ALTER TABLE `advertisments`
  ADD CONSTRAINT `fk_advertisment_category` FOREIGN KEY (`advertisment_category_id`) REFERENCES `advertisment_categories` (`id`),
  ADD CONSTRAINT `fk_location` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`),
  ADD CONSTRAINT `fk_owner` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);

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
  ADD CONSTRAINT `fk_advertisment_1` FOREIGN KEY (`advertisment_id`) REFERENCES `advertisments` (`id`),
  ADD CONSTRAINT `fk_user_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
