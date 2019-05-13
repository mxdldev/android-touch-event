package com.android.touchevent.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.android.touchevent.view.BlogMapView;
import com.android.touchevent.view.BlogTransView;
import com.android.touchevent.view.TransScrollView;

/**
 * Description: <View协调控制器><br>
 * Author:      gxl<br>
 * Date:        2019/1/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class BlogViewController {
    public static final String TAG = BlogViewController.class.getSimpleName();
    private RelativeLayout mLayoutBlogRoot;
    private BlogMapView mBlogMapView;
    private TransScrollView mTransScrollView;
    private BlogTransView mBlogTransView;

    public BlogViewController(RelativeLayout layoutBlogRoot, BlogMapView blogMapView, TransScrollView transScrollView, BlogTransView blogTransView) {
        mLayoutBlogRoot = layoutBlogRoot;
        mBlogMapView = blogMapView;
        mTransScrollView = transScrollView;
        mBlogTransView = blogTransView;
    }

    public void init() {
        mBlogTransView.setListener(new BlogTransView.TouchEventListener() {
            @Override
            public boolean dispatchTouchEvent(MotionEvent event) {
                return mBlogMapView.dispatchMapTouchEvent(event);
            }
        });
        mBlogTransView.setBtnMapZoomOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //如果不是人手动点击则返回
                if (!compoundButton.isPressed()) {
                    return;
                }
                mBlogMapView.setBtnMapZoomChecked(b);

                setFlexWindow(b);
            }
        });
        mTransScrollView.setListener(new BlogTransView.TouchEventListener() {
            @Override
            public boolean dispatchTouchEvent(MotionEvent event) {
                Rect rect = new Rect();
                mBlogTransView.getLocalVisibleRect(rect);
                if (rect.contains((int) event.getX(), (int) event.getY())) {
                    return true;
                } else {
                    return false;
                }
            }
        });

    }
    //设置弹性窗口true：展开；false：收缩
    private void setFlexWindow(boolean b) {
        //当为true的时候：1.ScrollView要往下移动，是一个位移动画；
        //当为false的时候：1.ScrollView要往上移动，是一个位移动画；
        //往下移动，要移动多少？其实就是整个内容区域的高度，也就是layoutRoot的高度,恢复高度为0即可
        //MapView要缩放，要放大到多少倍？其他就是layoutRoot的高度除以MapView的高度
        //计算MapView的高度
        Rect rect = new Rect();
        mBlogTransView.getLocalVisibleRect(rect);
        int mMapViewHeight = DisplayUtil.dip2px(250);
        //不判断将是个大Bug
        if (rect.height() < mMapViewHeight - DisplayUtil.dip2px(30)) {
            mBlogTransView.setBtnMapZoomChecked(!b);
            mBlogMapView.setBtnMapZoomChecked(!b);
            return;
        }

        Log.v(TAG, "mapview height:" + mMapViewHeight);
        //计算整个内容区域的高度
        int mRootHeight = mLayoutBlogRoot.getHeight();
        Log.v(TAG, "rootlayout height:" + mRootHeight);
        int offset = mRootHeight - mMapViewHeight;
        Log.v(TAG, "offset height:" + offset);
        Log.v(TAG, "scrollview width:" + mTransScrollView.getWidth() + ";height:" + mTransScrollView.getHeight() + ";x:" + mTransScrollView.getX() + ";y:" + mTransScrollView.getY());
        if (b) {
            expendAnimator(mMapViewHeight, mRootHeight, offset);
        } else {
            expendAnimator(mRootHeight, mMapViewHeight, 0);
        }
    }

    private void expendAnimator(int mMapViewHeight, int mRootHeight, int offset) {
        //创建ScrollView向下移动移动
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(mTransScrollView, "translationY", offset);
        //创建地图放大动画
        ValueAnimator valueAnimator = ValueAnimator.ofInt(mMapViewHeight, mRootHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                Log.v(TAG, "map height:" + animatedValue);
                mBlogMapView.setMapLayoutParams(animatedValue);
            }
        });
        //合成两个动画效果
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translateAnimator).with(valueAnimator);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
}
