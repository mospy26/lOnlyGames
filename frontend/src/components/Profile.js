import React, { useState } from 'react'
import Header from './Header'
import Footer from './Footer'
import "../styles/Profile.css"
import { Button, FormGroup, FormControl } from "react-bootstrap";
import ProfileCard from "./ProfileCard"


const Profile = () => {

    const [username, setUsername] = useState('mfulwala')
    const [fName, setFname] = useState('Mustafa')
    const [lName, setLname] = useState('Fulwala')
    const [bio, setBio] = useState('This is my bio')
    const [discord, setDiscord] = useState('discooo')
    const [steam, setSteam] = useState('steamoo')
    
    return (
        <div>
            <Header />
            <div className='profile_container'>
            <ProfileCard/>
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
                    <form >
                        <div className='row inner'>
                            <label>First Name</label>
                            <FormGroup className='form__group__profile' controlId="fName">
                            <FormControl
                                autoFocus
                                type="text"
                                value={fName}
                                onChange={e => setFname(e.target.value)}
                            />
                            </FormGroup>

                            <label>Last Name</label>
                            <FormGroup className='form__group__profile' controlId="lName">
                            <FormControl
                                value={lName}
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
                                value={discord}
                                onChange={e => setDiscord(e.target.value)}
                                type="text"
                            />
                            </FormGroup>

                            <label>Steam ID</label>
                            <FormGroup className='form__group__profile' controlId="steam">
                            <FormControl
                                value={steam}
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