-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 29, 2020 at 10:47 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventi`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_number` int(11) NOT NULL,
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_number`, `balance`) VALUES
(1, 0),
(2, 0),
(3, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `statement`
--

CREATE TABLE `statement` (
  `account_number` int(11) NOT NULL,
  `date` date NOT NULL,
  `beneficiary` varchar(70) NOT NULL,
  `comment` text CHARACTER SET utf32,
  `amount` double NOT NULL,
  `currency` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statement`
--

INSERT INTO `statement` (`account_number`, `date`, `beneficiary`, `comment`, `amount`, `currency`) VALUES
(1, '2020-01-01', 'Edvinas', '????????????????????', 100, 'eur'),
(1, '2020-01-02', 'Edvinas', '偡????????', 20, 'eur'),
(1, '2020-01-03', 'weasdas', 'asdasd', 10, 'eur'),
(1, '2020-01-10', 'asdas', 'asdas', -20, 'eur'),
(1, '2020-01-06', 'dddd', 'dasd', 100, 'eur'),
(1, '2020-01-08', 'asdas', 'asdas', -100, 'eur');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
