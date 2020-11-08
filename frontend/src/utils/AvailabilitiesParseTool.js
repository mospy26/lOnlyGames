export const parseDay = (day) => {
    switch (day) {
        case 0:
            return "Monday"
        case 1:
            return "Tuesday"
        case 2:
            return "Wednesday"
        case 3:
            return "Thursday"
        case 4:
            return "Friday"
        case 5:
            return "Saturday"
        case 6:
            return "Sunday"
    }
    return "ERROR"
}

export const parseTime = (time) => {
    let currMinutes = time % 60;
    let currHour = Math.floor(time/60)

    currMinutes = (currMinutes < 10) ? "0" + currMinutes : currMinutes
    currHour = (currHour < 10) ? "0" + currHour : currHour

    return currHour + ":" + currMinutes
}