FROM gradle:7.0.2-jdk11 AS build
COPY ./ ./
RUN gradle clean build --no-daemon

FROM openjdk:11-jre-slim
RUN mkdir /app
COPY --from=build /home/gradle/app/build/libs/*.jar /app/ktor-exposed.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/app/ktor-exposed.jar"]
