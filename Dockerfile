FROM openjdk:latest
COPY ./target/seMethodsGroup2-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsGroup2-0.1.0.2-jar-with-dependencies.jar"]