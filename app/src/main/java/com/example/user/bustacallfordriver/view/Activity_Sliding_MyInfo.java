package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;

/**
 * Created by user on 2016-11-12.
 */

public class Activity_Sliding_MyInfo extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_backBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_myinfo);
        app = (AppController) getApplicationContext();
        init();
    }

    private void init() {
        iv_backBtn = (ImageView) findViewById(R.id.activity_sliding_myinfo_iv_back);
        iv_backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sliding_myinfo_iv_back:
                finish();
                break;
        }
    }
}