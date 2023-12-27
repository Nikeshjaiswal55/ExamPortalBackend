FROM gradle:8.1.1-jdk17 AS build

COPY gradlew .  /home/gradle
COPY gradle/*   /home/gradle
COPY build.gradle   /home/gradle
COPY gradlew.bat /home/gradle
COPY settings.gradle /home/gradle

RUN gradle build

# COPY /portal /app


FROM openjdk:17-jdk-slim

# Copy the JAR file from the build stage
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /home/gradle/build/libs/portal.jar
# Specify the entry point for running the application
RUN ["java", "-jar", "portal.jar"]

