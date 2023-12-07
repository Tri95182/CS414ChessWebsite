package com.tco.game;

import com.google.gson.annotations.SerializedName;
import com.tco.server.Database;
import org.bson.Document;
import org.json.JSONObject;

import java.util.Map;

public class User {


    @SerializedName("_id")
    private String username;
    private String password;

    private transient boolean validUser = true;

    public User(String email, String password) {
        this.username = email;
        this.password = password;
    }

    public User(JSONObject jsonObj) {
        processUsernamePassword(jsonObj);
    }

    public String getUserEMail() {
        return this.username;
    }

    public static User findUser(String username) {
        Document doc = Database.findDoc("_id", username, "Users");
        if (doc == null) {
            return null;
        }

        return new User(doc.getString("_id"), doc.getString("password"));
    }

    public String getPassword() {
        return this.password;
    }

    public boolean authenticateUser() {
        return this.validCredential() && Database.objExists(this, "Users");
    }

    public void deleteUser() {

    }

    public boolean validCredential() {
        return username != null && password != null && validUser;
    }

    public void getUserData() {

    }

    // TODO(sbonafe) Add finishedGame
    public boolean registerUser() {
        this.validUser = !Database.keyExists("_id", this.username, "Users");
        if (this.validCredential()) {
            Database.insert(this, "Users", this.username);
            return true;
        }
        return false;
    }

    public void processUsernamePassword(JSONObject jsonObj) {
        Map<String, Object> json = jsonObj.toMap();

        this.username = (String) json.get("_id");
        this.password = (String) json.get("password");

        this.validUser = !(username == null || password == null) && !(username.isEmpty() || password.isEmpty());
    }

}
