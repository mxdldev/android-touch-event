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
    private BlogTransView.TouchEventListener mListener;
    private CheckBox mBtnMapZoom;
    private CompoundButton.OnCheckedChangeListener mChangeListener;

    public interface TouchEventListener {
        boolean dispatchTouchEvent(MotionEvent event);
    }

    public BlogTransView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_blog_trans, this, true);
        mBtnMapZoom = findViewById(R.id.btn_map_zoom);
        mBtnMapZoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mChangeListener != null) {
                    mChangeListener.onCheckedChanged(compoundButton, b);
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mListener != null) {
            Rect rect = new Rect();
            mBtnMapZoom.getHitRect(rect);
            Log.v(TAG, "x:" + event.getX() + ";y:" + event.getY());
            if (rect.contains((int) event.getX(), (int) event.getY())) {
                Log.v(TAG, "mBtnMapZoom click yes");
                return super.dispatchTouchEvent(event);//继续往下分发给CheckBox
            } else {
                Log.v(TAG, "mBtnMapZoom click no");
                return mListener.dispatchTouchEvent(event);
            }
        } else {
            return super.dispatchTouchEvent(event);
        }
    }

    public void setListener(BlogTransView.TouchEventListener listener) {
        mListener = listener;
    }

    public void setBtnMapZoomOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        mChangeListener = listener;
    }

    public void setBtnMapZoomChecked(boolean b) {
        if (mBtnMapZoom != null) {
            mBtnMapZoom.setChecked(b);
        }
    }
}
