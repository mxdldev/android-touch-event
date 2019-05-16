package com.android.touchevent.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.android.touchevent.util.MotionEventUtil;


/**
 * Description: <MyButton><br>
 * Author:      gxl<br>
 * Date:        2019/5/10<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    public static final String TAG = "MYTAG";
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v(TAG,"MyButton dispatchTouchEvent start:"+MotionEventUtil.getMotionEventName(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG,"MyButton onTouchEvent start:"+MotionEventUtil.getMotionEventName(event));
        return super.onTouchEvent(event);
    }
}
