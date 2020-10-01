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
esac