# User Service Microservice

This microservice manages user accounts, including registration, retrieval, updates, and activation/deactivation. It also supports login and JWT refresh functionality.

## Features

- Register customers and create users by admin
- Retrieve all users or a specific user by ID
- Update user information
- Enable or disable users
- Login and JWT token refresh

## API Endpoints

| Method | Path              | Description              |
| ------ | ----------------- | ------------------------ |
| GET    | `/`               | Get all users            |
| GET    | `/{id}`           | Get user by ID           |
| PATCH  | `/{id}/disable`   | Disable a user           |
| PATCH  | `/{id}/enable`    | Enable a user            |
| POST   | `/register`       | Register a new customer  |
| POST   | `/admin/register` | Admin creates a new user |
| PATCH  | `/me/{id}`        | Update user information  |
| POST   | `/auth/login`     | Login user (JWT)         |
| POST   | `/auth/refresh`   | Refresh JWT token        |

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
## Running the Service

- Start PostgreSQL (e.g., with Docker).

-  Build and run the Spring Boot application:

- ./mvnw clean install
- ./mvnw spring-boot:run
