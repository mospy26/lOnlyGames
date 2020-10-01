function get_jar() {
    mvn clean install package
}

function run() {
    docker-compose up --build -d
}

function stop() {
    docker-compose down
}

function restart() {
    stop
    run
}

function start() {
    get_jar
    restart
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