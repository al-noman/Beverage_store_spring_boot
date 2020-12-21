# thesis

Beverage Store implemented using following components

Backend:
Spring Boot for exposing APIs
PostgreSql for data storage
RabbitMQ for messaging as middleware component
Flyway schema for database migration

Contains `docker-compose.yml` file for creating docker container for 3 server. 
1) Spring boot server [port: 8080]
2) Postgres server [port: 5432]
3) RabbitMQ server [port: 5672][admin-console-port: 15672]


Frontend:
HTML for templating
Javascript ES6, jQuery and AJAX for invoking API
