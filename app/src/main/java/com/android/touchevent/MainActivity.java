package com.android.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.android.touchevent.util.MotionEventUtil;

/**
 * Description: <MyLayout><br>
 * Author:      gxl<br>
 * Date:        2019/5/10<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MainActivity extends AppCompatActivity {
    public static String TAG = "MYTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v(TAG,"MainActivity dispatchTouchEvent start:"+MotionEventUtil.getMotionEventName(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG,"MainActivity onTouchEvent start:"+MotionEventUtil.getMotionEventName(event));
        return super.onTouchEvent(event);
    }
}
