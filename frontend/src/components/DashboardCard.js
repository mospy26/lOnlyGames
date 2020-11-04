import React, { Component } from 'react';
import "../styles/DashboardCard.css";

const DashboardCard = () => {
    return (
        <div className="tinder">

            <div className="tinder--status">
                <i className="fa fa-remove"></i>
                <i className="fa fa-heart"></i>
            </div>

            <div className="tinder--card">
                {/* <img src="https://placeimg.com/600/300/arch"> */}
                <h3>Demo card 5</h3>
                <p>This is a demo for Tinder like swipe cards</p>
            </div>

            <div className="tinder--buttons">
                <button id="nope"><i className="fa fa-remove"></i></button>
                <button id="love"><i className="fa fa-heart"></i></button>
            </div>
        </div>
    )
}

export default DashboardCard;