FROM amazoncorretto:17-alpine
LABEL authors="kacper"


WORKDIR /app
COPY build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]