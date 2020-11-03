package com.example.cs128_project;

import android.app.Application;

public class GlobalClass extends Application{

    private int user1color;
    private int user2color;
    private int user1gun;
    private int user2gun;
    private int user1dead;
    private int user2dead;

    public int getUser1color() {
        return user1color;
    }
    public void setUser1color(int user1color) {
        this.user1color = user1color;
    }

    public int getUser2color() {
        return user2color;
    }
    public void setUser2color(int user2color) {
        this.user2color = user2color;
    }

    public int getUser1gun() { return user1gun; }
    public void setUser1gun(int user1gun) { this.user1gun = user1gun; }

    public int getUser2gun() { return user2gun; }
    public void setUser2gun(int user2gun) { this.user2gun = user2gun;}

    public int getUser1dead() { return user1dead; }
    public void setUser1dead(int user1dead) { this.user1dead = user1dead; }

    public int getUser2dead() { return user2dead; }
    public void setUser2dead(int user2dead) { this.user2dead = user2dead; }
}
