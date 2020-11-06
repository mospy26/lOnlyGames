import React, { useState } from 'react';
import {NavLink, useHistory} from 'react-router-dom'
import { Button, FormGroup, FormControl } from "react-bootstrap";
import "../styles/Signup.css";
import logo from '../resources/logo.svg';
import axios from 'axios';



function Login() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [password2, setPassword2] = useState("")
    const [steamId, setSteamId] = useState("")
    const history = useHistory()

    function validateForm() {
        return username.length > 0 && password.length > 0 && password2.length > 0 && password===password2;
    }

    function validatePassword() {
        if (password.length>0 && password2.length>0 && password!==password2){
            return <p>Passwords do not match</p>
        }
    }

    function uservalidate(val) {

        if (username.length > 0 && !val) {
            document.getElementById('userexist').style.display = 'block';
    }
        else if(username.length > 0 && val){
            document.getElementById('userexist').style.display = 'none';
        }
    }
    function handleSubmit(event) {
    event.preventDefault();
    
    const url = '/auth/register'
    const payload = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    };

    axios.post(url, { username, password, steamId })
        .then(res =>{
            if (res){
                console.log(res.data.result)
                localStorage.setItem("token", res.data.result);
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
                uservalidate(true)

                axios.get('/users')
                      .then(response => {
                          if (response.status == 200){
                            localStorage.setItem('user', JSON.stringify(response.data.result))
                          }
                          console.log("Local storage has been updated")
                      })
                      .then(() => { 
                        history.push('/profile');
                    })
            }
        })
        .catch(err => {
            console.log(err.response)
            uservalidate(false)
        })

    // fetch(url, payload)
    //     .then(async response => {
    //         const data = response.json()
    //         console.log(response)
    //         if (response.ok){
    //             
    //         } 
            
    //     })
    }

    return (
        <div className="wrapper">
            <div className='logo'>
                <NavLink to='/' exact><img src={logo} /></NavLink>
            </div>
            <div className='welcome'>
                <h1>Welcome!</h1>
            </div>
            <div className='message'>
                <p>Please fill out the form in order to sign up</p>
            </div>
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
                <div id="userexist">User already exists</div>
                </FormGroup>
                <label>Password</label>
                <FormGroup controlId="password">
                <FormControl
                    value={password}
                    onChange={e => setPassword(e.target.value.trim())}
                    type="password"
                />
                </FormGroup>
                <label>Re-enter Password</label>
                <FormGroup controlId="password">
                <FormControl
                    value={password2}
                    onChange={e => setPassword2(e.target.value.trim())}
                    type="password"
                />
                </FormGroup>
                <label>Steam Id</label>
                <FormGroup controlId="password">
                <FormControl
                    value={steamId}
                    onChange={e => setSteamId(e.target.value.trim())}
                    type="text"
                />
                </FormGroup>
                <div className="passwordmatch">{validatePassword()}</div>
                <Button block disabled={!validateForm()} type="submit" className="signup-btn">
                Sign up
                </Button>
            </form>
            </div>
        </div>
      );
};

export default Login;