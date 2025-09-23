# Use OpenJDK 17 slim base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the packaged JAR into the container
COPY target/crm-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (Railway will map this automatically)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
