import React, { useState } from 'react';
import Header from './Header'
import Footer from './Footer'
import { Button, FormGroup, FormControl } from "react-bootstrap";
import "../styles/Login.css";

function Login() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    function validateForm() {
        return username.length > 0 && password.length > 0;
      }
    
      function handleSubmit(event) {
        event.preventDefault();
        console.log(username, password)
      }
    
    return (
        <div className="wrapper">
            <Header />
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
            <Footer />
        </div>
      );
};

export default Login;