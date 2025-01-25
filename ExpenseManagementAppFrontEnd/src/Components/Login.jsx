import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
// import "./Auth.css";

const Login = () => {
    const [credentials, setCredentials] = useState({
        email: "",
        password: "",
    });

    const handleChange = (e) => {
        setCredentials({ ...credentials, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/login", credentials);
            alert(response.data.message);
        } catch (error) {
            console.error("Error during login:", error);
            alert("Login failed.");
        }
    };

    return (
        <div className="auth-container">
            <form onSubmit={handleSubmit} className="auth-form">
                <h2>Login</h2>
                <label>Email:</label>
                <input
                    type="email"
                    name="email"
                    value={credentials.email}
                    onChange={handleChange}
                    required
                />
                <br></br>
                <label>Password:  </label>
                <input
                    type="password"
                    name="password"
                    value={credentials.password}
                    onChange={handleChange}
                    required
                />
                <br></br>
                <button type="submit">Login</button>
            </form>
            <div>
                <p>
                Don't have an account?{" "}
                <Link to="/Registration" style={{ marginLeft: "5px", textDecoration: "none" }}>
                    Register
                </Link>
                </p>
                <Link to="/" style={{ marginLeft: "5px", textDecoration: "none" }}>  Forgot Password? </Link>             
            
            </div>
        </div>
    );
};

export default Login;
