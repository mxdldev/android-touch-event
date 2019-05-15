package com.android.touchevent.view;

import android.content.Context;
import android.util.AttributeSet;
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
                if(mChangeListener != null){
                    mChangeListener.onCheckedChanged(buttonView,isChecked);
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mCheckBox.onTouchEvent(event);
    }
}
