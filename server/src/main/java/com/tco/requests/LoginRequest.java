package com.tco.requests;

import com.tco.game.User;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(RegisterRequest.class);
    boolean success = false;

    public LoginRequest() {
        this.requestType = "login";
        this.success = true;
    }

    @Override
    public void buildResponse(spark.Request httpRequest) {
        JSONObject jsonObj = new JSONObject(httpRequest.body());
        this.requestType = "login";

        User user = new User(jsonObj);

        this.success = user.authenticateUser();
    }


    @Override
    public void buildResponse() {
    }

}