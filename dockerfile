FROM openjdk:openjdk:17-alpine
WORKDIR /app
EXPOSE 9009
COPY target/microenseignant.jar /app
CMD ["java","-jar","microenseignant.jar"]