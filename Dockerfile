# Stage 1: Build the application
# Use Maven with Java 21 to compile
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
# Use a lightweight Alpine Linux with Java 21 JRE
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Set the port dynamically (Railway sets the PORT variable)
ENV PORT=8080
EXPOSE ${PORT}

# Run the jar, telling it to listen on the correct port
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]