package com.bustacallfordriver.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.R;
import com.bustacallfordriver.user.bustacallfordriver.RentalAdapter;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental_List;
import com.bustacallfordriver.user.bustacallfordriver.presenter.Activity_Main_Presenter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Activity_Main extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    AppController app;
    SlidingMenu menu;
    ImageView iv_menu, iv_notiIcon;
    Spinner sp_region; // 지역 선택 스피너
    TextView tv_workArea; // 나의 영업지역 버튼
    LinearLayout noExistLayer;
    ListView listView;
    RentalAdapter adapter;

    Rental_List rentalList;

    Activity_Main_Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (AppController) getApplicationContext();
        presenter = new Activity_Main_Presenter(this);
        init();
        setMenu();
    }

    private void init() {
        iv_menu = (ImageView) findViewById(R.id.activity_main_iv_menu);
        iv_notiIcon = (ImageView) findViewById(R.id.activity_main_iv_noti);
        sp_region = (Spinner) findViewById(R.id.activity_main_sp_region);
        tv_workArea = (TextView) findViewById(R.id.activity_main_tv_workArea);
        listView = (ListView) findViewById(R.id.activity_main_listview);
        noExistLayer = (LinearLayout) findViewById(R.id.activity_main_noexist);
        iv_menu.setOnClickListener(this);
        iv_notiIcon.setOnClickListener(this);
        tv_workArea.setOnClickListener(this);
        listView.setOnItemClickListener(this);

        presenter.request_get_rental();

//
//        if (app.getRental_list().getRental_list().size() > 0) {
//            adapter = new RentalAdapter(app.getRental_list(), this);
//            listView.setAdapter(adapter);
//            listView.setOnItemClickListener(this);
//        }

        ArrayAdapter regionAdapter = ArrayAdapter.createFromResource(this, R.array.main_workArea, android.R.layout.simple_spinner_item);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_region.setAdapter(regionAdapter);

        sp_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setRentalList_SpinnerRegion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void setMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.fragment_sliding_menu);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sliding_menu_frame, SlidingMenuFragment.newInstance())
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_iv_menu:
                goTomenu();
                break;
            case R.id.activity_main_iv_noti: // 알림 아이콘
                Intent intent = new Intent(this, Activity_Notice.class);
                startActivity(intent);
                break;
            case R.id.activity_main_tv_workArea:
//                presenter.request_get_rental_region(app.getBus().getRegion());
                ArrayAdapter myAdap = (ArrayAdapter) sp_region.getAdapter();
                int spinnerPosition = myAdap.getPosition(app.getBus().getRegion());
                sp_region.setSelection(spinnerPosition);
                break;
        }
    }

    @Override
    public void onBackPressed() {//종료 버튼
        final Dialog_base_two_button dialog_base_two_button = new Dialog_base_two_button(this, "앱을 종료하시겠습니까?");
        dialog_base_two_button.show();
        dialog_base_two_button.getTv_enter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        dialog_base_two_button.getTv_cancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_base_two_button.dismiss();
            }
        });
    }

    /**
     * 리스트뷰 클릭 시 매물 상세 페이지 이동
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplication(), Activity_RentalDetail.class);

        Rental rental = getRental_list().getRental_list().get(position);
        int wayType = rental.getType(); // 1 : 왕복, 2: 편도
        int togetherType = rental.getTogether().getFlag(); // 0 : 기본, 2 : 같이타기
        int type = 0;
        if (wayType == 1 && togetherType == 0) { // 왕복 & 기본
            type = 1;
        } else if (wayType == 2 && togetherType == 0) { // 편도 & 기본
            type = 2;
        } else if (wayType == 2 && togetherType == 2) { // 편도 & 같이타기
            type = 3;
        } else {
            Log.d("error: ", "없는 type");
        }

        intent.putExtra("type", type);
        intent.putExtra("info", rental);

        startActivity(intent);
    }

    public void goTomenu() {
        menu.showMenu();
    }

    // 지금부터 고친다
    public void setRenewalListView() {
        Rental_List rentalList = getRental_list();
        if (!rentalList.getRental_list().isEmpty()) {
            noExistLayer.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            if (adapter == null) {
                adapter = new RentalAdapter(rentalList, this);
            } else {
                adapter.clearRentalList();
                adapter.addRentalList(rentalList);
                adapter.notifyDataSetChanged();
            }
            listView.setAdapter(adapter);
        } else {
            noExistLayer.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
    }

    public Rental_List getRental_list() {
        return rentalList;
    }

    public void setRental_list(Rental_List rentalList) {
        this.rentalList = rentalList;
    }

    /**
     * 스피너에 등록된 지역으로 탐색
     */
    private void setRentalList_SpinnerRegion() {
        String selItem = (String) sp_region.getSelectedItem();
        if (!selItem.equals("전체")) {
            presenter.request_get_rental_region(selItem);
        } else {
            presenter.request_get_rental();
        }
    }
}
