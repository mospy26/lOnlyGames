import React, { useState, useEffect } from 'react';
import {
  Card,
  CardBody,
  CardFooter,
  Row,
  Col,
} from "reactstrap";
import Header from "../Header"
import Footer from "../Footer"
import Availabilities from "./AvailabilityProfileDetails"
import axios from 'axios'

import "../../styles/ProfileCard.css"

const OthersProfileCard = (props) => {

  const [username, setUsername] = useState()
  const [firstName, setFname] = useState()
  const [lastName, setLname] = useState()
  const [bio, setBio] = useState()
  const [discordId, setDiscord] = useState()
  const [steamId, setSteam] = useState()
  const [avatarURL, setAvatarURL] = useState()
  const [gamesList, setGamesList] = useState([])

  useEffect(() => {
      const url = '/users/profile?username=' + props.match.params.id
      axios.get(url)
          .then(res => {
              if (res.status == 200) {
                  setFname(res.data.result.firstName)
                  setLname(res.data.result.lastName)
                  setDiscord(res.data.result.discordId === null ? "NaN" : res.data.result.discordId)
                  setSteam(res.data.result.steamId === null ? "NaN" : res.data.result.steamId)
                  setBio(res.data.result.bio)
                  setUsername(res.data.result.username)
                  setAvatarURL(res.data.result.avatarURL)
              }
          })
          .catch(err => {
              console.log(err)
          })
  }, [])

  useEffect(() => {
      const url = '/users/games'

      axios.get(url)
          .then(res => {
              if (res.status == 200) {
                setGamesList(res.data.result)
              }
          })
          .catch(err => {
              console.log(err)
          })
  }, [])

  return (
    <>
    <Header/>
      <div className="profile_container_others">
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
                <h5 className="title-name">{firstName} {lastName}<br/> ({username})</h5>
              </a>
            </div>
            <p className="description text-center">
              {bio}
            </p>
            <hr/>
            <br/>
            <h3>Availabilities</h3>
            <Availabilities username={props.match.params.id}/>
            <hr/>
            <br/>
          </CardBody>
          <CardBody>
            <div className="author">
            </div>
              {gamesList.map((item) => {
                return (
                <>
                <img className="game-icon" src={item.game.iconURL}/>
                <p className="description text-center"> {item.statistics} </p>
                </>
                );
              })}
          </CardBody>
          <CardFooter>
            <hr />
            <div className="button-container">
              <h3>Gaming Social Media Details</h3>
              <Row>
                <Col className="ml-auto" lg="6" md="6" xs="6">
                  <h5> {discordId} </h5>
                  <img className="avatar-icon" src={require("../../resources/discord.png")} />
                </Col>
                <Col className="ml-auto mr-auto" lg="6" md="6" xs="6">
                  <h5>{steamId} </h5>
                  <img className="avatar-icon" src={require("../../resources/steam.png")} />
                </Col>
              </Row>
            </div>
          </CardFooter>
        </Card>
      </div>
      <Footer/>
    </>
  )
}

export default OthersProfileCard;