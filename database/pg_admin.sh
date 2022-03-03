#!/bin/bash

# A simple script to start a postgres admin 4 instance

VOLUME=`docker volume list | grep pga4vol | awk {'print $2'}`

if [[ -z $VOLUME ]]; then
    echo "Creating new volume 'pga4vol'"
    docker volume create --driver local --name=pga4vol
else
    echo "Using existing volume 'pga4vol'"
fi

NETWORK=`docker network list | grep pgnetwork | awk '{print $2}'`

if [[ -z $NETWORK ]]; then
    echo "Creating bridge network 'pgnetwork'"
    docker network create --driver bridge pgnetwork
else
    echo "Using existing bridge network 'pgnetwork'"
fi

PROCESS=`docker ps | grep pga4 | awk '{print $2}'`

if [[ -z $PROCESS ]]; then
    echo "No existing process running"
else
    echo "Halting running container"
    docker stop pga4 &>/dev/null
    docker rm pga4 &>/dev/null
fi

CONTAINER=`docker ps --filter "status=exited" | grep pga4 | awk '{print $2}'`

if [[ -z $CONTAINER ]]; then
    echo ""
else
    echo "Removing old container"
    docker rm pga4 &>/dev/null
fi

echo "Starting docker container"

# Run new container under pga4
docker run --name pga4 \
    -p 80:80 \
    -v pga4volume:/var/lib/pgadmin \
    -e 'PGADMIN_DEFAULT_EMAIL=user@quark.rocks' \
    -e 'PGADMIN_DEFAULT_PASSWORD=quarky' \
    --hostname pgadmin4 \
    --network pgnetwork \
    -d dpage/pgadmin4