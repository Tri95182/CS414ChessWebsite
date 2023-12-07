package com.tco.requests;

import com.tco.game.User;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(RegisterRequest.class);
    boolean success = false;

    public RegisterRequest() {
        this.requestType = "register";
        this.success = true;
    }

    @Override
    public void buildResponse(spark.Request httpRequest) {
        JSONObject jsonObj = new JSONObject(httpRequest.body());
        this.requestType = "register";

        User user = new User(jsonObj);

        this.success = user.registerUser();
    }


    @Override
    public void buildResponse() {

    }

}
