-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 06, 2024 at 01:40 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `booking_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking_table`
--

CREATE TABLE `booking_table` (
  `booking_avail` varchar(20) NOT NULL,
  `booking_branch` varchar(50) NOT NULL,
  `booking_room_num` int(1) NOT NULL,
  `booking_type` varchar(20) NOT NULL,
  `booking_price` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking_table`
--

INSERT INTO `booking_table` (`booking_avail`, `booking_branch`, `booking_room_num`, `booking_type`, `booking_price`) VALUES
('Vacant', 'GBH Calape', 101, 'Single', '500.00'),
('Vacant', 'GBH Calape', 102, 'Partner', '600.00'),
('Vacant', 'GBH Loon', 126, 'Single', '400.00'),
('Vacant', 'GBH Cogon Tagbillaran', 106, 'Partner', '1500.00'),
('Vacant', 'GBH Cogon Tagbillaran', 154, 'Family', '2500.00'),
('Vacant', 'GBH Loon', 145, 'Single', '400.00'),
('Vacant', 'GBH Sagbayan', 146, 'Family', '1400.00'),
('Vacant', 'GBH Lucob Calape', 134, 'Single', '1000.00'),
('Vacant', 'GBH Calape', 174, 'Partner', '800.00');

-- --------------------------------------------------------

--
-- Table structure for table `branch_table`
--

CREATE TABLE `branch_table` (
  `branch_name` varchar(30) NOT NULL,
  `branch_manager` varchar(50) NOT NULL,
  `branch_number` int(1) NOT NULL,
  `branch_location` varchar(50) NOT NULL,
  `branch_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `branch_table`
--

INSERT INTO `branch_table` (`branch_name`, `branch_manager`, `branch_number`, `branch_location`, `branch_type`) VALUES
('GBH Calape', 'George Bugwak', 1, 'Bentig, Calape Bohol', 'Main Branch'),
('GBH Cogon Tagbillaran', 'Queene Anne Dumangas', 3, 'Cogon, Tagbilaran Bohol', 'Sub Branch'),
('GBH Loon', 'Glen Cyril Garidos', 2, 'Basdacu, Loon Bohol', 'Sub Branch'),
('GBH Lucob Calape', 'Kim Concha', 6, 'Lucob, CalapeBohol', 'Sub Branch'),
('GBH Sagbayan', 'Keth Dominic Tacatani', 5, 'Libertad, Sagabyan, Bohol', 'Sub Branch'),
('GBH Tubigon', 'Louren Mhel Garces', 4, 'Poblacion, Tubigon Bohol', 'Sub Branch');

-- --------------------------------------------------------

--
-- Table structure for table `recent_bookings_table`
--

CREATE TABLE `recent_bookings_table` (
  `customer_id` int(1) NOT NULL,
  `branch` varchar(50) NOT NULL,
  `location` varchar(50) NOT NULL,
  `reservation_date` varchar(50) NOT NULL,
  `check_in` varchar(50) NOT NULL,
  `check_out` varchar(50) NOT NULL,
  `room_number` int(1) NOT NULL,
  `room_type` varchar(50) NOT NULL,
  `days_occupied` int(1) NOT NULL,
  `room_price` varchar(50) NOT NULL,
  `price_payed` double(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recent_bookings_table`
--

INSERT INTO `recent_bookings_table` (`customer_id`, `branch`, `location`, `reservation_date`, `check_in`, `check_out`, `room_number`, `room_type`, `days_occupied`, `room_price`, `price_payed`) VALUES
(3, 'GBH Loon', 'Basdacu, Loon Bohol', 'Jul/04/2022', 'Jul/04/2022', 'Jul/05/2022', 145, 'Single', 1, '400.00', 400.00),
(7, 'GBH Cogon Tagbillaran', 'Cogon, Tagbilaran Bohol', 'Jul/04/2022', 'Jul/04/2022', 'Jul/07/2022', 154, 'Family', 3, '2500.00', 7500.00),
(6, 'GBH Calape', 'Bentig, Calape Bohol', 'Jul/04/2022', 'Jul/04/2022', 'Jul/06/2022', 174, 'Partner', 2, '800.00', 1600.00),
(14, 'GBH Calape', 'Bentig, Calape Bohol', 'Jul/04/2022', 'Jul/04/2022', 'Jul/05/2022', 101, 'Single', 1, '500.00', 500.00);

-- --------------------------------------------------------

--
-- Table structure for table `remember_table`
--

CREATE TABLE `remember_table` (
  `rem_id` int(1) NOT NULL,
  `username` varchar(30) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `remember_table`
--

INSERT INTO `remember_table` (`rem_id`, `username`, `pass`) VALUES
(1, 'george', 'george');

-- --------------------------------------------------------

--
-- Table structure for table `users_info`
--

CREATE TABLE `users_info` (
  `user_id` int(1) NOT NULL,
  `user_fname` varchar(30) NOT NULL,
  `user_lname` varchar(30) NOT NULL,
  `user_gender` varchar(10) NOT NULL,
  `user_address` varchar(50) NOT NULL,
  `user_bday` varchar(20) NOT NULL,
  `user_age` int(1) NOT NULL,
  `user_user` varchar(30) NOT NULL,
  `user_email` varchar(30) NOT NULL,
  `user_pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_info`
--

INSERT INTO `users_info` (`user_id`, `user_fname`, `user_lname`, `user_gender`, `user_address`, `user_bday`, `user_age`, `user_user`, `user_email`, `user_pass`) VALUES
(3, 'Keth Dominic', 'Tacatani', 'Male', 'Bentig, Calape Bohol', 'Sep/04/2001', 21, 'kethtacatani', 'kethtacatani@gmai.com', 'kethtacatani'),
(6, 'louren', 'Garces', 'Female', 'Bentig Centro', 'Jun/1/2000', 19, 'louren', 'louren@gmail.com', 'louren'),
(7, 'Queenne Ann', 'Dumangas', 'Female', 'Calape, Bohol', 'Jan/13/2001', 21, 'queenne', 'dumangasqueenneann@gmail.com', 'queenne'),
(9, 'George', 'Bugwak', 'Male', 'CAtigbian', 'Jun/01/2000', 21, 'george', 'george@gmail.com', 'george'),
(10, 'Nicole', 'Palwa', 'Female', 'Banlasan, Tubigon Bohol', 'Jul/21/2002', 19, 'nicole', 'nicolepalwa@gmail.com', 'nicole'),
(11, 'Glenn Cyril', 'Garidos', 'Male', 'Catigbian', 'Dec/30/2001', 20, 'jorgebugwak', 'jorgebugwak', '12345'),
(12, 'louren mhel', 'garces', 'Female', 'bentig,calape,bohol', 'Apr/16/2001', 21, 'lourenmhel', 'louren18@gmail.com', 'lourenmhel'),
(13, 'kim', 'concha', 'Male', 'Poblacion, Tubigon Bohol', 'Jun/01/2000', 20, 'kim', 'kim@gmail.com', 'kim'),
(14, 'Glen Cyril', 'Garidos', 'Male', 'Catagbacan, Loon Bohol', 'Dec/30/2001', 19, 'glen', 'glen@gmail.com', 'glencyril');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking_table`
--
ALTER TABLE `booking_table`
  ADD KEY `booking_branch` (`booking_branch`);

--
-- Indexes for table `branch_table`
--
ALTER TABLE `branch_table`
  ADD PRIMARY KEY (`branch_name`);

--
-- Indexes for table `recent_bookings_table`
--
ALTER TABLE `recent_bookings_table`
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `branch` (`branch`);

--
-- Indexes for table `remember_table`
--
ALTER TABLE `remember_table`
  ADD PRIMARY KEY (`rem_id`);

--
-- Indexes for table `users_info`
--
ALTER TABLE `users_info`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `remember_table`
--
ALTER TABLE `remember_table`
  MODIFY `rem_id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users_info`
--
ALTER TABLE `users_info`
  MODIFY `user_id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking_table`
--
ALTER TABLE `booking_table`
  ADD CONSTRAINT `booking_table_ibfk_1` FOREIGN KEY (`booking_branch`) REFERENCES `branch_table` (`branch_name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `recent_bookings_table`
--
ALTER TABLE `recent_bookings_table`
  ADD CONSTRAINT `recent_bookings_table_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `users_info` (`user_id`),
  ADD CONSTRAINT `recent_bookings_table_ibfk_2` FOREIGN KEY (`branch`) REFERENCES `branch_table` (`branch_name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
