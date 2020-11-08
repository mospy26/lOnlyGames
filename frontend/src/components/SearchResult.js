import React from 'react';
import Header from './Header'
import Footer from './Footer'
import logo from '../resources/logo.svg';
import '../styles/Blocked.css';
import axios from 'axios';
import {useState, useEffect} from 'react'
import { Button } from "react-bootstrap";
import { propTypes } from 'react-bootstrap/esm/Image';
import { useHistory } from 'react-router-dom';

// https://localhost:8080/api/v1/users/users-blocked

function SearchResult(props) { 
  const [list, setList] = React.useState([]);
  const history = useHistory();

  function block(username) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
    axios.post("/users/block", {username: username} )
    .then(res => {
        if(res.status == 200){         
          setList(list.filter(item => item !== username));
        }
    })
    .catch(err => {
        console.log(err.response)
    })
  }

  function like(username) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
    axios.post("/users/like", {username: username} )
    .then(res => {
        if(res.status == 200){         
          setList(list.filter(item => item !== username));
        }
    })
    .catch(err => {
        console.log(err.response)
    })
  }

  useEffect(() => {
      setList(props.history.location.state?.searchUsers)
  }, [])

  const gotoProfile = (username) => history.push('/others/' + username);

  return (
    <div className="Blocked">
        <Header/>
          <h1 className="header">Searched Users:</h1>
          <table className="styled-table">
            <tbody>
              {list.map((user) => {
                return (
                  <React.Fragment>
                    <tr>
                      <td className="username">{user}</td>
                      <td><Button onClick={() => like(user)}>Like</Button></td>
                      <td><Button onClick={() => block(user)}>Block</Button></td>
                      <td><Button onClick={() => gotoProfile(user)}>View Profile</Button></td>
                    </tr>
                  </React.Fragment>
                )
              })}
            </tbody>
          </table>
        <Footer/>
    </div>
  );
}

export default SearchResult;
