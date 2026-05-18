<div align="center">

# SkillProgress

![Release](https://img.shields.io/github/v/release/crysscoder/skill-progress?style=flat-square&label=release)
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Paper](https://img.shields.io/badge/Paper-1.20.1-2ea44f?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-required-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Issues](https://img.shields.io/github/issues/crysscoder/skill-progress?style=flat-square)

Paper-плагин классов и прогресса навыков с GUI и MySQL.

[Release](https://github.com/crysscoder/skill-progress/releases/latest) · [Issues](https://github.com/crysscoder/skill-progress/issues) · [CodeAdapter](https://codeadapter.ru)

</div>

## Что делает

- даёт игроку выбрать класс
- хранит прогресс навыков в MySQL
- открывает GUI через `/skill`
- поддерживает задачи и уровни навыков

## Версии

| Компонент | Версия |
| --- | --- |
| Plugin | `1.0.0` |
| Java | `17` |
| Paper API | `1.20.1-R0.1-SNAPSHOT` |
| Shadow | `8.3.0` |
| HikariCP | `5.0.1` |
| MySQL Connector | `8.0.33` |

## Команды

- `/skill`
- `/skill help`

## Сборка

```powershell
.\gradlew.bat clean build
```

## Запуск тестового сервера

```powershell
.\gradlew.bat runServer
```

## Настройка

После первого запуска укажи MySQL в `config.json`.
