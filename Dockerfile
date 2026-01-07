# FROM eclipse-temurin:17-jdk
# WORKDIR /app
# COPY target/*.jar app.jar
# ENTRYPOINT ["java","-jar","/app/app.jar"]

# FROM openjdk:17-jdk
# WORKDIR /app
# COPY target/*.jar app.jar
# ENTRYPOINT ["java","-jar","/app/app.jar"]

# Use the Official OpenJDK base image
# FROM openjdk:17-jdk-alpine
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/java-ci-demo01.jar java-ci-demo01.jar

# Expose port 8080 (if your application runs on a specific port)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "java-ci-demo01.jar"]