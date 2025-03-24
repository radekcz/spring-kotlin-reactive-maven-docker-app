# Spring Kotlin Reactive Maven Docker App

This project is a **Spring Boot** template built with **Kotlin**, **Reactive programming**, **Maven**, and **Docker**.  
It serves as a starting point for building reactive Spring Boot applications in a containerized environment.

## Features
- **Kotlin** – Modern, concise, and expressive programming language
- **Reactive programming** – Built with **Spring WebFlux** for handling asynchronous and non-blocking operations
- **Maven** – Dependency management and build automation
- **Docker** – Easily containerize and deploy the application

## Spring Modules Used
- **Spring WebFlux** – For building reactive web applications
- **Spring Security**

---

## 🚀 Getting Started

### Prerequisites
Make sure you have the following installed:
- Docker

### Clone the Repository
\`\`\`bash
git clone https://github.com/radekcz/spring-kotlin-reactive-maven-docker-app.git
\`\`\`

### Build the Application
\`\`\`bash
cd spring-kotlin-reactive-maven-docker-app
./mvnw clean package
\`\`\`

### Build the Docker Image
\`\`\`bash
docker build -t spring-kotlin-reactive-app .
\`\`\`

### Run the Docker Container
\`\`\`bash
docker run -p 8080:8080 spring-kotlin-reactive-app
\`\`\`

The application will be available at [http://localhost:8080](http://localhost:8080).

---

## 🛠️ Usage
This template provides a reactive Spring Boot setup with Kotlin.  
You can extend it by adding your own controllers, services, and repositories to fit your project needs.

---

## 🤝 Contributing
If you encounter any issues or have ideas for improvement, feel free to open an issue or submit a pull request.

---