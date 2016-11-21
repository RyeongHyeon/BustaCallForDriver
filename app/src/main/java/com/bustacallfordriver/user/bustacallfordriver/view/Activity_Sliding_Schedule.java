package com.bustacallfordriver.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.R;
import com.bustacallfordriver.user.bustacallfordriver.ScheduleAdapter;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental_List;
import com.bustacallfordriver.user.bustacallfordriver.presenter.Activity_Sliding_Schedule_Presenter;

/**
 * 사이드 메뉴 > 나의 스케줄
 * Created by user on 2016-11-12.
 */
public class Activity_Sliding_Schedule extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_backBtn;
    ListView listView;
    LinearLayout noExistLayer;
    ScheduleAdapter adapter;
    Rental_List scheduleList;
    Activity_Sliding_Schedule_Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_schedule);
        app = (AppController) getApplicationContext();
        presenter = new Activity_Sliding_Schedule_Presenter(this);
        presenter.request_get_schedule(app.getBus().getBus_num());
        init();
    }

    private void init() {
        iv_backBtn = (ImageView) findViewById(R.id.activity_schedule_iv_back);
        listView = (ListView) findViewById(R.id.activity_schedule_listview);
        noExistLayer = (LinearLayout) findViewById(R.id.activity_schedule_noexist);
        iv_backBtn.setOnClickListener(this);
    }


    public void setlListView() {
        Rental_List mScheduleList = getScheduleList();
        if (!mScheduleList.getRental_list().isEmpty()) {
            noExistLayer.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            adapter = new ScheduleAdapter(mScheduleList);
            listView.setAdapter(adapter);
        } else {
            noExistLayer.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_schedule_iv_back:
                finish();
                break;
        }
    }

    public Rental_List getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(Rental_List scheduleList) {
        this.scheduleList = scheduleList;
    }

}