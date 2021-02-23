package com.juj27.mongmong;

public class RecyclerNoticeItem {
    String imgUrl;
    String Day;
    String Say;
    String Time;

    public RecyclerNoticeItem() {
    }

    public RecyclerNoticeItem(String imgUrl, String day, String say, String time) {
        this.imgUrl = imgUrl;
        Day = day;
        Say = say;
        Time = time;
    }
}
