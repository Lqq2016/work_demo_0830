package com.lqq.test.work_demo_0830.Application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by lqq on 2016/8/30.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);

    }
}
