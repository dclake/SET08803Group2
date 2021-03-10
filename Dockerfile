FROM openjdk:latest
COPY ./out/artifacts/seMethodsGroup2_jar/seMethodsGroup2.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsGroup2.jar"]