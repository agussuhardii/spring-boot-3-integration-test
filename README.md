test your api using integration testing by unit test

### 1.  create database
docker run -d --name psql \
-e POSTGRES_USER=psql \
-e POSTGRES_PASSWORD=password \
-p 5432:5432 \
postgres:14

### 2. Run
