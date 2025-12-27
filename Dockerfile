# Етап 1: Збірка (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Копіюємо всі файли з репозиторію
COPY . .

# 👇 ДІАГНОСТИКА: Ця команда покаже в логах, де лежать файли
RUN echo "=== ФАЙЛИ В ПАПЦІ /app ===" && ls -laR /app && echo "=========================="

# 👇 МАГІЯ: Якщо pom.xml лежить у папці backend, заходимо туди. Інакше будуємо тут.
RUN if [ -d "backend" ] && [ -f "backend/pom.xml" ]; then \
        cd backend && mvn clean package -DskipTests && \
        mkdir -p ../target && \
        cp target/*.jar ../target/; \
    else \
        mvn clean package -DskipTests; \
    fi

# Етап 2: Запуск (Run)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Копіюємо JAR-файл (тепер ми точно знаємо, що він у /app/target, бо ми його туди поклали вище)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]