FROM openjdk:11
EXPOSE 8500
COPY build/libs/academic-management-service-0.1.0.jar academic-management-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/academic-management-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8500/actuator || exit 1