package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;

/**
 * 메물 상세 내역 - 합승 아닌것.
 * Created by user on 2016-11-08.
 */
public class Fragment_RentalDetail_Default extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rentaldetail_default, null);
        init(view);
        return view;
    }

    private void init(View view) {

    }
}
