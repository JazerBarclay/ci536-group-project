#!/bin/bash
# Kills any running instance of pgdb then launches a new container mounting the schema and seed sql files

# Kill any running docker process for pgdb
docker stop pgdb 2>/dev/null
docker rm pgdb 2>/dev/null

# Run new container under pgdb
docker run -v $PWD/schema.sql:/docker-entrypoint-initdb.d/1-schema.sql -v $PWD/seed.sql:/docker-entrypoint-initdb.d/2.seed.sql -p 5432:5432 --name pgdb -e POSTGRES_USER=web -e POSTGRES_PASSWORD=pass -e POSTGRES_DB=timerapp -d postgres