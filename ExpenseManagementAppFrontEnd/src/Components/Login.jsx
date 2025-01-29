import  { useState } from "react";
import axios from "axios";
import {Link, Navigate} from "react-router-dom";
// import "./Auth.css";

const Login = () => {
    const [credentials, setCredentials] = useState({
        email: "",
        password: "",
    });

    const [redirect, setRedirect] = useState(false);

    const handleChange = (e) => {
        setCredentials({ ...credentials, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/v1/Login", credentials);
            // console.log(response.data);
            console.log("Token:", response.data);
            if (response.status === 200){
                console.log("Login successful");
                setRedirect(true)
                localStorage.setItem("token", response.data);
                // localStorage.setItem("name", credentials.email);

            }
            console.log(response.data);
            console.log(response)
            // alert("Logine successfully");
        } catch (error) {
            console.error("Error during login:", error);
            console.log(credentials);
            alert("Login failed.");
        }
    };

    if (redirect) {
       return  <Navigate to="/Account" state={{"email" : credentials.email}} />
    }

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
                Don&#39;t have an account?{" "}
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
