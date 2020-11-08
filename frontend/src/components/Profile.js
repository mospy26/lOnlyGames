import React, { useState, useEffect } from 'react'
import { Button, FormGroup, FormControl } from "react-bootstrap";
import axios from 'axios'

import Header from './Header'
import Footer from './Footer'
import ProfileCard from "./ProfileCard"

import "../styles/Profile.css"

const Profile = () => {

    const [username, setUsername] = useState()
    const [firstName, setFname] = useState()
    const [lastName, setLname] = useState()
    const [bio, setBio] = useState()
    const [discordId, setDiscord] = useState()
    const [steamId, setSteam] = useState()
    const [avatarURL, setAvatarURL] = useState()
    const [gamesList, setGamesList] = useState([])

    useEffect(() => {
        const user = JSON.parse(localStorage.getItem('user'))
        
        if (user != null) {
            setUsername(user.username)
            setFname(user.firstName)
            setLname(user.lastName)
            setDiscord(user.discordId)
            setSteam(user.steamId)
            setBio(user.bio)
            setAvatarURL(user.avatarURL)
            return
        }

        const url = '/users'

        axios.get(url)
            .then(res => {
                if (res.status == 200) {
                    localStorage.setItem('user', JSON.stringify(res.data.result))
                    setFname(res.data.result.firstName)
                    setLname(res.data.result.lastName)
                    setDiscord(res.data.result.discordId)
                    setSteam(res.data.result.steamId)
                    setBio(res.data.result.bio)
                    setAvatarURL(user.avatarURL)
                }
            })
            .catch(err => {
                console.log(err)
            })
    }, [])

    const fetchGames = (displayBanner) => {

        const urlGames = '/users/games'

        axios.get(urlGames)
            .then(res => {
                if (res.status == 200) {
                    localStorage.setItem('games', JSON.stringify(res.data.result))
                    setGamesList(res.data.result)
                    console.log(gamesList)
                    if (displayBanner) {
                        const alert = document.querySelector('#alert')
                        alert.classList.replace('hide', 'show')
                    }

                }
            })
            .catch(err => {
                console.log(gamesList)
                console.log(err.response)
            })
    }
    
    useEffect(() => {
        // const games = JSON.parse(localStorage.getItem('games'))
        // if (games != null) {
        //     setGamesList(games)
        //     console.log(games)
        //     return
        // }

        fetchGames(false)
    }, [])


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
                    localStorage.setItem('user', JSON.stringify(res.data.result))
                    fetchGames(true)
                    console.log(gamesList)
                }
            })
            .then(() =>{
                console.log(alert.classList)
            })
                
            .catch(err => {
                console.log(err.response)
            })
    }

    function clearAlert(){

        const show = document.querySelector('.show')

        if(show){
            show.classList.replace('show', 'hide')
        }

    }

    return (
        <div>
            <Header />
            <div className='profile_container'>
                <ProfileCard username={username} firstName = {firstName} lastName = {lastName} discordId = {discordId} steamId = {steamId} bio = {bio} avatarURL = {avatarURL} gamesList = {gamesList}/>
                <div className='form_container'>
                    <form onSubmit={handleSubmit} oninput={clearAlert()}>
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

                        <button type="submit" className='save__btn' >
                            Save
                        </button>
                        <div className='hide green' color='green' id='alert'>
                            Your profile has been updated!
                        </div>


                        {/* <Button type="submit" className="save-btn">
                            Save
                        </Button> */}
                    </form>
                </div>
            </div>
            <Footer />
        </div>
    );
};


export default Profile; 