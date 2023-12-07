import React, {useState} from 'react'
import {Button, TextField} from "@material-ui/core";
import {getOriginalServerUrl, sendAPIRequest} from "../../utils/restfulAPI";


export default function Register(props) {
    const [email, setEmail] = useState("")
    const [password, setPassWord] = useState("")
    const [confirmPass, setConfirmPass] = useState("")
    let helperText = "Passwords don't match"

    let serverUrl = getOriginalServerUrl()


    let sendPassword = async () => {
        if (password === confirmPass) {
            // Send email and password to server here
            let res = await sendAPIRequest(
                {requestType: "register", username: email, password: confirmPass}
                , serverUrl)
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
                        value={email}
                        onChange={(event) => setEmail(event.target.value)}
                    />
                </div>
                <TextField
                    error={confirmPass !== password}
                    id="password field"
                    label="Password"
                    value={password}
                    type="password"
                    onChange={(event) => setPassWord(event.target.value)}
                />
                <div>
                    <TextField
                        error={confirmPass !== password}
                        id="confirmation field"
                        label="Confirm password"
                        helperText={confirmPass !== password ? helperText : ""}
                        value={confirmPass}
                        type="password"
                        onChange={(event) => setConfirmPass(event.target.value)}
                    />
                </div>
                <div>
                    <Button onClick={sendPassword} variant="outlined">Register</Button>
                    <Button onClick={() => props.setShowRegister(false)} variant="outlined">Back</Button>
                </div>
            </div>
        </form>
    );
}