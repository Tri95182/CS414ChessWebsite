import React from 'react'
import {AppBar, Box, Button, Toolbar} from "@material-ui/core";

//Home Page was setup but Not working, So this is a place Holder button
export default function Header(props) {
    return (
        <Box>
            <AppBar style={{backgroundColor: "#1E4D2B"}}>
                <Toolbar>
                    <Button onClick={() => props.setShowLoginWindow(true)} color="inherit">Login</Button>
                    <Button color="inherit">Home</Button>
                    <Button color="inherit">Profile</Button>
                    <Button color="inherit">Games</Button>
                </Toolbar>
            </AppBar>
        </Box>
    );
}