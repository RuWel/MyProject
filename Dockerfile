FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="Rudi Welter"

ADD target/URS-0.0.1-SNAPSHOT.jar springboot.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "springboot.jar"]
