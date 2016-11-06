package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.user.bustacallfordriver.R;

/**
 * 알림 페이지
 * Created by user on 2016-11-06.
 */
public class Activity_Notice extends BaseActivity{

    LinearLayout ll_notice_on, ll_notice_off, ll_notice_noexist; // 알림 켜기, 알림 끄기, 알림 없을때 표시해주는 바탕화면 아이콘
    ListView listView; // 알림 리스트

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        init();
    }

    private void init() {
        ll_notice_on = (LinearLayout)findViewById(R.id.activity_notice_notice_on);
        ll_notice_off = (LinearLayout)findViewById(R.id.activity_notice_notice_off);
        ll_notice_noexist = (LinearLayout)findViewById(R.id.activity_notice_noexist);
        listView = (ListView)findViewById(R.id.activity_notice_listview);
    }
}
