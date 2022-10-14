import { useState } from "react";
import { signin } from "utils/APIUtils";

const Login = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        //e.preventDefault() 적어주지 않으면
        //계속해서 새로고침이 된다.
        e.preventDefault();
        console.log("Click Submit Button");
        console.log("user ID : " + username);
        console.log("user PW : " + password);

        let loginRequestDto = {
            username: username,
            password: password
        }

        signin(loginRequestDto)
        .then(response => {
            console.log("login success");
            console.log(response);
        })
        .catch(error => {
            console.log(error);
        });

    }

    return (
      <div>
        <h1>Login Page</h1>
        <form onSubmit={handleSubmit}>
            <label htmlFor="userId">
                ID :
            </label>
            <input 
                type="text"
                name="username"
                value={username}
                autoComplete="off"
                onChange={(e) => setUsername(e.target.value)}
                required
            />
            <label htmlFor="password">
                Password :
            </label>
            <input 
                type="password"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
            />
            <button>
                Submit
            </button> 
        </form>
      </div>
    );
}

export default Login;