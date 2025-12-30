# 🏦 Microservices Banking Application

A comprehensive microservices-based banking application built with Spring Boot and Spring Cloud, demonstrating modern cloud-native architecture patterns.

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Services](#services)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Docker Support](#docker-support)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This project demonstrates a production-ready microservices architecture for a banking system, implementing industry best practices including:

- **Centralized Configuration Management** with Spring Cloud Config
- **Service Discovery** and load balancing
- **Containerization** with Docker
- **API Documentation** with OpenAPI/Swagger
- **Monitoring and Health Checks** with Spring Boot Actuator
- **Encrypted Configuration** for sensitive data

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────┐
│                     API Gateway                          │
└─────────────────────────────────────────────────────────┘
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
┌───────▼────────┐ ┌──────▼──────┐ ┌────────▼────────┐
│   Accounts     │ │    Cards    │ │     Loans       │
│   Service      │ │   Service   │ │    Service      │
└───────┬────────┘ └──────┬──────┘ └────────┬────────┘
        │                  │                  │
        └──────────────────┼──────────────────┘
                           │
                  ┌────────▼────────┐
                  │  Config Server  │
                  └─────────────────┘
```

## 🔧 Services

### 1. **Config Server** (`configserver`)
- Centralized configuration management
- Supports encryption for sensitive data
- Git-backed configuration repository
- **Port**: 8071

### 2. **Accounts Service** (`accounts`)
- Manages customer accounts
- Customer information management
- Account creation and updates
- **Port**: 8080

### 3. **Cards Service** (`cards`)
- Credit/Debit card management
- Card issuance and tracking
- **Port**: 9000

### 4. **Loans Service** (`loans`)
- Loan application processing
- Loan management and tracking
- **Port**: 8090

## 🛠️ Technologies

### Core Framework
- **Java 17**
- **Spring Boot 3.5.9**
- **Spring Cloud 2025.0.1**

### Key Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Spring Boot Starter Actuator
- Spring Cloud Config Server/Client
- H2 Database (Development)
- Lombok
- SpringDoc OpenAPI

### Build & Deployment
- Maven 3.x
- Docker
- Jib (Containerization)

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **Docker** (for containerization)
- **Git**

### Verify Installation

```bash
java -version    # Should show Java 17+
mvn -version     # Should show Maven 3.6+
docker --version # Should show Docker version
```

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd microservices
```

### 2. Build All Services

```bash
# Build all services at once
mvn clean install -DskipTests

# Or build individual services
cd accounts && mvn clean install
cd ../cards && mvn clean install
cd ../loans && mvn clean install
cd ../configserver && mvn clean install
```

### 3. Start Services (Local Development)

**Option A: Start individually**

```bash
# Terminal 1: Start Config Server first
cd configserver
mvn spring-boot:run

# Terminal 2: Start Accounts Service
cd accounts
mvn spring-boot:run

# Terminal 3: Start Cards Service
cd cards
mvn spring-boot:run

# Terminal 4: Start Loans Service
cd loans
mvn spring-boot:run
```

**Option B: Using Docker Compose** (Recommended)

```bash
# Build Docker images using Jib
mvn clean compile jib:dockerBuild

# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

## ⚙️ Configuration

### Environment-Specific Configurations

The application supports multiple environments:
- **dev** - Development environment
- **qa** - Quality Assurance environment
- **prod** - Production environment

### Configuration Files Location

```
configserver/src/main/resources/config/
├── accounts-dev.yml
├── accounts-qa.yml
├── accounts-prod.yml
├── cards-dev.yml
├── cards-qa.yml
├── loans-dev.yml
└── loans-qa.yml
```

### Activating Profiles

```bash
# Using Maven
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=qa"

# Using Java
java -jar accounts.jar --spring.profiles.active=qa

# Using Docker
docker run -e SPRING_PROFILES_ACTIVE=qa accounts:1.0
```

### Encrypted Properties

Sensitive configuration values are encrypted using Spring Cloud Config encryption:

```yaml
# Example encrypted property
spring:
  datasource:
    password: '{cipher}AQA...'
```

## 🐳 Docker Support

### Building Docker Images

**Using Spring Boot Maven Plugin:**
```bash
cd accounts
mvn spring-boot:build-image
```

**Using Jib (Faster, no Docker daemon required):**
```bash
# Build to Docker daemon
mvn compile jib:dockerBuild

# Build and push to registry
mvn compile jib:build
```

### Docker Image Naming Convention

Images follow the pattern: `sandeepteotia/{service-name}:{version}`

Example:
- `sandeepteotia/accounts:1.0`
- `sandeepteotia/cards:1.0`
- `sandeepteotia/loans:1.0`
- `sandeepteotia/configserver:1.0`

### Running Individual Containers

```bash
# Run Config Server
docker run -d -p 8071:8071 --name configserver sandeepteotia/configserver:1.0

# Run Accounts Service
docker run -d -p 8080:8080 --name accounts \
  -e SPRING_CONFIG_IMPORT="configserver:http://configserver:8071" \
  sandeepteotia/accounts:1.0
```

## 📚 API Documentation

Each service exposes OpenAPI documentation:

- **Accounts Service**: http://localhost:8080/swagger-ui.html
- **Cards Service**: http://localhost:9000/swagger-ui.html
- **Loans Service**: http://localhost:8090/swagger-ui.html

### Health Checks

Spring Boot Actuator endpoints:

- **Accounts**: http://localhost:8080/actuator/health
- **Cards**: http://localhost:9000/actuator/health
- **Loans**: http://localhost:8090/actuator/health
- **Config Server**: http://localhost:8071/actuator/health

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run tests for specific service
cd accounts && mvn test

# Run tests with coverage
mvn test jacoco:report
```

## 📊 Monitoring

### Actuator Endpoints

Available endpoints (when enabled):
- `/actuator/health` - Health status
- `/actuator/info` - Application info
- `/actuator/metrics` - Application metrics
- `/actuator/env` - Environment properties
- `/actuator/refresh` - Refresh configuration

### Refreshing Configuration at Runtime

```bash
# Refresh configuration without restart
curl -X POST http://localhost:8080/actuator/refresh
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Commit Message Convention

Follow [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: add new feature
fix: bug fix
docs: documentation changes
style: formatting changes
refactor: code refactoring
test: adding tests
chore: maintenance tasks
```

## 📝 Project Structure

```
microservices/
├── accounts/           # Accounts microservice
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── cards/              # Cards microservice
│   ├── src/
│   └── pom.xml
├── loans/              # Loans microservice
│   ├── src/
│   └── pom.xml
├── configserver/       # Configuration server
│   ├── src/
│   │   └── main/
│   │       └── resources/
│   │           └── config/  # Configuration files
│   └── pom.xml
├── Notes/              # Documentation and notes
├── .gitignore
└── README.md
```

## 🔐 Security Considerations

- Sensitive configuration values are encrypted
- H2 console disabled in production
- Actuator endpoints should be secured in production
- Use environment variables for secrets in production
- Enable HTTPS/TLS in production

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Docker Deployment
```bash
docker-compose up -d
```

### Production Deployment
- Use Kubernetes for orchestration
- Implement service mesh (Istio/Linkerd)
- Set up monitoring (Prometheus/Grafana)
- Configure centralized logging (ELK Stack)
- Implement CI/CD pipeline

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- **Sandeep Teotia** - Initial work

## 🙏 Acknowledgments

- Spring Boot and Spring Cloud communities
- Udemy course materials
- Open source contributors

---

**Note**: This is a learning/demonstration project. For production use, additional security hardening, monitoring, and infrastructure setup would be required.
