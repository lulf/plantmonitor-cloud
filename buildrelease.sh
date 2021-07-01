#!/bin/bash
VERSION=$1
set -e

./mvnw clean package
#docker build -t docker.io/lulf/plantmonitor:$VERSION -f src/main/docker/Dockerfile.legacy-jar .
./mvnw clean package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true -Dquarkus.container-image.image=docker.io/lulf/plantmonitor:$VERSION

docker push docker.io/lulf/plantmonitor:$VERSION
