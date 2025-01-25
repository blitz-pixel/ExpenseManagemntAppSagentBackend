import { Link } from "react-router-dom";

const Home = () => {
    return (<div>
    <h1>Home Page</h1>
        <nav>
            <Link to="/Registration">
                <button>Register</button>
            </Link>
            <Link to="/login">
                <button>Login</button>
            </Link>
        </nav>
    </div>)
        
};

export default Home;