package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.TenderAdapter;

/**
 * 사이드 메뉴 > 입찰 내역 페이지
 * Created by user on 2016-11-12.
 */

public class Activity_Sliding_Tender extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView backBtn;
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tender);
        app = (AppController)getApplicationContext();
        init();
    }

    private void init() {
        backBtn = (ImageView)findViewById(R.id.activity_tender_iv_back);
        listView = (ListView)findViewById(R.id.activity_tender_listview);
        backBtn.setOnClickListener(this);
        TenderAdapter adapter= new TenderAdapter(app.getRental_list());
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_tender_iv_back:
                finish();
                break;
        }
    }
}
