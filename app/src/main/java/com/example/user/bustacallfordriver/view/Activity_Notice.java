package com.example.user.bustacallfordriver.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.NoticeAdapter;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.model.Rental_List;
import com.example.user.bustacallfordriver.presenter.Activity_Notice_Presenter;


/**
 * 알림 페이지
 * Created by user on 2016-11-06.
 */
public class Activity_Notice extends BaseActivity implements View.OnClickListener {

    // 알림 설정여부 통신으로 그때그때 반영해야된다!!
    private static int NOTICE_ON_OFF = 0; // on == 0, off == 1 flag

    RelativeLayout rl_notice_button; // 알림 투글버튼같은 역할
    TextView tv_on, tv_off; // 켜기, 끄기 텍스트
    LinearLayout ll_notice_noexist; // 알림 없을때 표시해주는 바탕화면 아이콘
    ListView listView; // 알림 리스트
    Activity_Notice_Presenter presenter;
    NoticeAdapter adapter;
    AppController app;
    Rental_List noticeList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        app = (AppController) getApplicationContext();
        presenter = new Activity_Notice_Presenter(this);
        init();
    }

    private void init() {
        rl_notice_button = (RelativeLayout) findViewById(R.id.activity_notice_on_or_off_layout);
        tv_on = (TextView) findViewById(R.id.acticity_notice_on_text);
        tv_off = (TextView) findViewById(R.id.acticity_notice_off_text);
        ll_notice_noexist = (LinearLayout) findViewById(R.id.activity_notice_noexist);
        listView = (ListView) findViewById(R.id.activity_notice_listview);
        rl_notice_button.setOnClickListener(this);



        // 알림 설정 상태를 보고 처음 알림 켜기 끄기 셋팅
    }

    public void setRenewalListView() {
        if (!getNoticeList().getRental_list().isEmpty()) {
            ll_notice_noexist.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            adapter = new NoticeAdapter(getNoticeList());
            listView.setAdapter(adapter);
        } else {
            ll_notice_noexist.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_notice_on_or_off_layout: // 알림 켜기, 끄기
                setNoticeOnOff();
                break;
        }
    }

    /**
     * 알림 켜기 끄기 이미지 변경
     */
    private void setNoticeOnOff() {
        switch (NOTICE_ON_OFF) {
            case 0: // 켜기 -> 끄기
                rl_notice_button.setBackgroundResource(R.drawable.notice_off);
                tv_on.setTextColor(Color.parseColor("#2978B0"));
                tv_off.setTextColor(Color.parseColor("#FFFFFF"));
                NOTICE_ON_OFF = 1; // 끄기 flag로 바꿔줌
                break;
            case 1: // 끄기 -> 켜기
                rl_notice_button.setBackgroundResource(R.drawable.notice_on);
                tv_on.setTextColor(Color.parseColor("#FFFFFF"));
                tv_off.setTextColor(Color.parseColor("#2978B0"));
                NOTICE_ON_OFF = 0; // 켜기 flag로 바꿔줌
                break;
        }

        presenter.request_send_notice_onoff(NOTICE_ON_OFF); // 0: 끄기, 1: 켜기
    }


    public Rental_List getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(Rental_List noticeList) {
        this.noticeList = noticeList;
    }


}
