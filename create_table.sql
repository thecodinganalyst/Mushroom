# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.11.3-MariaDB)
# Database: mushroom
# Generation Time: 2023-07-05 13:50:46 +0000
# ************************************************************
USE mushroom;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table child
# ------------------------------------------------------------

CREATE TABLE `child` (
                         `dob` date DEFAULT NULL,
                         `id` bigint(20) NOT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



# Dump of table child_seq
# ------------------------------------------------------------

CREATE TABLE `child_seq` (
                             `next_not_cached_value` bigint(21) NOT NULL,
                             `minimum_value` bigint(21) NOT NULL,
                             `maximum_value` bigint(21) NOT NULL,
                             `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
                             `increment` bigint(21) NOT NULL COMMENT 'increment value',
                             `cache_size` bigint(21) unsigned NOT NULL,
                             `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
                             `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

LOCK TABLES `child_seq` WRITE;
/*!40000 ALTER TABLE `child_seq` DISABLE KEYS */;

INSERT INTO `child_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`)
VALUES
    (1,1,9223372036854775806,1,50,1000,0,0);

/*!40000 ALTER TABLE `child_seq` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table diaper_change
# ------------------------------------------------------------

CREATE TABLE `diaper_change` (
                                 `diaper_status` tinyint(4) DEFAULT NULL CHECK (`diaper_status` between 0 and 3),
                                 `child_id` bigint(20) DEFAULT NULL,
                                 `date_time` datetime(6) DEFAULT NULL,
                                 `id` bigint(20) NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `FKk0h5a2bev566xky67qhp774nu` (`child_id`),
                                 CONSTRAINT `FKk0h5a2bev566xky67qhp774nu` FOREIGN KEY (`child_id`) REFERENCES `child` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



# Dump of table diaper_change_seq
# ------------------------------------------------------------

CREATE TABLE `diaper_change_seq` (
                                     `next_not_cached_value` bigint(21) NOT NULL,
                                     `minimum_value` bigint(21) NOT NULL,
                                     `maximum_value` bigint(21) NOT NULL,
                                     `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
                                     `increment` bigint(21) NOT NULL COMMENT 'increment value',
                                     `cache_size` bigint(21) unsigned NOT NULL,
                                     `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
                                     `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

LOCK TABLES `diaper_change_seq` WRITE;
/*!40000 ALTER TABLE `diaper_change_seq` DISABLE KEYS */;

INSERT INTO `diaper_change_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`)
VALUES
    (1,1,9223372036854775806,1,50,1000,0,0);

/*!40000 ALTER TABLE `diaper_change_seq` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table milk_recording
# ------------------------------------------------------------

CREATE TABLE `milk_recording` (
                                  `amount` int(11) DEFAULT NULL,
                                  `type` tinyint(4) DEFAULT NULL CHECK (`type` between 0 and 3),
                                  `child_id` bigint(20) DEFAULT NULL,
                                  `date_time` datetime(6) DEFAULT NULL,
                                  `id` bigint(20) NOT NULL,
                                  `until` datetime(6) DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKtmykm2v2js58f3fb527miu4aw` (`child_id`),
                                  CONSTRAINT `FKtmykm2v2js58f3fb527miu4aw` FOREIGN KEY (`child_id`) REFERENCES `child` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



# Dump of table milk_recording_seq
# ------------------------------------------------------------

CREATE TABLE `milk_recording_seq` (
                                      `next_not_cached_value` bigint(21) NOT NULL,
                                      `minimum_value` bigint(21) NOT NULL,
                                      `maximum_value` bigint(21) NOT NULL,
                                      `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
                                      `increment` bigint(21) NOT NULL COMMENT 'increment value',
                                      `cache_size` bigint(21) unsigned NOT NULL,
                                      `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
                                      `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

LOCK TABLES `milk_recording_seq` WRITE;
/*!40000 ALTER TABLE `milk_recording_seq` DISABLE KEYS */;

INSERT INTO `milk_recording_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`)
VALUES
    (1,1,9223372036854775806,1,50,1000,0,0);

/*!40000 ALTER TABLE `milk_recording_seq` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sleep_recording
# ------------------------------------------------------------

CREATE TABLE `sleep_recording` (
                                   `child_id` bigint(20) DEFAULT NULL,
                                   `date_time` datetime(6) DEFAULT NULL,
                                   `id` bigint(20) NOT NULL,
                                   `until` datetime(6) DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `FK3hxh3lbeoicp7ktombtfnt300` (`child_id`),
                                   CONSTRAINT `FK3hxh3lbeoicp7ktombtfnt300` FOREIGN KEY (`child_id`) REFERENCES `child` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



# Dump of table sleep_recording_seq
# ------------------------------------------------------------

CREATE TABLE `sleep_recording_seq` (
                                       `next_not_cached_value` bigint(21) NOT NULL,
                                       `minimum_value` bigint(21) NOT NULL,
                                       `maximum_value` bigint(21) NOT NULL,
                                       `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
                                       `increment` bigint(21) NOT NULL COMMENT 'increment value',
                                       `cache_size` bigint(21) unsigned NOT NULL,
                                       `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
                                       `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

LOCK TABLES `sleep_recording_seq` WRITE;
/*!40000 ALTER TABLE `sleep_recording_seq` DISABLE KEYS */;

INSERT INTO `sleep_recording_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`)
VALUES
    (1,1,9223372036854775806,1,50,1000,0,0);

/*!40000 ALTER TABLE `sleep_recording_seq` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
