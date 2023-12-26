FROM gradle:8.1.1-jdk17 AS build
WORKDIR portal
COPY /portal  /portal
COPY gradlew .     /portal/home/gradle
COPY gradle/*      /portal/home/gradle
COPY build.gradle  /portal/home/gradle
COPY gradlew.bat   /portal/home/gradle
COPY settings.gradle /portal/home/gradle

RUN gradle build
# COPY . ../
FROM openjdk:17-ea-28-jdk-slim

COPY --from=build /build/libs/portal.jar  /home

ENTRYPOINT ["java","-jar","portal.jar"]

