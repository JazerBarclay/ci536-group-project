#!/bin/bash

# A simple script to start a dev apache php server via docker

PROCESS=`docker ps | grep pgdbdev | awk '{print $2}'`

if [[ -z $PROCESS ]]; then
    echo "No existing process running"
else
    echo "Halting running container"
    docker stop pgdbdev &>/dev/null
    docker rm pgdbdev &>/dev/null
fi

CONTAINER=`docker ps --filter "status=exited" | grep pgdbdev | awk '{print $2}'`

if [[ -z $CONTAINER ]]; then
    echo ""
else
    echo "Removing old container"
    docker rm pgdbdev &>/dev/null
fi

NETWORK=`docker network list | grep pgnetwork | awk '{print $2}'`

if [[ -z $NETWORK ]]; then
    echo "Creating bridge network 'pgnetwork'"
    docker network create --driver bridge pgnetwork
else
    echo "Using existing bridge network 'pgnetwork'"
fi

echo "Starting docker container"

# Run new container under pgdbdev
docker run --name pgdbdev \
    -v /var/www/quark/dev/database/schema.sql:/docker-entrypoint-initdb.d/1-schema.sql \
    -v /var/www/quark/dev/database/seed.sql:/docker-entrypoint-initdb.d/2-seed.sql \
    -p 5433:5432 \
    -e POSTGRES_USER=CHANGEME \
    -e POSTGRES_PASSWORD=CHANGEME \
    -e POSTGRES_DB=CHANGEME \
    --hostname postgres \
    --network pgnetwork \
    -d postgres