package com.juj27.mongmong;

public class RecyclerListItem {

    int no;
    String name;
    String img;
    String title;
    String msg;
    String price;
    int favor;
    String date;

    public RecyclerListItem(String category, String subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }

    String category;
    String subcategory;

    public RecyclerListItem() {
    }

    public RecyclerListItem(int no, String name, String imgUrl, String title, String message, String price, int favor, String date) {
        this.no = no;
        this.name = name;
        this.img = imgUrl;
        this.title = title;
        this.msg = message;
        this.price = price;
        this.favor = favor;
        this.date = date;
    }
}
