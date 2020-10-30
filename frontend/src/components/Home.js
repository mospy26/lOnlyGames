import React, { Component } from 'react';
import logo from '../resources/logo-photo-with-text.png'
import "../styles/Home.css"
import {NavLink} from 'react-router-dom'
import YoutubeBackground from 'react-youtube-background'

const Home = () => {
    return(
        <React.Fragment>
            <YoutubeBackground videoId={"lRTtMcx6rSM"}>
                <div className="background" id="background-spacing">
                    {/* <Header /> */}
                    <img src={logo} alt="logo" className="logo-img"/>

                    <div className="banner-text">
                        <h1>Match with other gamers today!</h1>
                    </div>

                    <div className="button-container">
                        <NavLink to='/login' className='button' exact activeClassName='active'>
                            <button>Sign In</button>
                        </NavLink>
                        <NavLink to='/signup' className='button' exact activeClassName='active'>
                            <button>Sign Up</button>
                        </NavLink>
                    </div>

                    {/* <Footer /> */}
                </div>
            </YoutubeBackground>

        </React.Fragment>
        
    )
}

export default Home;