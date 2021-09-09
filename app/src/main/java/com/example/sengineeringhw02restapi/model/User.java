package com.example.sengineeringhw02restapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    public User(int id, String username){
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
