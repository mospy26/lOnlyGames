import React, { useState } from 'react'
import Header from './Header'
import Footer from './Footer'
import "../styles/Profile.css"
import { Button, FormGroup, FormControl } from "react-bootstrap";
import ProfileCard from "./ProfileCard"


const Profile = () => {

    const [fName, setFname] = useState()
    const [lName, setLname] = useState()
    const [bio, setBio] = useState()
    const [discord, setDiscord] = useState()
    const [steam, setSteam] = useState()
    
    return (
        <div>
            <Header />
            <ProfileCard/>
            <div className='profile_container'>
                <div className='image_container'>
                    <img src='https://i.picsum.photos/id/612/200/200.jpg?hmac=HbIkwJ0QBqhSlGTi3bnF4JFTp9BntF-teQZUQhpqWyM' alt='image'></img>
                </div>
                <div className='form_container'>
                    <form >
                        <label>First Name</label>
                        <FormGroup controlId="fName">
                        <FormControl
                            autoFocus
                            type="text"
                            value={fName}
                            onChange={e => setFname(e.target.value.trim())}
                        />
                        </FormGroup>

                        <label>Last Name</label>
                        <FormGroup controlId="lName">
                        <FormControl
                            value={lName}
                            onChange={e => setLname(e.target.value.trim())}
                            type="text"
                        />
                        </FormGroup>

                        <label>Bio</label>
                        <FormGroup controlId="bio">
                        <FormControl
                            value={bio}
                            onChange={e => setBio(e.target.value.trim())}
                            type="text"
                        />
                        </FormGroup>

                        <label>Discord ID</label>
                        <FormGroup controlId="discord">
                        <FormControl
                            value={discord}
                            onChange={e => setDiscord(e.target.value.trim())}
                            type="text"
                        />
                        </FormGroup>

                        <label>Steam ID</label>
                        <FormGroup controlId="steam">
                        <FormControl
                            value={steam}
                            onChange={e => setSteam(e.target.value.trim())}
                            type="text"
                        />
                        </FormGroup>


                        <Button block type="submit" className="save-btn">
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