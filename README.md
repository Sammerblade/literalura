# LiterAlura

LiterAlura es un catálogo de libros en el cual la persona usuaria puede registrar libros en una base de datos y recibir información sobre estos libros que ya están registrados en la base de datos.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL

## Funcionalidades

1. **Buscar libro por título**: La persona usuaria puede buscar un libro por su título, y si no está en la base de datos, se buscará en la API de Gutendex y se registrará.
2. **Listar libros registrados**: Listar todos los libros que han sido registrados en la base de datos.
3. **Listar autores registrados**: Listar todos los autores únicos registrados en la base de datos.
4. **Listar autores vivos en un año determinado**: Listar autores que estaban vivos en un año determinado.
5. **Listar libros por idioma**: Listar todos los libros registrados en un idioma específico.

## Cómo Ejecutar

1. Clona este repositorio:
   ```sh
   git clone https://github.com/Sammerblade/literalura.git
Navega al directorio del proyecto
cd literalura
Asegúrate de tener PostgreSQL corriendo y configura las credenciales en src/main/resources/application.properties.
Ejecuta la aplicación
./mvnw spring-boot:run
Configuración de la Base de Datos
Asegúrate de tener PostgreSQL instalado y corriendo. Configura las credenciales de tu base de datos en el archivo application.properties.
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
