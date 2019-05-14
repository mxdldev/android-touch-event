package com.android.touchevent.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description: <TransScrollView><br>
 * Author:      gxl<br>
 * Date:        2018/12/29<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class BlogScrollView extends NestedScrollView {
    public BlogTransView.OnTouchEventListener mTouchEventListener;

    public BlogScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //如果触摸的是透明BlogTransView，那么该事件继续往下分发给BlogScrollView的子View：BlogTransView
        if (mTouchEventListener != null && mTouchEventListener.isTouchTransView(ev)) {
            //返回false表示继续往下层View树进行分发
            return false;
        }
        //否则就调用ScrollView的拦截事件，进行滚动处理
        return super.onInterceptTouchEvent(ev);
    }
    //设置透明View的触摸事件监听器
    public void setTransViewTouchListener(BlogTransView.OnTouchEventListener listener) {
        mTouchEventListener = listener;
    }
}
