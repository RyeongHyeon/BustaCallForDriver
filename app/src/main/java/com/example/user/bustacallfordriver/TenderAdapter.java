package com.example.user.bustacallfordriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.example.user.bustacallfordriver.model.Rental;
import com.example.user.bustacallfordriver.model.Rental_List;
import com.example.user.bustacallfordriver.presenter.Activity_Sliding_Tender_Presenter;
import com.example.user.bustacallfordriver.view.Activity_Sliding_Tender;

/**
 * 사이드 > 입찰 내역 어댑터
 * Created by user on 2016-11-11.
 */
public class TenderAdapter extends BaseAdapter {

    private Context context = null;
    private View v = null;
    private Activity_Sliding_Tender activityView = null;
    private AppController app;

    private Rental_List tenderList;
    private ViewHoder viewHoder = null;
    Rental tender;

    public TenderAdapter(Rental_List tender_list, Activity_Sliding_Tender view) {
        this.tenderList = tender_list;
        this.activityView = view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        context = parent.getContext();
        v = convertView;
        app = (AppController)context.getApplicationContext();
        tender = tenderList.getRental_list().get(pos);

        int viewType = tender.getType();
        if (v == null) {
            viewHoder = new ViewHoder();
        } else {
            viewHoder = (ViewHoder) v.getTag();
        }

        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (viewType) {
            case 1: // 왕복
                v = vi.inflate(R.layout.listview_item_tender_two_way, null);
                twoWayInit(v);
                break;
            case 2: // 편도
                v = vi.inflate(R.layout.listview_item_tender_one_way, null);
                oneWayInit(v);
                break;
        }


        switch (viewType) {
            case 1: // 왕복
                viewHoder.twoway_startDate.setText(tender.getDay_one());
                viewHoder.twoway_startTime.setText(tender.getTime_one());
                viewHoder.twoway_endDate.setText(tender.getDay_two());
                viewHoder.twoway_endTime.setText(tender.getTime_two());
                viewHoder.twoway_startPlace.setText(tender.getStart_point_one());
                viewHoder.twoway_endPlace.setText(tender.getEnd_point_one());
                viewHoder.twoway_price.setText(tender.getRental_money());
                break;
            case 2: // 편도
                viewHoder.oneway_startDate.setText(tender.getDay_one());
                viewHoder.oneway_startTime.setText(tender.getTime_one());
                viewHoder.oneway_startPlace.setText(tender.getStart_point_one());
                viewHoder.oneway_endPlace.setText(tender.getEnd_point_one());
                viewHoder.oneway_price.setText(tender.getRental_money());
                break;
        }

        v.setTag(viewHoder);

        return v;
    }

    private void oneWayInit(View v) {
        viewHoder.oneway_startDate = (TextView) v.findViewById(R.id.listview_tender_one_way_startDate);
        viewHoder.oneway_startTime = (TextView) v.findViewById(R.id.listview_tender_one_way_startTime);
        viewHoder.oneway_startPlace = (TextView) v.findViewById(R.id.listview_tender_one_way_startPlace);
        viewHoder.oneway_endPlace = (TextView) v.findViewById(R.id.listview_tender_one_way_endPlace);
        viewHoder.oneway_price = (TextView) v.findViewById(R.id.listview_tender_one_way_price);
        viewHoder.oneway_btnCancle = (LinearLayout) v.findViewById(R.id.listview_tender_one_way_cancelBtn);
        viewHoder.oneway_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityView.showDialog();
            }
        });
    }

    private void twoWayInit(View v) {
        viewHoder.twoway_startDate = (TextView) v.findViewById(R.id.listview_tender_two_way_startDate);
        viewHoder.twoway_startTime = (TextView) v.findViewById(R.id.listview_tender_two_way_startTime);
        viewHoder.twoway_endDate = (TextView) v.findViewById(R.id.listview_tender_two_way_endDate);
        viewHoder.twoway_endTime = (TextView) v.findViewById(R.id.listview_tender_two_way_startTime);
        viewHoder.twoway_startPlace = (TextView) v.findViewById(R.id.listview_tender_two_way_startPlace);
        viewHoder.twoway_endPlace = (TextView) v.findViewById(R.id.listview_tender_two_way_endPlace);
        viewHoder.twoway_price = (TextView) v.findViewById(R.id.listview_tender_two_way_price);
        viewHoder.twoway_btnCancle = (LinearLayout) v.findViewById(R.id.listview_tender_two_way_cancelBtn);
        viewHoder.twoway_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityView.showDialog();
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

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHoder {
        TextView oneway_startDate, oneway_startTime, oneway_startPlace, oneway_endPlace, oneway_price;
        TextView twoway_startDate, twoway_startTime, twoway_endDate, twoway_endTime, twoway_startPlace, twoway_endPlace, twoway_price;
        public LinearLayout oneway_btnCancle, twoway_btnCancle;
    }

    public void showDialog(final Activity_Sliding_Tender_Presenter presenter) {
        final Dialog_base_two_button dialog = new Dialog_base_two_button(context, "정말로 입찰 취소하시겠씁니까?");
        dialog.show();
        dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // 서버에서 지우는 작업
                System.out.println();
                presenter.request_delete_tender(app.getBus().getBus_num(), tender.getRental_num());
                dialog.dismiss();
            }
        });

        dialog.getTv_cancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
