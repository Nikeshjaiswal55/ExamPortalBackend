FROM gradle:8.1.1-jdk17 AS build
WORKDIR portal
COPY /portal    build
COPY gradlew .  /home/gradle
COPY gradle/*   /home/gradle
COPY build.gradle   /home/gradle
COPY gradlew.bat /home/gradle
COPY settings.gradle /home/gradle

RUN gradle build

FROM openjdk:17-ea-28-jdk-slim

COPY --from=build /build/libs/portal.jar portal.jar

ENTRYPOINT ["java","-jar","portal.jar"]

