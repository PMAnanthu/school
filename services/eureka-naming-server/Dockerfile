FROM openjdk:11
EXPOSE 8761
COPY build/libs/eureka-naming-server-0.1.0.jar eureka-naming-server.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${profile}","/eureka-naming-server.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8761/actuator || exit 1