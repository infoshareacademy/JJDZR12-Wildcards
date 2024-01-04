FROM maven:3.8.5-openjdk-17-slim as builder
COPY pom.xml /tmp/
COPY src /tmp/src
WORKDIR /tmp
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:17-jdk-slim
COPY --from=builder /tmp/target/JJDZR12-WildcardsWeb-0.0.1-SNAPSHOT.jar /tmp/app.jar
WORKDIR /tmp
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","app.jar"]