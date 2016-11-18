package com.bustacallfordriver.user.bustacallfordriver.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.BaseFragment;
import com.bustacallfordriver.user.bustacallfordriver.R;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental;
import com.bustacallfordriver.user.bustacallfordriver.presenter.Fragment_Booked_UserInfo_Together_Presenter;

/**
 * 예약된 손님 정보 > 같이타기
 * Created by user on 2016-11-15.
 */
public class Fragment_Booked_UserInfo_Together extends BaseFragment implements View.OnClickListener {

    TextView tv_name, tv_deposit, tv_phoneNum, tv_callBtn;
    Rental rental;

    Fragment_Booked_UserInfo_Together_Presenter presenter;

    public Fragment_Booked_UserInfo_Together(Rental rental) {
        this.rental = rental;
        Log.d("test",rental.toString());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_userinfo_together, null);
        presenter = new Fragment_Booked_UserInfo_Together_Presenter(this);
        init(view);
        return view;
    }

    private void init(View v) {
        tv_name = (TextView)v.findViewById(R.id.fragment_booked_userinfo_together_tv_name);
        tv_deposit = (TextView)v.findViewById(R.id.fragment_booked_userinfo_together_tv_deposit);
        tv_phoneNum = (TextView)v.findViewById(R.id.fragment_booked_userinfo_together_tv_phoneNum);
        tv_callBtn = (TextView)v.findViewById(R.id.fragment_booked_userinfo_together_tv_call);
        tv_callBtn.setOnClickListener(this);
        presenter.request_get_booked_userinfo(rental.getNickname());
    }


    public void set_Info(String phoneNum) {
        tv_name.setText(rental.getNickname());
        String depositStr = String.valueOf(Integer.valueOf(rental.getRental_money())/10);
        tv_deposit.setText(depositStr);

        tv_phoneNum.setText(phoneNum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_booked_userinfo_together_tv_call:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tv_phoneNum.getText().toString().trim()));
                startActivity(intent);
                break;
        }
    }
}
