#!/bin/bash
version="v1.0.0"
if [ -n "$1" ]; then
    version=$1
fi
echo "镜像版本号：" $version
#current_context=`kubectl config current-context`
echo "kubectl config:"current_context

warehouse="1633182434"

echo "镜像仓库"$warehouse

local="$(pwd)"
echo "本地目录："$local

authorization="top-issa/top-authorization"
gateway="top-issa/top-gateway"
system="top-issa/top-system/top-system-service"
user="top-issa/top-user/top-user-service"
member="top-issa/top-member/top-member-service"


baseList="src/main"

action="all"
if [ -n "$2" ]; then
    action=$2
fi

if [ $action = all ] || [ $action = package ];
then
echo "|ˉˉˉˉˉˉˉˉˉ\ˉ\      /\ˉ\     /ˉˉˉˉˉˉˉˉˉ\ˉ\ |ˉ|ˉ|  /ˉ/ˉ/      /\ˉ\       /ˉˉˉˉˉˉ\ˉ\  |ˉˉˉˉˉˉˉˉˉˉ|ˉ|"
echo "| |ˉ|ˉˉˉˉ\ \ \    /  \ \    | |ˉ|ˉˉˉ\ | | | | | / / /      /  \ \     / /ˉ/ˉˉ\ | | | |ˉ|ˉˉˉˉˉˉ ˉ"
echo "| | |    | | |   / /\ \ \   | | |    ˉ ˉ  |  ˉˉˉ | |      / /\ \ \   | | |    ˉˉˉ  |  ˉˉˉˉˉˉˉˉ|ˉ|"
echo "| ˉˉˉˉˉˉˉ  / /  / / /\ \ \  | | |         | |ˉ|ˉ\ \ \    / / /\ \ \  | | ||ˉˉˉˉˉ|ˉ|| |ˉ|ˉˉˉˉˉˉ ˉ"
echo "| |ˉ|ˉˉˉˉˉˉˉˉ  /  ˉˉˉˉ  \ \ | | |    /ˉ|ˉ|| | |  \ \ \  /  ˉˉˉˉ  \ \  \ \ \ˉ| |ˉ|ˉ | | |"
echo "| | |         / /ˉ/ˉˉˉˉ\ \ \\  ˉˉˉˉˉˉ / / | | |   \ \ \/ /ˉ/ˉˉˉˉ\ \ \  \ ˉˉˉ / /   |  ˉˉˉˉˉˉˉˉ|ˉ|"
echo "ˉˉˉˉˉ         ˉˉˉˉ      ˉˉˉˉ ˉˉˉˉˉˉˉˉˉˉˉ  ˉˉˉˉˉ    ˉˉˉˉˉˉˉˉ      ˉˉˉˉ   ˉˉˉˉˉˉˉ    ˉˉˉˉˉˉˉˉˉˉˉˉˉˉ"
echo ******************开始拉取代码******************
git pull
echo ******************结束拉取代码******************
echo ******************正在打包*********************
mvn clean
mvn install -DskipTests
echo ******************打包完成*********************
echo ******************打包镜像*********************

mkdir -p $authorization/target/docker
cp $authorization/$baseList/docker/Dockerfile $authorization/target/docker
cp $authorization/$baseList/resources/coinexchange.jks $authorization/target/docker
cp $authorization/target/*.jar $authorization/target/docker
cd $authorization/target/docker
pwd
docker rmi $authorization:$version
docker build -t $authorization:$version .
cd $local

mkdir -p $gateway/target/docker
cp $gateway/$baseList/docker/Dockerfile $gateway/target/docker
cp $gateway/target/*.jar $gateway/target/docker
cd $gateway/target/docker
pwd
docker rmi $gateway:$version
docker build -t $gateway:$version .
cd $local

mkdir -p $system/target/docker
cp $system/$baseList/docker/Dockerfile $system/target/docker
cp $system/target/*.jar $system/target/docker
cd $system/target/docker
pwd
docker rmi $system:$version
docker build -t $system:$version .
cd $local

mkdir -p $user/target/docker
cp $user/$baseList/docker/Dockerfile $user/target/docker
cp $user/target/*.jar $user/target/docker
cd $user/target/docker
pwd
docker rmi $user:$version
docker build -t $user:$version .
cd $local

mkdir -p $member/target/docker
cp $member/$baseList/docker/Dockerfile $member/target/docker
cp $member/target/*.jar $member/target/docker
cd $member/target/docker
pwd
docker rmi $member:$version
docker build -t $member:$version .
cd $local
echo ******************镜像打包成功*********************
echo ******************推送镜像*********************
docker tag $authorization:$version $warehouse/top-authorization:$version
docker tag $gateway:$version $warehouse/top-gateway:$version
docker tag $system:$version $warehouse/top-system:$version
docker tag $user:$version $warehouse/top-user:$version
docker tag $member:$version $warehouse/top-member:$version


docker push $warehouse/top-authorization:$version
docker push $warehouse/top-gateway:$version
docker push $warehouse/top-system:$version
docker push $warehouse/top-user:$version
docker push $warehouse/top-member:$version
echo ******************推送镜像OK*********************

docker rmi $authorization:$version
docker rmi $gateway:$version
docker rmi $system:$version
docker rmi $user:$version
docker rmi $member:$version

docker rmi $warehouse/top-authorization:$version
docker rmi $warehouse/top-gateway:$version
docker rmi $warehouse/top-system:$version
docker rmi $warehouse/top-user:$version
docker rmi $warehouse/top-member:$version

echo ******************helm uninstall*********************
#
fi
if [ $action = "build" ] || [ $action = "all" ]
then
namespace="xxxx"
service_account="xxxx"
echo "|ˉˉˉˉˉˉˉˉˉ\ |ˉˉˉˉˉˉˉˉˉˉ|  /ˉˉˉˉˉˉ\  |ˉˉˉˉˉˉˉˉˉˉ||ˉˉˉ\    |ˉ|            |ˉˉˉˉˉˉˉˉˉ\ |ˉ|      |ˉ||ˉˉˉˉˉˉˉˉˉˉ||ˉ|         |ˉˉˉˉˉˉˉˉ\  "
echo "| |ˉˉˉˉˉ\ | | |ˉˉˉˉˉˉˉˉ  / /ˉˉˉˉ\ |  ˉˉˉ|  |ˉˉˉ | |\ \   | |            | |ˉˉˉˉˉ\ | | |      | | ˉˉˉ|  |ˉˉˉ | |         | |ˉˉˉˉˉ\ \ "
echo "|  ˉˉˉˉˉ  / |  ˉˉˉˉˉˉˉˉ|| |      ˉˉ     |  |    | | \ \  | |            |  ˉˉˉˉˉ  / | |      | |    |  |    | |         | |      | |"
echo "| |ˉˉˉˉˉ\ \ | |ˉˉˉˉˉˉˉˉ | |  |ˉˉˉˉˉ|    |  |    | |  \ \ | |            | |ˉˉˉˉˉ\ \ | |      | |    |  |    | |         | |      / |"
echo "| |     /  || |          \ \  ˉ| |ˉ     |  |    | |   \ \| |            | |     /  | \ \    / /     |  |    | |         | |     / / "
echo "|  ˉˉˉˉˉ  / |  ˉˉˉˉˉˉˉˉ|  \ ˉˉˉ /   |ˉˉˉ    ˉˉˉ|| |    \ ˉ |            |  ˉˉˉˉˉ  /   \ ˉˉˉˉ /  |ˉˉˉ    ˉˉˉ||  ˉˉˉˉˉˉˉˉ||  ˉˉˉˉˉ /  "
echo " ˉˉˉˉˉˉˉˉˉ   ˉˉˉˉˉˉˉˉˉˉ    ˉˉˉˉˉ     ˉˉˉˉˉˉˉˉˉˉ  ˉ      ˉˉˉ              ˉˉˉˉˉˉˉˉˉ     ˉˉˉˉˉˉ    ˉˉˉˉˉˉˉˉˉˉ  ˉˉˉˉˉˉˉˉˉˉ  ˉˉˉˉˉˉˉˉ   "

mysql_user="root"
mysql_password="xxxx"
mysql_database_url="jdbc:mysql://xxxx:3306/top_app?useUnicode=true&characterEncoding=UTF-8"
redis_host="xxxx"
redis_password="xxxx"
redis_database="0"


env="test"


if [ -n "$3" ]; then
    env=$3
fi

if [ $env = "stage" ];
then
  mysql_user="xxxx"
  mysql_password="xxxx"
  mysql_database_url="jdbc:mysql://xxxx/top_app?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai"
  redis_host="xxxx"
  redis_password="xxxx"
  redis_database="2"
  namespace="xxxx"
  service_account="xxxx"
elif [ $env = "prod" ];
then
  mysql_user="xxxx"
  mysql_password="xxxx"
  mysql_database_url="jdbc:mysql://xxxx/top_app?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai"
  redis_host="xxxx"
  redis_password="xxxx"
  redis_database="3"
  namespace="xxxx"
  service_account="xxxx"
fi
profiles_active=$env
helm uninstall mobile -n $namespace


helm install mobile deployment \
    --set top_version=$version \
    --set namespace=$namespace \
    --set service_account=$service_account \
    --set mysql_user=$mysql_user \
    --set mysql_password=$mysql_password \
    --set mysql_database_url=$mysql_database_url \
    --set redis_host=$redis_host \
    --set redis_password=$redis_password \
    --set redis_database=$redis_database \
    --set profiles_active=$profiles_active \
     -n $namespace
echo "|ˉˉˉˉˉˉˉˉˉˉ||ˉˉˉ\    |ˉ||ˉˉˉˉˉˉˉˉ\              |ˉˉˉˉˉˉˉˉˉ\ |ˉ|      |ˉ||ˉˉˉˉˉˉˉˉˉˉ||ˉ|         |ˉˉˉˉˉˉˉˉ\  "
echo "| |ˉˉˉˉˉˉˉˉ | |\ \   | || |ˉˉˉˉˉ\ \             | |ˉˉˉˉˉ\ | | |      | | ˉˉˉ|  |ˉˉˉ | |         | |ˉˉˉˉˉ\ \ "
echo "|  ˉˉˉˉˉˉˉˉ|| | \ \  | || |      | |            |  ˉˉˉˉˉ  / | |      | |    |  |    | |         | |      | |"
echo "| |ˉˉˉˉˉˉˉˉ | |  \ \ | || |      / |            | |ˉˉˉˉˉ\ \ | |      | |    |  |    | |         | |      / |"
echo "| |         | |   \ \| || |     / /             | |     /  | \ \    / /     |  |    | |         | |     / / "
echo "|  ˉˉˉˉˉˉˉˉ|| |    \ ˉ ||  ˉˉˉˉˉ /              |  ˉˉˉˉˉ  /   \ ˉˉˉˉ /  |ˉˉˉ    ˉˉˉ||  ˉˉˉˉˉˉˉˉ||  ˉˉˉˉˉ /  "
echo " ˉˉˉˉˉˉˉˉˉˉ  ˉ      ˉˉˉ  ˉˉˉˉˉˉˉˉ                ˉˉˉˉˉˉˉˉˉ     ˉˉˉˉˉˉ    ˉˉˉˉˉˉˉˉˉˉ  ˉˉˉˉˉˉˉˉˉˉ  ˉˉˉˉˉˉˉˉ   "
fi