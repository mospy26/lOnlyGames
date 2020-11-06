import React, { useState } from 'react';
import {NavLink, useHistory} from 'react-router-dom'
import logo from '../resources/logo.png'
import "../styles/Header.css"


function Header(){

    const history = useHistory()
    const [open, setOpen] = useState(false)
    const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')))

    function navSlide(e){
        e.preventDefault();
        const nav =  document.querySelector('.navbar__links');
        const navLinks = document.querySelectorAll('.navbar__links li')
        const hamburger = document.querySelector('.navbar__hamburger')

        if (!open){
            hamburger.classList.add('open')
            setOpen(!open)
        }else{
            hamburger.classList.remove('open')
            setOpen(!open)
        }

        navLinks.forEach((link, index)=>{
            link.style.animation = `navLinkFade 0.2s ease forwards ${index/7}s`;
        });

        nav.classList.toggle('nav__active');
    }

    function logout(){
        setUser()
        localStorage.clear()
        history.push('/')
    }

    const renderAuthButton = () =>{
        if (user){
            return (
                <div className="dropdown">
                    <button className="dropbtn">Hi, {user.username}</button>
                    <div className="dropdown-content">
                        <div onClick={logout}>Logout</div>
                        {/* <a href="#">Logout</a>   */}
                    </div>
                </div>
            )
        }
    }

    return (
        <div>  
            <nav className='navbar__header' >
                <div className='navbar__logo'>
                    <NavLink to='/' exact><img src={logo} /></NavLink>
                </div>
                <div className='navbar__content'>
                    <div className="navbar__links">
                            <li><NavLink to='/card' exact activeClassName='active'>Home</NavLink></li>
                            <li><NavLink to='/matches' activeClassName='active'>Matches</NavLink></li>
                            <li><NavLink to='/availability' activeClassName='active'>Available</NavLink></li>
                            <li><NavLink to='/about' activeClassName='active'>About Us</NavLink></li> 
                            <li><NavLink to='/blocked' activeClassName='active'>Blocked Users</NavLink></li> 
                            <li><NavLink to='/profile' activeClassName='active'>My Profile</NavLink></li> 
                    </div>
                    
                    {renderAuthButton()}
                    <div className="navbar__hamburger" onClick={navSlide} >
                        <div className="line"></div>
                        <div className="line"></div>
                        <div className="line"></div>
                    </div>
                </div>
            </nav>
        </div>
    );
}export default Header;