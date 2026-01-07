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
- Java 17+ with Spring Boot
- Spring Security with JWT
- PostgreSQL
- Docker

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

## application-dev.properties
server.port=8085
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5435/loving_users_db
spring.datasource.username=user_service
spring.datasource.password=user_service_pass
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.springframework.security.web=DEBUG

## Running the Service
-Docker is required because PostgreSQL runs in a container. 
The Spring Boot application runs locally.

1. Start PostgreSQL with Docker:
   docker-compose up -d

2. Build the application:
   ./mvnw clean install
3. Run the service locally:
   ./mvnw spring-boot:run