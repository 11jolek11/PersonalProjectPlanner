FROM eclipse-temurin:21-alpine
LABEL authors="jolek"


WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .
COPY pom.xml .
RUN ["./mvnw", "dependency:resolve"]

COPY . .

EXPOSE 3000

#ENTRYPOINT ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=dev"]
ENTRYPOINT ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=dev"]