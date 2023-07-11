FROM openjdk:17-alpine

WORKDIR /app

COPY target/mailsender-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

RUN ls -la

CMD sleep 10 && java -jar app.jar
