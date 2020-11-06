import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import './index.css';
import App from './App';

axios.defaults.baseURL = 'http://localhost/api/v1'
axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
// axios.defaults.headers.common['Content-Type'] = 'application/json'


ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);


