#!/bin/bash
VERSION=$1
set -e

./mvnw clean package
docker build -t docker.io/lulf/plantmonitor:$VERSION -f src/main/docker/Dockerfile.legacy-jar .
docker push docker.io/lulf/plantmonitor:$VERSION
