-- 3-CANTIDAD DE PUESTOS POR DEPARTAMENTO

SELECT
 Departamento.nombre AS 'Departamento',
 COUNT(Puesto.idPuesto) AS 'Cantidad de Puestos'
FROM Puesto
JOIN Departamento ON Puesto.idDepartamento = Departamento.idDepartamento
GROUP BY Departamento.idDepartamento