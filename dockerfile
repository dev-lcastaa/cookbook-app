FROM openjdk:22-slim-bullseye

COPY target/cookbook-0.0.1-SNAPSHOT.jar /app.jar
COPY src/main/resources/application.properties /app/application.properties

ENTRYPOINT ["java","-jar","app.jar"]
