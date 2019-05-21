package com.android.touchevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.android.touchevent.util.MotionEventUtil;
import com.android.touchevent.view.MyButton;

/**
 * Description: <MyLayout><br>
 * Author:      gxl<br>
 * Date:        2019/5/10<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static String TAG = "MYTAG";

    public MyButton mMyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this,SelectTouchActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this,MapTouchActivity.class));
                break;
        }
    }
}
