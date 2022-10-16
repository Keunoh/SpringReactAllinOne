import { useState } from "react";
import { signin, signup } from "utils/APIUtils";

const Login = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [signUpUsername, setSignUpUsername] = useState('');
    const [signUpPassword, setSignUpPassword] = useState('');

    const loginHandleSubmit = (e) => {
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
            console.log("SignIn SUCCESS");
            console.log(response);
        })
        .catch(error => {
            console.log("SignIn FAILED");
            console.log("SignIn Error Message : " + error);
        });

    }

    const signUpHandleSubmit = (e) => {
        e.preventDefault();
        console.log("Click Sign Up Button");
        console.log("SignUp user ID : " + signUpUsername);
        console.log("SignUp user PW : " + signUpPassword);

        let signUpRequestDto = {
            username: signUpUsername,
            password: signUpPassword
        }

        signup(signUpRequestDto)
        .then(response => {
            console.log("SignUp SUCCESS");
            console.log(response);
        })
        .catch(error => {
            console.log("SignUp FAILED");
            console.log("SignUp Error Message : " + error);
        })
    }

    return (
      <div>
        <h1>Login Area</h1>
        <form onSubmit={loginHandleSubmit}>
            <label htmlFor="username">
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

        <h2>----</h2>

        <h1>SignUp Area</h1>
        <form onSubmit={signUpHandleSubmit}>
            <label htmlFor="signUpUsername">
                ID : 
            </label>
            <input 
                type="text"
                name="signUpUsername"
                value={signUpUsername}
                autoComplete="off"
                onChange={(e) => setSignUpUsername(e.target.value)}
                required
            />
            <label htmlFor="signUpPassword">
                PW : 
            </label>
            <input 
                type="password"
                name="signUpPassword"
                value={signUpPassword}
                onChange={(e) => setSignUpPassword(e.target.value)}
                required
            />
            <button>
                Sign Up
            </button>
        </form>
      </div>
    );
}

export default Login;