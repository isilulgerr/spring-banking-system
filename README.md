# 🏦 Smart Wallet & Banking System API

A high-performance, scalable Backend API designed for modern banking operations. This project demonstrates enterprise-level software engineering practices using **Java 17**, **Spring Boot 3**, and **Microservices-ready architecture**.

## 🚀 Key Features

- **Real-time Currency Integration:** Seamlessly fetches live exchange rates from external financial APIs.
- **Performance Optimization:** Implements **Redis Caching** to reduce API latency and minimize external service costs.
- **Robust Exception Handling:** Global error handling mechanism using `@RestControllerAdvice` for consistent API responses.
- **Data Integrity:** Strict transactional management with `@Transactional` to ensure atomicity in money transfers.
- **Security & Validation:** Role-based access control and strict data validation using **Spring Security** and **Bean Validation**.
- **Automated Documentation:** Interactive API explorer and documentation provided by **Swagger UI (OpenAPI 3)**.

## 🛠 Tech Stack

- **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA
- **Database:** PostgreSQL
- **Caching:** Redis
- **Containerization:** Docker, Docker Compose
- **Testing:** JUnit 5, Mockito
- **Documentation:** Swagger / OpenAPI

## 🏗 System Architecture

The project follows a **Layered Architecture** (Controller, Service, Repository) to ensure separation of concerns and maintainability.

- **Redis** is used as a caching layer for exchange rates.
- **PostgreSQL** stores users, accounts, and transaction history.
- **Docker Compose** orchestrates all services for a "one-click" startup experience.

## 🛠 Installation & Setup

### Prerequisites
- Docker & Docker Compose installed.

### Running the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/isilulgerr/spring-banking-system.git
   cd bankingsystem
   ```

2. Spin up the environment (App, DB, Redis):
   ```bash
   docker-compose up --build
   ```

The API will be available at http://localhost:8080.

## 📖 API Documentation
Once the application is running, you can explore and test the API endpoints via Swagger UI:
📌 http://localhost:8080/swagger-ui/index.html

## 🧪 Testing
The core business logic (Money Transfer, Account Creation, etc.) is fully covered by unit tests.
To run the tests:

```bash
mvn test
```

Developed by Işıl Ülger - Computer Engineer
