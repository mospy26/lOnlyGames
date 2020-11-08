import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {parseDay, parseTime} from '../../utils/AvailabilitiesParseTool'
import "../../styles/Availability.css";

const Availabilities = (props) => {

    const [list, setList] = useState([]);

    useEffect(() => {
        axios.get("/users/availability?user=" + props.username)
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

    return(
        <React.Fragment>
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
                        </tr>
                    )
                })}
                </tbody>
            </table>
        </React.Fragment>
    )
}

export default Availabilities;