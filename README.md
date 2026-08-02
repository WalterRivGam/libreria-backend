# Librería API REST

## Descripción

API REST con operaciones básicas sobre libros de una librería.

## Tecnologías usadas

Realizado con Java y Spring. La base de datos en MySQL.

## Requisitos
- JDK 21
- MySQL 8.0

## Configuración de base de datos

Ejecutar en MySQL el archivo **libreria.sql** ubicado en la raiz del proyecto.

## Ejecución

- Crear las variables de entorno y colocar los datos correspondientes a la base de datos:

```
DB_URL="jdbc:mysql://localhost:<PUERTO_MYSQL>/libreria"
DB_USERNAME="<USERNAME>"
DB_PASSWORD="<PASSWORD>"
```

- En la raiz del proyecto ejecutar:
  `./mvnw spring-boot:run`
