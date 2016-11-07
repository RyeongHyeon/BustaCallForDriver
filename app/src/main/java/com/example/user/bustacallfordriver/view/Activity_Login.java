package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.presenter.Activtiy_Login_Presenter;

/**
 * Created by user on 2016-11-02.
 */

public class Activity_Login extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_btnTitleBarBack; // 타이틀 바 뒤로가기
    EditText et_phoneNum, et_requestNum; // 휴대폰 번호 입력, 인증번호 입력
    TextView tv_btnPhoneRequest, tv_btnLogin; // 인증번호 요청 버튼,
    boolean is_phonenum,is_requestnum; //버튼 활성화
    boolean is_phonenumbutton; //중복, 요청이 잘 됬는지

    Activtiy_Login_Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app = (AppController)getApplicationContext();
        presenter = new Activtiy_Login_Presenter(this);
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
        et_phoneNum.addTextChangedListener(tv_phoneNum);
        et_requestNum.addTextChangedListener(tv_requsetNum);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_titlebar_iv_back: // 타이틀 바 뒤로가기 버튼
                break;
            case R.id.activity_login_tv_phonerequest: // 인증번호 요청 버튼
                presenter.request_requestNum(et_phoneNum.getText().toString());
                break;
            case R.id.activity_login_tv_login: // 로그인 버튼
                // case01) 자동 로그인
                // case02) 회원가입 페이지
                presenter.request_login(et_phoneNum.getText().toString(),et_requestNum.getText().toString());

                break;
        }

    }

    public void nextPage(){
        Intent intent = new Intent(this, Activity_Signin.class);
        startActivity(intent);
    }

    public boolean is_phonenum() {
        return is_phonenum;
    }

    public void setIs_phonenum(boolean is_phonenum) {
        this.is_phonenum = is_phonenum;
    }

    public boolean is_requestnum() {
        return is_requestnum;
    }

    public void setIs_requestnum(boolean is_requestnum) {
        this.is_requestnum = is_requestnum;
    }

    public boolean is_phonenumbutton() {
        return is_phonenumbutton;
    }

    public void setIs_phonenumbutton(boolean is_phonenumbutton) {
        this.is_phonenumbutton = is_phonenumbutton;
    }

    public TextView getTv_btnPhoneRequest() {
        return tv_btnPhoneRequest;
    }

    public void setTv_btnPhoneRequest(TextView tv_btnPhoneRequest) {
        this.tv_btnPhoneRequest = tv_btnPhoneRequest;
    }

    public TextView getTv_btnLogin() {
        return tv_btnLogin;
    }

    public void setTv_btnLogin(TextView tv_btnLogin) {
        this.tv_btnLogin = tv_btnLogin;
    }

    public void checkTrueofLogin(){
        if(is_phonenumbutton==true){
            setEnableTextView(tv_btnLogin,true);
        }
    }

    TextWatcher tv_phoneNum = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_phonenum(et_phoneNum.getText().toString());
            checkTrueofLogin();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher tv_requsetNum = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_phonenum(et_requestNum.getText().toString());
            checkTrueofLogin();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
