import Axios from 'axios';
import React, {useState, useRef, useEffect} from 'react'
import { Button } from 'react-bootstrap';
import axios from 'axios'
import "../../styles/Availability.css"

const Form = () => {

    const [day, setDay] = useState(0);
    const [hourStart, setHourStart] = useState(0);
    const [minStart, setMinStart] = useState(0);
    const [hourEnd, setHourEnd] = useState(0);
    const [minEnd, setMinEnd] = useState(0);
    const [error, setError] = useState("");
    const hourDisplay = [];
    const minDisplay = [];



    for (var i = 0; i < 60; i++) {
        if (i < 24) {
            hourDisplay.push(i);
        }
        minDisplay.push(i);
    }
    

    const sendForm = () => {
        let timeStart = parseInt(hourStart * 60) + parseInt(minStart);
        let timeEnd = parseInt(hourEnd * 60) + parseInt(minEnd);
        
        if (timeEnd < timeStart) {
            document.getElementById('availability__oob').style.display = 'None';
            document.getElementById('equal__err').style.display = 'None';
            document.getElementById('overlapping__err').style.display = 'block';
            document.getElementById('generic__err').style.display = 'None';
            return;
        } else if (timeEnd == timeStart) {
            document.getElementById('availability__oob').style.display = 'None';
            document.getElementById('equal__err').style.display = 'block';
            document.getElementById('overlapping__err').style.display = 'None';
            document.getElementById('generic__err').style.display = 'None';
            return;
        }

        let json = {
            "day": parseInt(day),
            "timeStart": parseInt(timeStart),
            "timeEnd": parseInt(timeEnd)
        }
        
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
        axios.post("users/availability/add", json )
        .then(res => {
            if(res.status == 200){  
                window.location.reload(false);
            }
        })
        .catch(err => {
           const { response } = err;
           if (response.data.code === "AVAILABILITY_OVERLAP") {
                document.getElementById('availability__oob').style.display = 'None';
                document.getElementById('equal__err').style.display = 'None';
                document.getElementById('overlapping__err').style.display = 'Block';
                document.getElementById('generic__err').style.display = 'None';
           } else {
                document.getElementById('availability__oob').style.display = 'None';
                document.getElementById('equal__err').style.display = 'None';
                document.getElementById('overlapping__err').style.display = 'None';
                document.getElementById('generic__err').style.display = 'Block';
           }
        })
    }

    return (
        <React.Fragment>
            <h4>Add a new availability: </h4>
            <form>
                <h5>Day</h5>
                <select value={day} onChange={(e) => {setDay(e.target.value)} }>
                    <option value="0">Monday</option>
                    <option value="1">Tuesday</option>
                    <option value="2">Wednesday</option>
                    <option value="3">Thursday</option>
                    <option value="4">Friday</option>
                    <option value="5">Saturday</option>                    
                    <option value="6">Sunday</option>
                </select>
            </form>
            <form>
                <h5>Time Start</h5>
                <select value={hourStart} onChange={(e) => {setHourStart(e.target.value)} }>
                    {hourDisplay.map((value, index) => {
                        return <option value={value}>{value = value < 10 ? "0" + value : value}</option>
                    })}
                </select>
                :
                <select value={minStart} onChange={(e) => {setMinStart(e.target.value)} }>
                    {minDisplay.map((value, index) => {
                        return <option value={value}>{value = value < 10 ? "0" + value : value}</option>
                    })}
                </select>
            </form>
            <form>
                <h5>Time End</h5>
                <select value={hourEnd} onChange={(e) => {setHourEnd(e.target.value)} }>
                    {hourDisplay.map((value, index) => {
                        return <option value={value}>{value = value < 10 ? "0" + value : value}</option>
                    })}
                </select>
                :
                <select value={minEnd} onChange={(e) => {setMinEnd(e.target.value)} }>
                    {minDisplay.map((value, index) => {
                        return <option value={value}>{value = value < 10 ? "0" + value : value}</option>
                    })}
                </select>
            </form>
            <div id="availability__oob">Time start cannot be more than time end ????</div>
            <div id="overlapping__err">Your time is overlapping...</div>
            <div id="equal__err">You cant have same start time as end time</div>
            <div id="generic__err">Something went wrong try again later</div>
            <Button onClick={() => sendForm()}>Submit</Button>
        </React.Fragment>
    );
}

export default Form;