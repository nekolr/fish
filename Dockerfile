#FROM maven:3.6.1-jdk-8-alpine AS build
# https://github.com/nekolr/maven-image/tree/master/3.6.1-jdk-8
FROM nekolr/maven:3.6.1 AS build

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY . .

RUN mvn clean package


FROM openjdk:8-jdk-alpine

ARG VERSION=0.0.1.RELEASE

COPY --from=build /usr/src/app/target/fish-${VERSION}.jar .

EXPOSE 12001

CMD java -jar fish-${VERSION}.jar