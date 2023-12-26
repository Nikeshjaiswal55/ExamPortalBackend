FROM gradle:8.1.1-jdk17 AS build

COPY /portal/*

RUN gradle build


FROM openjdk:17-ea-28-jdk-slim 
COPY --from=build build/libs/portal.java portal.java

ENTRYPOINT ["java","-jar","portal.jar"]

