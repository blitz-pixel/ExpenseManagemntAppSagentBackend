// import React, { useState } from "react";
import Registration from "./Components/Registration";
import Login from "./Components/Login";
import { Routes, Route, BrowserRouter as Router } from "react-router-dom";
import "./App.css";
import Home from "./Components/Home";
import Dashboard from "./Components/Dashboard";
import Expense from "./Components/Expense"
import Revenue from "./Components/Revenue"
// import Navbar from "./Components/Navbar"

const App = () => {
    return (
        <div className="App">
            {/* <Navbar/> */}
            <Router>
                <Routes>
                    <Route path="/Registration" element={<Registration />} />
                    <Route path="/Login" element={<Login />} />
                    <Route path="/Dashboard" element={<Dashboard />} />
                    <Route path="/Expense" element={<Expense/>}/>
                    <Route path="/Revenue" element={<Revenue/>}/>
                    <Route path="/" element={<Home/>} />
                </Routes>
            </Router>
        </div>
    );
};

export default App;
