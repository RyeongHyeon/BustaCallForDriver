package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.ScheduleAdapter;

/**
 * 사이드 메뉴 > 나의 스케줄
 * Created by user on 2016-11-12.
 */
public class Activity_Sliding_Schedule extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_backBtn;
    ListView listView;

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
        iv_backBtn.setOnClickListener(this);
        ScheduleAdapter adapter = new ScheduleAdapter(app.getRental_list());
        listView.setAdapter(adapter);
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