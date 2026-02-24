# Cloud-Native Note API (Backend)

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0%2B-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Cloud-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Deployed](https://img.shields.io/badge/Deployed-Render-46E3B7?style=for-the-badge&logo=render&logoColor=white)

A highly scalable, cloud-native RESTful API built with Java and Spring Boot. This service acts as the decoupled backend for a note-taking application, handling secure user authentication, state management, and data persistence via a remote MySQL cluster.

## 🔗 Infrastructure & Environments
- **Production API URL:** https://note-app-1-tnpi.onrender.com
- **Database Hosting:** Aiven Cloud Services
- **Client Application:** https://raj0825.github.io/Note_App-Frontend-/

## 🏗️ System Architecture & Technologies
- **Core Framework:** Spring Boot (Spring Web, Spring Boot Starter)
- **Security:** Spring Security (Stateless Authorization, Bcrypt Password Hashing, CORS filtering)
- **Persistence Layer:** Spring Data JPA / Hibernate ORM
- **Database:** MySQL (Cloud-provisioned)
- **Build Tool:** Maven

## 🔐 Security Implementation
- **Cross-Origin Resource Sharing (CORS):** Explicitly configured `SecurityFilterChain` to whitelist the production client domain and manage `OPTIONS` preflight requests.
- **Authentication:** Implements stateless Basic Authentication. Credentials are encrypted in transit and securely verified via Spring Security context.
- **Data Protection:** Passwords are never stored in plaintext; they are hashed utilizing the BCrypt algorithm before persistence.

## ⚙️ Local Development Setup

### Prerequisites
- JDK 17+
- Maven 3.8+
- MySQL Server 8.0+

### Configuration
The application relies on environment variables to abstract sensitive credentials. Define the following in your local environment or `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```




