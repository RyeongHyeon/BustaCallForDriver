package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;

/**
 * 사이드 메뉴 > 설정
 * Created by user on 2016-11-12.
 */
public class Activity_Sliding_Setting extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_back;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_setting);
        app = (AppController) getApplicationContext();
        init();
    }

    private void init() {
        iv_back = (ImageView) findViewById(R.id.activity_sliding_setting_iv_back);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sliding_setting_iv_back:
                finish();
                break;
        }
    }
}