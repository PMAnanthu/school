
cd api-gateway-service/
gradlew  build buildDocker pushDocker
cd ..
authentication-service/gradlew  build buildDocker pushDocker
eureka-naming-server/gradlew  build buildDocker pushDocker
hystrix-dashboard/gradlew  build buildDocker pushDocker
notification-service/gradlew  build buildDocker pushDocker