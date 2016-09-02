package com.lqq.test.work_demo_0830.Pojo;

/**
 * Created by lqq on 2016/9/1.
 */
public class CategoryListItem {

    private int id;
    private String icon;
    private String name;

    public CategoryListItem() {
    }

    public CategoryListItem(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryListItem{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
