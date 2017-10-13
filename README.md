# RepoPHC
Front-end y Base de datos del Sistema de Gestión de RRHH: Proyecto NetBeans + modelo WorkBench.
Para la materia Gestión de Capital Humano de la carrera de Ingeniería en Sistemas de Información - Universidad Tecnológica Nacional - Facultad Regional Tucumán

Sistema transaccional de escritorio hecho en java con NetBeans. Se conecta al back-end (alojado en Heroku en https://capitalphc.herokuapp.com) a través de una API REST hecha en java con Intellij (ver repositorio: https://github.com/benjacarp/phc). Sigue los patrones de diseño MVC y DAO, con la variante de que al modelo lo separamos en dos paquetes: "dominio", con las entidades y "persistencia" con los DAO y todo lo que tenga que ver con el acceso al back-end.

# REQUISITOS DEL SISTEMA

El sistema debe ser entragado al cliente con un Script de la base de datos, lista para ser instalada(crear el .bat también)
	Este script inicial debe tener las tablas vacías de datos, salvo un usuario administrador: usario="admin", contraseña="admin"

El sistema debe soportar altas de 2 tipos de usuarios:
	Admninistrador, que tenga todos los permisos
	No administrador, con permiso de solo lectura

ABM de todas las tablas: Departamentos, Puestos, Usuarios, etc.
	
Asignar los puestos a los departamentos
	
Asignar los empleados a los puestos de trabajo

Consultas:

1. Cantidad de departamentos en la organizacion

2. Responsables por cada departamento (nivel de puesto, departamento)

3. Cantidad de puestos por departamento

4. Cantidad de posiciones por departamento (cant de personas x depto)

5. Listado de empleados que trabajen en mas de un departamento --> detalle: puesto actual, antigüedad en el puesto

Al menos dos opciones para cargar nuevos empleados:
1- Crear el empleado y asignarle un departamento
2- Seleccionar un departamento y agregarle uno o varios empleados

Cuando se elimina un departamento se deben eliminar primero las entidades relacionadas
