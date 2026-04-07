FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /build
COPY . .
RUN mvn clean package -pl processing-consumer-api -am -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /build/processing-consumer-api/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]