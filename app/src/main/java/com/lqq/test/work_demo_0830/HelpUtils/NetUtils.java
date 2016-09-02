package com.lqq.test.work_demo_0830.HelpUtils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by lqq on 2016/8/30.
 */
public class NetUtils {

    ConnectivityManager con;

    public NetUtils(Context context){

        con = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

    }

    public boolean getNetWorkInfo(){

        NetworkInfo info = con.getActiveNetworkInfo();
        if (info != null && info.isAvailable()){

            return true;

        }else{

            return false;
        }

    }

}
