import React, { useState } from 'react';
import {
  Card,
  CardBody,
  CardFooter,
  Row,
  Col,
} from "reactstrap";

import "../styles/ProfileCard.css"

const ProfileCard = () => {

  const [steamID] = useState('stemoo')
  const [discordID] = useState('discoo')
  const [bio] = useState("I am Nabeel, I love CSS")


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
                <h5 className="title">Nabeel Khan</h5>
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
                  <h5> {discordID} </h5>
                  <img className="avatar-icon" src={require("../resources/discord.png")} />
                </Col>
                <Col className="ml-auto mr-auto" lg="6" md="6" xs="6">
                  <h5>{steamID} </h5>
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