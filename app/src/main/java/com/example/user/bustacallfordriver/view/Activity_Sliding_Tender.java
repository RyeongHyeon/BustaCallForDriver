package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.TenderAdapter;
import com.example.user.bustacallfordriver.model.Rental_List;
import com.example.user.bustacallfordriver.presenter.Activity_Sliding_Tender_Presenter;

/**
 * 사이드 메뉴 > 입찰 내역 페이지
 * Created by user on 2016-11-12.
 */

public class Activity_Sliding_Tender extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView backBtn;
    LinearLayout noExistLayer;
    ListView listView;
    TenderAdapter adapter;
    Activity_Sliding_Tender_Presenter presenter;
    Rental_List tenderList = new Rental_List();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tender);
        app = (AppController) getApplicationContext();
        presenter = new Activity_Sliding_Tender_Presenter(this);
        presenter.request_get_tender(app.getBus().getBus_num());
        init();
    }

    private void init() {
        backBtn = (ImageView) findViewById(R.id.activity_tender_iv_back);
        noExistLayer = (LinearLayout) findViewById(R.id.activity_tender_noexist);
        listView = (ListView) findViewById(R.id.activity_tender_listview);
        backBtn.setOnClickListener(this);
    }

    public void setRenewalListView() {
        Rental_List tenderList = getTenderList();
        if (!tenderList.getRental_list().isEmpty()) {
            noExistLayer.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            if (adapter == null) {
                adapter = new TenderAdapter(tenderList, this);
                listView.setAdapter(adapter);
            } else {
                if (tenderList.getRental_list().size() > 0) {
                    adapter.clearTenderList();
                    adapter.addTenderList(tenderList);
                    adapter.notifyDataSetChanged();
                }
            }
        } else {
            noExistLayer.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
    }

    public void deleteTender(int rentalNum) {
        presenter.request_delete_tender(app.getBus().getBus_num(), rentalNum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_tender_iv_back:
                finish();
                break;
        }
    }

    public Rental_List getTenderList() {
        return tenderList;
    }

    public void setTenderList(Rental_List tenderList) {
        this.tenderList = tenderList;
    }
}
