FROM gradle:7-jdk17 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN --mount=type=cache,target=./.gradle gradle buildFatJar --no-daemon

FROM openjdk:17-jdk-slim as runner
EXPOSE 8081:8081
RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/ktor-docker-sample.jar
ENTRYPOINT ["java","-jar","/app/ktor-docker-sample.jar"]