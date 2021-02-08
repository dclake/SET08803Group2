FROM openjdk:latest
COPY ./target/seMethodsGroup2-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsGroup2-0.1.0.1-jar-with-dependencies.jar"]