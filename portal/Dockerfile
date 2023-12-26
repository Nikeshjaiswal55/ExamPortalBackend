FROM gradle:8.1.1-jdk17 AS build

COPY gradlew .  /home/gradle
COPY gradle/*   /home/gradle
COPY build.gradle   /home/gradle
COPY gradlew.bat /home/gradle
COPY settings.gradle /home/gradle

RUN ["./gradlew", "build"]

WORKDIR /app
# COPY /portal /app


FROM openjdk:17-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /home/gradle/build/libs/portal.jar /app

# Specify the entry point for running the application
# ENTRYPOINT ["java", "-jar", "portal.jar"]
ENTRYPOINT ["java","-jar","portal.jar"]

