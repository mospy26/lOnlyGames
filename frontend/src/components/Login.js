import React, { useState } from 'react';
import {NavLink} from 'react-router-dom'
import { Button, FormGroup, FormControl } from "react-bootstrap";
import "../styles/Login.css";
import logo from '../resources/logo.png'


function Login() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    function validateForm() {
        return username.length > 0 && password.length > 0;
      }
    
      function handleSubmit(event) {
        event.preventDefault();
        
        const url = 'http://localhost/api/v1/auth/login'
        const payload = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        };

        fetch(url, payload)
            .then(async response => {
                const data = response.json()
                console.log(data)
            })


      }
    
    return (
        <div className="wrapper">
            <div className='logo'>
                <NavLink to='/' exact><img src={logo} /></NavLink>
            </div>
            <div className="Login">
            <form onSubmit={handleSubmit}>
                <label>Username</label>
                <FormGroup controlId="username">
                <FormControl
                    autoFocus
                    type="text"
                    value={username}
                    onChange={e => setUsername(e.target.value)}
                />
                </FormGroup>
                <label>Password</label>
                <FormGroup controlId="password">
                <FormControl
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    type="password"
                />
                </FormGroup>
                <Button block disabled={!validateForm()} type="submit" className="login-btn">
                Login
                </Button>
                <div className='signup'>Need an account? <a href="/about"> Register Now</a></div>
            </form>
            </div>
        </div>
      );
};

export default Login;