#!/bin/bash

# A simple script to start a postgres admin 4 instance

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
    -p 8000:80 \
    -v $PWD/pgadmin:/var/lib/pgadmin \
    -e 'PGADMIN_DEFAULT_EMAIL=user@quark.rocks' \
    -e 'PGADMIN_DEFAULT_PASSWORD=quarky' \
    -d dpage/pgadmin4