# Pet Service – LovingPets

Pet Service is a microservice in the LovingPets project that handles all operations related to pets registered by clients. Its main responsibilities include creating, updating, listing, and deleting pets. Each pet is associated with a client.

---

## Project Overview

This service is built with **Java 21** and **Spring Boot 3.5.9**, packaged with **Gradle**.  
It follows a layered architecture separating domain logic, persistence, and web layers.

- **Domain**: DTOs, models, services, exceptions  
- **Persistence**: JPA entities, repositories, mappers  
- **Web**: REST controllers handling API requests  

The service is containerized with **Docker** and uses **PostgreSQL 16** as the database.

---

## Getting Started

### Database

```bash
docker-compose up -d
```

Connection details:

URL: jdbc:postgresql://localhost:5433/loving_pets_db  
Username: pet_service  
Password: pet_service_pass  

---

### Run the Service

```bash
./gradlew bootRun
```

By default, it runs on port **8083**.

---

## Features / Endpoints

| Method| Endpoint                  | Description             |
|-------|---------------------------|-------------------------|
| POST  | /pets                     | Register a new pet      |
| GET   | /pets/client/{clientId}   | List pets by client     |
| PUT   | /pets/{id}                | Update pet information  |
| DELETE| /pets/{id}                | Delete a pet            |

---

## Notes

- Each pet belongs to a client.  
- DTOs are used for communication between layers.  
- Logging is enabled for development and debugging.

---

## Folder Structure (Summary)

```
pet-service/
├── src/
├── Dockerfile
├── build.gradle
├── settings.gradle
└── README.md
```

---

## Tech Stack

- Java 21  
- Spring Boot 3.5.9  
- Gradle – Groovy  
- PostgreSQL 16  
- Docker  
- LangChain4j / OpenAI
