FROM openjdk:11
EXPOSE 8100
EXPOSE 5432/tcp
COPY build/libs/authentication-service-0.0.1.jar authentication-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/authentication-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8100/v0.1/actuator || exit 1