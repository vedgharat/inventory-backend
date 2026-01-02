# Stage 1: Build the application (Using Java 17)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application (Using Java 17 JRE)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Set the port dynamically (Railway sets the PORT variable)
ENV PORT=8080
EXPOSE ${PORT}

# Run the jar
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]