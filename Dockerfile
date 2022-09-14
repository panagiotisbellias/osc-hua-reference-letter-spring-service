FROM openjdk
# COPY . .
# RUN java -jar target/*.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
