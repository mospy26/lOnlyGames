import React, { useState, useEffect } from 'react'
import Header from './Header'
import Footer from './Footer'
import "../styles/Profile.css"
import { Button, FormGroup, FormControl } from "react-bootstrap";
import ProfileCard from "./ProfileCard"
import axios from 'axios'

const Profile = () => {

    const [username, setUsername] = useState()
    const [firstName, setFname] = useState()
    const [lastName, setLname] = useState()
    const [bio, setBio] = useState()
    const [discordId, setDiscord] = useState()
    const [steamId, setSteam] = useState()

    function handleSubmit(event) {
        event.preventDefault();

        const url = '/users/update'

        let config = {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem('token'),
              "Content-Type": "application/json"
            }
          }

        axios.put(url, { username, firstName, lastName, discordId, steamId, bio }, config=config)
            .then(res => {
                if (res.status == 200) {
                    console.log(res)
                }
            })
            .catch(err => {
                console.log(err.response)
                if (err.response.status == 400) {
                    document.getElementById('incorrect__pwd').style.display = 'block';
                }
            })
    }

    useEffect(() => {
        const url = '/users'

        let config = {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem('token'),
              "Content-Type": "application/json"
            }
          }

        axios.get(url, config=config)
            .then(res => {
                if (res.status == 200) {
                    setFname(res.data.result.firstName)
                    setLname(res.data.result.lastName)
                    setDiscord(res.data.result.discordId)
                    setSteam(res.data.result.steamId)
                    setBio(res.data.result.bio)
                }
            })
            .catch(err => {
                console.log(err)
                if (err.response.status == 400) {
                    document.getElementById('incorrect__pwd').style.display = 'block';
                }
            })
        return () => {
            
        }
    }, [])

    return (
        <div>
            <Header />
            <div className='profile_container'>
                <ProfileCard firstName = {firstName} lastName = {lastName} discordId = {discordId} steamId = {steamId} bio = {bio}/>
                {/* <div className='card__container'>
                    <div className='avatar'>
                        <img src='https://i.picsum.photos/id/612/200/200.jpg?hmac=HbIkwJ0QBqhSlGTi3bnF4JFTp9BntF-teQZUQhpqWyM' alt='image'></img>
                    </div>
                    <div className='username card__info'>{username}</div>
                    <div className='bio card__info'>
                        {bio}
                    </div>
                    <hr />
                    <div className='social__ids'>
                        <div className='id'>{discord}</div>
                        <div className='id'>{steam}</div>
                    </div>
                </div> */}
                <div className='form_container'>
                    <form onSubmit={handleSubmit}>
                        <div className='row inner'>
                            <label>First Name</label>
                            <FormGroup className='form__group__profile' controlId="fName">
                                <FormControl
                                    autoFocus
                                    type="text"
                                    value={firstName}
                                    onChange={e => setFname(e.target.value)}
                                />
                            </FormGroup>

                            <label>Last Name</label>
                            <FormGroup className='form__group__profile' controlId="lName">
                                <FormControl
                                    value={lastName}
                                    onChange={e => setLname(e.target.value)}
                                    type="text"
                                />
                            </FormGroup>
                        </div>

                        <div className='row'>
                            <label>Bio</label>
                            <FormGroup className='form__group__profile' controlId="bio">
                                <FormControl
                                    value={bio}
                                    onChange={e => setBio(e.target.value)}
                                    as="textarea" rows={5}
                                />
                            </FormGroup>
                        </div>

                        <div className='row ids__inner'>
                            <label>Discord ID</label>
                            <FormGroup className='form__group__profile' controlId="discord">
                                <FormControl
                                    value={discordId}
                                    onChange={e => setDiscord(e.target.value)}
                                    type="text"
                                />
                            </FormGroup>

                            <label>Steam ID</label>
                            <FormGroup className='form__group__profile' controlId="steam">
                                <FormControl
                                    value={steamId}
                                    onChange={e => setSteam(e.target.value)}
                                    type="text"
                                />
                            </FormGroup>
                        </div>

                        <Button type="submit" className="save-btn">
                            Save
                        </Button>
                    </form>
                </div>
            </div>
            <Footer />
        </div>
    );
};


export default Profile; 