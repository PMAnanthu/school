FROM openjdk:11
EXPOSE 8400
COPY build/libs/student-management-service-0.0.1.jar student-management-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/student-management-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8400/actuator || exit 1