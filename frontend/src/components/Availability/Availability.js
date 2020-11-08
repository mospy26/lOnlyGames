import React from 'react';
import Header from '../Header'
import Footer from '../Footer'
import logo from '../../resources/logo.svg';
import '../../styles/Blocked.css';
import "../../styles/Availability.css";
import axios from 'axios';
import {useState, useEffect} from 'react'
import { Button } from "react-bootstrap";
import Form from './AvailabilityForm';
import {parseTime, parseDay} from '../../utils/AvailabilitiesParseTool'

function Availability() { 
  const [list, setList] = useState([]);
  const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')))
  const [dayInput, setDayInput] = useState("")

function removeTime(id) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
    axios.post("users/availability/remove", {id: id} )
    .then(res => {
        if(res.status == 200){         
          console.log("Removed  " + id)
          setList(list.filter(item => item.id !== id));
        }
    })
    .catch(err => {
        console.log(err)
    })
}

  useEffect(() => {

    // let user = localStorage.getItem('user')

    axios.get("/users/availability?user=" + user.username)
    .then(res => {
        if(res.status == 200){ 
          setList(res.data.result)
          console.log(res.data.result)
        }
    })
    .catch(err => {
        console.log(err.response)
    })
  }, [])

  return (
    <div className="Blocked">
        <Header/>
          <h1 className="header">Your Availability Times:</h1>
          <table className="styled-table">
            <tbody>
                <tr>
                    <td>
                        Day
                    </td>
                    <td>
                        Starting Time
                    </td>
                    <td>
                        End Time
                    </td>
                </tr>
              {list.map((avail) => {
                return (
                  <React.Fragment>
                    <tr>
                        <td style={{font:"x"}}>
                            {parseDay(avail.day)}
                        </td>
                        <td>
                            {parseTime(avail.timeStart)} 
                        </td>
                        <td>
                            {parseTime(avail.timeEnd)} 
                        </td>
                        <td>
                            <Button onClick={() => removeTime(avail.id, avail.day, avail.timeStart, avail.timeEnd)}>Remove</Button>
                        </td>
                    </tr>
                  </React.Fragment>
                )
              })}
            </tbody>
          </table>
          <Form/>
        <Footer/>
    </div>
  );
}

export default Availability;
