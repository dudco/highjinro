package com.example.dudco.highjinro.Datas;

/**
 * Created by dudco on 2017. 2. 14..
 */

public class DashBoardData {
    private String title;
    private String description;
    private String author;
    private String love_num;
    private String reply_num;

    public DashBoardData() {
    }

    public DashBoardData(String title, String description, String author, String love_num, String reply) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.love_num = love_num;
        this.reply_num = reply;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLove_num() {
        return love_num;
    }

    public void setLove_num(String love_num) {
        this.love_num = love_num;
    }

    public String getReply_num() {
        return reply_num;
    }

    public void setReply_num(String reply) {
        this.reply_num = reply;
    }
}
