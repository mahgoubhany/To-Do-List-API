# ToDo List API — Spring Boot + JWT

A secure RESTful backend API for managing personal tasks.
The system supports **user registration, authentication using JWT, and task management (CRUD)** where each authenticated user can manage **only their own tasks**.

This project was built as a backend practice project focusing on:

* REST API design
* Spring Boot architecture
* JWT authentication
* PostgreSQL integration
* Secure user-scoped data access

---

# 🚀 Features

### Authentication

* User Registration
* User Login
* JWT Token Generation
* Secure endpoints using JWT

### Task Management

* Create tasks
* Retrieve tasks
* Filter tasks by title and status
* Update tasks
* Delete tasks

### Security

* Passwords stored using **BCrypt hashing**
* Authentication using **JWT tokens**
* Users can **only access their own tasks**
* Sensitive data (password hash) is **never returned in API responses**

---

# 🧱 Tech Stack

Backend Framework

* Java
* Spring Boot

Security

* Spring Security
* JWT (JSON Web Tokens)
* BCrypt password hashing

Database

* PostgreSQL
* Spring Data JPA / Hibernate

Utilities

* Lombok
* Maven

---

# 📁 Project Structure

```
src/main/java/com/example/todo

controller
    AuthController
    TaskController

service
    UserService
    TaskService

repository
    UserRepository
    TaskRepository

model
    User
    Task
    TaskStatus

dto
    LoginRequest
    RegisterRequest
    CreateTaskRequest
    UpdateTaskRequest

security
    JwtService
    JwtAuthenticationFilter
    SecurityConfig
```

---

# 🔐 Authentication

The API uses **JWT-based authentication**.

After login, the client receives a **JWT token** which must be included in every protected request.

Example header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# 📦 API Endpoints

## Authentication Endpoints

### Register User

POST `/auth/register`

Creates a new user account.

Request Body

```json
{
  "username": "mahgoub1",
  "email": "m@example.com",
  "password": "123456"
}
```

Response

```json
{
  "message": "User registered successfully"
}
```

---

### Login

POST `/auth/login`

Authenticates the user and returns a JWT token.

Request Body

```json
{
  "username": "mahgoub1",
  "password": "123456"
}
```

Response

```json
{
  "token": "JWT_TOKEN_HERE"
}
```

---

# 📝 Task Endpoints

All task endpoints require authentication.

Header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Create Task

POST `/tasks`

Creates a new task for the authenticated user.

Request Body

```json
{
  "title": "Study Spring",
  "description": "Practice REST APIs",
  "status": "PENDING"
}
```

Response

```json
{
  "id": "UUID",
  "title": "Study Spring",
  "description": "Practice REST APIs",
  "status": "PENDING",
  "created_at": "timestamp",
  "updated_at": "timestamp"
}
```

---

## Get All Tasks

GET `/tasks`

Returns all tasks belonging to the authenticated user.

Response

```json
[
  {
    "id": "UUID",
    "title": "Study Spring",
    "description": "Practice REST APIs",
    "status": "PENDING"
  }
]
```

---

## Get Task by ID

GET `/tasks/{id}`

Returns a specific task owned by the authenticated user.

---

## Update Task

PUT `/tasks/{id}`

Updates a task.

Request Body

```json
{
  "title": "Study Spring Boot",
  "description": "Practice security",
  "status": "IN_PROGRESS"
}
```

---

## Delete Task

DELETE `/tasks/{id}`

Deletes a task belonging to the authenticated user.

Response

```
Task deleted successfully
```

---

## Filter Tasks

GET `/tasks/filter`

Optional query parameters:

| Parameter | Description            |
| --------- | ---------------------- |
| status    | Filter tasks by status |
| title     | Filter tasks by title  |

Example

```
GET /tasks/filter?status=PENDING
```

```
GET /tasks/filter?title=Study
```

```
GET /tasks/filter?status=PENDING&title=Study
```

---

# 📊 Task Status Enum

Allowed values:

```
PENDING
IN_PROGRESS
COMPLETED
CANCELLED
```

---

# 🗄 Database Schema

## Users Table

| Column        | Type      |
| ------------- | --------- |
| id            | UUID      |
| username      | VARCHAR   |
| email         | VARCHAR   |
| password_hash | VARCHAR   |
| created_at    | TIMESTAMP |
| updated_at    | TIMESTAMP |

---

## Tasks Table

| Column      | Type                 |
| ----------- | -------------------- |
| id          | UUID                 |
| title       | VARCHAR              |
| description | TEXT                 |
| status      | VARCHAR              |
| created_at  | TIMESTAMP            |
| updated_at  | TIMESTAMP            |
| user_id     | UUID (FK → users.id) |

---

# 🔐 Security Rules

* All `/tasks/**` endpoints require authentication
* `/auth/login` and `/auth/register` are public
* Tasks are **user-scoped**
* A user **cannot access or modify another user's tasks**

---

# ⚙️ Setup Instructions

## 1 Clone Repository

```
git clone https://github.com/YOUR_USERNAME/todo-api.git
```

---

## 2 Configure Database

Create PostgreSQL database:

```
TodoList
```

Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/TodoList
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 3 Run Application

```
mvn spring-boot:run
```

Server will start at:

```
http://localhost:8080
```

---

# 🧪 Testing

Recommended tools:

* Postman
* Insomnia
* curl

Test flow:

1. Register a user
2. Login to obtain JWT
3. Use JWT to access `/tasks` endpoints

---

# 📌 Future Improvements

Potential enhancements:

* Pagination for tasks
* Task due dates
* Task priority levels
* Global exception handling improvements
* DTO-based response models
* Refresh tokens for JWT
* Docker support

---

# 👨‍💻 Author

Mahgoub Hany
Computer Engineering Student

