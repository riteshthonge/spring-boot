# Spring Boot Employee Management API

A comprehensive employee and user management system built with modern Java technologies.

---

## Overview

This project is a **Spring Boot** based web application for managing employees and users. It supports secure user authentication and authorization with **JWT tokens** and **Spring Security**. The system provides CRUD operations for employees and user management features such as registration, password change, and role-based access control.

---

## Technologies Used

- **Spring Boot** - Framework for building Java web applications.
- **Spring Security** - Provides authentication and authorization.
- **JWT (JSON Web Token)** - For stateless secure user authentication.
- **Spring Data JPA** - Simplifies data access with ORM.
- **Hibernate** - JPA implementation.
- **MySQL** - Relational database for data storage.
- **BCrypt** - Password hashing.
- **Maven** - Dependency management and build automation.
- **Java 17** (or specify your Java version)
- **REST API** - For communication with frontend or API clients like Postman.

---

## Features

- User registration and login with JWT authentication.
- Role-based access control: ADMIN and USER roles.
- CRUD operations for employees (Add, Update, Delete, List).
- Secure password storage with BCrypt.
- Password change functionality for users and admins.
- Token-based stateless authentication with JWT.
- CORS configuration for cross-origin requests.
- Validation of user inputs.
- Clear and meaningful error handling.
- Swagger UI integration for API documentation (if added).

---

## Project Structure

- **controller** - REST API controllers for handling HTTP requests.
- **service** - Business logic layer.
- **repository** - Database access layer using Spring Data JPA.
- **model** - Entity classes representing database tables.
- **dto** - Data Transfer Objects to shape API responses.
- **security** - JWT utilities and Spring Security configuration.
- **exception** - Custom exception handling (optional).

---

## Getting Started

### Prerequisites

- Java JDK 17 or higher installed
- MySQL database up and running
- Maven installed

### Setup

1. Clone the repository:

```bash
git clone https://github.com/abdularahman1243/springboot-employee-management-system-api.git
cd springboot-employee-management-system-api
---
```

2. Configure your database connection in src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/ems
spring.datasource.username=root
spring.datasource.password=abc@123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3. Build and run the application

mvn clean install
mvn spring-boot:run

4. Access API endpoints via http://localhost:8080/api using tools like Postman.

API Endpoints (examples)
POST /api/auth/login - User login, returns JWT token.

POST /api/auth/register - Register new users (ADMIN only).

GET /api/admin/allEmployees - List all employees (ADMIN only).

POST /api/admin/addEmployee - Add new employee (ADMIN only).

PUT /api/admin/updateEmployee - Update employee info (ADMIN only).

DELETE /api/admin/deleteEmployee/{id} - Delete employee by ID (ADMIN only).

POST /api/admin/changeUserPassword - Admin changes user password.

POST /api/users/changeOwnPassword - Users change their own password.

GET /api/admin/getAllUsers - Get All Users.

DELETE /api/admin/deleteUser/{id} - Delete Users with Id.

GET /api/admin/adminDashboard - Show Dashboard Content.

.....






Note :
Employee Management System, Spring Boot Employee Management, Spring Security, JWT Authentication, Role-Based Access Control, User Management, Employee CRUD, MySQL Database, Hibernate ORM, JPA, BCrypt Password Hashing, RESTful API, Java Backend, Secure User Login, Password Encryption, Admin Panel, User Roles, API Security, Spring Data JPA, Token-Based Authentication, Java Spring Project, Backend Development, Open Source Employee System, Employee Records Management, Secure REST API, User Registration, Password Reset, Access Control, CORS Configuration, Software Development, Full Stack Java Application
