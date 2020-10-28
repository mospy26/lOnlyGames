import React from 'react';
import './styles/App.css';
import Home from './components/Home'
import Matches from './components/Matches'
import Availability from './components/Availability'
import Profile from './components/Profile'
import About from './components/About'
import Login from './components/Login'

import {BrowserRouter as Router, Switch, Route,} from "react-router-dom";


function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route exact path="/availability" component={Availability}/>
          <Route exact path="/matches" component={Matches}/>
          <Route exact path="/about" component={About}/>
          <Route exact path="/profile" component={Profile}/>
          <Route exact path="/login" component={Login}/>
          <Route path="/" component={Home}/>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
