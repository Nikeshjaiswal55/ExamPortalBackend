FROM gradle:8.1.1-jdk17 AS build

COPY /portal/*  build/
COPY gradlew .  build/
COPY gradle/*   build/
COPY build.gradle   build/
COPY gradlew.bat  build/
COPY settings.gradle  build/

RUN gradle build


FROM openjdk:17-ea-28-jdk-slim 
COPY --from=build build/libs/portal.java portal.java

ENTRYPOINT ["java","-jar","portal.jar"]

