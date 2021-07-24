cd api-gateway-service
gradlew.sh clean build buildDocker pushDocker
cd ..

cd authentication-service
gradlew.sh clean build buildDocker pushDocker
cd ..

cd eureka-naming-server
gradlew.sh clean build buildDocker pushDocker
cd ..

cd hystrix-dashboard
gradlew.sh clean build buildDocker pushDocker
cd ..

cd notification-service
gradlew.sh clean build buildDocker pushDocker
cd ..