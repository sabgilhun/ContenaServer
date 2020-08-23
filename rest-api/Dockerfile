FROM openjdk:8-jdk-alpine

COPY ../var/auth/firebase_key.json firebase_key.json

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
