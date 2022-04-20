#!/bin/bash

env="test"
if [ -n "$1" ]; then
    env=$1
fi
# 需要填充的变量（下面默认是test 环境下的变量）
mysql_user="root"
mysql_password="dppwx2wTj0B1trhZ"
mysql_database_url="jdbc:mysql://stmobile-mysql-0.stmobile-mysql-gvr.kaipai-test.svc:3306/stmobile_cloud?useUnicode=true&characterEncoding=UTF-8"
redis_host="stmobile-redis.kaipai-test.svc.cluster.local"
redis_password="rRrsEjxljJ"
redis_database="0"
kafka_server="119.91.196.164:9092"
namespace="kaipai-test"
service_account="kaipai-test-user"
pull_secrets="stmobile-cloud"

if [ "$env" = "stage" ];
then
  mysql_user="kaipai2testuser"
  mysql_password="tetrasai"
  mysql_database_url="jdbc:mysql://rm-bp1d9i4mb52vtbe09.mysql.rds.aliyuncs.com/kaipai2_app_test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai"
  redis_host="r-bp1nwxmi68qffbylyd.redis.rds.aliyuncs.com"
  redis_password="SenseVide0"
  redis_database="4"
  namespace="kaipai-test2"
  service_account="kaipai-test2-user"
  kafka_server="119.91.196.164:9092"
  pull_secrets="sense-video"
elif [ "$env" = "prod" ];
then
  mysql_user="kaipaiuser"
  mysql_password="tetrasai"
  mysql_database_url="jdbc:mysql://rm-bp1d9i4mb52vtbe09.mysql.rds.aliyuncs.com/kaipai2_app?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai"
  redis_host="r-bp1nwxmi68qffbylyd.redis.rds.aliyuncs.com"
  redis_password="SenseVide0"
  redis_database="3"
  namespace="kaipai-prod2"
  service_account="kaipai-prod2-user"
  kafka_server="119.91.196.164:9092"
  pull_secrets="sense-video"
fi