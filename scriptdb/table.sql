-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.13 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              8.1.0.4583
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных get_mac
DROP DATABASE IF EXISTS `get_mac`;
CREATE DATABASE IF NOT EXISTS `get_mac` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `get_mac`;


-- Дамп структуры для таблица get_mac.arp_table_static
DROP TABLE IF EXISTS `arp_table_static`;
CREATE TABLE IF NOT EXISTS `arp_table_static` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentary` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `host_ip` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `host_mac` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `host_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица get_mac.device
DROP TABLE IF EXISTS `device`;
CREATE TABLE IF NOT EXISTS `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_ip` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_passw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `switch_enable` bit(1) DEFAULT NULL,
  `id_device_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t947fw5848gwaomf1c2q0dper` (`id_device_type`),
  CONSTRAINT `FK_t947fw5848gwaomf1c2q0dper` FOREIGN KEY (`id_device_type`) REFERENCES `device_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица get_mac.device_type
DROP TABLE IF EXISTS `device_type`;
CREATE TABLE IF NOT EXISTS `device_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица get_mac.dhcp_table
DROP TABLE IF EXISTS `dhcp_table`;
CREATE TABLE IF NOT EXISTS `dhcp_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `duration` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ip_Address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mac_address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ip_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `politics` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profile` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `securityAccess` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `termination` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6t9ksothpwheqj89xs69b1cmy` (`ip_Address`,`mac_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица get_mac.mac_table
DROP TABLE IF EXISTS `mac_table`;
CREATE TABLE IF NOT EXISTS `mac_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Mac_Address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Switch_Address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Switch_Name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Switch_Port` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Time_collection` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Индекс 2` (`Switch_Address`,`Switch_Port`,`Mac_Address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица get_mac.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица get_mac.user_roles
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Экспортируемые данные не выделены.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
