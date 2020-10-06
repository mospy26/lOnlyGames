FROM maven:3.6.3-openjdk-11

RUN mkdir /app
COPY ./ /app
WORKDIR /app

ENTRYPOINT ["mvn", "spring-boot:run"]