# Use a base image with OpenJDK Java 30 installed
FROM openjdk:20

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY build/libs/apispring.jar /app

# Specify the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "apispring.jar"]
