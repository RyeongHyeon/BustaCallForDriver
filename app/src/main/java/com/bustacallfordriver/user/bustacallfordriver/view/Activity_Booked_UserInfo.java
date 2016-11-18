package com.bustacallfordriver.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.bustacallfordriver.user.bustacallfordriver.BaseFragment;
import com.bustacallfordriver.user.bustacallfordriver.R;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental;

/**
 * 예약된 손님 정보
 * Created by user on 2016-11-15.
 */
public class Activity_Booked_UserInfo extends BaseActivity implements View.OnClickListener {


    BaseFragment fragment; // FrameLayout에 넣을 fragment
    ImageView backBtn;
    Rental rental;
    int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_userinfo);
        init();
        fragmentInit();

//        인텐트로 받아온 값으로 Frame에 default 넣을껀지 together 넣을꺼지 결정
    }

    private void init() {
        backBtn = (ImageView) findViewById(R.id.activity_booked_userinfo_iv_back);
        backBtn.setOnClickListener(this);
    }

    private void fragmentInit() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", -1);
        rental = (Rental)intent.getSerializableExtra("info");
        setFramelayout(type);
    }

    /***
     * FrameLayout에 넣을 fragment 셋팅
     */
    public void setFramelayout(int type) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        fragment = null;

        switch (type) {
            case 1:
                fragment = new Fragment_Booked_UserInfo_Default(rental);
                break;
            case 2:
                fragment = new Fragment_Booked_UserInfo_Together(rental);
                break;
        }

        ft.add(R.id.activity_booked_userinfo_frameLayout, fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_booked_userinfo_iv_back:
                finish();
                break;
        }
    }

}
