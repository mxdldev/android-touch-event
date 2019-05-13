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
public class TransScrollView extends NestedScrollView {
    public BlogTransView.TouchEventListener mListener;

    public TransScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mListener != null && mListener.dispatchTouchEvent(ev)) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setListener(BlogTransView.TouchEventListener listener) {
        mListener = listener;
    }
}
