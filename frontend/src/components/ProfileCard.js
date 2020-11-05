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


  return (
    <>
      <div className="content">
        <Card className="card-user">
          <div className="image">
            <img
              alt="..."
              src={"https://i.picsum.photos/id/612/200/200.jpg?hmac=HbIkwJ0QBqhSlGTi3bnF4JFTp9BntF-teQZUQhpqWyM"}
            />
          </div>
          <CardBody>
            <div className="author">
              <a onClick={(e) => e.preventDefault()}>
                <img
                  alt="..."
                  className="avatar border-gray"
                  src={"https://i.picsum.photos/id/612/200/200.jpg?hmac=HbIkwJ0QBqhSlGTi3bnF4JFTp9BntF-teQZUQhpqWyM"}
                />
                <h5 className="title">{firstName} {lastName}</h5>
              </a>
            </div>
            <p className="description text-center">
              {bio}
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