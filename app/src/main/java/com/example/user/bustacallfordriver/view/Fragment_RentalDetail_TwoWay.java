package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.model.Rental;

/**
 * 매물 상세 내역 - 왕복
 * Created by user on 2016-11-10.
 */

public class Fragment_RentalDetail_TwoWay extends BaseFragment {

    Rental rental;

    public Fragment_RentalDetail_TwoWay(Rental rental) {
        this.rental = rental;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rentaldetail_twoway, null);
        init(view);
        return view;
    }

    private void init(View v) {
    }
}