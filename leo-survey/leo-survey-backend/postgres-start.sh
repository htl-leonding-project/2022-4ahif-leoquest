cd db-postgres || exit
docker-compose -f docker-compose-postgres.yml up --build -d
