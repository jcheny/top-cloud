#!/bin/bash

version="v1.0.0"
if [ -n "$1" ]; then
    version=$1
fi

warehouse="registry.sensetime.com"
warehouse_title="sense-video-dev"
authorization="stmobile-authorization"
baseList="src/main"
mvn clean
mvn install -DskipTests

mkdir -p ../$authorization/target/docker
cp ../$authorization/$baseList/docker/Dockerfile ../$authorization/target/docker
cp ../$authorization/target/*.jar ../$authorization/target/docker
cd ../$authorization/target/docker
pwd

docker rmi $authorization:$version
docker build -t $authorization:$version .
cd $local

docker tag $authorization:$version $warehouse/$warehouse_title/$authorization:$version

docker push $warehouse/$warehouse_title/$authorization:$version

docker rmi $authorization:$version

docker rmi $warehouse/$warehouse_title/$authorization:$version
