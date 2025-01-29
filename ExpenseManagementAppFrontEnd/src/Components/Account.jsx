import {useLocation} from "react-router-dom";

function Account () {
    const location = useLocation();
    const data = location.state || {};
    console.log(data.email);

    return (
        <div>
            <h1>Welcome {data.email}</h1>
        </div>
    );
}

export default Account;