package com.lqq.test.work_demo_0830.Pojo;

/**
 * Created by lqq on 2016/9/1.
 */
public class QuestionList {

    private String content;
    private String type;
    private String date;

    public QuestionList() {
    }

    public QuestionList(String content, String type, String date) {
        this.content = content;
        this.type = type;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "QuestionList{" +
                "content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
