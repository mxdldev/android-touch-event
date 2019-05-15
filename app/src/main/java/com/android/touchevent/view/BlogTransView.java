package com.android.touchevent.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.android.touchevent.R;


/**
 * Description: <BlogTransView><br>
 * Author:      gxl<br>
 * Date:        2019/1/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class BlogTransView extends RelativeLayout {
    public static final String TAG = BlogTransView.class.getSimpleName();
    private CheckBox mBtnMapZoom;
    private CompoundButton.OnCheckedChangeListener mBtnZoomChangeListener;
    private DispatchEventListener mDispatchEventListener;
    //是否被触摸的监听器
    public interface OnTouchEventListener {
        boolean isTouchTransView(MotionEvent event);
    }
    //透明View的事件分发监听器
    public interface DispatchEventListener {
        boolean dispathTouchEvent(MotionEvent event);
    }
    public BlogTransView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_blog_trans, this, true);
        mBtnMapZoom = findViewById(R.id.btn_map_zoom);
        mBtnMapZoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mBtnZoomChangeListener != null) {
                    mBtnZoomChangeListener.onCheckedChanged(compoundButton, b);
                }
            }
        });
    }

    //如果触摸了ScrollView上半部分的透明的部分，则事件会分发至此
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mDispatchEventListener != null) {
            Rect rect = new Rect();
            mBtnMapZoom.getHitRect(rect);
            Log.v(TAG, "x:" + event.getX() + ";y:" + event.getY());

            //如果用户点击了放大，缩小按钮则该事件会继续往下分发给他的子View：CheckBox
            if (rect.contains((int) event.getX(), (int) event.getY())) {
                Log.v(TAG, "mBtnMapZoom click yes");
                return super.dispatchTouchEvent(event);//继续往下分发给CheckBox
            } else {
                Log.v(TAG, "mBtnMapZoom click no");
                return mDispatchEventListener.dispathTouchEvent(event);
            }
        } else {
            return super.dispatchTouchEvent(event);
        }
    }

      public void setBtnZoomChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        mBtnZoomChangeListener = listener;
    }

    public void setBtnMapZoomChecked(boolean b) {
        if (mBtnMapZoom != null) {
            mBtnMapZoom.setChecked(b);
        }
    }
    public boolean isTouchTransView(MotionEvent event){
        Rect rect = new Rect();
        getLocalVisibleRect(rect);
        //根据坐标来判断，是否是点击了透明View，如果是则返回true，否则返回false
        if (rect.contains((int) event.getX(), (int) event.getY())) {
            return true;
        } else {
            return false;
        }
    }
    public void setDispatchEventListener(DispatchEventListener dispatchEventListener) {
        mDispatchEventListener = dispatchEventListener;
    }
}
