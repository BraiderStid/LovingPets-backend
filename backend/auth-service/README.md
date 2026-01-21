# Auth Service Microservice - LovingPets

This microservice is responsible for user authentication, user management, and JWT token generation for the LovingPets platform.

## Features

- Register customers and create users by admin
- Retrieve all users or a specific user by ID
- Update user information
- Enable or disable users
- Login and JWT token refresh

## Responsibility

This service acts as the authentication authority.
Other microservices trust the JWT tokens issued by this service
to authorize protected endpoints.

## Authentication & Authorization

This service uses **JWT-based authentication**.

After a successful login, a JWT token is issued.
To access protected endpoints, the client must include the token
in the `Authorization` header using the **Bearer Token** scheme.

Example:
Authorization: Bearer <JWT_TOKEN>

The authentication type must be set to **Bearer Token** when calling secured endpoints.

## API Endpoints

| Method | Endpoint        | Description                      | Roles           |
| ------ | --------------- | -------------------------------- | --------------- |
| POST   | /auth/login     | Authenticate user and return JWT | Public (anyone) |
| POST   | /auth/refresh   | Refresh JWT token                | Public (anyone) |
| POST   | /users/register | Register a new user              | Public (anyone) |
| GET    | /users/{id}     | Retrieve user information by ID  | ADMIN, EMPLOYEE |
| PATCH  | /users/me       | Update own user information      | ADMIN, EMPLOYEE |
| GET    | /users          | List all users                   | ADMIN           |


## Technologies

- Java 17+ with Spring Boot
- Spring Security with JWT
- PostgreSQL
- Docker for database

## Database Configuration

- Database: `loving_auth_db`
- Username: `auth_service`
- Password: `auth_service_pass`
- Port: `5434`

### Docker Example

```yaml
services:
  postgres:
    image: 'postgres:16'
    restart: unless-stopped
    environment:
      - POSTGRES_DB=loving_auth_db
      - POSTGRES_USER=auth_service
      - POSTGRES_PASSWORD=auth_service_pass
    ports:
      - '5434:5432'
    volumes:
      - auth_pgdata:/var/lib/postgresql/data

volumes:
  auth_pgdata:
    driver: local 
```

## Configuration

### application.properties
spring.profiles.active=dev
spring.application.name=auth-service
spring.config.import=optional:configserver:http://localhost:8888

### Dev Profile
- Stored in LovingPets\config-repo
- Expected properties:
  server.port
  spring.datasource.url
  spring.datasource.username
  spring.datasource.password

## Running the Service

> Docker is required because PostgreSQL runs in a Docker container.
> The Spring Boot application runs locally.

1. Start PostgreSQL using Docker:
   ```bash
   docker-compose up -d
2. Build the application:
  ./mvnw clean install
3. Run the service locally:
  ./mvnw spring-boot:run