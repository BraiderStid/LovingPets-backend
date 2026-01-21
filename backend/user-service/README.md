# User Service Microservice – LovingPets

User Service is a microservice in the LovingPets project responsible for managing all user-related data and operations, including clients, employees, and administrators. It allows creating user profiles, updating information, and retrieving user data, enforcing role-based access control via JWTs issued by the Auth Service.

## Features
- Create user profiles
- List all users (ADMIN, EMPLOYEE only)
- Get authenticated user's profile
- Update profiles (self-update for clients, full update for admins/employees)
- JWT-based endpoint security

## Responsibility
This service handles all user-related operations and data.  
It does not issue JWTs but validates tokens issued by the Auth Service to secure endpoints and enforce role-based access.

## Authentication & Authorization

This service uses **JWT-based authentication**.

After a successful login in auth-service, a JWT token is issued.
To access protected endpoints, the client must include the token
in the `Authorization` header using the **Bearer Token** scheme.

Example:
Authorization: Bearer <JWT_TOKEN>

The authentication type must be set to **Bearer Token** when calling secured endpoints.

## API Endpoints

| Method | Endpoint      | Description                               | Roles                        |
| ------ | ------------- | ----------------------------------------- | ---------------------------- |
| POST   | /users        | Create a new user profile                  | ADMIN, EMPLOYEE              |
| GET    | /users        | List all users                             | ADMIN, EMPLOYEE              |
| GET    | /users/me     | Retrieve authenticated user's profile      | Any authenticated user       |
| GET    | /users/{id}   | Retrieve user by ID                        | ADMIN, EMPLOYEE              |
| PATCH  | /users/{id}   | Update a user profile                       | ADMIN, EMPLOYEE              |
| PATCH  | /users/me     | Update authenticated user's profile        | Any authenticated user       |

## Technologies
- Java 21
- Spring Boot 3.5.9
- Gradle
- PostgreSQL (via Docker)
- `.properties` configuration files

## Database Configuration
- Database: `loving_users_db`
- Username: `user_service`
- Password: `user_service_pass`
- Port: `5435`

### Docker Example
```yaml
services:
  postgres:
    image: 'postgres:16'
    restart: unless-stopped
    environment:
      - POSTGRES_DB=loving_users_db
      - POSTGRES_USER=user_service
      - POSTGRES_PASSWORD=user_service_pass
    ports:
      - '5435:5432'
    volumes:
      - user_pgdata:/var/lib/postgresql/data

volumes:
  user_pgdata:
    driver: local
```

## Configuration

### application.properties
spring.profiles.active=dev
spring.config.import=optional:configserver:http://localhost:8888
spring.application.name=user-service

### Dev Profile 
- Stored in LovingPets\config-repo
- Expected properties:
  server.port
  spring.datasource.url
  spring.datasource.username
  spring.datasource.password

## Running the Service
-Docker is required because PostgreSQL runs in a container. 
The Spring Boot application runs locally.

1. Start PostgreSQL with Docker:
   docker-compose up -d

2. Build the application:
   ./mvnw clean install
3. Run the service locally:
   ./mvnw spring-boot:run