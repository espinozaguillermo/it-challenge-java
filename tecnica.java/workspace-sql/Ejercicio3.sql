--- EJERCICIO 3 - COMPLEJIDAD MEDIA: 
--Realizar una consulta para consultar la cantidad de inscriptos por curso, mostrando: nombre carrera, nombre curso, cantidad inscriptos, cupo máximo por curso

SELECT ca.nombre AS NombreCarrera, c.nombre AS NombreCurso, COUNT(*) AS CantidadInscriptos, c.cupomaximo
FROM inscripciones_curso AS ic
JOIN curso AS c ON c.identificador = ic.idcurso
JOIN carrera AS ca ON ca.identificador = c.idcarrera
GROUP BY ca.nombre, c.nombre, c.cupomaximo;
