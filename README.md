# E-Commerce Backend (Monolithic Architecture)

## Overview

This project is a backend e-commerce application built using Spring Boot following a monolithic architecture. It provides REST APIs for managing products, users, and orders within a single application.

The project was developed to strengthen backend development skills using Java, Spring Boot, PostgreSQL, and RESTful API design.

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Docker (PostgreSQL)
* REST APIs

## Features

* Product Management

  * Create products
  * Update products
  * Delete products
  * Retrieve product details

* User Management

  * Register users
  * Update user information
  * Retrieve user details

* Order Management

  * Place orders
  * View order history
  * Track order status

## Project Structure

```text
src
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── dto
 └── resources
```

## Database

PostgreSQL is used as the primary relational database.

## Running the Project

1. Clone the repository.

2. Configure PostgreSQL in `application.properties`.

3. Start the application.

```bash
./mvnw spring-boot:run
```

The application will start on:

```
http://localhost:8088
```

## Future Improvements

* JWT Authentication
* Docker Compose
* API Documentation using Swagger
* Unit and Integration Testing
* Caching with Redis
* Microservices Migration

## Learning Outcomes

* Spring Boot application development
* REST API design
* Database design using JPA
* CRUD operations
* Maven project management
* Backend architecture fundamentals
