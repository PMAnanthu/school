FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8200
EXPOSE 587/tcp
COPY build/libs/notification-service-0.1.0.jar notification-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/notification-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8200/v0.1/actuator || exit 1