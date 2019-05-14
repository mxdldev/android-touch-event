package com.android.touchevent;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.android.touchevent.util.DisplayUtil;
import com.android.touchevent.view.BlogMapView;
import com.android.touchevent.view.BlogTransView;
import com.android.touchevent.view.BlogScrollView;

public class MapTouchActivity extends AppCompatActivity {
    public static final String TAG = MapTouchActivity.class.getSimpleName();
    private RelativeLayout mLayoutBlogRoot;
    private BlogMapView mBlogMapView;
    private BlogTransView mBlogTransView;
    private BlogScrollView mBlogScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBlogMapView = findViewById(R.id.blog_map_view);
        mBlogTransView = findViewById(R.id.view_blog_trans);
        mBlogScrollView = findViewById(R.id.view_scrollview);
        mLayoutBlogRoot = findViewById(R.id.layout_blog_root);


        mBlogMapView.onCreate(savedInstanceState);

        init();

    }
    public void init() {
        //设置BlogScrollView的透明部分的监听器
        mBlogScrollView.setTransViewTouchListener(new BlogTransView.OnTouchEventListener() {
            @Override
            public boolean isTouchTransView(MotionEvent event) {
                return mBlogTransView.isTouchTransView(event);
            }
        });
        //设置透明的view的事件分发器
        mBlogTransView.setDispatchEventListener(new BlogTransView.DispatchEventListener() {
            @Override
            public boolean dispathTouchEvent(MotionEvent event) {
                //直接把事件分发给BlogMapView
                return mBlogMapView.dispatchMapTouchEvent(event);
            }
        });
        //设置透明view里放大，缩小按钮的监听器
        mBlogTransView.setBtnZoomChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //如果不是人手动点击则返回
                if (!compoundButton.isPressed()) {
                    return;
                }
                //把BlogTransView状态设置给MapView的CheckBox
                mBlogMapView.setBtnMapZoomChecked(b);
            }
        });
        //设置地图放大按钮的回调监听，进行地图展开和缩小的操作
        mBlogMapView.setChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //开始展开，收缩的操作
                setFlexWindow(isChecked);
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
        Log.v(TAG, "scrollview width:" + mBlogScrollView.getWidth() + ";height:" + mBlogScrollView.getHeight() + ";x:" + mBlogScrollView.getX() + ";y:" + mBlogScrollView.getY());
        if (b) {
            expendAnimator(mMapViewHeight, mRootHeight, offset);
        } else {
            expendAnimator(mRootHeight, mMapViewHeight, 0);
        }
    }

    private void expendAnimator(int mMapViewHeight, int mRootHeight, int offset) {
        //创建ScrollView向下移动移动
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(mBlogScrollView, "translationY", offset);
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
