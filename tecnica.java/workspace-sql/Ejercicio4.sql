--- EJERCICIO 4 - COMPLEJIDAD ALTA: 
--Sobre la consulta anterior (copiar y pegarla y modificarla) modificar la sql para que la consulta retorne solo los cursos cuya cantidad de inscripciones 
--supera su cupo maximo

SELECT c.nombre AS NombreCarrera, cr.nombre AS NombreCurso, COUNT(*) AS CantidadInscriptos, cr.cupomaximo 
FROM carrera AS c 
JOIN curso AS cr ON c.identificador = cr.idcarrera 
JOIN inscripciones_curso AS ic ON c.identificador = ic.idcurso 
GROUP BY c.nombre, cr.nombre, cr.cupomaximo 
HAVING COUNT(*) > cr.cupomaximo;
