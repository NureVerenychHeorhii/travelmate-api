# Етап 1: Збірка (Build)
FROM maven:3.8.5-openjdk-17 AS build
# 👇 Створюємо робочу папку. Це критично важливо!
WORKDIR /app

# Копіюємо файли з GitHub у цю папку /app
COPY . .

# Запускаємо збірку саме в цій папці
RUN mvn clean package -DskipTests

# Етап 2: Запуск (Run)
FROM openjdk:17-jdk-slim
WORKDIR /app

# 👇 Копіюємо готовий JAR-файл із папки /app/target попереднього етапу
COPY --from=build /app/target/travelmate-api-0.0.1-SNAPSHOT.jar travelmate-api.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","travelmate-api.jar"]