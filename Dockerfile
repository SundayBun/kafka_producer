
FROM openjdk:17-jdk-slim as build
EXPOSE 8087:8087
RUN mkdir /app
ARG JAR_FFILE=build/libs/service_fat.jar
COPY ${JAR_FFILE} /app/ktor-docker-service.jar
ENTRYPOINT ["java","-jar","/app/ktor-docker-service.jar"]