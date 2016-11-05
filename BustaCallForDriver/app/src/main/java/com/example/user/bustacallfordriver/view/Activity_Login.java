package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;

/**
 * Created by user on 2016-11-02.
 */

public class Activity_Login extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_btnTitleBarBack; // 타이틀 바 뒤로가기
    EditText et_phoneNum, et_requestNum; // 휴대폰 번호 입력, 인증번호 입력
    TextView tv_btnPhoneRequest, tv_btnLogin; // 인증번호 요청 버튼,

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app = (AppController)getApplicationContext();
        init();
    }

    private void init() {
        iv_btnTitleBarBack = (ImageView)findViewById(R.id.layout_titlebar_iv_back);
        et_phoneNum = (EditText)findViewById(R.id.activtiy_login_et_phonenum);
        et_requestNum = (EditText)findViewById(R.id.activtiy_login_et_request_answer);
        tv_btnPhoneRequest = (TextView)findViewById(R.id.activity_login_tv_phonerequest);
        tv_btnLogin = (TextView)findViewById(R.id.activity_login_tv_login);
        iv_btnTitleBarBack.setOnClickListener(this);
        tv_btnPhoneRequest.setOnClickListener(this);
        tv_btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_titlebar_iv_back: // 타이틀 바 뒤로가기 버튼
                break;
            case R.id.activity_login_tv_phonerequest: // 인증번호 요청 버튼
                break;
            case R.id.activity_login_tv_login: // 로그인 버튼
                // case01) 자동 로그인
                // case02) 회원가입 페이지
                Intent intent = new Intent(this, Activity_Signin.class);
                startActivity(intent);
                break;
        }

    }
}
