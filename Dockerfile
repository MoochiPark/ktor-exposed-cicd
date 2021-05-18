FROM openjdk:11-jdk
EXPOSE 8080
ADD app/build/libs/app.jar dockerdemo.jar
ENTRYPOINT ["java", "-jar", "dockerdemo.jar"]
