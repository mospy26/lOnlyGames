import React from 'react';
import Header from './Header'
import Footer from './Footer'
import logo from '../resources/logo.svg';
import '../styles/Blocked.css';
import axios from 'axios';
import {useState, useEffect} from 'react'
import { Button } from "react-bootstrap";

// https://localhost:8080/api/v1/users/users-blocked

var users = [
];

function Blocked() { 
  const [list, setList] = React.useState(users);

  function unblock(username) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
    axios.post("/users/unblock", {username: username})
    .then(res => {
        if(res.status == 200){         
          console.log("Unblocked User " + username)
          setList(list.filter(item => item.username !== username));
        }
    })
    .catch(err => {
        console.log(err.response)
    })
  }

  useEffect(() => {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
    axios.get("/users/users-blocked")
    .then(res => {
        if(res.status == 200){ 
          var blockedUsers = res.data.result
          setList(res.data.result)
          console.log("lol");
        }
    })
    .catch(err => {
        console.log(err.response)
    })
  }, [])

  return (
    <div className="Blocked">
        <Header />
          <ul>
            {list.map((user) => {
              return (
                <React.Fragment>
                  <div >
                    <h1>Username: {user.username}</h1>
                    <Button onClick={() => unblock(user.username)}>Unblock</Button>           
                  </div>
                </React.Fragment>
              )
            })}
          </ul>
        <Footer/>
    </div>
  );
};

export default Blocked;
