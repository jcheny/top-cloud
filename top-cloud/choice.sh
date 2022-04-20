#!/bin/bash
read -p "请填写版本号（例如v1.0.0）:" -t 30  version
echo -e  "1:all(打包并部署)"
echo -e  "2:build（仅部署)"
echo -e  "3:package（仅打包)"
read -p "请选择你的打包方式：" -t 30  actionCho
case $actionCho in
        "1")
                action="all"
                ;;
        "2")
                action="build"
                ;;
        "3")
                action="package"
                ;;
        *)
                echo "输入错误，程序退出！"
                exit
                ;;
esac
echo -e  "1:test"
echo -e  "2:stage"
echo -e  "3:prod"
echo "注意，如果选择了stage或者prod环境，请先确定k8s 集群是否选择到了阿里云，否则将部署失败！！"
read -p "请选择环境：" -t 30  envCho

case $envCho in
        "1")
                env="test"
                ;;
        "2")
                env="stage"
                ;;
        "3")
                env="prod"
                ;;
        *)
                echo "输入错误，程序退出！"
                exit
                ;;
esac
echo "当前部署版本号：$version"
echo "当前部署方式：$action"
echo "当前部署环境：$env"
read -p "确认部署？[y/n]" -t 30  input
case $input in
  [yY][eE][sS]|[yY])
  echo "正在部署"
  ;;
  [nN][oO]|[nN])
  echo "结束"
  exit
  ;;
  *)
  echo "输入错误，程序退出！"
  exit
  ;;
esac