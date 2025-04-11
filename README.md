# microservices-architecture-particles
(Eureka Server, Config Server, API Gateway, Kafka, File Storage, JWT, Authentication, Authorization, Redis, Docker)
## 📖 About the Project

This project follows the **Spring Boot Microservices** architecture, allowing users to:

✅ **Register and log in** via the **Auth Service** using JWT authentication.  
✅ **Send requests** to different services through the **API Gateway**.  
✅ **Use role-based access control** (`ADMIN` & `USER`).  

The system consists of **8 microservices**:  

1️⃣ **Config Server** - Centralized configuration management.  
2️⃣ **Eureka Server** - Service discovery and registration.  
3️⃣ **API Gateway** - Unified entry point for all services.  
4️⃣ **Auth Service** - Authentication & authorization with JWT.  
5️⃣ **Job Service** - Manages job-related operations.  
6️⃣ **User Service** - Handles user-related functionality.  
7️⃣ **Notification Service** - Sends notifications.  
8️⃣ **File Storage Service** - Manages file uploads & storage.  

---

## 🏗️ **Project Architecture**

📦 spring-boot-microservices

┣ 📂 config-server

┣ 📂 eureka-server

┣ 📂 api-gateway

┣ 📂 auth-service

┣ 📂 job-service

┣ 📂 user-service

┣ 📂 notification-service

┣ 📂 file-storage-service

Each service runs independently and communicates through **API Gateway** & **Eureka Service Discovery**.

---

## 🛠 **Tech Stack & Dependencies**
### 🌱 **Core Technologies**
- ☕ **Spring Boot**
- 🛡️ **Spring Security**
- 🔐 **JWT Authentication & Authorization**
- 🌐 **Spring Web**
- 🤝 **Feign Client**

### 🏗 **Spring Cloud**
- 🌩 **Spring Cloud Gateway**
- ⚙ **Spring Cloud Config Server**
- 🔍 **Eureka Server & Client**

### 🗄 **Database & Storage**
- 🐬 **MySQL**
- 📦 **Redis**
- 📜 **Kafka**
- 🐳 **Docker**

### 🛠 **Additional Dependencies**
- 🧩 **Spring Data JPA**
- 📂 **File Storage**
- 🔄 **ModelMapper**
- 🔎 **OpenAPI UI**
- ✍ **Lombok**
- 📜 **Log4j2**
- ✅ **Validation**
  
### 🧪 **Testing**
- 🧪 **JUnit 5** - Unit testing framework  
- 🔍 **Mockito** - Mocking dependencies in tests  
- 🔄 **Testcontainers** - Integration testing with real databases  
- 🌐 **Spring Boot Test** - Spring testing utilities  
- 📡 **WireMock** - Mock external APIs  
---

## 🚀 **Getting Started**
### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/daoohoangg/microservices-architecture-particle.git
cd microservices-architecture-particle

### 📫 **API Documentation**
http://localhost:8080/swagger-ui.html

### **We welcome contributions! To contribute:**

Fork the repository 🍴

Create a new branch 🌿

Make changes and commit ✨

Submit a pull request 🚀

