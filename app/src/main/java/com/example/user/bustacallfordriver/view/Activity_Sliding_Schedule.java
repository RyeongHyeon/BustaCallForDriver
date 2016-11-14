package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.ScheduleAdapter;
import com.example.user.bustacallfordriver.model.Rental_List;

/**
 * 사이드 메뉴 > 나의 스케줄
 * Created by user on 2016-11-12.
 */
public class Activity_Sliding_Schedule extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_backBtn;
    ListView listView;
    LinearLayout ll_noExistLayer;
    ScheduleAdapter adapter;

    public Rental_List getSchduleList() {
        return schduleList;
    }

    public void setSchduleList(Rental_List schduleList) {
        this.schduleList = schduleList;
    }

    Rental_List schduleList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_schedule);
        app = (AppController) getApplicationContext();
        init();
    }

    private void init() {
        iv_backBtn = (ImageView) findViewById(R.id.activity_schedule_iv_back);
        listView = (ListView) findViewById(R.id.activity_schedule_listview);
        ll_noExistLayer = (LinearLayout) findViewById(R.id.activity_schedule_noexist);
        iv_backBtn.setOnClickListener(this);
        ScheduleAdapter adapter = new ScheduleAdapter(getSchduleList());
        listView.setAdapter(adapter);
//
//        if(!app.getTender_list().getRental_list().isEmpty()){
//            ll_noExistLayer.setVisibility(View.INVISIBLE);
//            listView.setVisibility(View.VISIBLE);
////            adapter = new ScheduleAdapter(app.getNotice_list(), this);
////            listView.setAdapter(adapter);
//        }else {
//            ll_noExistLayer.setVisibility(View.VISIBLE);
//            listView.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_schedule_iv_back:
                finish();
                break;
        }
    }
}