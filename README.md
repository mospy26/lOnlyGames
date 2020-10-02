# lOnlyGames

## Front end 
Make sure you're in the directory of front end and run: `npm start`


## Back end 

### 1. Using Docker:

You can dockerize every service. For ease of development, we have provided a script called `run.sh`. This option lets you dockerize every service and includes `nginx`, a load balancer. If you only want to dockerize the mysql db, then follow instructions on 2. instead.

1. Make sure you install [docker](https://docs.docker.com/get-docker/) and [docker-compose](https://docs.docker.com/compose/install/) in your computer
2. Download the repository, and `cd backend`
3. Run `./run.sh run` and wait until the program finishes.
4. Wait for a few minutes (this will only be needed in the first run).
5. Do not stop any container.
6. Visit `localhost` at port `80` and your spring application should be running there.

###### What if there are changes to the application, does it reflect?
Yes, every time you make a change it will automatically reload in the docker container.

###### Stopping the application

If you want to stop the application, run `./run.sh stop`.

### 2. Docker for mysql but no docker for spring

You can still run the application on your computer while having docker run the database.To do this, do the following:

1. Install java 11 in your machine.
2. Run `mvn clean install`
3. Then finally run:

```bash
./run.sh db-only
mvn spring-boot:run
```

###### Stopping the application

You can kill the mvn process through hitting `ctrl+c` in the terminal. And, you can stop the db container using:
```bash
./run.sh stop
```

### 3. Without Docker at all

1. Ensure you're using IntelliJ
2. Ensure that you have MySQL Installed
3. Ensure that the MySQL Server is running
4. Make sure that you have a database up and running with the following properties: 

> - Database is running under `localhost:3306/db_lonlygames`
> - There is a user called `lonlygames`
> - The password should be `password`
> - The server port should `8080`