FROM adoptopenjdk/openjdk16:alpine-jre

EXPOSE 8080

ARG JAR_FILE=target/*.jar

WORKDIR /myfolder

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]