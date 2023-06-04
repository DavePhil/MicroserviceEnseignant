FROM openjdk:8
WORKDIR /app

COPY target/microenseignant.jar /app
CMD ["java","-jar","microenseignant.jar"]