FROM gradle:8.1.1-jdk17 AS build

COPY gradlew .  /home/gradle
COPY gradle/*   /home/gradle
COPY build.gradle   /home/gradle
COPY gradlew.bat /home/gradle
COPY settings.gradle /home/gradle

RUN gradle build
WORKDIR /app
COPY /build /app


FROM openjdk:17-ea-28-jdk-slim
WORKDIR /app
COPY --from=build /build/libs/portal.jar /app

ENTRYPOINT ["java","-jar","portal.jar"]

