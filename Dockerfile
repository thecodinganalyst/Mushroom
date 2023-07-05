FROM gradle:7.6.2-jdk17-focal AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon --exclude-task jar

FROM amazoncorretto:17-alpine3.16
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar ./mushroom.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mushroom.jar"]
