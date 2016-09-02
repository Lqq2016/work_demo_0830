package com.lqq.test.work_demo_0830.Pojo;


/**
 * Created by lqq on 2016/8/31.
 */
public class ListItem {

    private int img;
    private String itemName;

    public ListItem() {
    }

    public ListItem(String itemName, int img) {
        this.itemName = itemName;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
