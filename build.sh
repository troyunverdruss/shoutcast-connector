#!/usr/bin/env bash

mvn clean package

rm -rf docker/target
mkdir docker/target
cp target/shoutcast-connector-0.0.1-SNAPSHOT.jar docker/target/
cp src/main/resources/shoutcast-connector-config.yml docker/target/

cd docker/
docker build .
