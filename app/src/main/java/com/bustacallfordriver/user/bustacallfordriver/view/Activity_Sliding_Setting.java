package com.bustacallfordriver.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.R;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.bustacallfordriver.user.bustacallfordriver.presenter.Activity_Sliding_Setting_Presenter;

/**
 * 사이드 메뉴 > 설정
 * Created by user on 2016-11-12.
 */
public class Activity_Sliding_Setting extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_back;
    Spinner sp_bank;
    EditText et_accountNum;
    TextView tv_logout, tv_setting;

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
        sp_bank = (Spinner) findViewById(R.id.activity_sliding_setting_spinner_bank);
        tv_logout = (TextView) findViewById(R.id.activity_sliding_setting_tv_logout);
        tv_setting = (TextView) findViewById(R.id.activity_sliding_setting_tv_enter);
        et_accountNum = (EditText) findViewById(R.id.activity_sliding_setting_et_accountnum);
        et_accountNum.addTextChangedListener(tw_account);
        iv_back.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
        tv_setting.setOnClickListener(this);


        ArrayAdapter bankAdapter = ArrayAdapter.createFromResource(this, R.array.bank, android.R.layout.simple_spinner_item);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_bank.setAdapter(bankAdapter);

        setEnableTextView_Rect(tv_setting,false);
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
            case R.id.activity_sliding_setting_tv_enter:
                String selItem= (String)sp_bank.getSelectedItem();
                String accoutNum = et_accountNum.getText().toString().trim();
                presenter.request_set_setting(app.getBus().getBus_num(), selItem, accoutNum);
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

    public void set_accountNumEmpty() {
        et_accountNum.setText("");
    }

    TextWatcher tw_account = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String str = et_accountNum.getText().toString();
            if(!str.equals("")){
                setEnableTextView_Rect(tv_setting,true);
            }else{
                setEnableTextView_Rect(tv_setting,false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}