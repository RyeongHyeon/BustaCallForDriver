package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.model.Rental;

/**
 * Created by user on 2016-11-07.
 */

public class Activity_RentalDetail extends BaseActivity implements View.OnClickListener {

    BaseFragment fragment; // FrameLayout에 넣을 fragment
    ImageView backBtn;
    TextView tenderBtn;
    Rental rental;
    int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentaldetail);
        init();
        fragmentInit();

//        인텐트로 받아온 값으로 Frame에 default 넣을껀지 together 넣을꺼지 결정
    }

    private void init() {
        backBtn = (ImageView) findViewById(R.id.activity_rentaldetail_iv_back);
        tenderBtn = (TextView) findViewById(R.id.activity_rentaldetail_tv_tender_button);
        backBtn.setOnClickListener(this);
        tenderBtn.setOnClickListener(this);
    }

    private void fragmentInit() {
        Intent intent = getIntent();
        type = setType(intent.getIntExtra("oneWayOrTwoWay", -1), intent.getIntExtra("defaultOrTogether", -1));
        setFramelayout(type);
        rental = (Rental)intent.getSerializableExtra("info");
    }

    private int setType(int oneWayOrTwoWay, int defaultOrTogether) {
        if (oneWayOrTwoWay == 0) {
            if (defaultOrTogether == 0) {
                type = 0; // 편도, 기본
            } else {
                type = 1; // 편도, 같이가기
            }
        } else {
            if (defaultOrTogether == 0) {
                type = 2; // 왕복, 기본
            } else {
                type = 3; // 왕복, 같이가기
            }
        }
        return type;
    }


    /***
     * FrameLayout에 넣을 fragment 셋팅
     */
    public void setFramelayout(int type) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        fragment = null;
        switch (type) {
            case 0:
                fragment = new Fragment_RentalDetail_OneWay(rental);
                break;
            case 1:
                fragment = new Fragment_RentalDetail_OneWay_Together(rental);
                break;
            case 2:
                fragment = new Fragment_RentalDetail_TwoWay(rental);
                break;
            case 3:
                fragment = new Fragment_RentalDetail_TwoWay_Together(rental);
                break;
        }

        ft.add(R.id.activity_rentaldetail_layout, fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_rentaldetail_iv_back:
                finish();
                break;
            case R.id.activity_rentaldetail_tv_tender_button:
                break;
        }
    }
}
