package com.android.touchevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.android.touchevent.R;


/**
 * Description: <FatArrowView><br>
 * Author:      gxl<br>
 * Date:        2019/4/9<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class FatArrowView extends RelativeLayout {
    public static final String TAG = FatArrowView.class.getSimpleName();
    private CheckBox mCheckBox;
    private CompoundButton.OnCheckedChangeListener mChangeListener;
    private boolean ischeck = false;
    public void setChangeListener(CompoundButton.OnCheckedChangeListener changeListener) {
        mChangeListener = changeListener;
    }

    public FatArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_usecar_fat_arrow,this);
        mCheckBox = findViewById(R.id.check_trip_expend);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v(TAG,"setOnCheckedChangeListener start...");
                if(mChangeListener != null){
                    mChangeListener.onCheckedChanged(buttonView,isChecked);
                }
            }
        });
    }

    //拦截事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.v(TAG,"onInterceptTouchEvent start...");
        return true;
    }

    //把事件分发给我们的子View CheckBox
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG,"onTouchEvent start...");
        //当手指离开屏幕的时候设置CheckBox的状态
        if (event.getAction() == MotionEvent.ACTION_UP){
            ischeck = !ischeck;
            mCheckBox.setChecked(ischeck);
        }
         return true;
    }
}
