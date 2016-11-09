package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;

/**
 * Created by user on 2016-11-07.
 */

public class Activity_RentalDetail extends BaseActivity{

    BaseFragment fragment; // FrameLayout에 넣을 fragment
    int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentaldetail);
        init();

//        인텐트로 받아온 값으로 Frame에 default 넣을껀지 together 넣을꺼지 결정
    }

    private void init() {
        Intent intent = getIntent();
        type = intent.getIntExtra("defaultOrTogether", -1);
        if(type == -1) {
            Log.d("error","정의되어 있지 않은 매물 type입니다");
        }
        setFramelayout(type);
    }


    /***
     * FrameLayout에 넣을 fragment 셋팅
     */
    public void setFramelayout(int type) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        fragment = null;
        String TAG = null;
        if (type == 0) {
            fragment = new Fragment_RentalDetail_Default();
            TAG = "default";
        } else if (type == 1) {
            fragment = new Fragment_RentalDetail_Together();
            TAG = "together";
        }

        ft.replace(R.id.activity_rentaldetail_layout, fragment, TAG);
        ft.commit();
    }
}
