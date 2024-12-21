FROM gradle:7.6.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build -x test --no-daemon

FROM openjdk:17-jdk-alpine
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/study-center-api.jar
ENTRYPOINT ["java", "-jar", "/app/study-center-api.jar"]
