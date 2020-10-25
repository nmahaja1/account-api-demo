-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `account_number` int(30),
  `account_name` VARCHAR(30),
  `account_type` VARCHAR(30),
  `balance_date` DATE,
  `currency` VARCHAR(30),
  `opening_balance` DECIMAL(5,2)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `account_transaction`;
CREATE TABLE `account_transaction` (
  `transaction_id` int(30),
  `account_number` int(30),
  `account_name` VARCHAR(30),
  `value_date` DATE,
  `currency` VARCHAR(30),
  `debit_amount` DECIMAL(5,2),
  `credit_amount` DECIMAL(5,2),
  `transaction_type` VARCHAR(30),
  `transaction_narrative` VARCHAR(30)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user_account` WRITE, `account_transaction` WRITE;

--
-- Dumping data for table `user_account`
--
INSERT INTO `user_account` VALUES (1, 'Test1', 'Savings', '2013-12-01', 'AUD', 1000.23);
INSERT INTO `user_account` VALUES (2, 'Test2', 'Current', '2013-12-01', 'SGD', 500.51);
INSERT INTO `user_account` VALUES (3, 'Test3', 'Savings', '2013-12-01', 'AUD', 4034.22);

--
-- Dumping data for table `account_transaction`
--
INSERT INTO `account_transaction` VALUES (5, 1, 'Test1', '2013-12-01', 'AUD', null, 300.23, 'Credit', 'Credit Txn1 for Test1');
INSERT INTO `account_transaction` VALUES (6, 1, 'Test1', '2013-12-01', 'AUD', null, 300.00, 'Credit', 'Credit Txn1 for Test1');
INSERT INTO `account_transaction` VALUES (7, 1, 'Test1', '2013-12-01', 'AUD', null, 400.00, 'Credit', 'Credit Txn1 for Test1');
INSERT INTO `account_transaction` VALUES (8, 2, 'Test1', '2013-12-01', 'SGD', null, 200.51, 'Credit', 'Credit Txn1 for Test2');
INSERT INTO `account_transaction` VALUES (9, 2, 'Test1', '2013-12-01', 'SGD', null, 300.00, 'Credit', 'Credit Txn1 for Test2');
INSERT INTO `account_transaction` VALUES (10, 3, 'Test1', '2013-12-01', 'AUD', null, 4034.22, 'Credit', 'Credit Txn1 for Test3');

UNLOCK TABLES;