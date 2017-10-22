-- 2-RESPONSABLES POR CADA DEPARTAMENTO (NIVEL DEL PUESTO, DEPARTAMENTO)
--
SELECT 
 d.nombre AS 'Departamento',
 n.nombre AS 'Nivel',
 e.nombre AS 'Nombre Responsable',
 e.apellido AS 'Apellido Responsable',
 p.nombre AS 'Puesto Responsable'
FROM HistorialEmpleado h
JOIN Empleado e ON h.idEmpleado = e.idEmpleado
JOIN Puesto p ON p.idPuesto = h.idPuesto
JOIN Departamento d ON p.idDepartamento = d.idDepartamento
JOIN Nivel n ON p.idNivel = n.idNivel
WHERE h.fechaEgreso IS NULL -- Todabía ocupa el puesto
AND p.esResponsableDpto = 1 AND e.esActivo = 1
 	