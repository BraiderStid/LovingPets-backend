# LovingPets Eureka Server

The Eureka Server for the LovingPets project acts as the **Service Registry**.
All microservices register themselves here so they can discover and communicate with each other without hardcoded URLs.

## Features

- Service discovery using Netflix Eureka
- Central registry for all microservices
- Enables dynamic communication between services
- Integrated with Spring Cloud Config Server

## Configuration

- Java 21
- Spring Boot 3.5.9
- Spring Cloud Netflix Eureka Server
- Configuration loaded from Config Server (config-repo)

## application.properties (local)

These properties are kept locally to allow the Eureka Server to start correctly:

spring.application.name=lovingpets-eureka-server  
spring.profiles.active=dev

## Config loaded from Config Server (config-repo)

In config-repo:

lovingpets-eureka-server-dev.properties

server.port=8761  
eureka.client.register-with-eureka=false  
eureka.client.fetch-registry=false  
eureka.server.enable-self-preservation=true  
eureka.server.eviction-interval-timer-in-ms=60000

## Running the Eureka Server

1. Make sure the Config Server is running first:
   http://localhost:8888

2. Build the project:
   ./mvnw clean install

3. Run the server:
   ./mvnw spring-boot:run

4. Open the Eureka Dashboard:
   http://localhost:8761

## How it Works

- Microservices load their configuration from the Config Server
- Each microservice **automatically registers itself** in Eureka when it starts
- The Eureka Server must be running so other services can register
- Services discover each other using their `spring.application.name`
- No hardcoded host/ports are needed between microservices

## Example Registered Services

- auth-service
- user-service
- pet-service
- appointment-service

All of them appear in the Eureka dashboard once running.
