// import React, { useState } from "react";
import Registration from "./Components/Registration";
import Login from "./Components/Login";
import { Routes, Route, BrowserRouter as Router } from "react-router-dom";
import "./App.css";
import Home from "./Components/Home";
import Dashboard from "./Components/Dashboard";
import Account from "./Components/Account.jsx";

const App = () => {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/Account" element={<Account/>} />
                    <Route path="/Registration" element={<Registration />} />
                    <Route path="/Login" element={<Login />} />
                    <Route path="/Dashboard" element={<Dashboard />} />
                    <Route path="/" element={<Home/>} />
                </Routes>
            </Router>
        </div>
    );
};

export default App;
