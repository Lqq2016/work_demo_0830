package com.lqq.test.work_demo_0830.Pojo;


/**
 * Created by lqq on 2016/9/1.
 */
public class QuestionItemDetail {

    private String content;
    private int id;
    private long pubTime;
    private int typeid;
    private int cataid;
    private String answer;
    private String options;

    public QuestionItemDetail() {
    }

    public QuestionItemDetail(String content, int id, long pubTime, int typeid, String answer, int cataid, String options) {
        this.content = content;
        this.id = id;
        this.pubTime = pubTime;
        this.typeid = typeid;
        this.cataid = cataid;
        this.answer = answer;
        this.options = options;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPubTime() {
        return pubTime;
    }

    public void setPubTime(long pubTime) {
        this.pubTime = pubTime;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getCataid() {
        return cataid;
    }

    public void setCataid(int cataid) {
        this.cataid = cataid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "QuestionItemDetail{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", pubTime=" + pubTime +
                ", typeid=" + typeid +
                ", cataid=" + cataid +
                ", answer='" + answer + '\'' +
                ", options='" + options + '\'' +
                '}';
    }
}
