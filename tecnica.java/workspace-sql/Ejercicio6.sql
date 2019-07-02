--- EJERCICIO 6 -  COMPLEJIDAD ALTA: 
-- actualizar todas las fechas de inscripcion a cursados que posean el siguiente error: la fecha de inscripcion al cursado de un 
-- alumno es menor a la fecha de inscripcion a la carrera. La nueva fecha que debe tener es la fecha del dia. Se puede hacer en dos pasos, primero
-- realizando la consulta y luego realizando el update manual

UPDATE inscripciones_curso AS icu
SET fechainscripcion = current_date
FROM curso AS c
JOIN carrera AS ca ON ca.identificador = c.idcarrera
JOIN inscripciones_carrera AS ica ON ca.identificador = ica.idcarrera
WHERE c.identificador = icu.idcurso AND
      ica.idalumno = icu.idalumno AND 
      icu.fechainscripcion < ica.fechainscripcion;

