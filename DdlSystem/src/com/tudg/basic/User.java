package com.tudg.basic;

import java.util.ArrayList;

public class User {
    private String id;
    private String userPassWord;

    private String name;
    private ArrayList<Homework> homeworks =new ArrayList<>();

    public User(String id, String userPassWord, String name) {
        this.id = id;
        this.userPassWord = userPassWord;
        this.name = name;
    }

    public User() {
    }

    public ArrayList<Homework> getHomeworks() {
        return homeworks;
    }

    public String getId() {
        return id;
    }

    public String getUserPassWord() {
        return userPassWord;
    }



    public String getName() {
        return name;
    }

    public void setHomeworks(ArrayList<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }



    public void setName(String name) {
        this.name = name;
    }
}
