# LovingPets – Microservices Backend

Portfolio project focused on backend development using Java, Spring Boot, and a microservices architecture. 
The platform combines veterinary and pet shop services, with access for both clients and employees.

## Architecture Overview

This repository contains the backend for LovingPets. 
Each microservice is independent and will be connected later through API Gateway and service discovery.

### Microservices

**Implemented**
- Pet Service (see [Pet Service README](./pet-service/README.md))

**Planned**
- Auth Service: handles login, registration, roles, and JWT tokens
- User Service: manages user profiles and role assignment

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

## Notes

- Each microservice has its own README with instructions for running, endpoints, and Docker setup.