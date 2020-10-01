# lOnlyGames

## Front end 
Make sure you're in the directory of front end and run: `npm start`


## Back end 

#### Using Docker:

1. Make sure you install [docker-compose](https://docs.docker.com/compose/install/)
2. Download the repository, and `cd backend`
3. Run `./run.sh run` and wait until the program finishes.
4. Wait for a few seconds.
5. Visit `localhost` at port `80` and your spring application should be running there.

###### Restarting the application
Every time you make a change and wish to restart the application, run:

```bash
./run.sh restart
```

###### Stopping the application

If you want to stop the application, run `./run.sh stop`.
#### ~ Without Docker

1. Ensure you're using IntelliJ
2. Ensure that you have MySQL Installed
3. Ensure that the MySQL Server is running
4. Make sure that you have a database up and running with the following properties: 

> - Database is running under `localhost:3306/db_lonlygames`
> - There is a user called `lonlygames`
> - The password should be `password`
> - The server port should `8080`