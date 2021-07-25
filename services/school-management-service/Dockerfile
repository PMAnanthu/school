FROM openjdk:11
EXPOSE 8500
COPY build/libs/school-management-service-0.0.1.jar school-management-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/school-management-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8500/actuator || exit 1