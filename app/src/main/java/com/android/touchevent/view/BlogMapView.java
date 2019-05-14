package com.android.touchevent.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import com.amap.api.maps.AMap;
import com.amap.api.maps.TextureMapView;
import com.android.touchevent.R;

/**
 * Description: <BlogMapView><br>
 * Author:      gxl<br>
 * Date:        2019/1/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class BlogMapView extends RelativeLayout {
    public static final String TAG = BlogMapView.class.getSimpleName();
    private AMap mMap;
    private TextureMapView mTextureMapView;
    private CheckBox mBtnMapZoom;
    private CompoundButton.OnCheckedChangeListener mChangeListener;

    public BlogMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_blog_map, this, true);
        mTextureMapView = findViewById(R.id.map);
        mMap = mTextureMapView.getMap();
        mMap.getUiSettings().setZoomControlsEnabled(false);

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

    public boolean dispatchMapTouchEvent(MotionEvent event) {
        return mTextureMapView.dispatchTouchEvent(event);
    }

    public void setMapLayoutParams(int height) {
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));
        mTextureMapView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));
    }

    public void setBtnMapZoomChecked(boolean b) {
        mBtnMapZoom.setChecked(b);
    }

    public void onCreate(Bundle bundle) {
        mTextureMapView.onCreate(bundle);
    }

    public AMap getMap() {
        return mMap;
    }

    public void setChangeListener(CompoundButton.OnCheckedChangeListener changeListener) {
        mChangeListener = changeListener;
    }
}
