

# FROM azul/zulu-openjdk:17-latest AS build
# WORKDIR /app

# COPY /portal/app

# COPY --from=build /app/build/libs/portal.jar /app

# CMD ["java", "-jar", "portal.jar"]
# Build Stage
FROM gradle:7.3.3-jdk17 AS build

WORKDIR /app

# COPY build.gradle .
# COPY settings.gradle .
# # COPY gradle.properties .
# COPY gradlew

COPY src /app/src

# RUN ./gradlew build
#
ARG JAR_FILE=/build/libs/pokerstats-portal.jar
# Runtime Stage
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/portal.jar /app

CMD ["java", "-jar", "portal.jar"]
