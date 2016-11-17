package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.example.user.bustacallfordriver.presenter.Activity_Sliding_Setting_Presenter;

/**
 * 사이드 메뉴 > 설정
 * Created by user on 2016-11-12.
 */
public class Activity_Sliding_Setting extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_back;
    TextView tv_login;

    Activity_Sliding_Setting_Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_setting);
        app = (AppController) getApplicationContext();
        presenter = new Activity_Sliding_Setting_Presenter(this);
        init();
    }

    private void init() {
        iv_back = (ImageView) findViewById(R.id.activity_sliding_setting_iv_back);
        tv_login = (TextView) findViewById(R.id.activity_sliding_setting_tv_logout);
        iv_back.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sliding_setting_iv_back:
                finish();
                break;
            case R.id.activity_sliding_setting_tv_logout:
                goToLogout();
                break;
        }
    }

    public void goToLogout() {
        //다이알로그
        final Dialog_base_two_button dialog_base_two_button = new Dialog_base_two_button(this, this.getString(R.string.logout_content));
        dialog_base_two_button.show();
        dialog_base_two_button.getTv_enter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃
                app.setSavedId("0");
                presenter.request_logout(app.getBus().getBus_num());
                //TODO 통신
            }
        });
        dialog_base_two_button.getTv_cancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃 취소
                dialog_base_two_button.dismiss();
            }
        });
    }
}