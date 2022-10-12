FROM openjdk:11
COPY target/reference-letter-service-0.0.1-SNAPSHOT.jar reference-letter-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/reference-letter-service-0.0.1-SNAPSHOT.jar"]