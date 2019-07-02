--- EJERCICIO 1 - COMPLEJIDAD BAJA: 
--Realizar una consulta para consultar todos los alumnos existentes, mostrar: TipoDoc, Documento, Nombre, Apellido, Legajo.

SELECT p.tipodoc, p.documento, p.nombre, p.apellido, a.legajo 
FROM persona AS p 
JOIN alumno AS a ON p.identificador = a.idpersona;
