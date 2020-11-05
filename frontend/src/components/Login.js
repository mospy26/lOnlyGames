import React, { useState } from 'react';
import {NavLink, useHistory} from 'react-router-dom'
import axios from 'axios'
import { Button, FormGroup, FormControl } from "react-bootstrap";
import "../styles/Login.css";
import logo from '../resources/logo.png'


function Login() {
    const history = useHistory()
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")


    function validateForm() {
        return username.length > 0 && password.length > 0;
      }
    
      function handleSubmit(event) {
        event.preventDefault();
        
        const url = '/auth/login'

        axios.post(url, { username, password })
            .then(res => {
                if(res.status == 200){
                    localStorage.setItem('token', res.data.result)

                    // make request to get user info using the token and save in local storage

                    axios.get('/users')
                      .then(response => {
                          if (response.status == 200){
                            localStorage.setItem('user', JSON.stringify(response.data.result))
                          }
                      })
                      .then(history.push('/profile')) 
                }
            })
            .catch(err => {
                console.log(err.response)
                if(err.response.status == 400){
                    document.getElementById('incorrect__pwd').style.display = 'block';
                }
            })
      }
    
    return (
        <div className="wrapper">
            <div className='logo'>
                <NavLink to='/' exact><img src={logo} /></NavLink>
            </div>
            <div id="incorrect__pwd">Incorrect Username or Password</div>
            <div className="Login">
            <form onSubmit={handleSubmit}>
                <label>Username</label>
                <FormGroup controlId="username">
                <FormControl
                    autoFocus
                    type="text"
                    value={username}
                    onChange={e => setUsername(e.target.value.trim())}
                />
                </FormGroup>
                <label>Password</label>
                <FormGroup controlId="password">
                <FormControl
                    value={password}
                    onChange={e => setPassword(e.target.value.trim())}
                    type="password"
                />
                </FormGroup>
                <Button block disabled={!validateForm()} type="submit" className="login-btn">
                Login
                </Button>
                <div className='signup'>Need an account? <a href="/signup"> Register Now</a></div>
            </form>
            </div>
    </div>
      );
};

export default Login;