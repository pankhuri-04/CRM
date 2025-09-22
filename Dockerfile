# =======================
# Stage 1: Build the JAR
# =======================
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy Maven files first (for caching dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# =======================
# Stage 2: Run the JAR
# =======================
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]
