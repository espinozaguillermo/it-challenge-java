--- EJERCICIO 5 -  COMPLEJIDAD BAJA: 
-- actualizar todos las cursos que posean anio 2018 y cuyo cupo sea menor a 5, y actualizar el cupo de todos ellos a 10.

UPDATE curso 
SET cupomaximo = 10 
WHERE cupomaximo < 5 AND
      anio = 2018;
