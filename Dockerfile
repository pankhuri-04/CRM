# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper & pom.xml first (to leverage caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (cached if pom.xml doesn’t change)
RUN ./mvnw dependency:go-offline -B

# Copy the full source
COPY src src

# Build the application (produces target/*.jar)
RUN ./mvnw clean package -DskipTests

# Expose port (Render will map this automatically)
EXPOSE 8080

# Copy the jar explicitly (replace crm-0.0.1-SNAPSHOT.jar with your real jar name if different)
COPY target/*SNAPSHOT.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
