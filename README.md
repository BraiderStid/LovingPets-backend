# LovingPets – Microservices Backend

Portfolio project focused on backend development using Java, Spring Boot, and a microservices architecture. 
The platform combines veterinary and pet shop services, with access for both clients and employees.

## Architecture Overview

This repository contains the backend for LovingPets. 
Each microservice is independent and will be connected later through API Gateway and service discovery.

## Authentication Flow

Authentication is handled by a dedicated auth-service.
This service is responsible for user login and JWT token generation.

1. The client authenticates using the auth-service.
2. The auth-service returns a JWT access token.
3. The client must include this token in the `Authorization` header
when calling protected endpoints in this or in others microservices.

## Authentication & Demo Users

This project uses JWT-based authentication with role-based access control.

For demonstration purposes, the database is initialized with predefined users
using `data.sql`.

### Preloaded demo users (local environment only)

⚠️ These credentials are for demonstration purposes only.

| Role     | Email                     | Password     |
|----------|---------------------------|--------------|
| ADMIN    | admin@lovingpets.com      | admin123     |
| CLIENT   | cliente1@lovingpets.com   | cliente123   |
| EMPLOYEE | empleado1@lovingpets.com  | empleado123  |

Note: The user must be 

## Microservices

**Implemented**
- Auth Service (see [Auth Service README](./backend/auth-service/README.md))
- Pet Service (see [Pet Service README](./backend/pet-service/README.md))
- User Service (see [User Service README](./backend/user-service/README.md))
- Appointment Service (see [Appointment Service README](./backend/appointment-service/README.md))

**Planned**
- Appointment Service: handles scheduling and management of appointments between clients and pets

## Current Status

- Only the Pet Service is implemented so far.
- Each service has its own Docker container and database (PostgreSQL).
- Microservices follow clean architecture principles and expose REST APIs.

## Technologies

- Java 21
- Spring Boot 3.5.9
- Gradle
- PostgreSQL (via Docker)
- `.properties` configuration files

## Roadmap / Next Steps

- Implement Auth Service with JWT authentication
- Implement User Service
- Add API Gateway and service discovery
- Integrate all services with Docker Compose

## Postman Collection

Postman collection to test the endpoints of the LovingPets project.

# Files
- LovingPets.postman_collection.json

# Usage
Import the collection into Postman and run the microservices to test the available endpoints.

## Notes

- Each microservice has its own README with instructions for running, endpoints, and Docker setup.