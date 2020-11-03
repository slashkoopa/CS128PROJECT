package com.example.cs128_project;

import android.app.Application;

public class GlobalClass extends Application{

    private int user1color;
    private int user2color;

    public int getUser2color() {
        return user2color;
    }

    public void setUser2color(int user2color) {
        this.user2color = user2color;
    }

    public int getUser1color() {
        return user1color;
    }

    public void setUser1color(int user1color) {
        this.user1color = user1color;
    }

}
