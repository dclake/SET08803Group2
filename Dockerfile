FROM openjdk:latest
COPY ./target/seMethodsGroup2-0.1.0.4.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsGroup2-0.1.0.4.jar"]