FROM gradle:8.1.1-jdk17 AS build
WORKDIR /app
COPY /portal /app
COPY gradlew .     /home/gradle
COPY gradle/*      /home/gradle
COPY build.gradle  /home/gradle
COPY gradlew.bat   /home/gradle
COPY settings.gradle /home/gradle
WORKDIR /app
RUN gradle build
# COPY . ../
FROM openjdk:17-ea-28-jdk-slim
WORKDIR app
COPY --from=build /app/build/libs/portal.jar  /app

ENTRYPOINT ["java","-jar","portal.jar"]

