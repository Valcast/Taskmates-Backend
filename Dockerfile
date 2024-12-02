FROM amazoncorretto:17-alpine
LABEL authors="kacpe"


WORKDIR /app
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]