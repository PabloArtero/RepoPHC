-- 4-CANTIDAD DE POSICIONES POR DEPARTAMENTO (CANTIDAD DE PERSONAS POR DPTO.)

SELECT
 Departamento.nombre AS 'Departamento',
 COUNT(Empleado.idEmpleado) AS 'Cantidad de empleados'
FROM HistorialEmpleado
JOIN Empleado ON HistorialEmpleado.idEmpleado = Empleado.idEmpleado
JOIN Puesto ON HistorialEmpleado.idPuesto = Puesto.idPuesto
JOIN Departamento ON Puesto.idDepartamento = Departamento.idDepartamento
WHERE Empleado.esActivo = 1
AND Historialempleado.fechaEgreso IS NULL -- todabía ocupa el puesto
GROUP BY Departamento