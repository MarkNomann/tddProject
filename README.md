# tddProject
## How to run the project?
Install <i>postgres:17.6-alpine</i> docker image and run it:
``` bash
docker pull postgres:17.6-alpine
docker run -e POSTGRES_PASSWORD=secret -v postgres_data:/var/lib/postgresql/data -d -p 5432:5432 postgres:17.6-alpine
```
Setup env variables:
``` bash
export JDBC_URL=...
export JDBC_USERNAME=...
export JDBC_PASSWORD=...
```

