import React, {useEffect, useState} from 'react';
import Footer from './Margins/Footer';
import {LOG} from '../utils/constants';
import {getOriginalServerUrl, sendAPIRequest} from '../utils/restfulAPI';
import Board from "./Chessboard/Board";
import {Box} from "@material-ui/core";
import Header from "./Margins/Header"
import Login from "./User/Login";

export default function Page(props) {
    const [serverSettings, processServerConfigSuccess] = useServerSettings(props.showMessage);
    const [showLoginWindow, setShowLoginWindow] = useState(false)

    if (showLoginWindow === true) {
        return (
            <>
                <Login setShowLoginWindow={setShowLoginWindow}/>
                <Footer
                    serverSettings={serverSettings}
                    processServerConfigSuccess={processServerConfigSuccess}
                />
            </>
        )
    }
    return (
        <>
            <Header setShowLoginWindow={setShowLoginWindow}/>
            <Box
                display="flex"
                justifyContent="center"
                alignItems="center"
                justify="center"
                style={{minHeight: '90vh'}}
            >
                <Board/>
            </Box>
            <Footer
                serverSettings={serverSettings}
                processServerConfigSuccess={processServerConfigSuccess}
            />
        </>
    )
}

function useServerSettings(showMessage) {
    const [serverUrl, setServerUrl] = useState(getOriginalServerUrl());
    const [serverConfig, setServerConfig] = useState(null);

    useEffect(() => {
        sendConfigRequest();
    }, []);

    function processServerConfigSuccess(config, url) {
        LOG.info("Switching to Server:", url);
        setServerConfig(config);
        setServerUrl(url);
    }

    async function sendConfigRequest() {
        const configResponse = await sendAPIRequest({requestType: "config"}, serverUrl);
        if (configResponse) {
            processServerConfigSuccess(configResponse, serverUrl);
        } else {
            setServerConfig(null);
            showMessage(`Config request to ${serverUrl} failed. Check the log for more details.`, "error");
        }
    }

    return [{serverUrl: serverUrl, serverConfig: serverConfig}, processServerConfigSuccess];
}
