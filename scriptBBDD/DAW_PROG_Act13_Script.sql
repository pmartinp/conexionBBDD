/*** Creción y selección de la base de datos ***/
CREATE DATABASE categorias_productos character set utf8 collate utf8_spanish_ci;
USE categorias_productos;

/*** Creación de tablas ***/
CREATE TABLE categoria(
	id_categoria INT(9) PRIMARY KEY AUTO_INCREMENT,
    activo BOOLEAN  NOT NULL,
	fecha_alta DATE NOT NULL,
	nombre VARCHAR(256) NOT NULL,
	descripcion TEXT NOT NULL,
	url VARCHAR(256) NOT NULL
)engine=InnoDB;

CREATE TABLE producto(
	id_producto INT(9) PRIMARY KEY AUTO_INCREMENT,
    id_categoria_default INT(9) NOT NULL,
    id_impuesto INT(9) NOT NULL,
    ean13 VARCHAR(13) NOT NULL,
    cantidad INT(9) NOT NULL,
    precio FLOAT(12,6) NOT NULL,
    precio_fabrica FLOAT(12,6) NOT NULL,
    referencia VARCHAR(65) NOT NULL,
    activo BOOLEAN NOT NULL,
    fecha_alta DATE NOT NULL,
    nombre VARCHAR(256) NOT NULL,
    resumen VARCHAR(256) NOT NULL,
    descripcion TEXT NOT NULL,
    url VARCHAR(256) NOT NULL,
    CONSTRAINT categoria_FK FOREIGN KEY (id_categoria_default) REFERENCES categoria (id_categoria)
)engine=InnoDB;