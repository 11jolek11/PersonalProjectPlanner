FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
LABEL authors="jolek"

WORKDIR /app

COPY pom.xml .
RUN ["mvn", "dependency:resolve"]

COPY . .

RUN ["mvn", "package", "-DskipTests"]

FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY --from=build /app/target/planner-0.0.1-SNAPSHOT.jar .

EXPOSE 3000

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "-Dspring.profiles.active=dev", "planner-0.0.1-SNAPSHOT.jar"]
