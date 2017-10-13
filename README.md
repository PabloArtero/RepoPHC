# RepoPHC
Front-end y Base de datos del Sistema de Gestión de RRHH: Proyecto NetBeans + modelo WorkBench

Sistema transaccional de escritorio hecho en java con NetBeans. Se conecta al back-end (alojado en Heroku en https://capitalphc.herokuapp.com) a través de una API REST hecha en java con Intellij (ver repositorio: https://github.com/benjacarp/phc). Sigue los patrones de diseño MVC y DAO, con la variante de que al modelo lo separamos en dos paquetes: "dominio", con las entidades y "persistencia" con los DAO y todo lo que tenga que ver con el acceso al back-end.