GESTION DE LA CONFIGURACION:

Correr siempre con gradle bootRun desde consola.

1- El archivo import.sql es un archivo de inicialización que se ejecuta por defecto cuando 
se ha seteado spring.jpa.hibernate.ddl-auto = create este propiedad de JPA en application.properties.
y tiene que llevar este nombre asi como la propiedad debe tener el valor de create o create-drop.

2- Application-profile.properties permite configurar con cual profile se desea correr, en caso de correrse desde gradle el build, debería configurarse esto también.



TEST:

Conceptos claros: http://stackoverflow.com/questions/4904096/whats-the-difference-between-unit-functional-acceptance-and-integration-test
Ejemplos de pruebas es ProyBaseSBAT y hibernateexa1.

Pendiente:

- Configuración para manejar diferentes bases de datos, y diferentes tareas para correr las diferentes pruebas.
- Organización del código de front end y uso total responsive de angular-material
- Implementación de pruebas jbehave.


