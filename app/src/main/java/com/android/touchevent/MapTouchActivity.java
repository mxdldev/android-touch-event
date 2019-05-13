package com.android.touchevent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.touchevent.util.BlogViewController;
import com.android.touchevent.view.BlogMapView;
import com.android.touchevent.view.BlogTransView;
import com.android.touchevent.view.TransScrollView;

public class MapTouchActivity extends AppCompatActivity {
    private RelativeLayout mLayoutBlogRoot;
    private BlogMapView mBlogMapView;
    private BlogTransView mBlogTransView;
    private TransScrollView mTransScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBlogMapView = findViewById(R.id.blog_map_view);
        mBlogTransView = findViewById(R.id.view_blog_trans);
        mTransScrollView = findViewById(R.id.view_scrollview);
        mLayoutBlogRoot = findViewById(R.id.layout_blog_root);


        mBlogMapView.onCreate(savedInstanceState);

        BlogViewController mBlogViewController = new BlogViewController(mLayoutBlogRoot, mBlogMapView, mTransScrollView, mBlogTransView);
        mBlogViewController.init();

    }

}
