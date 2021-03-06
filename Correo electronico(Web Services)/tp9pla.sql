-- MySQL Script generated by MySQL Workbench
-- 11/19/18 16:58:28
-- Model: New Model    Version: 1.0
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tpla
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tpla` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `tpla` ;

-- -----------------------------------------------------
-- Table `tpla`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpla`.`Usuarios` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NULL,
  `contrasenia` VARCHAR(45) NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpla`.`Mensajes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tpla`.`Mensajes` (
  `idMensaje` INT NOT NULL AUTO_INCREMENT,
  `asunto` VARCHAR(45) NULL,
  `texto` VARCHAR(999) NULL,
  `eliminarEmisor` INT NULL,
  `eliminarReceptor` INT NULL,
  `idUsuarioEmisor` INT NOT NULL,
  `idUsuarioReceptor` INT NOT NULL,
  PRIMARY KEY (`idMensaje`),
  INDEX `fk_Mensajes_Usuarios_idx` (`idUsuarioEmisor` ASC),
  INDEX `fk_Mensajes_Usuarios1_idx` (`idUsuarioReceptor` ASC),
  CONSTRAINT `fk_Mensajes_Usuarios`
    FOREIGN KEY (`idUsuarioEmisor`)
    REFERENCES `tpla`.`Usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mensajes_Usuarios1`
    FOREIGN KEY (`idUsuarioReceptor`)
    REFERENCES `tpla`.`Usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
