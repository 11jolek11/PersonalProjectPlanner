FROM eclipse-temurin:21-alpine
LABEL authors="jolek"


WORKDIR /app

COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn ./
COPY pom.xml ./
RUN mvn dependency:resolve

COPY ./src ./

EXPOSE 8080

ENTRYPOINT ["./mvnw", "spring-boot:run"]
