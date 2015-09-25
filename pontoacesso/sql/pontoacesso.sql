CREATE DATABASE `pontoacesso` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE pontoacesso;

CREATE TABLE `pontos` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `pontoacesso`.`pontos` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;