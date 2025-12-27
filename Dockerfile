# Етап 1: Збірка (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Копіюємо всі файли
COPY . .

# Збираємо проект
RUN mvn clean package -DskipTests

# Етап 2: Запуск (Run)
# 👇 МИ ЗАМІНИЛИ ЦЕЙ РЯДОК (було openjdk:17-jdk-slim)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Копіюємо JAR
COPY --from=build /app/target/travelmate-api-0.0.1-SNAPSHOT.jar travelmate-api.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","travelmate-api.jar"]