
cd api-gateway-service/
./gradlew clean build buildDocker pushDocker
cd ..
cd authentication-service/
./gradlew clean build buildDocker pushDocker
cd ..
cd eureka-naming-server/
./gradlew clean build buildDocker pushDocker
cd ..
cd hystrix-dashboard/
./gradlew clean build buildDocker pushDocker
cd ..
cd notification-service/
./gradlew clean build buildDocker pushDocker
cd ..