import Registration from "./Components/Registration";
import Login from "./Components/Login";
import { Routes, Route, BrowserRouter as Router, Link } from "react-router-dom";
import "./App.css";
import Home from "./Components/Home";
import Dashboard from "./Components/Dashboard";
import Navbar from "./Components/Navbar";

const App = () => {
    return (
        <div className="App h-screen bg-gray-700 flex items-center justify-center">
            <Navbar/>
            <Router>
                <Routes>
                    <Route path="/Registration" element={<Registration />} />
                    <Route path="/Login" element={<Login />} />
                    <Route path="/Dashboard" element={<Dashboard />} />
                    <Route path="/" element={<Home />} />
                </Routes>
            </Router>
        </div>
    );
};

export default App;
