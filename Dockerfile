FROM openjdk:23
LABEL authors="sebasmo"
WORKDIR /app
COPY target/producto-service-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "producto-service-0.0.1-SNAPSHOT.jar"]