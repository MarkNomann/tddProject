# tddProject
## How to run the project?
Install <i>postgres:17.6-alpine</i> docker image and run it:
``` console
docker pull postgres:17.6-alpine
docker run -e POSTGRES_PASSWORD=secret -v postgres_data:/var/lib/postgresql/data -d -p 5432:5432 postgres:17.6-alpine
```

Setup role for database:
```
create role somerole;
alter role somerole with password 'somepassword';
create database somedatabase;
grant create on schema public to somerole;
grant all privileges on database somedatabase to somerole;
```

Setup env variables:
``` console
export JDBC_URL=...
export JDBC_USERNAME=...
export JDBC_PASSWORD=...
```

