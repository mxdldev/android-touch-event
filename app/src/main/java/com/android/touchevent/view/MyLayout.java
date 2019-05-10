package com.android.touchevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.android.touchevent.util.MotionEventUtil;


/**
 * Description: <MyLayout><br>
 * Author:      gxl<br>
 * Date:        2019/5/10<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MyLayout extends LinearLayout {
    public static final String TAG = "MYTAG";
    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v(TAG,"MyLayout dispatchTouchEvent start:"+MotionEventUtil.getMotionEventName(ev));
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.v(TAG,"MyLayout onInterceptTouchEvent start:"+MotionEventUtil.getMotionEventName(ev));
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG,"MyLayout onTouchEvent start:"+MotionEventUtil.getMotionEventName(event));
        return true;
    }
}
