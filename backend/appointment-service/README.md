# Appointment Service Microservice – LovingPets

Appointment Service is a microservice in the LovingPets project responsible for managing all veterinary appointment-related 
operations, including scheduling, status management, medical records, and treatments. It enforces role-based access 
control via JWTs issued by the Auth Service.

## Features

- Create veterinary appointments
- View appointment schedules (for employees)
- Access pet medical history
- Register diagnostics and treatments
- Change appointment status (Pending, Attended, Cancelled)
- JWT-based endpoint security

## Responsibility

This service handles all appointment, medical record, and treatment operations.
It does not issue JWTs, but validates tokens issued by the Auth Service to secure endpoints and enforce role-based access.

### Appointment Endpoints

| Method | Endpoint                  | Description                | Roles           | Filters      |
| ------ | ------------------------- | -------------------------- | --------------- | ------------ |
| POST   | /appointments             | Create a new appointment   | ADMIN, EMPLOYEE | N/A          |
| GET    | /appointments             | List appointments          | ADMIN, EMPLOYEE | status, date |
| PATCH  | /appointments/{id}        | Update appointment details | ADMIN, EMPLOYEE | N/A          |
| PATCH  | /appointments/{id}/status | Change appointment status  | ADMIN, EMPLOYEE | N/A          |


### Medical Record Endpoints

| Method | Endpoint         | Description                 | Roles           | Filters              |
| ------ | ---------------- | --------------------------- | --------------- | -------------------- |
| POST   | /medical-records | Create a new medical record | ADMIN, EMPLOYEE | N/A                  |
| GET    | /medical-records | List medical records        | ADMIN, EMPLOYEE | petId, ownerId, date |


### Treatment Endpoints (Nested under Medical Records)

| Method | Endpoint                                      | Description                             | Roles           | Filters                |
| ------ | --------------------------------------------- | --------------------------------------- | --------------- | ---------------------- |
| POST   | /medical-records/{medicalRecordId}/treatments | Create a treatment for a medical record | ADMIN, EMPLOYEE | N/A                    |
| GET    | /medical-records/{medicalRecordId}/treatments | List treatments of a medical record     | ADMIN, EMPLOYEE | medicalRecordId (path) |

### Treatment Endpoints (Global)

| Method | Endpoint                | Description        | Roles           | Filters     |
| ------ | ----------------------- | ------------------ | --------------- | ----------- |
| GET    | /treatments             | List treatments    | ADMIN, EMPLOYEE | treatmentId |
| PATCH  | /treatments/{id}/finish | Finish a treatment | ADMIN, EMPLOYEE | N/A         |

## Technologies

- Java 21
- Spring Boot 3.5.9
- Gradle
- PostgreSQL (via Docker)
- .properties configuration files

## Database Configuration

- Database: loving_appointment_db
- Username: appointment_service
- Password: appointment_service_pass
- Port: 5436

## Docker Example

```yaml
services:
  postgres:
    image: 'postgres:16'
    restart: unless-stopped
    environment:
      - POSTGRES_DB=loving_appointment_db
      - POSTGRES_USER=appointment_service
      - POSTGRES_PASSWORD=appointment_service_pass
    ports:
      - '5436:5432'
    volumes:
      - appointment_pgdata:/var/lib/postgresql/data

volumes:
  appointment_pgdata:
    driver: local

```

## application-dev.properties

server.port=8086

### OpenAI API key for LangChain (demo)
langchain4j.open-ai.chat-model.api-key=demo

# Spring JPA Driver
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5436/loving_appointment_db
spring.datasource.username=appointment_service
spring.datasource.password=appointment_service_pass

spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

### Logging
logging.level.dev.langchain4j=DEBUG
logging.level.dev.ai4j.openai4j=DEBUG
langchain4j.open-ai-chat-model.log-requests=true
langchain4j.open-ai-chat-model.log-responses=true
logging.level.org.springframework.security.web=DEBUG

## Running the Service

1. Start PostgreSQL with Docker
   docker-compose up -d

2. Build the application
   ./mvnw clean install

3. Run the service locally
   ./mvnw spring-boot:run