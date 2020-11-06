import React from 'react';
import Header from './Header'
import Footer from './Footer'
import logo from '../resources/logo.svg';
import '../styles/Matches.css';
import axios from 'axios';
import {useState, useEffect} from 'react'
import { Button } from "react-bootstrap";

var users = [
];

function Likes() { 
  const [list, setList] = React.useState(users);

  function dislike(username) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
    axios.post("/users/dislike", {username: username})
    .then(res => {
        if(res.status == 200){         
          console.log("Disliked User " + username)
          setList(list.filter(item => item.username !== username));
        }
    })
    .catch(err => {
        console.log(err.response)
    })
  }

  useEffect(() => {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
    axios.get("/users/users-liked")
    .then(res => {
        if(res.status == 200){ 
          setList(res.data.result)
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
                    <Button onClick={() => dislike(user.username)}>Dislike</Button>           
                  </div>
                </React.Fragment>
              )
            })}
          </ul>
        <Footer/>
    </div>
  );
};

export default Likes;