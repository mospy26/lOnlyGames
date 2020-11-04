import React, {useEffect, useState} from 'react';
import axios from 'axios'
import {BrowserRouter as Router, Switch, Route,} from "react-router-dom";

import './styles/App.css';
import Home from './components/Home'
import Matches from './components/Matches'
import Availability from './components/Availability'
import Profile from './components/Profile'
import About from './components/About'
import Login from './components/Login'
import Signup from './components/Signup'
import DashboardCard from './components/DashboardCard';




function App() {

  // useEffect(() => {
  //   const config = {
  //     headers: {
  //       Authorization: 'Bearer ' + localStorage.getItem('token')
  //     }
  //   };

  //   // axios.get('/users', config)
  //   //   .then(res => {
  //   //     console.log(res)
  //   //   })
  //   //   .catch(err => {
  //   //     console.log("ERROR" + err)
  //   //   })
  //    const payload = {
  //           method: 'GET',
  //           headers: { Authorization: 'Bearer ' + localStorage.getItem('token')},
  //       };

  //       fetch('http://localhost/api/v1/users', payload)
  //         .then(response => {
  //           console.log(response)
  //         })
  //         .catch(err =>{
  //           console.log(err)
  //         })
  
  // }, []);

  return (
    <Router>
      <div className="App">
        <Switch>
          <Route exact path="/availability" component={Availability}/>
          <Route exact path="/matches" component={Matches}/>
          <Route exact path="/about" component={About}/>
          <Route exact path="/profile" component={Profile}/>
          <Route exact path="/login" component={Login}/>
          <Route exact path="/signup" component={Signup}/>
          <Route exact path="/card" component={DashboardCard}/>
          <Route path="/" component={Home}/>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
