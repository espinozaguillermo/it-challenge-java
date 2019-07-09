# it-challenge-java

## Requerimientos y herramientas para los ejercicios

* PostgreSQL - psql (PostgreSQL) 9.5.17
* JDBC para PostgreSQL
* JDK — 1.8 or later 
* IDE — Eclipse - Version: 2019-06 (4.12.0) Build id: 20190614-1200

IMPORTANTE: Es necesario cargar en una DB PostgreSQL el script [inicializacion-db.sql](tecnica.java/workspace-sql/inicializacion-db.sql)

## Ejercicios

Los ejercicios consisten en:

    PASO 1: En el archivo adjunto encontraras un workspace-sql con un script de creación de una base de datos muy sencilla (create de tablas), que deberás correr en una base de datos "PostgreSQL" (te vas a tener que instalar en este caso una base de datos de este tipo o adaptar los script para correrlos en la base de datos que quieras), y realizar algunas consultas ("querys") sobre este modelo de base de datos según algunas consignas dadas en el segundo archivo adjuntado también en el .rar.
[Ejercicios SQL](tecnica.java/workspace-sql) se encuentran los ejercicios de SQL en archivos .sql separados
    
    PASO 2: Realizar los ejercicios de Programación que se encuentran en un proyecto en java dentro del workspace-java dentro del adjunto que te enviamos. Para realizar estos ejercicios primero tenes que abrir "eclipse" y selecionar el workspace similar al siguiente: .../apellido.nombres/workspace-java (reemplanzando donde dice apellido.nombre por el tuyo recién descargado). Dentro de ese workspace ya existe un proyecto java creado de nombre "proyecto-java-examen". Consultar y realizar en orden los ejercicios planteados dentro de este proyecto.
[Ejercicios Java](tecnica.java/workspace-java) se encuentra el workspace para Eclipse con las clases Java editadas

## Requerimientos y herramientas utilizadas para la aplicación

* PostgreSQL - psql (PostgreSQL) 9.5.17
* JDBC para PostgreSQL
* JDK — 1.8 or later 
* IDE — Eclipse - Version: 2019-06 (4.12.0) Build id: 20190614-1200
* Spring Boot — 2.1.16.RELEASE
* Tomcat 8.5.42 - corriendo en el puerto 8080. ej: http://localhost:8080
* Spring Framework
* Hibernate
* JPA

## Aplicación

    PASO 3: Desarrollar una aplicación, puede ser en cualquier lenguaje y tanto una aplicación web o de escritorio o simplemente una aplicación que requiera ingreso de datos por consola, que:

    a. Registre y edite alumnos en el modelo de base de datos anterior.
    b. Provea los siguientes reportes:
    i. Estado académico de un alumno dado (inscripciones actuales a carreras y cursos,  estado de cursos anteriores, promedio general por carrera tomando los cursos ya aprobados)
    ii. Para una asignatura(curso) dado, los alumnos inscriptos y el docente correspondiente.
    c. Permita a un alumno inscribirse a una materia.
    (para esto se deberán realizar los cambios que crean convenientes en el modelo de datos provisto en el paso 1)

## Como probar la aplicación

! IMPORTANTE: Es necesario cargar en una DB PostgreSQL el script [db-actualizada.sql](db-actualizada.sql) con las actualizaciones del modelo original. Además PostgreSQL tiene que estar corriendo en *jdbc:postgresql://localhost:5432/postgres*

1. En el IDE Eclipse, cargar el workspace [it-java-challenge](it-java-challenge)
2. Correr la aplicación con Run As.. y seleccionar Application, o correrla como una Spring boot application seleccionando el Project challenge y como Main Type it.java.challenge.ItJavaChallengeApplication
![Run Config](it-java-challenge/runconfig.png?raw=true "Run Config")
3. Una vez levantada la aplicación abrir en un navegador web el enlace http://localhost:8080/swagger-ui.html para abrir la interfaz de swagger para probar la aplicación. 
   Nota: la API de la aplicación también se puede probar con una herramienta tipo POSTMAN

## Funciones y Reportes

a. Registre y edite alumnos en el modelo de base de datos anterior.
/api/v1/personas (POST)
{
  "apellido": "string",
  "fechaNacimiento": "yyyy-MM-dd",
  "id": 0,
  "nombre": "string",
  "nroDocumento": 0,
  "tipoDocumento": "string"
}
c. Permita a un alumno inscribirse a una materia.

b. Provea los siguientes reportes:
- inscripciones actuales a carreras y cursos
- estado de cursos anteriores
- promedio general por carrera tomando los cursos ya aprobados
- Para una asignatura(curso) dado, los alumnos inscriptos y el docente correspondiente.


--

Links:

- [PostgreSQL](https://www.digitalocean.com/community/tutorials/como-instalar-y-utilizar-postgresql-en-ubuntu-16-04-es)
- [JDBC](https://jdbc.postgresql.org/download.html)
- [Eclipse](https://websiteforstudents.com/how-to-install-eclipse-oxygen-ide-on-ubuntu-167-04-17-10-18-04/)
- [Tomcat](https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-8-on-ubuntu-16-04)
- [POSTMAN](https://www.getpostman.com/)
