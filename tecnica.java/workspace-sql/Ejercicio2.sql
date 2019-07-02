--- EJERCICIO 2 - COMPLEJIDAD BAJA: 
--Realizar una consulta para consultar todas las carreras a la que un alumno esta inscripto, mostrar: Legajo, nombre, apellido, nombre carrera, fechainscripcioncarrera
--ordenado por legajo descendiente

SELECT a.legajo, p.nombre, p.apellido, c.nombre AS NombreCarrera, ic.fechainscripcion 
FROM persona AS p 
JOIN alumno AS a ON p.identificador = a.idpersona 
JOIN inscripciones_carrera AS ic ON a.identificador = ic.idalumno 
JOIN carrera AS C ON c.identificador = ic.idcarrera 
ORDER BY a.legajo DESC;
