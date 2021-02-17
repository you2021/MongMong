package com.juj27.mongmong;

public class RecyclerListItem {

    int no;
    String name;
    String imgUrl;
    String title;
    String message;
    String price;
    int favor;
    String date;

    public RecyclerListItem() {
    }

    public RecyclerListItem(int no, String name, String imgUrl, String title, String message, String price, int favor, String date) {
        this.no = no;
        this.name = name;
        this.imgUrl = imgUrl;
        this.title = title;
        this.message = message;
        this.price = price;
        this.favor = favor;
        this.date = date;
    }
}
