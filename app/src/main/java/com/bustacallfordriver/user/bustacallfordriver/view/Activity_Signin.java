package com.bustacallfordriver.user.bustacallfordriver.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.BaseFragment;
import com.bustacallfordriver.user.bustacallfordriver.R;

/**
 * 회원가입 페이지
 * 회원정보 , 버스 정보, 자격증 등록 Fragment 포함
 * Created by user on 2016-11-03.
 */
public class Activity_Signin extends BaseActivity {

    TextView tv_signin_user, tv_signin_bus, tv_signin_license; // subTitleBar에 있는 각 fragment들 명칭
    BaseFragment fragment; // FrameLayout에 넣을 fragment


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        init();
        initFrameLayout();
    }

    private void init() {
        tv_signin_user = (TextView) findViewById(R.id.signin_user);
        tv_signin_bus = (TextView) findViewById(R.id.signin_bus);
        tv_signin_license = (TextView) findViewById(R.id.signin_license);
    }

    private void initFrameLayout() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragment =  Fragment_Signin_User.getInstance();
        ft.add(R.id.activity_signin_framelayout, fragment);
        ft.commit();
    }

    /***
     * FrameLayout에 넣을 fragment 셋팅
     */
    public void setFramelayout(int fragmentSignin) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        fragment = null;
        String TAG = null;
        if (fragmentSignin == R.layout.fragment_signin_user) {
            fragment = Fragment_Signin_User.getInstance();
            TAG = "user";
        } else if (fragmentSignin == R.layout.fragment_signin_bus) {
            fragment = Fragment_Signin_Bus.getInstance();
            TAG = "bus";
        } else if (fragmentSignin == R.layout.fragment_signin_license) {
            fragment = Fragment_Signin_License.getInstance();
            TAG = "license";
        }

        ft.replace(R.id.activity_signin_framelayout, fragment, TAG);
//        ft.addToBackStack(TAG);
        ft.commit();
        setSubTitleBarTextColor(fragmentSignin);
    }

    /***
     * fragment에 따라 서브타이틀바 목차 텍스트 변경
     */
    private void setSubTitleBarTextColor(int fragmentSignin) {
        String yellow = "#F7D26C";
        String black = "#000000";
        switch (fragmentSignin) {
            case R.layout.fragment_signin_user:
                tv_signin_user.setTextColor(Color.parseColor(yellow));
                tv_signin_bus.setTextColor(Color.parseColor(black));
                tv_signin_license.setTextColor(Color.parseColor(black));
                break;
            case R.layout.fragment_signin_bus:
                tv_signin_user.setTextColor(Color.parseColor(black));
                tv_signin_bus.setTextColor(Color.parseColor(yellow));
                tv_signin_license.setTextColor(Color.parseColor(black));
                break;
            case R.layout.fragment_signin_license:
                tv_signin_user.setTextColor(Color.parseColor(black));
                tv_signin_bus.setTextColor(Color.parseColor(black));
                tv_signin_license.setTextColor(Color.parseColor(yellow));
                break;
        }
    }
}