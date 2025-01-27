import  { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";


const Registration = () => {
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: "",
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/register", formData);
            alert(response.data.message);
        } catch (error) {
            console.error("Error during registration:", error);
            alert("Registration failed.");
        }
    };

    return (
        <div className="auth-container">
            <form onSubmit={handleSubmit} className="auth-form">
                <h2>Register</h2>
                <label>Username:{" "}</label>
                <input
                    type="text"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    required
                />
                <br></br>
                <label>Email: </label>
                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />
                <br></br>
                <label>Password: </label>
                <input
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />
                <br></br>
                <label>Confirm Password: </label>
                <input
                    type="password"
                    name="Cpassword"
                    // value={formData.password}
                    // onChange={handleChange}
                    required
                />
                <br></br>
                <button type="submit">Register</button>
            </form>
            <p>
                Aldready have an account ?{" "}
                <Link to="/Login" style={{ marginLeft: "5px", textDecoration: "none" }}>
                    Login
                </Link>
            </p>
        </div>
    );
};

export default Registration;