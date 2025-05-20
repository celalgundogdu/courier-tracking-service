# 📍 Courier Tracking Service

A RESTful web application that receives geolocation data of couriers and tracks their movement in relation to store locations. Developed using Java and Spring Boot.

## 🚀 Features

- Accepts **geolocation data** (`courierId`, `latitude`, `longitude`, `timestamp`).
- Detects and logs **courier entrance events** into a store's 100-meter radius.
  - Re-entries to the same store within 1 minute are **ignored**.
- Calculates and provides **total distance traveled** by each courier.
- Loads **store data** from `stores.json` on application startup.
- Implements **design patterns**:
  - **Strategy Pattern** for distance calculation logic.
  - **Observer Pattern** (via Spring Events) for handling store entrance logging.

## 📦 Tech Stack

- **Java 17**
- **Spring Boot 3.4**
- **H2 Database**
- **Spring Data JPA**
- **Spring Events**
- **Spring Bean Validation**
- **Springdoc OpenAPI**
- **Lombok**
- **JUnit & Mockito**
- **Docker & Docker Compose**


## ⚙️ Installation & Running

### 1. Clone the repository

```bash
git clone https://github.com/celalgundogdu/courier-tracking-service
cd courier-tracking-service
```

### 2. Start the application with Docker Compose
```bash
docker-compose up --build
```

## 📚 API Documentation

You can access the API documentation via Swagger UI at the following URL:
http://localhost:8080/swagger-ui/index.html

Here, you can explore all available API endpoints, test them, and get detailed information.

## 🛢️ Accessing H2 Database Console
To access the H2 Database Console, follow these steps:

* Make sure the application is running.

* Navigate to: http://localhost:8080/h2-console

* Use the following credentials:

    * JDBC URL: jdbc:h2:mem:couriertrackingdb
    * Username: sa 
    * Password: (leave empty)

* Click Connect.

## 🧪 Testing
Run unit and integration tests:
```bash
./mvnw test
```

## 📐 Design Patterns
Strategy Pattern: Used for switching distance calculation algorithms if needed in future (e.g. Haversine).

Observer Pattern: Used with @EventListener to trigger store entrance log creation after location is saved.

## 👤 **Contact**

For any questions or feedback, please contact:
- **Name**: Celal Gündoğdu
- **LinkedIn**: [celalgundogdu](https://www.linkedin.com/in/celalgundogdu/)