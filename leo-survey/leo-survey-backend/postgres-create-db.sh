#!/bin/bash
set -e

DIR="./db-postgres"
if [ -d "$DIR" ]; then
  echo "${DIR} already exists, the installation has exited ..."
  exit 1
fi
echo "Installing postgres into ${DIR} ..."
mkdir ${DIR}
cd ${DIR}
cp ../src/main/docker/docker-compose-postgres.yml ./docker-compose-postgres.yml
