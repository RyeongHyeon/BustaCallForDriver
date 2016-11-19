package com.bustacallfordriver.user.bustacallfordriver.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.BaseFragment;
import com.bustacallfordriver.user.bustacallfordriver.R;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental;
import com.bustacallfordriver.user.bustacallfordriver.presenter.Fragment_RentalDetail_OneWay_Together_Presenter;

/**
 * 매물 상세 내역 - 편도 & 같이타기
 * Created by user on 2016-11-10.
 */

public class Fragment_RentalDetail_OneWay_Together  extends BaseFragment implements View.OnClickListener {

    TextView tv_money, tv_enter;
    TextView tv_day, tv_time, tv_start_point, tv_end_point, tv_goal;

    Rental rental;
    AppController app;
    Fragment_RentalDetail_OneWay_Together_Presenter presenter;

    public Fragment_RentalDetail_OneWay_Together(Rental rental) {
        this.rental = rental;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rentaldetail_oneway_together, null);
        app = (AppController)getActivity().getApplicationContext();
        init(view);
        return view;
    }

    private void init(View v) {
        presenter = new Fragment_RentalDetail_OneWay_Together_Presenter(this);
        tv_money = (TextView)v.findViewById(R.id.fragment_rentaldetail_oneway_together_tv_tenderPrice);
        tv_enter = (TextView)v.findViewById(R.id.fragment_rentaldetail_oneway_together_tv_enter);
        tv_day = (TextView)v.findViewById(R.id.listview_one_way_together_top_startDate);
        tv_time =(TextView)v.findViewById(R.id.listview_one_way_together_top_startTime);
        tv_start_point = (TextView)v.findViewById(R.id.listview_one_way_together_top_startPlace);
        tv_end_point = (TextView)v.findViewById(R.id.listview_one_way_together_top_endPlace);
        tv_goal = (TextView)v.findViewById(R.id.listview_one_way_together_top_goal);

        tv_money.setText(rental.getRental_money());
        tv_day.setText(rental.getDay_one());
        tv_time.setText(rental.getTime_one());
        tv_start_point.setText(rental.getStart_point_one());
        tv_end_point.setText(rental.getEnd_point_one());
        tv_goal.setText(rental.getRental_reason());
        tv_enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_rentaldetail_oneway_together_tv_enter:
                presenter.request_send_bus(rental.getRental_num(), app.getBus().getBus_num(), rental.getRental_money());
                break;
        }
    }
}
