FROM openjdk:11
EXPOSE 8100
COPY build/libs/student-management-service-0.0.1.jar student-management-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/student-management-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8100/actuator || exit 1