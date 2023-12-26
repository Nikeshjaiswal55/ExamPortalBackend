
FROM openjdk:17 As build 
WORKDIR /app

COPY /portal /app

COPY --from=build /app/build/libs/portal.jar /app

CMD ["java", "-jar", "portal.jar"]
