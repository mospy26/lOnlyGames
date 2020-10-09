import React, { Component } from 'react';
import Header from './Header'
import Footer from './Footer'
import logo from '../resources/logo-photo-with-text.png'
import "../styles/Home.css"
import {NavLink} from 'react-router-dom'

class Home extends Component {

    render() {
        return(
            <div className="background">
                {/* <Header /> */}
                <img src={logo} alt="logo" className="logo-img"/>

                <div className="banner-text">
                    <h1>Welcome to lOnlyGames</h1>
                    <p>
                        lOnlyGames is a social gaming collaboration platform, 
                        aimed at connecting users with similar gaming interests. 
                        It is a platform that allows a user to find other players based on characteristics such as games played, 
                        type of gamer, skills in a particular game, location and availability
                    </p>
                </div>

                <div className="button-container">
                    <NavLink to='/about' exact activeClassName='active'>
                        <button>Sign In</button>
                    </NavLink>
                    <NavLink to='/profile' exact activeClassName='active'>
                        <button>Sign Up</button>
                    </NavLink>
                </div>

                <Footer />
            </div>
        )
    }
}

export default Home;