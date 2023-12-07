package com.tco.requests;

import com.google.gson.Gson;
import com.tco.game.Game;
import com.tco.game.User;
import com.tco.server.Database;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ActiveGamesRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(RegisterRequest.class);
    boolean success;
    Game[] games;

    public ActiveGamesRequest() {
        this.requestType = "getActiveGames";
        this.success = true;
    }

    @Override
    public void buildResponse(spark.Request httpRequest) {
        JSONObject jsonObj = new JSONObject(httpRequest.body());
        this.requestType = "getActiveGames";

        String username = jsonObj.getString("username");
        User current = User.findUser(username);

        if (current == null || !current.validCredential()) {
            this.success = false;
            this.games = new Game[0];
            return;
        }

        ArrayList<String> strgames = Database.getGamesWithUser(current, "Games");
        Gson gson = new Gson();
        this.games = new Game[strgames.size()];

        for (int i = 0; i < strgames.size(); i++) {
            games[i] = gson.fromJson(strgames.get(i), Game.class);
        }

        this.success = this.games.length != 0;
    }


    @Override
    public void buildResponse() {
    }
}
