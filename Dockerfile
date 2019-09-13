FROM maven:3-jdk-12 as build
WORKDIR /workspace/app

COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY src src

RUN mvn package

# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:12-jdk-alpine
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
