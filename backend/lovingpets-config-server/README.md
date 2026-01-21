#  LovingPets Config Server

The Config Server for the LovingPets project centralizes configuration for all microservices.
It reads .properties files from the config-repo folder and serves them to microservices based on their name and active profile.

## Features

- Centralized configuration for all microservices
- Supports profile-based configuration (dev or more)
- Spring Cloud Config native mode (local filesystem)

## Configuration

- Java 21
- Spring Boot 3.5.9
- Config files stored in LovingPets\config-repo folder

## application.properties

spring.application.name=lovingpets-config-server
server.port=8888
spring.cloud.config.server.native.search-locations=file:///C:/Users/USER/Desktop/LovingPets/config-repo
spring.profiles.active=native

## Running the Config Server

1. Build the project:
   ./mvnw clean install
2. Run the server:
   ./mvnw spring-boot:run
3. Test endpoints:
   Auth Service dev config: http://localhost:8888/auth-service/dev
   Pet Service dev config: http://localhost:8888/pet-service/dev
   Appointment Service dev config: http://localhost:8888/appointment-service/dev
   User Service dev config: http://localhost:8888/user-service/dev