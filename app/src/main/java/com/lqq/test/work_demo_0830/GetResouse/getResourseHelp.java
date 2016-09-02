package com.lqq.test.work_demo_0830.GetResouse;

import com.lqq.test.work_demo_0830.Pojo.UserDetail;

import org.json.JSONArray;

/**
 * Created by lqq on 2016/9/2.
 */
public class getResourseHelp {

    private static getResourseHelp getResHelp;

    private getResourseHelp(){}

    public static getResourseHelp getResourHelp(){

        if (getResHelp==null){

            getResHelp = new getResourseHelp();

        }

        return  getResHelp;

    }

    private static JSONArray jsonArray;
    private static UserDetail userDetail;
    private static boolean openFavorite;

    public boolean getOpenFavorite(){

        return openFavorite;

    }

    public void setOpenFavorite(boolean openFavorite){

        this.openFavorite = openFavorite;

    }

    public UserDetail getUserDetail(){

        return userDetail;

    }

    public void setUserDetail(UserDetail userDetail){

        this.userDetail = userDetail;

    }

    public JSONArray getJsonArray(){

        return jsonArray;

    }
    public void setJsonArray(JSONArray jsonArray){

        this.jsonArray = jsonArray;

    }

}
