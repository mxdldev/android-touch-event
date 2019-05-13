package com.android.touchevent;

import android.app.Application;

/**
 * Description: <MyApplication><br>
 * Author:      gxl<br>
 * Date:        2019/5/13<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MyApplication extends Application {
    public static MyApplication mApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
    public static MyApplication getInstance(){
        return  mApplication;
    }
}
