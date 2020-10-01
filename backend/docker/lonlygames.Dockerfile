FROM openjdk:11

RUN mkdir /app
COPY ./target/backend-0.0.1-SNAPSHOT.jar /app
WORKDIR /app

ENTRYPOINT ["java","-jar","backend-0.0.1-SNAPSHOT.jar"]