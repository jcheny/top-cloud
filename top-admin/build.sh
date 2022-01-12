version="v1.0.0"
if [ -n "$1" ]; then
    version=$1
fi

rm -rf dist

npm run build 

docker build -t top-admin:$version .
docker tag top-admin:$version 1633182434/top-admin:$version
docker push 1633182434/top-admin:$version
docker rmi top-admin:$version
docker rmi 1633182434/top-admin:$version