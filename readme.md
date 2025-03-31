# Rewards Program API

A Spring Boot demo application to simulate a customer rewards program based on monthly purchase transactions. This app uses an in-memory H2 database and exposes REST APIs to fetch total and month-wise reward points for customers.

---

## 🚀 Tech Stack

- Java 17
- Spring Boot (Web, Data JPA, Validation, Actuator)
- H2 In-Memory Database
- Lombok
- Maven
- JUnit 5 + Mockito

---

## 📦 Features

- Retrieve **total reward points** for a customer
- Retrieve **monthly reward points** for a customer
- Input validation and exception handling
- Sample transaction data auto-loaded using `import.sql`
- Unit tests for service and controller layers

---

## ▶️ Getting Started

### 1. Clone the repository


git clone https://github.com/YOUR_USERNAME/rewards-program.git
cd rewards-program

### 2. Build and run
mvn clean install
mvn spring-boot:run

🔗 API Endpoints
1. GET /api/v1/rewards/{custId}

    custId: Required, length between 5–10 characters

2. Monthly Reward Points for a Customer
   GET /api/v1/rewards/{custId}/{month}
   custId: Required, length 5–10 characters

   month: Required, number from 1 to 12 (e.g., 1 for Jan, 3 for Mar)

