package com.juj27.mongmong;

public class ChattingVO {

    public String id;
    public String time;
    public String message;

    public ChattingVO() {
    }

    public ChattingVO(String id, String time, String message) {
        this.id = id;
        this.time = time;
        this.message = message;
    }
}
