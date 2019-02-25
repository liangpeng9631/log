#!/bin/sh

if [ -z "$1" ]
then
	echo "error:please input run name in first param"
	exit
fi

if [ -z "$2" ]
then
	echo "error:please input port in second param"
	exit
fi

#判定指定docker是否运行[关闭]
for i in $(docker ps --filter "name=$1" --format "{{.Names}}")
do
	if [ "$i" == "$1" ]
	then
		docker stop $1
		break
	fi
done

#判断指定容器是否存在[删除]
for i in $(docker ps -a --filter "name=$1" --format "{{.Names}}")
do
	if [ "$i" == "$1" ]
	then
		docker rm $1
		break
	fi
done

#判断镜像是否存在[删除]
for i in $(docker images --format "{{.Repository}}")
do
	if [ "$1" == "$i" ]
	then
		docker rmi $1
		break
	fi
done

#构建容器
cd $(dirname "$0")
docker build --rm -f DockerFile -t $1 .

#启动容器
docker run --name="$1" -v /etc/localtime:/etc/localtime:ro -p $2:80 -dit $1