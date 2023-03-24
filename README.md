# ms-application

## Запуск приложения

Создать контейнер с БД
```shell
docker run --name smart4it_db -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres:15.2-alpine3.17
```


Подключиться к докер контейнеру и создать БД
1. docker exec -it DOCKER_ID bash - подключиться к докер контейнеру (DOCKER_ID - id контейнера)
2. psql -U postgres - подключиться к БД под пользователем postgres
3. create database smart4it_db; - указать скрипт для создания БД;
4. \q - выйти из под пользователя postgres
5. Crl + p, Crl + q - выйти из докер контейнера 


Запустить приложение с профилем local

Для отображения доступных endpoint запустить приложение и перейти по url:
 http://host:port//swagger-ui.html (host - имя хоста, port - порт на котором поднято приложение)