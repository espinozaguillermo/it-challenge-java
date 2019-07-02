--- EJERCICIO 7 - COMPLEJIDAD BAJA:  
--INSERTAR un nuevo registro de alumno con tus datos personales, (hacer todos inserts que considera necesario)

INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac)
VALUES (6, 'DNI', 32087682, 'Guillermo', 'Espinoza', '1986-03-20');

INSERT INTO alumno (identificador, idpersona, legajo)
VALUES (6, 6, '12345');
