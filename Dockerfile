FROM maven:3.8.1-openjdk-11-slim AS build


COPY pom.xml /usr/src/app/
COPY src /usr/src/app/src


WORKDIR /usr/src/app/
RUN mvn clean package

FROM openjdk:11-jre-slim

COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/app/app.jar"]
