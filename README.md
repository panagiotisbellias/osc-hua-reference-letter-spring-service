# reference-letter-service
Reference-Letter Service, a Spring Boot project in the context of HUA DIT course 'Distributed Systems'

<h3 align="left">Languages and Tools:</h3>
<p align="left"> <a href="https://www.gnu.org/software/bash/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/gnu_bash/gnu_bash-icon.svg" alt="bash" width="40" height="40"/> </a> <a href="https://getbootstrap.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/bootstrap/bootstrap-plain-wordmark.svg" alt="bootstrap" width="40" height="40"/> </a> <a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="40" height="40"/> </a> <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://www.linux.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/linux/linux-original.svg" alt="linux" width="40" height="40"/> </a> <a href="https://www.nginx.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/nginx/nginx-original.svg" alt="nginx" width="40" height="40"/> </a> <a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="postgresql" width="40" height="40"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> </p>

## Prerequisites
### Clone Project

Go to a directory of your choice and do
```bash
git clone https://github.com/panagiotisbellias/reference-letter-service.git
```

### Database

#### 1. Using Docker
[Install Docker](https://docs.docker.com/get-docker/)

**Run a postgres container as below**  
Below details can be different  
(--name is database-name: db-postgres,  
 -p is port mapping: 5432,  
 -e is to define environmental variables such as  
 POSTGRES_PASSWORD is the password for default postgres user like pass123  
 -d is for detach mode and  
 finally we specify the image name. It is postgres (the latest version will be used this way)

```bash
docker run --name db-postgres -p 5432:5432 -e POSTGRES_PASSWORD=pass123 -d postgres
```
Check connectivity to postgresql database.  
-h is for the host, -U is for the username of user and -p is for the port database is accessible
```bash
psql -h localhost -U postgres -p 5432
```

#### 2. Using docker-compose
[Here](docker-compose.yaml) is the yaml file that tells docker-compose what to do. So if you don't like the previous way or you are getting bored try this one.  
All you have to do is
```bash
docker-compose up
```
when you are in the root directory of project, where yaml file is located.

In case, database isn't initialized automatically through docker-compose, you can execute the commands below while database is available.
```bash
psql -h localhost -U postgres -p 5432 < assets/db/schema.sql
```

When you want to stop the postgresql container you can do
```bash
docker-compose down
```

Simple, hah?

## Application Properties

You have to do the following so the spring application can connect with the database properly.  
Create file application.properties in src/main/resources and follow this template entering your own values.
```bash
# JDBC URL of the database
spring.datasource.url=jdbc:postgresql://localhost/ref_letters_db
# Login username of the database
spring.datasource.username=postgres
# Login password of the database
spring.datasource.password=pass123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL81Dialect

# Default user name
spring.security.user.name=admin
# Password for admin
spring.security.user.password=pass123

info.app.name=Reference Letter Service
info.app.description=Backend service for reference letter management in the context of 'Distributed Systems' course at HUA DIT
info.app.version=1.0.0

# HTTP server port
server.port=8080
# Context path of the application
server.servlet.context-path=/reference-letters
# Default HTTP session time out
server.servlet.session.timeout=15m

# Endpoints to include by name or wildcard
management.endpoints.web.exposure.include=*
# Endpoints to exclude by name or wildcard
management.endpoints.web.exposure.exclude=health,info,beans,mapping
# Base path for actuator endpoints
management.endpoints.web.base-path=/actuator

# Log levels severity mapping
logging.level.org.springframework=DEBUG
logging.level.org.hibernate=TRACE
logging.level.com.luv2code=INFO
# Log file name
logging.file.name=reference-letter-service.log
spring.data.rest.base-path=/api

spring.main.allow-circular-references=true

spring.mail.host=smtp.gmail.com
spring.mail.port=25
spring.mail.username=<email-user>
spring.mail.password=<email-password>

# Other properties
spring.mail.properties.mail.debug=true
spring.mail.properties.mail.transport.protocol=smtp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000

# TLS , port 587
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Run Locally
Use some IDE like IntelliJ or Eclipse. Otherwise, use these commands after you have installed and configured maven.
```bash
mvn spring-boot:run
```

## Test with postman tool
We can test our REST API using Postman, sending requests getting and sending JSON objects

## Technologies
Spring Boot
Postgres
Thymeleaf

Thank you!
#DS