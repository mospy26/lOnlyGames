import React, { useState, useMemo } from 'react';
import "../styles/DashboardCard.css";
import TinderCard from 'react-tinder-card'
import Header from './Header'
import Footer from './Footer'

const db = [
    {
        name: 'Richard Hendricks',
        url: './img/richard.jpg'
    },
    {
        name: 'Erlich Bachman',
        url: './frontend/src/resources/logo.png'
    },
    {
        name: 'Monica Hall',
        url: './frontend/src/resources/logo.png'
    },
    {
        name: 'Jared Dunn',
        url: './frontend/src/resources/logo.png'
    },
    {
        name: 'Dinesh Chugtai',
        url: './img/richard.jpg'
    }
]

const alredyRemoved = []
let charactersState = db // This fixes issues with updating characters state forcing it to use the current state and not the state that was active when the card was created.

const DashboardCard = () => {
    const [characters, setCharacters] = useState(db)
    const [lastDirection, setLastDirection] = useState()

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
            childRefs[index].current.swipe(dir) // Swipe the card!
        }
    }

    return (
        <div>
        <Header/>
        <div className='toplevel'>
            <h1>Choose your gamer</h1>
            <div className='cardContainer'>
                {characters.map((character, index) =>
                    <TinderCard ref={childRefs[index]} className='swipe' key={character.name} onSwipe={(dir) => swiped(dir, character.name)} onCardLeftScreen={() => outOfFrame(character.name)}>
                        <div style={{ backgroundImage: 'url('+ character.url + ')' }} className='card__tinder'>
                        </div>
                        <h3>{character.name}</h3>
                    </TinderCard>
                )}
                
            </div>
            <div className='buttons'>
                <button onClick={() => swipe('left')}>Dislike</button>
                <button onClick={() => swipe('right')}>Like</button>
            </div>
            {/* {lastDirection ? <h2 key={lastDirection} className='infoText'>You swiped {lastDirection}</h2> : <h2 className='infoText'>Swipe a card or press a button to get started!</h2>}     */}
            </div>
        <Footer/>
        </div>
    )
}

export default DashboardCard;