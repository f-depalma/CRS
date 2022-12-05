HOW TO CONNECT THE DB

- open the file init.sql in DataGrip
- Press on configure data source
- Name: postgres@localhost
- Port: 5431
- User: postgres
- Password: postgres
- Database: postgres


CREATE IMAGE AND PUSH TO THE REPOSITORY
- docker image build . -t crs_db:latest
- docker tag my-image fdepalma95/crs_db:latest
- docker push fdepalma95/crs_db:latest

PULL IMAGE AND START DB
- docker pull fdepalma95/crs_db:latest
- docker container run -d --rm -p 5431:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres --name crs_db fdepalma95/crs_db:latest
