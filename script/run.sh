#! /bin/bash

cd ../docker
docker network create it-jobs-network
docker compose -f docker-compose.yml up -d --force-recreate --build
