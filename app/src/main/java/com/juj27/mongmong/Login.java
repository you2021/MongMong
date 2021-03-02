package com.juj27.mongmong;

public class Login {

    public static String nickName;
    public static String profileUrl;
    public static ProductVO currentItem = new ProductVO();  //회원가입 시 아이디

    public String ID;
    public String PW;

    public Login() {
    }

    public Login(String ID, String PW) {
        this.ID = ID;
        this.PW = PW;
    }
}
