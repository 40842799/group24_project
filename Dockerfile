FROM amazoncorretto:17
COPY ./target/devopsApp.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "devopsApp.jar"]