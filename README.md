# SQLDeadline

Тестирование входа в систему через веб-интерфейс.

## Начало работы

1. Запустить контейнер командой: `docker-compose up`
2. Запустить SUT командой: `java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/db -P:jdbc.user=alex -P:jdbc.password=E7PgwDjz`

## Процесс работы

Запустить тесты командой: `gradlew test`

## Окончание работы

1. Открыть файл `clear-tables.sql`
2. Командой `Execute` очистить все таблицы
3. Остановить SUT
4. Остановить контейнер командой `docker-compose down`

### Окружение

- версия ОС: Windows 10 Pro, 64bit
- Браузер: Google Chrome ver.88.0.4324.190
- версия Java: Open JDK "11.0.9" 2020-10-20