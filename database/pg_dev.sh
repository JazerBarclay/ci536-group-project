#!/bin/bash

# A simple script to start a dev apache php server via docker

PROCESS=`docker ps | grep pgdb | awk '{print $2}'`

if [[ -z $PROCESS ]]; then
    echo "No existing process running"
else
    echo "Halting running container"
    docker stop pgdb &>/dev/null
    docker rm pgdb &>/dev/null
fi

CONTAINER=`docker ps --filter "status=exited" | grep pgdb | awk '{print $2}'`

if [[ -z $CONTAINER ]]; then
    echo ""
else
    echo "Removing old container"
    docker rm pgdb &>/dev/null
fi

NETWORK=`docker network list | grep pgnetwork | awk '{print $2}'`

if [[ -z $NETWORK ]]; then
    echo "Creating bridge network 'pgnetwork'"
    docker network create --driver bridge pgnetwork
else
    echo "Using existing bridge network 'pgnetwork'"
fi

echo "Starting docker container"

# Run new container under pgdb
docker run --name pgdb \
    -v $PWD/schema.sql:/docker-entrypoint-initdb.d/1-schema.sql \
    -v $PWD/seed.sql:/docker-entrypoint-initdb.d/2.seed.sql \
    -p 5432:5432 \
    -e POSTGRES_USER=web \
    -e POSTGRES_PASSWORD=pass \
    -e POSTGRES_DB=timerapp \
    --hostname postgres \
    --network pgnetwork \
    -d postgres