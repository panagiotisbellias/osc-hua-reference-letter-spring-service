# reference-letter-service

## Clone Project

Go to a directory of your choice and do
```bash
git clone https://github.com/panagiotisbellias/reference-letter-service.git
```

## Application Properties

Create file application.properties in src/main/resources and follow this template entering your own values
```bash
spring.datasource.url=jdbc:postgresql://localhost/<DB-NAME>
spring.datasource.username=<DB-USER>
spring.datasource.password=<DB-PASSWORD>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL81Dialect

spring.data.rest.base-path=/api

management.endpoints.web.exposure.include=*
```
