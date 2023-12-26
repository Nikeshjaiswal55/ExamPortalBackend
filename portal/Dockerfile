FROM gradle:8.1.1-jdk17 AS build

COPY /portal  portal   
COPY gradlew .  /home/gradle
COPY gradle/*   /home/gradle
COPY build.gradle   /home/gradle
COPY gradlew.bat /home/gradle
COPY settings.gradle /home/gradle

RUN gradle build



FROM openjdk:17-ea-28-jdk-slim 
COPY --from=build portal/build/libs/portal.jar portal.java

ENTRYPOINT ["java","-jar","portal.jar"]

