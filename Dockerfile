FROM openjdk:11-jre-slim-stretch
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/tinybankcustomer.jar
ENTRYPOINT ["java","-jar","/app/tinybankcustomer.jar"]