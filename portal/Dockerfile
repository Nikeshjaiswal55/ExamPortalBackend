FROM azul/zulu-openjdk:17-latest
VOLUME /tmp
CMD ./gradlew build
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]