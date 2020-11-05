import React, { useState, useMemo, useEffect } from 'react';
import "../styles/DashboardCard.css";
import TinderCard from 'react-tinder-card'
import Header from './Header'
import Footer from './Footer'
import { Card } from 'reactstrap'
import axios from 'axios'

const db = [
    {
        name: 'Richard Hendricks',
        url: 'https://i.imgur.com/kKfFMgv.png'
    },
    {
        name: 'Erlich Bachman',
        url: 'https://i.pinimg.com/originals/dc/cd/99/dccd99e1be1fa3f4f1543aaa9cbfc810.webp'
    },
    {
        name: 'Monica Hall',
        url: 'https://i.imgur.com/SRxUakd.png'
    },
    {
        name: 'Jared Dunn',
        url: 'https://i.imgur.com/NfS3y42.png'
    },
    {
        name: 'Dinesh Chugtai',
        url: 'https://i.imgur.com/fvl0dqi.png'
    }
]

const alredyRemoved = []
let charactersState = db // This fixes issues with updating characters state forcing it to use the current state and not the state that was active when the card was created.

const DashboardCard = () => {
    const [characters, setCharacters] = useState(db)
    const [lastDirection, setLastDirection] = useState()

    const [users, setUsers] = useState([])


    useEffect(() => {

        const url = '/users/matches'

        axios.get(url)
            .then(res => {
                if (res.status == 200) {
                    setUsers(res.data.result)
                }
            })
            .catch(err => {
                console.log(err.response)
            })
        return () => {
        }
    }, [])


    const childRefs = useMemo(() => Array(db.length).fill(0).map(i => React.createRef()), [])

    const swiped = (direction, nameToDelete) => {
        console.log('removing: ' + nameToDelete)
        setLastDirection(direction)
        alredyRemoved.push(nameToDelete)
    }

    const outOfFrame = (name) => {
        console.log(name + ' left the screen!')
        charactersState = charactersState.filter(character => character.name !== name)
        setCharacters(charactersState)
    }

    const swipe = (dir) => {
        const cardsLeft = characters.filter(person => !alredyRemoved.includes(person.name))
        if (cardsLeft.length) {
            const toBeRemoved = cardsLeft[cardsLeft.length - 1].name // Find the card object to be removed
            const index = db.map(person => person.name).indexOf(toBeRemoved) // Find the index of which to make the reference to
            alredyRemoved.push(toBeRemoved) // Make sure the next card gets removed next time if this card do not have time to exit the screen

            if (childRefs[index].current){
                childRefs[index].current.swipe(dir) // Swipe the card!
            }
        }
    }

    return (
        <div>
        <Header/>
        <div className='toplevel'>
            <h1>Choose your gamer</h1>
            <div className='cardContainer'>
                {users.map((gameuser, index) =>
                    gameuser.map((character, index2) =>
                    <TinderCard ref={childRefs[index]} className='swipe' key={character.name} onSwipe={(dir) => swiped(dir, character.user.username)} onCardLeftScreen={() => outOfFrame(character.user.username)}>
                        <div style={{ backgroundImage: 'url('+ character.user.url + ')' }} className='card__tinder'>
                        <h3 className="dashboard-card-username">{character.user.username}</h3>
                        </div>
                        <Card className="swipe">
                            I play {character.game.name}! <br/>
                            {/* <img className="game-avatar" src={"https://upload.wikimedia.org/wikipedia/en/0/0b/Dota_2_%28Steam_2019%29.jpg"}/> */}
                        </Card>
                    </TinderCard>
                ))}
                
            </div>
            <div className='buttons'>
                <button className="buttons-dislike" onClick={() => swipe('left')}>Dislike</button>
                <button className="buttons-like" onClick={() => swipe('right')}>Like</button>
            </div>
            {/* {lastDirection ? <h2 key={lastDirection} className='infoText'>You swiped {lastDirection}</h2> : <h2 className='infoText'>Swipe a card or press a button to get started!</h2>}     */}
            </div>
        <Footer/>
        </div>
    )
}

export default DashboardCard;