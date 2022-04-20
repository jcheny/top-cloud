#!/bin/bash
# 外部变量
version="v1.0.0"
if [ -n "$1" ]; then
    version=$1
    action="all"
    if [ -n "$2" ]; then
        action=$2
    fi
    env="test"
    if [ -n "$3" ]; then
        echo "当前环境：$3"
        echo "注意，如果选择了stage或者prod环境，请先确定k8s 集群是否选择到了阿里云，否则将部署失败！！"
        env=$3
    fi
else
  source ./choice.sh
fi

# 基本变量
warehouse="registry.sensetime.com"
warehouse_title="sense-video-dev"
local="$(pwd)"
baseList="src/main"

# 包的路径
authorization="stmobile-issa/stmobile-authorization"
gateway="stmobile-issa/stmobile-gateway"
system="stmobile-system/stmobile-system-service"
resources="stmobile-resources/stmobile-resources-service"
user="stmobile-user/stmobile-user-service"
member="stmobile-member/stmobile-member-service"

if [ "$action" = all ] || [ "$action" = package ];
then
git pull
mvn clean
mvn install -DskipTests

arrayWen=("$authorization" "$gateway" "$system" "$resources" "$user" "$member")
# shellcheck disable=SC2068
for var in ${arrayWen[@]};
do
  {
    mkdir -p "$var"/target/docker
    cp "$var"/"$baseList"/docker/Dockerfile "$var"/target/docker
    if [ "$var" == "$authorization" ]
      then
        cp "$var"/"$baseList"/resources/coinexchange.jks "$var"/target/docker
      fi
    cp "$var"/target/*.jar "$var"/target/docker
    cd "$var"/target/docker || exit
    pwd
    docker rmi "$var"
    docker rmi "$warehouse"/"$warehouse_title"/"$var":"$version"
    docker build -t "$var":"$version" .
    docker tag "$var":"$version" "$warehouse"/"$warehouse_title"/"$var":"$version"
    docker push "$warehouse"/"$warehouse_title"/"$var":"$version"
    docker rmi "$var":"$version"
    docker rmi "$warehouse"/"$warehouse_title"/"$var":"$version"
    cd "$local" || exit
  }  &
done
wait
fi

if [ "$action" = "build" ] || [ "$action" = "all" ]
then
source ./env.sh "$env"
profiles_active=$env
helm uninstall mobile -n "$namespace"
helm install mobile deployment \
    --set stmobile_version="$version" \
    --set namespace="$namespace" \
    --set service_account="$service_account" \
    --set mysql_user="$mysql_user" \
    --set mysql_password="$mysql_password" \
    --set mysql_database_url="$mysql_database_url" \
    --set redis_host="$redis_host" \
    --set redis_password="$redis_password" \
    --set redis_database="$redis_database" \
    --set profiles_active="$profiles_active" \
    --set kafka_server="$kafka_server" \
    --set pull_secrets="$pull_secrets" \
     -n "$namespace"
fi