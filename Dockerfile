FROM openjdk:11
COPY target/reference-letter-service-0.0.1-SNAPSHOT.jar reference-letter-service-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java","-jar","/reference-letter-service-0.0.1-SNAPSHOT.jar", "--spring.config.location=file:/application.properties"]