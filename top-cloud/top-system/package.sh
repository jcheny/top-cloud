#!/bin/bash

version="v1.0.0"
if [ -n "$1" ]; then
    version=$1
fi

warehouse="registry.sensetime.com"
warehouse_title="sense-video-dev"
system="stmobile-system/stmobile-system-service"
baseList="src/main"
mvn clean
mvn install -DskipTests

mkdir -p ../$system/target/docker
cp ../$system/$baseList/docker/Dockerfile ../$system/target/docker
cp ../$system/target/*.jar ../$system/target/docker
cd ../$system/target/docker
pwd

docker rmi $system:$version
docker build -t $system:$version .
cd $local

docker tag $system:$version $warehouse/$warehouse_title/$system:$version

docker push $warehouse/$warehouse_title/$system:$version

docker rmi $system:$version

docker rmi $warehouse/$warehouse_title/$system:$version
