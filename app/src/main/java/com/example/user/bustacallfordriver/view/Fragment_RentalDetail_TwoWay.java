package com.example.user.bustacallfordriver.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.model.Rental;
import com.example.user.bustacallfordriver.presenter.Fragment_RentalDetail_TwoWay_Presenter;

import java.util.ArrayList;

/**
 * 매물 상세 내역 - 왕복
 * Created by user on 2016-11-10.
 */

public class Fragment_RentalDetail_TwoWay extends BaseFragment implements View.OnClickListener{

    TextView tv_day,tv_time,tv_start_point,tv_end_point,tv_goal,tv_user,tv_enter;
    TextView tv_toll_inc,tv_toll_out,tv_park_inc,tv_park_out; //총 6개의 money_list(톨비 등등)
    TextView tv_food_inc,tv_food_out,tv_motel_inc,tv_motel_out;
    TextView tv_vol_inc,tv_vol_out,tv_tax_inc,tv_tax_out;
    int isExcludeToll=1, isExcludePark=1, isExcludeFood=1;// 톨비, 주차, 식사,
    int isExcludeMotel=1, isExcludeVol=1, isExcludeTax=1; // 숙박, 봉사, 부가세
    ArrayList<Integer> money_list = new ArrayList<>();

    Rental rental;
    EditText et_money;
    boolean is_money;
    AppController app;
    Fragment_RentalDetail_TwoWay_Presenter presenter;

    public Fragment_RentalDetail_TwoWay(Rental rental) {
        this.rental = rental;
        Log.d("test",rental.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rentaldetail_twoway, null);
        init(view);
        return view;
    }

    private void init(View v) {
        app = (AppController)getActivity().getApplicationContext();
        presenter = new Fragment_RentalDetail_TwoWay_Presenter(this);
        tv_day = (TextView)v.findViewById(R.id.listview_two_way_startDate);
        tv_time =(TextView)v.findViewById(R.id.listview_two_way_startTime);
        tv_start_point = (TextView)v.findViewById(R.id.listview_two_way_startPlace);
        tv_end_point = (TextView)v.findViewById(R.id.listview_two_way_endPlace);
        tv_goal = (TextView)v.findViewById(R.id.listview_two_way_goal);
        tv_user = (TextView)v.findViewById(R.id.listview_two_way_userCnt);
        et_money = (EditText)v.findViewById(R.id.fragment_rentaldetail_twoway_et_tenderPrice);
        et_money.addTextChangedListener(tw_money);
        tv_enter = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_enter);
        tv_toll_inc = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_toll_include);
        tv_toll_out = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_toll_exclude);
        tv_park_inc = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_park_include);
        tv_park_out = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_park_exclude);
        tv_food_inc = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_food_include);
        tv_food_out = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_food_exclude);
        tv_motel_inc = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_motel_include);
        tv_motel_out = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_motel_exclude);
        tv_vol_inc = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_vol_include);
        tv_vol_out = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_vol_exclude);
        tv_tax_inc = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_tax_include);
        tv_tax_out = (TextView)v.findViewById(R.id.fragment_rentaldetail_twoway_tv_tax_exclude);

        tv_toll_inc.setOnClickListener(this);
        tv_toll_out.setOnClickListener(this);
        tv_park_inc.setOnClickListener(this);
        tv_park_out.setOnClickListener(this);
        tv_food_inc.setOnClickListener(this);
        tv_food_out.setOnClickListener(this);
        tv_motel_inc.setOnClickListener(this);
        tv_motel_out.setOnClickListener(this);
        tv_vol_inc.setOnClickListener(this);
        tv_vol_out.setOnClickListener(this);
        tv_tax_inc.setOnClickListener(this);
        tv_tax_out.setOnClickListener(this);

        setEnableTextView(tv_enter,false);
        tv_enter.setOnClickListener(this);
        tv_day.setText(rental.getDay_one());
        tv_time.setText(rental.getTime_one());
        tv_start_point.setText(rental.getStart_point_one());
        tv_end_point.setText(rental.getEnd_point_one());
        tv_goal.setText(rental.getRental_reason());
        tv_user.setText(rental.getUser_count());
    }


    TextWatcher tw_money = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            is_money= true;
            checkButton();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void checkButton()
    {
        if(is_money==true){
            setEnableTextView(tv_enter,true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_rentaldetail_oneway_tv_toll_include:
                tv_toll_inc.setTextColor(Color.parseColor("#2978B0")); // 파랑
                tv_toll_out.setTextColor(Color.parseColor("#dcdddd")); // 회색
                isExcludeToll = 1;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_toll_exclude:
                tv_toll_inc.setTextColor(Color.parseColor("#dcdddd"));
                tv_toll_out.setTextColor(Color.parseColor("#cc5555"));
                isExcludeToll = 0;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_park_include:
                tv_park_inc.setTextColor(Color.parseColor("#2978B0"));
                tv_park_out.setTextColor(Color.parseColor("#dcdddd"));
                isExcludePark = 1;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_park_exclude:
                tv_park_inc.setTextColor(Color.parseColor("#dcdddd"));
                tv_park_out.setTextColor(Color.parseColor("#cc5555"));
                isExcludePark = 0;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_food_include:
                tv_food_inc.setTextColor(Color.parseColor("#2978B0"));
                tv_food_out.setTextColor(Color.parseColor("#dcdddd"));
                isExcludeFood = 1;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_food_exclude:
                tv_food_inc.setTextColor(Color.parseColor("#dcdddd"));
                tv_food_out.setTextColor(Color.parseColor("#cc5555"));
                isExcludeFood = 0;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_motel_include:
                tv_motel_inc.setTextColor(Color.parseColor("#2978B0"));
                tv_motel_out.setTextColor(Color.parseColor("#dcdddd"));
                isExcludeMotel = 1;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_motel_exclude:
                tv_motel_inc.setTextColor(Color.parseColor("#dcdddd"));
                tv_motel_out.setTextColor(Color.parseColor("#cc5555"));
                isExcludeMotel = 0;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_vol_include:
                tv_vol_inc.setTextColor(Color.parseColor("#2978B0"));
                tv_vol_out.setTextColor(Color.parseColor("#dcdddd"));
                isExcludeVol = 1;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_vol_exclude:
                tv_vol_inc.setTextColor(Color.parseColor("#dcdddd"));
                tv_vol_out.setTextColor(Color.parseColor("#cc5555"));
                isExcludeVol = 0;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_tax_include:
                tv_tax_inc.setTextColor(Color.parseColor("#2978B0"));
                tv_tax_out.setTextColor(Color.parseColor("#dcdddd"));
                isExcludeTax = 1;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_tax_exclude:
                tv_tax_inc.setTextColor(Color.parseColor("#dcdddd"));
                tv_tax_out.setTextColor(Color.parseColor("#cc5555"));
                isExcludeTax = 0;
                break;
            case R.id.fragment_rentaldetail_oneway_tv_enter:
                //통신
                money_list.add(isExcludeToll);
                money_list.add(isExcludePark);
                money_list.add(isExcludeFood);
                money_list.add(isExcludeMotel);
                money_list.add(isExcludeVol);
                money_list.add(isExcludeTax);
                presenter.request_send_bus(rental.getRental_num(),app.getBus().getBus_num(),et_money.getText().toString(),money_list);
                getActivity().finish();
                break;
        }
    }
}