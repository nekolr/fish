#FROM maven:3.6.1-jdk-8-alpine AS build
FROM nekolr/maven:3.6.1 AS build

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY . .

RUN mvn clean package


FROM openjdk:8-jdk-alpine

ENV VERSION 0.0.1-SNAPSHOT

COPY --from=build /usr/src/app/target/fish-${VERSION}.jar .

EXPOSE 12001

CMD java -jar fish-${VERSION}.jar