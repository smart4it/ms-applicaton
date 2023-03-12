# ms-application

## Запуск приложения

Создание контейнера с БД
```
docker run --name smart4it_db -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres:15.2-alpine3.17
```

Создание БД на локальной машине
```sql
create database smart4it_db;
```