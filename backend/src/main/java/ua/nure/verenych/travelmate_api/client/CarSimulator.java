package com.example.travelmate.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CarSimulator {

    // ⚠️ ВАЖЛИВО: Перевір порт! Якщо твій сервер на 8080, зміни тут на 8080.
    private static final String SERVER_URL = "http://localhost:8081/api/telemetry";

    // Початкові параметри нашої віртуальної машини
    private static long carId = 1L;      // ID машини (має існувати в таблиці cars, інакше створи спочатку машину)
    private static double fuelLevel = 100.0;
    private static double speed = 0.0;
    private static double latitude = 50.4501;  // Центр Києва
    private static double longitude = 30.5234;

    private static final Random random = new Random();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        System.out.println("🚗 ЗАПУСК БОРТОВОГО КОМПЬЮТЕРА (IoT Client)...");
        System.out.println("📡 Підключення до сервера: " + SERVER_URL);

        try {
            while (true) {
                simulateDriving();

                // ВИПРАВЛЕННЯ: Використовуємо Locale.US, щоб завжди була КРАПКА (65.5), а не кома (65,5)
                String json = String.format(java.util.Locale.US,
                        "{\"carId\":%d, \"speed\":%.2f, \"fuelLevel\":%.2f, \"latitude\":%.6f, \"longitude\":%.6f}",
                        carId, speed, fuelLevel, latitude, longitude
                );

                // Виводимо в консоль те, що відправляємо (для перевірки)
                System.out.println("📤 ВІДПРАВЛЯЮ JSON: " + json);

                sendTelemetry(json);

                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {
            System.out.println("🛑 Симуляція зупинена.");
        }
    }

    private static void simulateDriving() {
        // Тратимо пальне (0.5 літра кожні 3 секунди)
        fuelLevel -= 0.5;
        if (fuelLevel < 0) {
            fuelLevel = 100.0; // "Заправились" на заправці
            System.out.println("\n⛽ ЗАПРАВКА БАКА! (Повний бак)\n");
        }

        // Змінюємо швидкість (випадково від 40 до 90 км/год)
        speed = 40 + random.nextDouble() * 50;

        // Змінюємо координати (трохи рухаємось на північний схід)
        latitude += 0.0002;
        longitude += 0.0002;
    }

    private static void sendTelemetry(String jsonBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SERVER_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("✅ ДАНІ ВІДПРАВЛЕНО: Швидкість " + String.format("%.1f", speed) + " км/год | Пальне " + String.format("%.1f", fuelLevel) + "%");
            } else {
                System.out.println("⚠️ СЕРВЕР ВІДПОВІВ ПОМИЛКОЮ: " + response.statusCode());
            }

        } catch (Exception e) {
            System.err.println("❌ ПОМИЛКА: Немає зв'язку з сервером. Він запущений?");
        }
    }
}