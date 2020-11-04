import React, { useState } from 'react';
import {
    Button,
    Card,
    CardHeader,
    CardBody,
    CardFooter,
    CardTitle,
    FormGroup,
    Form,
    Input,
    Row,
    Col,
} from "reactstrap";

import "../styles/ProfileCard.css"


const ProfileCard = () => {

    const [steamID , setSteamID] = useState('stemoo')
    const [discordID , setDiscordID] = useState('discoo')
    const [bio , setBio] = useState("I am Nabeel, I love CSS")


    return (
        <>
            <div className="content">
                <Row>
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
                                <h5 className="title">Chet Faker</h5>
                            </a>
                            {/* <p className="description">@chetfaker</p> */}
                        </div>
                        <p className="description text-center">
                            {bio}
                  </p>
                    </CardBody>
                    <CardFooter>
                  <hr />
                  <div className="button-container">
                    <Row>
                      <Col className="ml-auto" lg="3" md="6" xs="6">
                        <h5>
                          {discordID} <br />
                          <small>Discord ID</small>
                        </h5>
                      </Col>
                      <Col className="ml-auto mr-auto" lg="4" md="6" xs="6">
                        <h5>
                          {steamID} <br />
                          <small>Steam ID</small>
                        </h5>
                      </Col>
                    </Row>
                  </div>
                </CardFooter>   
                    </Card>
                </Row>
            </div>
        </>
    )
}

export default ProfileCard;