import React, {useState} from 'react'
import {Button, TextField} from "@material-ui/core";
import Register from "./Register";
import {getOriginalServerUrl, sendAPIRequest} from "../../utils/restfulAPI";

export default function Login(props) {
    const [showRegister, setShowRegister] = useState(false)
    const [username, setUsername] = useState("")
    const [password, setPassWord] = useState("")
    if (showRegister) {
        return (
            <Register setShowRegister={setShowRegister} setShowLoginWindow={props.setShowLoginWindow}/>
        )
    }
    let sendCreds = async () => {
        if (password && username) {
            // Send email and password to server here
            let res = await sendAPIRequest(
                {requestType: "login", username: username, password: password}
                , getOriginalServerUrl())
            if (res['success']) {
                props.setShowLoginWindow(false)
            }
        }
    }
    return (
        <form noValidate autoComplete="off"
              style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh'}}>
            <div>
                <div>
                    <TextField
                        id="email field"
                        label="Email"
                        value={username}
                        onChange={(event) => setUsername(event.target.value)}
                    />
                    <div>
                        <TextField
                            id="password field"
                            label="Password"
                            type="password"
                            value={password}
                            onChange={(event) => setPassWord(event.target.value)}
                        />
                    </div>
                </div>
                <div>
                    <Button onClick={sendCreds} variant="contained">Login</Button>
                    <Button onClick={() => setShowRegister(true)} variant="outlined">Register</Button>
                    <Button onClick={() => props.setShowLoginWindow(false)} variant="outlined">Back</Button>
                </div>
            </div>
        </form>
    );
}