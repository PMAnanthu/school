FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8888
COPY build/libs/config-service-0.0.1.jar config-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/config-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8888/v0.1/actuator || exit 1