-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2021 at 08:14 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_shop`
--
CREATE DATABASE IF NOT EXISTS `e_shop` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `e_shop`;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) UNSIGNED NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `user_name`, `address`, `telephone`, `password`) VALUES
(1000, 'admin', 'admin', '000000000', 'admin'),
(1001, 'Ivan', 'Yagur 12/5', '0549803978', '333'),
(1002, 'Denis', 'Havatzelet 7/29', '055055055', '333'),
(1005, 'Marina', 'Balfur 1/1', '054054054', '333');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) UNSIGNED NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `product_price` float NOT NULL,
  `product_sale` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `product_price`, `product_sale`) VALUES
(1000, 'bread', 4, 0.1),
(1001, 'beef', 15, 0.01),
(1002, 'lamb', 20, 0.025),
(1003, 'cake', 4, 0.001),
(1004, 'waffle', 3, 0),
(1005, 'salmon', 25, 0.15),
(1006, 'tuna', 20, 0.12),
(1007, 'milk', 2, 0.04),
(1008, 'cheese', 7, 0.1),
(1009, 'mozzarella', 20, 0.12),
(1010, 'cream', 1.2, 0),
(1011, 'apple', 7, 0.2),
(1012, 'apricot', 8, 0.4),
(1013, 'grapes', 9, 0.42),
(1014, 'banana', 3, 0.03),
(1015, 'strawberry', 10, 0),
(1016, 'nuts', 25, 0.1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_name` (`user_name`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1006;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1017;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
