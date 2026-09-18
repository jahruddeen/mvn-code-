# Java Project CI/CD Pipeline

This project includes a GitHub Actions workflow for continuous integration and deployment.

## Project Structure

```
your-java-project/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/
│   │           └── Main.java
│   └── test/
│       └── java/
│           └── com/example/
│               └── MainTest.java
├── .github/
│   └── workflows/
│       └── ci-cd.yml
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Workflow Features

The GitHub Actions workflow includes:

- **Build Job**: Compiles code and runs tests on Java 11 and 17
- **Deploy Job**: Deploys to production when changes are pushed to main branch
- **Maven Caching**: Uses caching for faster builds
- **Code Coverage**: Uploads coverage reports to Codecov

## Prerequisites

- Java 11 or later
- Maven 3.6.0 or later
- GitHub repository

## Running Locally

Build the project:
```bash
./mvnw clean package
```

Run tests:
```bash
./mvnw test
```

Run the application:
```bash
java -jar target/java-project-1.0.0.jar
```

## Workflow Triggers

The CI/CD pipeline runs on:
- Push to `main` and `develop` branches
- Pull requests to `main` and `develop` branches

## Customization

Edit `.github/workflows/ci-cd.yml` to customize:
- Java versions to test
- Build commands
- Deployment commands
- Notification settings
