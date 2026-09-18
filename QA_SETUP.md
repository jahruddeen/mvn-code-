# QA Environment Setup Guide

## 📋 QA Environment Structure

```
your-java-project/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── Main.java
│   │   │   └── HealthController.java
│   │   └── resources/
│   │       └── application-qa.properties
│   └── test/
│       ├── java/com/example/
│       │   ├── QASmokeTest.java
│       │   └── QAIntegrationTest.java
│       └── resources/
│           └── application-qa.properties
├── sql/
│   └── qa-init.sql
├── scripts/
│   ├── init-qa-db.bat
│   └── init-qa-db.sh
├── .env.qa
└── pom.xml
```

## 🛠️ QA Configuration Files

### 1. **application-qa.properties**
- Main QA runtime configuration
- Database connection settings
- Actuator endpoints enabled
- Debug logging
- Cache and connection pool settings

### 2. **application-qa.properties (Test)**
- H2 in-memory database for testing
- Test-specific settings
- Schema creation/drop on test runs

### 3. **.env.qa**
- Environment variables for QA environment
- Database credentials
- Application ports and URLs
- Test configurations

## 📊 QA Testing

### Unit Tests
```bash
./mvnw.cmd test
```

### Integration Tests
```bash
./mvnw.cmd verify
```

### Smoke Tests (QASmokeTest.java)
- Application health check
- Metrics endpoint validation
- Application info endpoint
- 404 error handling
- Context loading

### Integration Tests (QAIntegrationTest.java)
- Application initialization
- Configuration properties loading
- Logging configuration

## 🗄️ Database Setup

### SQL Script (sql/qa-init.sql)
Creates:
- Users table
- Application logs table
- Audit trail table
- Indexes
- Test data

### Database Initialization Scripts

**Windows:**
```bash
.\scripts\init-qa-db.bat
```

**Linux/Mac:**
```bash
bash scripts/init-qa-db.sh
```

## 🚀 Running QA Environment

### Option 1: Local Testing
```bash
# Run unit tests
./mvnw.cmd test

# Run integration tests
./mvnw.cmd verify

# Build application
./mvnw.cmd clean package

# Run application
java -jar target/java-project-1.0.0.jar --spring.profiles.active=qa
```

### Option 2: Docker (Optional)
```bash
# Build Docker image
docker build -t java-project:qa .

# Run with Docker Compose
docker-compose -f docker-compose.qa.yml up
```

### Option 3: CI/CD Pipeline
- Push to master branch
- GitHub Actions triggers build
- Tests run automatically
- Deploy to QA environment

## ✅ Health Checks

Health Endpoints:
- `GET /actuator/health` - Application health
- `GET /actuator/metrics` - Application metrics
- `GET /actuator/info` - Application info
- `GET /api/health` - Custom health endpoint
- `GET /ready` - Kubernetes readiness probe
- `GET /live` - Kubernetes liveness probe

## 📝 Test Coverage

Run tests with coverage:
```bash
./mvnw.cmd clean test jacoco:report
```

Coverage report: `target/site/jacoco/index.html`

## 🔍 Logging

QA logs can be monitored:
- `logs/qa-application.log` - Application logs
- Spring Boot actuator endpoints
- Database query logs (enabled in QA)

## 🔐 Security Notes

- Store DB_PASSWORD in environment variables
- Never commit sensitive data
- Use .env.qa for local development only
- Enable HTTPS in production QA
- Use secrets management in CI/CD

## 📞 Troubleshooting

### Database Connection Failed
```
Check: Database host, port, credentials
Run: .\scripts\init-qa-db.bat
```

### Tests Not Running
```
./mvnw.cmd clean test -DskipTests=false -X
```

### Health Check Failed
```
Check port 8082 is available
Check application logs
Verify database connection
```

## 📚 References

- [Spring Boot Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [JUnit Testing](https://junit.org/junit4/)
- [TestNG](https://testng.org/)
