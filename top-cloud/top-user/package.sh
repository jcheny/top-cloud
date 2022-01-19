#!/bin/bash

version="v1.0.0"
if [ -n "$1" ]; then
    version=$1
fi

warehouse="xxxx"
warehouse_title="xxxx"
user="top-user/top-user-service"
baseList="src/main"
mvn clean
mvn install -DskipTests

mkdir -p ../$user/target/docker
cp ../$user/$baseList/docker/Dockerfile ../$user/target/docker
cp ../$user/target/*.jar ../$user/target/docker
cd ../$user/target/docker
pwd

docker rmi $user:$version
docker build -t $user:$version .
cd $local

docker tag $user:$version $warehouse/$warehouse_title/$user:$version

docker push $warehouse/$warehouse_title/$user:$version

docker rmi $user:$version

docker rmi $warehouse/$warehouse_title/$user:$version
