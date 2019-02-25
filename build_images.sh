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

#构建容器
cd $(dirname "$0")
docker build --rm -f DockerFile -t $1:$4 .

#推送镜像到镜像库  ip:port/name:tag
echo "docker tag $1 $2:$3/$1:$4"
docker tag $1:$4 $2:$3/$1:$4

echo "docker push $2:$3/$1:$4"
docker push $2:$3/$1:$4

#删除镜像
echo "docker rmi $2:$3/$1:$4"
docker rmi $2:$3/$1:$4

echo "docker rmi $1:$4"
docker rmi $1:$4