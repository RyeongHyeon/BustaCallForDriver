package com.example.user.bustacallfordriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bustacallfordriver.model.Rental;
import com.example.user.bustacallfordriver.model.Rental_List;

/**
 * Created by user on 2016-11-11.
 */

public class TenderAdapter extends BaseAdapter {

    Rental_List tenderList;

    TextView oneway_startDate, oneway_startTime, oneway_startPlace, oneway_endPlace, oneway_price;
    TextView twoway_startDate, twoway_startTime, twoway_endDate, twoway_endTime, twoway_startPlace, twoway_endPlace, twoway_price;
    LinearLayout oneway_btnCancle, twoway_btnCancle;

    public TenderAdapter(Rental_List tender_list) {
        this.tenderList = tender_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        View v = convertView;
        int viewType = tenderList.getRental_list().get(pos).getType();
        Rental tender = tenderList.getRental_list().get(pos);
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (viewType) {
                case 1: // 왕복
                    v = vi.inflate(R.layout.listview_item_tender_two_way, null);
                    twoWayInit(v);
                    twoway_startDate.setText(tender.getDay_one());
                    twoway_startTime.setText(tender.getTime_one());
                    twoway_endDate.setText(tender.getDay_two());
                    twoway_endTime.setText(tender.getTime_two());
                    twoway_startPlace.setText(tender.getStart_point_one());
                    twoway_endPlace.setText(tender.getEnd_point_one());
                    twoway_price.setText(tender.getRental_money());
                    break;
                case 2: // 편도
                    v = vi.inflate(R.layout.listview_item_tender_one_way, null);
                    oneWayInit(v);
                    oneway_startDate.setText(tender.getDay_one());
                    oneway_startTime.setText(tender.getTime_one());
                    oneway_startPlace.setText(tender.getStart_point_one());
                    oneway_endPlace.setText(tender.getEnd_point_one());
                    oneway_price.setText(tender.getRental_money());
                    break;
            }
        }



        return v;
    }

    private void oneWayInit(View v) {
        oneway_startDate = (TextView) v.findViewById(R.id.listview_tender_one_way_startDate);
        oneway_startTime = (TextView) v.findViewById(R.id.listview_tender_one_way_startTime);
        oneway_startPlace = (TextView) v.findViewById(R.id.listview_tender_one_way_startPlace);
        oneway_endPlace = (TextView) v.findViewById(R.id.listview_tender_one_way_endPlace);
        oneway_price = (TextView) v.findViewById(R.id.listview_tender_one_way_price);
        oneway_btnCancle = (LinearLayout) v.findViewById(R.id.listview_tender_one_way_cancelBtn);
        oneway_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void twoWayInit(View v) {
        twoway_startDate = (TextView) v.findViewById(R.id.listview_tender_two_way_startDate);
        twoway_startTime = (TextView) v.findViewById(R.id.listview_tender_two_way_startTime);
        twoway_endDate = (TextView) v.findViewById(R.id.listview_tender_two_way_endDate);
        twoway_endTime = (TextView) v.findViewById(R.id.listview_tender_two_way_startTime);
        twoway_startPlace = (TextView) v.findViewById(R.id.listview_tender_two_way_startPlace);
        twoway_endPlace = (TextView) v.findViewById(R.id.listview_tender_two_way_endPlace);
        twoway_price = (TextView) v.findViewById(R.id.listview_tender_two_way_price);
        twoway_btnCancle = (LinearLayout) v.findViewById(R.id.listview_tender_two_way_cancelBtn);
        twoway_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getCount() {
        return tenderList.getRental_list().size();
    }

    @Override
    public Object getItem(int position) {
        return tenderList.getRental_list().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
