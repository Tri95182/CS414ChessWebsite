package com.tco.requests;

import com.tco.game.Game;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateGameRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(RegisterRequest.class);
    boolean success;

    public CreateGameRequest() {
        this.requestType = "createGame";
        this.success = true;
    }

    @Override
    public void buildResponse(spark.Request httpRequest) {
        JSONObject jsonObj = new JSONObject(httpRequest.body());
        this.requestType = "createGame";

        Game current = new Game(jsonObj);

        this.success = current.registerGame();
    }


    @Override
    public void buildResponse() {
    }

}

