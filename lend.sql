-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2024 at 08:25 AM
-- Server version: 8.0.30
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lend`
--

-- --------------------------------------------------------

--
-- Table structure for table `accessory`
--

CREATE TABLE `accessory` (
  `a_id` int NOT NULL,
  `a_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_count` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accessory`
--

INSERT INTO `accessory` (`a_id`, `a_name`, `a_count`) VALUES
(4, 'pan', 10),
(5, 'table', 10),
(9, 'ruler', 20),
(10, 'eraser', 20),
(11, 'pencil sharpener', 20),
(12, 'calculator', 10),
(13, 'masterart', 10),
(14, 'Book', 10);

-- --------------------------------------------------------

--
-- Table structure for table `list`
--

CREATE TABLE `list` (
  `list_id` int NOT NULL,
  `a_id` int NOT NULL,
  `username` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lend_date` date NOT NULL,
  `borrow_date` date DEFAULT NULL,
  `lend_number` int DEFAULT NULL,
  `lend_s_id` int DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `return_number` int DEFAULT NULL,
  `return_s_id` int DEFAULT NULL,
  `fine` double DEFAULT NULL,
  `accessory_status` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `list`
--

INSERT INTO `list` (`list_id`, `a_id`, `username`, `lend_date`, `borrow_date`, `lend_number`, `lend_s_id`, `return_date`, `return_number`, `return_s_id`, `fine`, `accessory_status`) VALUES
(11, 14, 'Loga', '2024-03-23', '2024-03-21', 2, 1, NULL, NULL, NULL, NULL, NULL),
(12, 9, 'user1', '2024-03-23', '2024-03-22', 1, 1, NULL, NULL, NULL, NULL, NULL),
(13, 5, 'miss', '2024-03-23', '2024-03-22', 1, 1, '2024-03-24', 1, 0, 0, 'Normal'),
(14, 13, 'tien', '2024-03-23', '2024-03-23', 2, 1, '2024-03-23', NULL, NULL, NULL, NULL),
(15, 14, 'tien', '2024-03-23', '2024-03-23', 2, 1, NULL, NULL, NULL, NULL, NULL),
(16, 13, 'pooo', '2024-03-23', '2024-03-26', 2, 1, '2024-03-26', 2, 0, 0, 'none');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `s_id` int NOT NULL,
  `s_user` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `s_pw` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `s_fname` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `s_lname` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `s_tel` text COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`s_id`, `s_user`, `s_pw`, `s_fname`, `s_lname`, `s_tel`, `role`) VALUES
(1, 'user1', '1234', 'user1', 'lname', '098887654', 'เจ้าหน้าที่'),
(2, 'user2', '1234', 'Pimpisa', 'Namwong', '0987654321', 'ผู้บริหาร'),
(4, 'tien', 'tien1234', 'Tienchai', 'Chumpa', '0897453615', 'เจ้าหน้าที่'),
(5, 'misst', '12345', 'Thararat', 'Namwong', '0662662226', 'เจ้าหน้าที่'),
(7, 'Loga', '1234', 'Hima', 'Wari', '0668569941', 'ผู้บริหาร');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accessory`
--
ALTER TABLE `accessory`
  ADD PRIMARY KEY (`a_id`);

--
-- Indexes for table `list`
--
ALTER TABLE `list`
  ADD PRIMARY KEY (`list_id`),
  ADD KEY `a_id` (`a_id`),
  ADD KEY `s_id` (`lend_s_id`) USING BTREE;

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`s_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accessory`
--
ALTER TABLE `accessory`
  MODIFY `a_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `list`
--
ALTER TABLE `list`
  MODIFY `list_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `s_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `list`
--
ALTER TABLE `list`
  ADD CONSTRAINT `list_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `accessory` (`a_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
