#!/bin/bash

version="v1.0.0"
if [ -n "$1" ]; then
    version=$1
fi

warehouse="registry.sensetime.com"
warehouse_title="sense-video-dev"
gateway="stmobile-gateway"
baseList="src/main"
mvn clean
mvn install -DskipTests

mkdir -p ../$gateway/target/docker
cp ../$gateway/$baseList/docker/Dockerfile ../$gateway/target/docker
cp ../$gateway/target/*.jar ../$gateway/target/docker
cd ../$gateway/target/docker
pwd

docker rmi $gateway:$version
docker build -t $gateway:$version .
cd $local

docker tag $gateway:$version $warehouse/$warehouse_title/$gateway:$version

docker push $warehouse/$warehouse_title/$gateway:$version

docker rmi $gateway:$version

docker rmi $warehouse/$warehouse_title/$gateway:$version
