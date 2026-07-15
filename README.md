# QuickFix Backend 🚀

QuickFix Backend is a Spring Boot REST API for an Urban Company–style home service platform. It provides secure JWT authentication, worker profile management, category and service management, and forms the foundation for a complete booking system.

## Features

- JWT Authentication & Authorization
- User Registration & Login
- Worker Profile Management
- Category CRUD Operations
- Service CRUD Operations
- Services by Category API
- Spring Data JPA & Hibernate
- MySQL Database Integration
- RESTful APIs
- Layered Architecture (Controller → Service → Repository)
- DTO-based Request & Response Handling

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Postman

## Project Structure

```
src/main/java/com/quickfix
│
├── controller
├── dto
├── entity
├── repository
├── security
├── service
└── config
```

## Implemented APIs

### Authentication
- Register User
- Login User

### Worker
- Create Worker Profile
- Get Worker Profile
- Update Worker Profile

### Categories
- Create Category
- Get All Categories
- Get Category By ID
- Update Category
- Delete Category

### Services
- Create Service
- Get All Services
- Get Service By ID
- Update Service
- Delete Service
- Get Services By Category

## Upcoming Features

- Booking Management
- Worker Assignment
- Reviews & Ratings
- Payment Integration
- Admin Dashboard
- Email Notifications
- Global Exception Handling
- Request Validation
- Deployment

## Getting Started

1. Clone the repository

```bash
git clone https://github.com/JITENDERKUMAR-CODER/QuickFix-Backend.git
```

2. Configure MySQL in `application.properties`

3. Run the project

```bash
mvn spring-boot:run
```

The backend will start at:

```
http://localhost:8080
```

## Author

**Jitender Kumar**

GitHub: https://github.com/JITENDERKUMAR-CODER
