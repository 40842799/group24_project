FROM amazoncorretto:17
COPY ./target/devopsApp.jar /tmp
ENTRYPOINT ["java", "-jar", "devopsApp.jar"]
FROM mysql:8.0

COPY ./db/world.sql /docker-entrypoint-initdb.d/world.sql