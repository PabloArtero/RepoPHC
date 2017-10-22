-- 5-LISTADO DE EMPLEADOS QUE TRABAJAN EN MÁS DE UN DEPARTAMENTO
-- Detallando:
-- 	Los puestos que ocupa.
-- 	A qué depto. pertenece cada puesto
-- 	La ontiguedad en la ocupación de cada puesto.
 
SELECT
	Empleado.idEmpleado AS 'id',
	Empleado.nombre AS 'Nombre',
	Empleado.apellido AS 'Apellido',
	Puesto.nombre AS 'Puesto',
	Departamento.nombre AS 'Departamento',
	DATEDIFF(CURDATE(), HistorialEmpleado.fechaIngreso) DIV 365 AS 'Años',
	(DATEDIFF(CURDATE(), HistorialEmpleado.fechaIngreso) MOD 365) DIV 30 AS 'Meses',
	CONCAT( DATEDIFF(CURDATE(), HistorialEmpleado.fechaIngreso) DIV 365,'-', (DATEDIFF(CURDATE(), HistorialEmpleado.fechaIngreso) MOD 365) DIV 30) AS 'Años-Meses'
FROM
	Empleado, HistorialEmpleado, Puesto, Departamento,
 	(SELECT -- Empleados que trabajan en más de un Departamento
		Empleado.idEmpleado
	FROM Empleado, HistorialEmpleado,Puesto,Departamento
	WHERE Empleado.idEmpleado = HistorialEmpleado.idEmpleado
	AND HistorialEmpleado.fechaEgreso IS NULL -- Aún está en el Puesto
	AND HistorialEmpleado.idPuesto = Puesto.idPuesto
	AND Puesto.idDepartamento = Departamento.idDepartamento
	GROUP BY Empleado.idEmpleado
	HAVING COUNT(Departamento.idDepartamento) > 1
	) AS EmplTrabajaEnMasDeUnDpto
WHERE Empleado.idEmpleado = EmplTrabajaEnMasDeUnDpto.idEmpleado
AND HistorialEmpleado.idEmpleado = Empleado.idEmpleado
AND HistorialEmpleado.idPuesto = Puesto.idPuesto
AND Puesto.idDepartamento = Departamento.idDepartamento
AND HistorialEmpleado.fechaEgreso IS NULL