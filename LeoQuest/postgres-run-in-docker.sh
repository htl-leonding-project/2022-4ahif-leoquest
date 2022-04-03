docker run --name postgres --rm \
        -e POSTGRES_USER=app \
        -e POSTGRES_PASSWORD=app \
        -e POSTGRES_DB=db \
        -p 5432:5432 \
        -d postgres:13.2-alpine