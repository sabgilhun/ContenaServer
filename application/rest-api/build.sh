
./gradlew build -x test

docker build . -t registry.ccontena.com/rest-api:latest

docker push registry.ccontena.com/rest-api:latest