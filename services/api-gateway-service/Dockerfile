FROM openjdk:11
EXPOSE 8000
COPY build/libs/api-gateway-service-0.0.1.jar api-gateway-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/api-gateway-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8000/v0.1/actuator || exit 1