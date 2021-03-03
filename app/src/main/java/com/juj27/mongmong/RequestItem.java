package com.juj27.mongmong;

public class RequestItem {

     String title;
     String img;
     String message;
     String price;

    public RequestItem() {
    }

    public RequestItem(String title, String img, String message, String price) {
        this.title = title;
        this.img = img;
        this.message = message;
        this.price = price;
    }
}
