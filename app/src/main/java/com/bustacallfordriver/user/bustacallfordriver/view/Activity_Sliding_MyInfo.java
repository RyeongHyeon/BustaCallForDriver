package com.bustacallfordriver.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.MyInfoAdapter;
import com.bustacallfordriver.user.bustacallfordriver.R;

import java.util.ArrayList;

/**
 * 사이드 > 나의 정보
 * Created by user on 2016-11-12.
 */
public class Activity_Sliding_MyInfo extends BaseActivity implements View.OnClickListener {

    AppController app;
    RecyclerView recyclerView;
    ImageView iv_backBtn, iv_profile;
    TextView tv_nickname, tv_type, tv_career, tv_carnum, tv_btnModify;
    MyInfoAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_myinfo);
        app = (AppController) getApplicationContext();
        init();
    }

    private void init() {
        iv_backBtn = (ImageView) findViewById(R.id.activity_sliding_myinfo_iv_back);
        recyclerView = (RecyclerView)findViewById(R.id.activity_sliding_myinfo_rv_list);
        iv_profile = (ImageView) findViewById(R.id.activity_sliding_myinfo_iv_profile);
        tv_nickname = (TextView) findViewById(R.id.activity_sliding_myinfo_tv_nickname);
        tv_type = (TextView) findViewById(R.id.activity_sliding_myinfo_tv_type);
        tv_career = (TextView) findViewById(R.id.activity_sliding_myinfo_tv_career);
        tv_carnum = (TextView) findViewById(R.id.activity_sliding_myinfo_tv_carnum);
        tv_btnModify = (TextView) findViewById(R.id.activity_sliding_myinfo_tv_btnModify);
        iv_backBtn.setOnClickListener(this);
        tv_btnModify.setOnClickListener(this);

        initImage();
        initText();
    }

    private void initText() {
        tv_nickname.setText(app.getBus().getNickname());
        tv_type.setText(app.getBus().getBus_type());
        tv_career.setText(app.getBus().getBus_career());
        tv_carnum.setText(app.getBus().getBus_num());
    }

    private void initImage() {
        Glide.with(this).load(app.getBus().getBus_url().get(0)).into(iv_profile);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList<String> busUrl = new ArrayList<>();
        busUrl.add( app.getBus().getBus_url().get(1));
        busUrl.add( app.getBus().getBus_url().get(2));
        if(!app.getBus().getBus_url().get(3).equals("")){
            busUrl.add( app.getBus().getBus_url().get(3));
        }
        if(!app.getBus().getBus_url().get(4).equals("")) {
            busUrl.add( app.getBus().getBus_url().get(4));

        }
        adapter = new MyInfoAdapter(this, recyclerView, busUrl);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sliding_myinfo_iv_back:
                finish();
                break;
            case R.id.activity_sliding_myinfo_tv_btnModify:
                Intent intent = new Intent(this, Activity_Signin.class);
                startActivity(intent);
                break;

        }
    }
}