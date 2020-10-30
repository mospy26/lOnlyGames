import React, { useEffect} from 'react';
import logo from '../resources/logo-photo-with-text.png'
import {NavLink} from 'react-router-dom'
import YoutubeBackground from 'react-youtube-background'

import "../styles/Home.css"
import Login from './Login'

const Home = () => {
   
    useEffect(() => {
        const config = {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token')
          }
        };
    
        // axios.get('/users', config)
        //   .then(res => {
        //     console.log(res)
        //   })
        //   .catch(err => {
        //     console.log("ERROR" + err)
        //   })
         const payload = {
                method: 'GET',
                headers: { Authorization: 'Bearer ' + localStorage.getItem('token')},
            };
    
            fetch('http://localhost/api/v1/users', payload)
              .then(response => {
                console.log(response)
              })
              .catch(err =>{
                console.log(err)
              })
      
      }, [Login]);

    return(
        <React.Fragment>
            <YoutubeBackground videoId={"lRTtMcx6rSM"}>
                <div className="background" id="background-spacing">
                    {/* <Header /> */}
                    <img src={logo} alt="logo" className="logo-img"/>

                    <div className="banner-text">
                        <h1>Match with other gamers today!</h1>
                    </div>

                    <div className="button-container">
                        <NavLink to='/login' className='button' exact activeClassName='active'>
                            <button>Sign In</button>
                        </NavLink>
                        <NavLink to='/profile' className='button' exact activeClassName='active'>
                            <button>Sign Up</button>
                        </NavLink>
                    </div>

                    {/* <Footer /> */}
                </div>
            </YoutubeBackground>

        </React.Fragment>
        
    )
}

export default Home;