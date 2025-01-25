import React, { useState } from "react";
import Registration from "./Components/Registration";
import Login from "./Components/Login";
import "./App.css";

const App = () => {
    const [isRegistering, setIsRegistering] = useState(true);

    return (
        <div className="app-container">
            <div className="toggle-buttons">
                <button onClick={() => setIsRegistering(true)}>Register</button>
                <button onClick={() => setIsRegistering(false)}>Login</button>
            </div>
            {isRegistering ? <Registration /> : <Login />}
        </div>
    );
};

export default App;
