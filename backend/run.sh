function get_jar() {
    mvn clean package -Dmaven.test.skip=true
}

function run() {
    docker-compose up --build -d
}

function stop() {
    docker-compose down
}

function restart() {
    get_jar
    docker-compose stop lonlygames-rest
    docker-compose up --no-deps -d --build lonlygames-rest
}

function start() {
    get_jar
    run
}

function run_database() {
    docker-compose up --no-deps -d lonlygames-mysql
}

function usage() {
    echo "Usage: ./run.sh command"
    echo "\nUtility for running lOnlyGames services.\n"
    echo "command:"
    echo "  run\t\t runs the service stack"
    echo "  stop\t\t stops the service stack"
    echo "  restart\t only restarts the spring boot app in a docker container"
    echo "  db-only\t only runs the db (if you are running mvn outside docker)"
}


case $1 in
    run)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    db-only)
        run_database
        ;;
    *)
        usage
        ;;
esac