import React from 'react';
import { BrowserRouter as Router, Switch, Route, } from "react-router-dom";

import './styles/App.css';
import Home from './components/Home'
import Matches from './components/Matches'
import Availability from './components/Availability/Availability'
import Profile from './components/Profile'
import About from './components/About'
import Login from './components/Login'
import Signup from './components/Signup'
import DashboardCard from './components/DashboardCard';
import Blocked from './components/Blocked';
import OthersProfileCard from './components/OthersProfileCard/OthersProfileCard';
import SearchResult from './components/SearchResult.js';
import PrivateRoute from './components/PrivateRoute';
import OthersLiked from './components/OthersLiked';



function App() {

  return (
    <>
    <Router>
      <link
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        rel="stylesheet"
      />
      <div className="App">
        <Switch>
          <PrivateRoute exact path="/availability" component={Availability} />
          <PrivateRoute exact path="/blocked" component={Blocked} />
          <PrivateRoute exact path="/matches" component={Matches} />
          <Route exact path="/about" component={About} />
          <PrivateRoute exact path="/profile" component={Profile} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/signup" component={Signup} />
          <PrivateRoute exact path="/home" component={DashboardCard} />
          <PrivateRoute exact path="/liked" component={OthersLiked} />
          <PrivateRoute exact path="/others/:id" component={OthersProfileCard} />
          <PrivateRoute exact path="/searchresults/:id" component={SearchResult} />
          <Route path="/" component={Home} />
        </Switch>
      </div>
    </Router>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </>
  );
}

export default App;
