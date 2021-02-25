package com.juj27.mongmong;

public class Login {

    public static String nickName;
    public static String profileUrl;

    public String ID;
    public String PW;

    public Login() {
    }

    public Login(String ID, String PW) {
        this.ID = ID;
        this.PW = PW;
    }
}
