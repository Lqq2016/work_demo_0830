package com.lqq.test.work_demo_0830.GetResouse;

import android.app.Activity;
import android.app.ListActivity;

import com.lqq.test.work_demo_0830.Pojo.QuestionList;
import com.lqq.test.work_demo_0830.Pojo.UserDetail;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

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
    private static List<QuestionList> list;
    private static List<Activity> activityList = new ArrayList<Activity>();
    private static boolean openFavorite;

    public List<Activity> getActivityList(){

        return activityList;

    }

    public void setActivityList(Activity activity){

        activityList.add(activity);

    }

    public List<QuestionList> getList(){

        return list;

    }

    public void setList(List<QuestionList> list){

        this.list = list;

    }

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
