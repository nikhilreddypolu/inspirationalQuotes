# ---- Stage 1: Build the JAR ----
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml and download dependencies first (cache-friendly)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire source code and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Stage 2: Run the JAR ----
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/inspirationalQuotes-0.0.1-SNAPSHOT.jar app.jar

# Expose the app port
EXPOSE 8080

# Start the app
CMD ["java", "-jar", "app.jar"]
