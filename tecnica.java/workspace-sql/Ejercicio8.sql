--- EJERCICIO 8 -  COMPLEJIDAD BAJA: 
-- si se desea comenzar a persistir de ahora en mas el dato de direccion de un alumno y considerando que este es un único cambio string de 200 caracteres.
-- Determine sobre que tabla seria mas conveniente persistir esta información y realizar la modificación de estructuras correspondientes.



-- La tabla más conveniente para persistir el campo direccion es persona, ya que luego se puede utilizar con otro tipo de relacion, como por ejemplo una tabla profesor
ALTER TABLE persona
ADD COLUMN direccion varchar(200) NULL;
