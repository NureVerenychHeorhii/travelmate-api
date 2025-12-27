# Етап збірки (Build stage)
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Етап запуску (Run stage)
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/travelmate-api-0.0.1-SNAPSHOT.jar travelmate-api.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","travelmate-api.jar"]