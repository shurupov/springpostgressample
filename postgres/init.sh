#!/usr/bin/env bash

cd $(dirname $0)

docker run -d --name postgres -p 5432:5432 -v "$(pwd)":/dump -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_USER=springboot -e POSTGRES_DB=springbootpostgres postgres

sleep 5

#docker exec -it -w /dump postgres psql -d springbootpostgres -U springboot -f init.sql