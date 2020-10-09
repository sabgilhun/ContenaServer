FROM openjdk:8-jdk-alpine

COPY ./firebase_key.json firebase_key.json

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ADD docker_entrypoint.sh /docker_entrypoint.sh
RUN ["chmod", "+x", "/docker_entrypoint.sh"]
ENTRYPOINT ["sh","/docker_entrypoint.sh"]
