FROM openjdk:11
EXPOSE 8300
COPY build/libs/hystrix-dashboard-0.0.1.jar hystrix-dashboard.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/hystrix-dashboard.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8300/v0.1/actuator || exit 1