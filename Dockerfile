# Use OpenJDK 17 slim base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper & pom.xml first (for caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the application (creates target/crm-0.0.1-SNAPSHOT.jar)
RUN ./mvnw clean package -DskipTests

# Expose the port (Railway will map this automatically)
EXPOSE 8080

# Run the built jar directly from target
CMD ["java", "-jar", "target/crm-0.0.1-SNAPSHOT.jar"]