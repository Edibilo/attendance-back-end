# Attendance Management System - Backend

A Spring Boot REST API for managing school attendance, students, classes, users, and reports.

---

# Technologies Used

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- JWT Authentication
- MySQL
- Maven
- ModelMapper
- Lombok
- Jakarta Validation

---

# Features

## Authentication

- User Login
- JWT Authentication
- Role Based Authorization
- BCrypt Password Encryption

---

## User Management

- Create User
- Update User
- Delete User
- Get All Users
- Get User By ID
- User Profile

---

## Student Management

- Register Student
- Update Student
- Delete Student
- View Student
- Search Student
- Pagination Support

---

## Class (Level) Management

- Create Class
- Update Class
- Delete Class
- Get All Classes
- Get Class By ID

---

## Student Class History

- Assign Student to Class
- Track Student Class History
- Filter Students by
  - Class
  - Year
  - Student Name

---

## Student Promotion

Promote an entire class into another class while keeping student history.

Example:

```
FORM ONE A
        ↓
FORM TWO A
```

Duplicate promotion is prevented.

---

## Attendance Management

- Mark Attendance
- Present
- Absent
- Sick
- Edit Attendance
- Daily Attendance

---

## Dashboard Reports

### Today Dashboard

- Total Students
- Present
- Absent
- Sick
- Attendance Rate

---

### Student Report

- Present Days
- Absent Days
- Sick Days
- Attendance Percentage

---

### Class Report

- Students
- Male
- Female
- Present
- Absent
- Sick
- Attendance Percentage

---

### Stream Report

- Class
- Stream
- Students
- Male
- Female
- Attendance Statistics

---

# Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── exception
 ├── repository
 ├── security
 ├── service
 │     ├── impl
 ├── util
 └── AttendanceApplication
```

---

# Database

MySQL

Example configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/attendance_db
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# API Modules

## Authentication

```
POST /api/auth/login
```

---

## Users

```
GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}
GET    /users/profile
```

---

## Students

```
GET    /students
GET    /students/{id}
POST   /students
PUT    /students/{id}
DELETE /students/{id}
```

---

## Classes

```
GET    /classes
GET    /classes/{id}
POST   /classes
PUT    /classes/edit/{id}
DELETE /classes/delete/{id}
```

---

## Student Promotion

```
POST /classes/promote?fromLevelId=1&toLevelId=2
```

---

## Attendance

```
POST   /attendance
PUT    /attendance/{id}
GET    /attendance
GET    /attendance/today
```

---

## Dashboard

```
GET /dashboard/today
GET /dashboard/student
GET /dashboard/class
GET /dashboard/stream
```

---

# Security

- JWT Authentication
- BCrypt Password Encoding
- Spring Security
- Role Based Access Control

---

# Exception Handling

Global exception handling for:

- Resource Not Found
- Validation Errors
- Duplicate Records
- Unauthorized Access
- Bad Requests

---

# Build

Clone the repository

```bash
git clone https://github.com/yourusername/attendance-management-backend.git
```

Move into project

```bash
cd attendance-management-backend
```

Run

```bash
mvn spring-boot:run
```

Or build

```bash
mvn clean install
```

---

# Default Roles

```
ROLE_ADMIN

ROLE_TEACHER

ROLE_STUDENT
```

---

# Author

**Mohamed Yussuf**

Attendance Management System Backend using Spring Boot, Spring Security, JWT, JPA, and MySQL.
