FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR /courier-tracking-service
COPY --from=build target/*.jar courier-tracking-service.jar
ENTRYPOINT ["java", "-jar", "courier-tracking-service.jar"]