FROM openjdk:11
EXPOSE 8400
COPY build/libs/user-management-service-0.1.0.jar user-management-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/user-management-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8400/actuator || exit 1