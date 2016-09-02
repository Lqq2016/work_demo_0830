package com.lqq.test.work_demo_0830.Pojo;

import java.io.Serializable;

/**
 * Created by lqq on 2016/8/31.
 */
public class QuestionListItem implements Serializable{

    private String content;
    private int page;
    private int totalElements;
    private int totalPages;
    private int size;

    public QuestionListItem() {
    }

    public QuestionListItem(String content, int page, int totalElements, int totalPages, int size) {
        this.content = content;
        this.page = page;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "QuestionListItem{" +
                "content='" + content + '\'' +
                ", page=" + page +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", size=" + size +
                '}';
    }
}
