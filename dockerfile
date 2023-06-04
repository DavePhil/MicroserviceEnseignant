FROM openjdk:8
WORKDIR /app
EXPOSE 9009
COPY target/microenseignant.jar /app
CMD ["java","-jar","microenseignant.jar"]