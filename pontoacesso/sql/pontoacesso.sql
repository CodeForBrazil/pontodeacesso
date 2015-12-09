CREATE DATABASE `pontoacesso` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE pontoacesso;

CREATE TABLE `caracteristicas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

CREATE TABLE `pontos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_categoria_idx` (`id_categoria`),
  CONSTRAINT `id_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

CREATE TABLE `ponto_caracteristicas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ponto` int(11) NOT NULL,
  `id_caracteristica` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idPonto_idx` (`id_ponto`),
  KEY `idCaracteristica_idx` (`id_caracteristica`),
  CONSTRAINT `idCaracteristica` FOREIGN KEY (`id_caracteristica`) REFERENCES `caracteristicas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idPonto` FOREIGN KEY (`id_ponto`) REFERENCES `pontos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

ALTER TABLE `pontoacesso`.`pontos` 
ADD COLUMN `foto` MEDIUMTEXT NULL DEFAULT NULL AFTER `id_categoria`;
