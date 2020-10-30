import React, { useState } from 'react';
import {NavLink} from 'react-router-dom'
import { Button, FormGroup, FormControl } from "react-bootstrap";
import "../styles/Signup.css";
import logo from '../resources/logo.svg'


function Login() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [password2, setPassword2] = useState("")

    function validateForm() {
        return username.length > 0 && password.length > 0;
    }
    function validatePassword() {
        if (password.length>0 && password2.length>0 && password!==password2){
            return <p>Passwords do not match</p>
        }
    }
    function handleSubmit(event) {
    event.preventDefault();
    
    const url = 'http://localhost/api/v1/auth/register'
    const payload = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    };

    fetch(url, payload)
        .then(async response => {
            const data = response.json()
            console.log(data)
            if (data) {
                localStorage.setItem("user", JSON.stringify(response.data));
              } 
            
        })

    return


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
                <label>Re-enter Password</label>
                <FormGroup controlId="password">
                <FormControl
                    value={password2}
                    onChange={e => setPassword2(e.target.value)}
                    type="password"
                />
                </FormGroup>
                <div className="passwordmatch">{validatePassword()}</div>

                <Button block disabled={!validateForm() && !validatePassword()} type="submit" className="signup-btn">
                Sign up
                </Button>
                {/* <div className='signup'>Need an account? <a href="/about"> Register Now</a></div> */}
            </form>
            </div>
        </div>
      );
};

export default Login;