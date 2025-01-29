import {Navigate, useLocation} from "react-router-dom";

function Account () {
    const location = useLocation();
    const data = location.state || {};
    console.log(data.email);
    const key = localStorage.getItem("token") || null;
    console.log(key)

    if (!key) {
        return <Navigate to="/Login" />;
    }

    // if (key){
    //
    // }

    return (
        <div>
            <h1>Welcome {data.email}</h1>
        </div>
    );
}

export default Account;