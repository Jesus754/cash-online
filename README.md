# Evaluación Cash Online

## Levantar aplicación

Ejecutar comando **mvn test spring-boot:run**

Se debe tener instalado y configurado maven en las variables de entorno.

### Tecnologías
Proyecto construido a partir de Maven donde se utiliza Dependency Management para el manejo de dependencias. Basado en Java 8 donde se define Spring Boot para exponer los métodos REST haciendo uso de las annotations. 
Se utiliza H2 como base de datos SQL. Ésta se crea y carga con datos de prueba al levantar la aplicacion, y al bajarla se elimina.
Se proveen test utilizando JUNIT y Mockito.

### Consideración
El atributo email se considero como **único** al momento de crear usuario.
