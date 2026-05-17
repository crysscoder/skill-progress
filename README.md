# SkillProgress

Paper-плагин классов и прогресса навыков с GUI и MySQL.

## Что делает

- Даёт игроку выбрать класс.
- Хранит прогресс навыков в MySQL.
- Открывает GUI меню через `/skill`.
- Поддерживает задачи и уровни навыков.

## Зачем нужен

Нужен для RPG-серверов, прокачки персонажа и систем классов, где прогресс должен сохраняться между заходами.

## Версии

- Java 17
- Gradle 8.8
- Paper API 1.20.1
- Shadow 8.3.0
- Lombok 1.18.42
- HikariCP 5.0.1
- MySQL Connector 8.0.33
- Plugin `1.0.0`

## Команды

- `/skill`
- `/skill help`

## Сборка

```powershell
.\gradlew.bat clean build
```

Jar: `build/libs/SkillProgress-1.0.0.jar`

## Запуск тестового сервера

```powershell
.\gradlew.bat runServer
```

## Настройка

После первого запуска указать MySQL в `config.json`.
