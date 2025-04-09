# 🎁 Rewards Program API

A Spring Boot demo application to simulate a customer rewards program based on monthly purchase transactions.  
This app uses an in-memory H2 database and exposes REST APIs to fetch total and month-wise reward points for customers.

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
- Input validation and custom exception handling
- Sample transaction data loaded via `import.sql`
- Flow : Sample data loaded during application start up .
  Sample data consists of 5 customers transaction over a period of three months . 
  Rest Api's to fetch rewards point earned by a customer per month and in total

---

## ▶️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/rewards-program.git
cd rewards-program
```

### 2. Build and run the app

```bash
mvn clean install
mvn spring-boot:run
```

Appliation  starts at: `http://localhost:8080`

---

## 🧪 Access H2 Console

- URL: `http://localhost:8080/h2-console`

---

## 🔗 API Endpoints

---

### 1. Total Reward Points for a Customer

**Request**
```
GET /api/v1/rewards/{custId}
```

**Example**
```
GET /api/v1/rewards/CUST001
```

**Response (200 OK)**
```json
{
  "custId": "CUST001",
  "totalRewardPointsEarned": 250.0
}
```

**Response (404 Not Found — Invalid customer)**
```json
{
  "status": 404,
  "message": "Customer Id : INVALID123 not found. "
}
```

---

### 2. Monthly Reward Points for a Customer

**Request**
```
GET /api/v1/rewards/{custId}/{month}
```

**Path Parameters**
- `custId`: Required, length 5–10 characters
- `month`: Required, number from 1 to 12 (e.g., 1 for Jan, 3 for Mar)

**Example**
```
GET /api/v1/rewards/CUST001/3
```

**Response (200 OK)**
```json
{
  "custId": "CUST001",
  "rewardPointsEarnedForTheMonth": 85.0,
  "month": "3"
}
```

**Response (404 No Content — no transactions in the given month)**
```
(no response body)
```

**Response (400 Bad Request — invalid month format)**
```json
{
  "status": 400,
  "message": "Invalid month. Please provide a number from 1 to 12."
}
```

---

### 3. Validation Error (Invalid customer ID)

**Request**
```
GET /api/v1/rewards/CUS
```

**Response (400 Bad Request)**
```json
{
  "status": 400,
  "message": "Validation failed: custId size must be between 5 and 10"
}
```



