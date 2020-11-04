import React from 'react';
import Header from './Header'
import Footer from './Footer'
import logo from '../resources/logo.svg';
import '../styles/About.css';


function About(){
    return (
        <div className="About">
            <Header />
            <div className="container">
            <div className="container__image">
              <img src={logo} className="container__image" alt="logo" />
            </div>
              <div class="container__content">
                <div class="title">
                <h1>About Us</h1>
                </div>
                <p>lOnlyGames is a social gaming collaboration platform, aimed at connecting users with similar gaming interests.
                It is a platform that allows a user to find other players based on characteristics such as games played, type of gamer, skills in a particular game, location and availability.
                Once a particular player is selected the user will then be matched with that player and can choose to connect allowing them to share their in-game contact details such as Steam, Discord ID, in-game username.
                In conjunction secondary features include an achievements system, through Restful practices, OnlyGames will connect with external APIs, allowing the platform to display the in-game statistics of users.
                These statistics will be included for most popular games. The system will also compromise an encrypted chatting feature, allowing users to communicate with matched players.
                A user can only match with other players, if the action is also reciprocated.
                </p>
              </div>
            </div>

            <Footer/>
        </div>
    );
};

export default About;
