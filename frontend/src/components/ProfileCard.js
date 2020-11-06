import React, { useState } from 'react';
import {
  Card,
  CardBody,
  CardFooter,
  Row,
  Col,
} from "reactstrap";

import "../styles/ProfileCard.css"

const ProfileCard = (props) => {

  const steamId = props.steamId
  const discordId = props.discordId
  const bio = props.bio
  const firstName = props.firstName
  const lastName = props.lastName
  const avatarURL = props.avatarURL
  const gamesList = props.gamesList


  return (
    <>
      <div className="content">
        <Card className="card-user">
          <div className="image">
            <img
              alt="..."
              src={avatarURL}
            />
          </div>
          <CardBody>
            <div className="author">
              <a onClick={(e) => e.preventDefault()}>
                <img
                  alt="..."
                  className="avatar border-gray"
                  src={avatarURL}
                />
                <h5 className="title">{firstName} {lastName}</h5>
              </a>
            </div>
            <p className="description text-center">
              {bio}
            </p>
            <hr/>
            <br/>
          </CardBody>
          <CardBody>
            <div className="author">
              <h5 className="title">Games</h5>
            </div>
            <p className="description text-center">
              {gamesList.map((item) => {
                return (
                  <>
                <img className="game-icon" src={item.iconURL}/>
                <p className="right description text-center"> {"Rank 1"} </p>
                </>
                );
              })}
            </p>
          </CardBody>
          <CardFooter>
            <hr />
            <div className="button-container">
              <Row>
                <Col className="ml-auto" lg="6" md="6" xs="6">
                  <h5> {discordId} </h5>
                  <img className="avatar-icon" src={require("../resources/discord.png")} />
                </Col>
                <Col className="ml-auto mr-auto" lg="6" md="6" xs="6">
                  <h5>{steamId} </h5>
                  <img className="avatar-icon" src={require("../resources/steam.png")} />
                </Col>
              </Row>
            </div>
          </CardFooter>
        </Card>
      </div>
    </>
  )
}

export default ProfileCard;