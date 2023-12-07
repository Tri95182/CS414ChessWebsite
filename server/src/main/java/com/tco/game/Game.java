package com.tco.game;

import com.google.gson.annotations.SerializedName;
import com.tco.server.Database;
import org.json.JSONObject;
import java.time.Instant;
import java.util.Map;

public class Game {
    // TODO(sn0w): We will need another type of request so that the client
    // is able to inform the server of when the game has started
    private transient Instant startTime;
    private User white;
    private User black;
    private User turn;
    @SerializedName("_id")
    private String name;
    private boolean valid;

    public Game(User white, User black, String name) {
        this.white = white;
        this.black = black;
        this.name = name;
        this.turn = white;
    }

    public Game(User white, String name) {
        this.white = white;
        this.name = name;
        this.turn = white;
    }

    public Game(JSONObject jsonObj) {
        Map<String, Object> json = jsonObj.toMap();

        populateUsers(jsonObj);
        this.name = (String) json.get("name");
        if (this.turn == null) {
            System.out.println("Invalid Game; turn is null!");
            this.valid = false;
        }
        if (this.name == null) {
            System.out.println("Invalid game; name is null");
            this.valid = false;
        }
        if (this.white == null) {
            System.out.println("Invalid game; white player is null");
            this.valid = false;
        }
        this.valid = true;
    }

    private void populateUsers(JSONObject jsonObject) {
        User white = User.findUser((String) jsonObject.get("white"));
        User black = null;
        User turn = white;
        try {
            black = User.findUser((String) jsonObject.get("black"));
            turn = User.findUser((String) jsonObject.get("turn"));
        } catch (org.json.JSONException ignored) {
        }
        this.turn = turn;
        this.white = white;
        this.black = black;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean gameIsReady() {
        return !(this.white == null && this.black == null);
    }

    public boolean registerGame() {
        if (this.white == null) {
            System.out.println("White piece is null!");
            return false;
        }
        if (!this.valid) {
            System.out.println("Not a valid game!");
            return false;
        }
        if (Database.keyExists("_id", this.name, "Games")) {
             if(this.black == null){
                return false;
            }
            try {
                if(!Database.checkBlackField("_id", this.name, "Games")){
                    throw new Exception("Game Invalid: Duplicate name");
                }
            }
            catch(Exception e){
                this.black.toString();
                Database.updateGame("_id", this.name, "Games", this.black);
                return true;
            }
            System.out.println("Game invalid; Duplicate name");
            return false;
        }
        Database.insert(this, "Games", this.name);
        return true;
    }

}
