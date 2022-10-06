import { useState } from "react";
import { memberLogin } from "utils/APIUtils";

const Login = () => {

    const [userId, setUserId] = useState('');
    const [userPw, setUserPw] = useState('');

    const handleSubmit = (e) => {
        //e.preventDefault() 적어주지 않으면
        //계속해서 새로고침이 된다.
        e.preventDefault();
        console.log("Click Submit Button");
        console.log("user ID : " + userId);
        console.log("user PW : " + userPw);

        let loginRequestDto = {
            memberId: userId,
            memberPw: userPw
        }

        memberLogin()
        .then(response => {
            console.log("login success")
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
                name="userId"
                value={userId}
                autoComplete="off"
                onChange={(e) => setUserId(e.target.value)}
                required
            />
            <label htmlFor="userPw">
                Password :
            </label>
            <input 
                type="password"
                name="userPw"
                value={userPw}
                onChange={(e) => setUserPw(e.target.value)}
                required
            />
            <button>
                Submit
            </button> 
        </form>
        <h1>created it?</h1>
      </div>
    );
}

export default Login;