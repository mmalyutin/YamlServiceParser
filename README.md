# YamlServiceParser
Тестовое задание от Тинькофф

Задание:
Есть несколько сервисов с зависимостями, которые нужно запустить как можно быстрее (например есть Postgres и Java приложение, нужно сначала запустить Postgres, а потом java приложение, т.к. java приложение не запустится без запущенного Postgres’а).
Зависимости между сервисами заданы в yml файле, например

services:
- service: service2
dependsOn:
- 'postgres'
- 'service3'
- service: service3
dependsOn:
- 'redis'
- service: service4
dependsOn:
- 'postgres'
- service: redis
- service: postgres


Нужно написать java приложение, которое будет читать yml с зависимостями и выводить порядок, в котором нужно запускать сервисы. Например, для yml, приведенного выше, вывод приложения должен быть следующий:

шаг 1, одновременный запуск: redis postgres
шаг 2, одновременный запуск: service3 service4
шаг 2, запуск: service2
