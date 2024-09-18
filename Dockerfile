FROM openjdk:17.0.1-jdk-slim

COPY target/AdventureBook_Tourism_App.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar","AdventureBook_Tourism_App.jar"]