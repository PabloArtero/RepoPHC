-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema PHC
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `PHC` ;

-- -----------------------------------------------------
-- Schema PHC
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PHC` DEFAULT CHARACTER SET utf8 ;
USE `PHC` ;

-- -----------------------------------------------------
-- Table `PHC`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`Empleado` (
  `idEmpleado` INT NOT NULL AUTO_INCREMENT,
  `apellido` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `legajo` VARCHAR(45) NOT NULL,
  `fechaIngreso` DATE NULL,
  `dni` INT NULL,
  `cuil` VARCHAR(45) NULL,
  `fechaNacimiento` DATE NULL,
  `esActivo` BIT NULL,
  `telefono` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `domicilio` VARCHAR(100) NULL,
  `sexo` CHAR(1) NULL DEFAULT 'M',
  PRIMARY KEY (`idEmpleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`Nivel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`Nivel` (
  `idNivel` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idNivel`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`Departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`Departamento` (
  `idDepartamento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  PRIMARY KEY (`idDepartamento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`Usuario` (
  `idEmpleado` INT NOT NULL,
  `nombreUsuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `esAdministrador` BIT NOT NULL DEFAULT 0,
  `habilitado` BIT NOT NULL DEFAULT 1,
  `contraseñaRestaurada` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`idEmpleado`),
  INDEX `fk_Usuario_Empleado1_idx` (`idEmpleado` ASC),
  CONSTRAINT `fk_Usuario_Empleado1`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `PHC`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`Puesto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`Puesto` (
  `idPuesto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `idDepartamento` INT NOT NULL,
  `idNivel` INT NOT NULL,
  `esResponsableDpto` BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idPuesto`),
  INDEX `fk_Puesto_Departamento1_idx` (`idDepartamento` ASC),
  INDEX `fk_Puesto_Nivel1_idx` (`idNivel` ASC),
  CONSTRAINT `fk_Puesto_Departamento1`
    FOREIGN KEY (`idDepartamento`)
    REFERENCES `PHC`.`Departamento` (`idDepartamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Puesto_Nivel1`
    FOREIGN KEY (`idNivel`)
    REFERENCES `PHC`.`Nivel` (`idNivel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`HistorialEmpleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`HistorialEmpleado` (
  `idHistorialEmpleado` INT NOT NULL AUTO_INCREMENT,
  `fechaIngreso` DATE NULL,
  `fechaEgreso` DATE NULL,
  `idEmpleado` INT NOT NULL,
  `idPuesto` INT NOT NULL,
  PRIMARY KEY (`idHistorialEmpleado`),
  INDEX `fk_HistorialEmpleado_Puesto1_idx` (`idPuesto` ASC),
  INDEX `fk_HistorialEmpleado_Empleado1_idx` (`idEmpleado` ASC),
  CONSTRAINT `fk_HistorialEmpleado_Puesto1`
    FOREIGN KEY (`idPuesto`)
    REFERENCES `PHC`.`Puesto` (`idPuesto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistorialEmpleado_Empleado1`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `PHC`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`Requerimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`Requerimiento` (
  `idRequerimiento` INT NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  PRIMARY KEY (`idRequerimiento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`Tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`Tarea` (
  `idTarea` INT NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  PRIMARY KEY (`idTarea`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`RequerimientoPuesto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`RequerimientoPuesto` (
  `idRequerimiento` INT NOT NULL,
  `idPuesto` INT NOT NULL,
  PRIMARY KEY (`idRequerimiento`, `idPuesto`),
  INDEX `fk_Requerimiento_has_Puesto_Puesto1_idx` (`idPuesto` ASC),
  INDEX `fk_Requerimiento_has_Puesto_Requerimiento1_idx` (`idRequerimiento` ASC),
  CONSTRAINT `fk_Requerimiento_has_Puesto_Requerimiento1`
    FOREIGN KEY (`idRequerimiento`)
    REFERENCES `PHC`.`Requerimiento` (`idRequerimiento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requerimiento_has_Puesto_Puesto1`
    FOREIGN KEY (`idPuesto`)
    REFERENCES `PHC`.`Puesto` (`idPuesto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PHC`.`PuestoTarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHC`.`PuestoTarea` (
  `idPuesto` INT NOT NULL,
  `idTarea` INT NOT NULL,
  PRIMARY KEY (`idPuesto`, `idTarea`),
  INDEX `fk_Puesto_has_Tarea_Tarea1_idx` (`idTarea` ASC),
  INDEX `fk_Puesto_has_Tarea_Puesto1_idx` (`idPuesto` ASC),
  CONSTRAINT `fk_Puesto_has_Tarea_Puesto1`
    FOREIGN KEY (`idPuesto`)
    REFERENCES `PHC`.`Puesto` (`idPuesto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Puesto_has_Tarea_Tarea1`
    FOREIGN KEY (`idTarea`)
    REFERENCES `PHC`.`Tarea` (`idTarea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `PHC`.`Empleado`
-- -----------------------------------------------------
START TRANSACTION;
USE `PHC`;
INSERT INTO `PHC`.`Empleado` (`idEmpleado`, `apellido`, `nombre`, `legajo`, `fechaIngreso`, `dni`, `cuil`, `fechaNacimiento`, `esActivo`, `telefono`, `email`, `domicilio`, `sexo`)
VALUES (1, 'ApellidoAdministrador', 'NombreAdministrador', '0', '2000-01-01', 000000, '00-000000-0', '1991-01-01', 1, '000000', 'ejemplo@dominio.com', 'Dirección del Administrador', NULL);


COMMIT;



-- -----------------------------------------------------
-- Data for table `PHC`.`Usuario`
-- -----------------------------------------------------
START TRANSACTION;
USE `PHC`;
INSERT INTO `PHC`.`Usuario` (`idEmpleado`, `nombreUsuario`, `contraseña`, `esAdministrador`, `habilitado`, `contraseñaRestaurada`)
VALUES (1, 'admin', md5('admin'), 1, 1, 0);


COMMIT;
