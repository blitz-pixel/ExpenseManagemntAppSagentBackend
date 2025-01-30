import {Navigate, useLocation} from "react-router-dom";
import {useEffect} from "react";

function Account () {
    const location = useLocation();
    const data = location.state || {};

    // console.log(data.email);
    const key = localStorage.getItem("token");
    console.log(key);


    if (key === "undefined") {
        return <Navigate to="/Login"/>;
    }
    // } else {
    //     return
    // }

    return (
        <div>
            <h1>Welcome {data.email}</h1>
        </div>
    );
}

export default Account;