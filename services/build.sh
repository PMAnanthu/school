
cd api-gateway-service/
./gradlew clean build buildDocker pushDocker -i
cd ..
cd authentication-service/
./gradlew clean build buildDocker pushDocker -i 
cd ..
cd notification-service/
./gradlew clean build buildDocker pushDocker -i
cd ..
cd user-management-service/
./gradlew clean build buildDocker pushDocker -i
cd ..
cd school-management-service/
./gradlew clean build buildDocker pushDocker -i
cd ..