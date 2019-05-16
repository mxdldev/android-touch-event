package com.android.touchevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.android.touchevent.view.FatArrowView;
import com.android.touchevent.view.SettingBarView;

public class SelectTouchActivity extends AppCompatActivity {

    private CheckBox mCheckBox;
    private FatArrowView mFatArrowView;
    private SettingBarView mSetBarCarSelect;
    private SettingBarView mSetBarStartPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_touch);
        mCheckBox = findViewById(R.id.chb);
        mFatArrowView = findViewById(R.id.view_arrow);
        mSetBarCarSelect = findViewById(R.id.view_car_select);
        mSetBarStartPosition = findViewById(R.id.view_start_positon);
        mSetBarCarSelect.enableEditContext(false);
        mSetBarStartPosition.enableEditContext(true);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(SelectTouchActivity.this,"选中了",Toast.LENGTH_LONG).show();
            }
        });

        mFatArrowView.setChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(SelectTouchActivity.this,"选中了",Toast.LENGTH_LONG).show();
            }
        });

        mSetBarCarSelect.setOnClickSettingBarViewListener(new SettingBarView.OnClickSettingBarViewListener() {
            @Override
            public void onClick() {
                Toast.makeText(SelectTouchActivity.this,"我要选择车辆了",Toast.LENGTH_LONG).show();
            }
        });
        //mSetBarStartPosition.setOnClickSettingBarViewListener();
    }
}
