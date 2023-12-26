FROM gradle:8.1.1-jdk17 AS build

COPY gradlew .  /home/gradle
COPY gradle/*   /home/gradle
COPY build.gradle   /home/gradle
COPY gradlew.bat /home/gradle
COPY settings.gradle /home/gradle

RUN ["./gradlew", "build"]

WORKDIR /app
COPY /portal /app


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/portal.jar /app

ENTRYPOINT ["java","-jar","portal.jar"]

