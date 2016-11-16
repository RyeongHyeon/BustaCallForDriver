package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;

/**
 * Created by user on 2016-11-12.
 */
public class Activity_Sliding_Inquiry extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_backBtn;
    TextView tv_enter;
    EditText et_text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_inquiry);
        app = (AppController) getApplicationContext();
        init();
    }

    private void init() {
        iv_backBtn = (ImageView) findViewById(R.id.activity_sliding_inquiry_iv_btnback);
        tv_enter = (TextView) findViewById(R.id.activity_sliding_inqulry_tv_enter);
        et_text = (EditText) findViewById(R.id.activity_sliding_inqulry_et_text);
        iv_backBtn.setOnClickListener(this);
        tv_enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sliding_inquiry_iv_btnback:
                finish();
                break;
            case R.id.activity_sliding_inqulry_tv_enter:
                Intent it = new Intent(Intent.ACTION_SEND);
                String[] mailaddr = {"goodys1011@naver.com"};

                it.setType("plaine/text");
                it.putExtra(Intent.EXTRA_EMAIL, mailaddr); // 받는사람
                it.putExtra(Intent.EXTRA_SUBJECT, "문의합니다"); // 제목
                it.putExtra(Intent.EXTRA_TEXT, "[닉네임]\n"+app.getBus().getNickname() +"\n\n" + "[문의 내용]\n"+ et_text.getText()); // 첨부내용

                startActivity(it);

                et_text.setText("");
                et_text.setHint("문의글을 써주세요.");
                break;
        }
    }
}

