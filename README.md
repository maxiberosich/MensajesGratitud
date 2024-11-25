# Gratitud Challenge API

## Descripción

Gratitud Challenge API es una aplicación desarrollada en Java con Spring Boot que permite a los usuarios registrar mensajes de gratitud, puntuarlos y visualizarlos ordenados cronológicamente y por popularidad. La API también ofrece autenticación basada en JWT para proteger ciertas operaciones.

## Características

- **Autenticación JWT**: Usuarios registrados pueden autenticarse y obtener un token JWT.
- **CRUD de Usuarios**: Registro, actualización, eliminación y visualización de usuarios.
- **CRUD de Mensajes**: Crear, puntuar, visualizar y eliminar mensajes.
- **Documentación Swagger**: Documentación interactiva de la API disponible en `/swagger-ui.html`.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL (puede ser reemplazado por cualquier otra base de datos soportada por JPA)
- Swagger/OpenAPI
- Maven

## Prerrequisitos

- Java 17 o superior
- Maven 3.6.3 o superior
- MySQL u otra base de datos configurada
- Postman o cualquier otra herramienta para probar APIs (opcional)

## Instalación

1. **Clonar el repositorio**

    ```bash
    git clone https://github.com/maxiberosich/MensajesGratitud.git
    cd gratitud-challenge
    ```

2. **Configurar la base de datos**

    Actualiza el archivo `application.properties` en `src/main/resources` con las credenciales de tu base de datos:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/challenge_gratitud
    spring.datasource.username=tu-usuario
    spring.datasource.password=tu-contraseña
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. **Compilar y ejecutar la aplicación**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Acceder a la documentación de la API**

    Abre tu navegador y ve a `http://localhost:8080/swagger-ui.html`.

## Endpoints Principales

### Autenticación

- **POST /login**
    - Autentica un usuario y devuelve un token JWT.

### Usuarios

- **POST /usuario/registrar**
    - Registra un nuevo usuario.
- **GET /usuario/{idUsuario}**
    - Obtiene los detalles de un usuario por su ID.
- **GET /usuario**
    - Muestra todos los usuarios.
- **PUT /usuario/{idUsuario}**
    - Actualiza los detalles de un usuario.
- **DELETE /usuario/{idUsuario}**
    - Elimina un usuario.

### Mensajes

- **POST /mensaje/{idUsuario}/crear-mensaje**
    - Crea un nuevo mensaje (tienes que estar logueado para poder hacerlo).
- **GET /mensaje/usuario/{idUsuario}**
    - Obtiene todos los mensajes de un usuario por su ID.
- **GET /mensaje/usuario**
    - Obtiene todos los mensajes de un usuario por su nombre.
- **POST /mensaje/{id}/puntuar**
    - Puntúa un mensaje, ingresando el ID de éste.
- **GET /mensaje**
    - Obtiene todos los mensajes.
- **GET /mensaje/orden/fecha**
    - Obtiene todos los mensajes ordenados por fecha en orden descendente.
- **GET /mensaje/orden/puntuacion**
    - Obtiene todos los mensajes ordenados por popularidad en orden descendente.
- **DELETE /mensaje/{idMensaje}**
    - Elimina un mensaje.

## Uso de Swagger

Swagger UI está disponible en `http://localhost:8080/swagger-ui.html`. Esta interfaz permite probar todos los endpoints de la API directamente desde el navegador y ver la documentación de cada uno.

## Seguridad

La API utiliza JWT para la autenticación y autorización. Solo los usuarios autenticados pueden crear, puntuar o eliminar mensajes. Los usuarios no autenticados pueden ver los mensajes.

## Contribución

Si deseas contribuir, por favor abre un pull request o reporta problemas en la sección de issues.
