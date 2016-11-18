package com.bustacallfordriver.user.bustacallfordriver;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental_List;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Sliding_Tender;

/**
 * 사이드 > 입찰 내역 어댑터
 * Created by user on 2016-11-11.
 */
public class TenderAdapter extends BaseAdapter {

    private Context context = null;
    private Activity_Sliding_Tender activityView = null;

    private Rental_List tenderList;
    private ViewHoder viewHoder = null;
    Rental tender;
    DataSetObservable mDataSetObservable = new DataSetObservable();


    public TenderAdapter(Rental_List tender_list, Activity_Sliding_Tender view) {
        this.tenderList = tender_list;
        this.activityView = view;
        this.context = view;
    }

    public int getItemViewType_view(Rental tender) {
        int wayType = tender.getType(); // 1 : 왕복, 2: 편도
        int togetherType = tender.getTogether().getFlag(); // 0 : 기본, 2 : 같이타기
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
        return type;
    }
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        final int pos = position;
        View v = convertView;
        tender = tenderList.getRental_list().get(pos);
        int viewType = getItemViewType_view(tender);
        if (v == null) {
            viewHoder = new ViewHoder();
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
                case 3: // 편도 & 같이타키
                    v = vi.inflate(R.layout.listview_item_tender_one_way, null);
                    oneWayInit(v);
                    break;
            }

            v.setTag(viewHoder);

        } else {
            viewHoder = (ViewHoder) v.getTag();
        }

        switch (viewType) {
            case 1: // 왕복
                v.setBackgroundResource(R.drawable.listview_default_back_file);
                viewHoder.twoway_startDate.setText(tender.getDay_one());
                viewHoder.twoway_startTime.setText(tender.getTime_one());
                viewHoder.twoway_endDate.setText(tender.getDay_two());
                viewHoder.twoway_endTime.setText(tender.getTime_two());
                viewHoder.twoway_startPlace.setText(tender.getStart_point_one());
                viewHoder.twoway_endPlace.setText(tender.getEnd_point_one());
                viewHoder.twoway_price.setText(tender.getRental_money());
                viewHoder.twoway_btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rental mtender= tenderList.getRental_list().get(pos);
                        showDialog(mtender);
                    }
                });
                break;
            case 2: // 편도
                v.setBackgroundResource(R.drawable.listview_default_back_file);
                viewHoder.oneway_startDate.setText(tender.getDay_one());
                viewHoder.oneway_startTime.setText(tender.getTime_one());
                viewHoder.oneway_startPlace.setText(tender.getStart_point_one());
                viewHoder.oneway_endPlace.setText(tender.getEnd_point_one());
                viewHoder.oneway_price.setText(tender.getRental_money());
                viewHoder.oneway_btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rental mtender= tenderList.getRental_list().get(pos);
                        showDialog(mtender);
                    }
                });
                break;
            case 3: // 편도 & 같이타기
                v.setBackgroundResource(R.drawable.listview_together_back_file);
                viewHoder.oneway_startDate.setText(tender.getDay_one());
                viewHoder.oneway_startTime.setText(tender.getTime_one());
                viewHoder.oneway_startPlace.setText(tender.getStart_point_one());
                viewHoder.oneway_endPlace.setText(tender.getEnd_point_one());
                viewHoder.oneway_price.setText(tender.getRental_money());
                viewHoder.oneway_btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rental mtender= tenderList.getRental_list().get(pos);
                        showDialog(mtender);
                    }
                });
                break;
        }


        return v;
    }

    private void oneWayInit(View v) {
        viewHoder.oneway_startDate = (TextView) v.findViewById(R.id.listview_tender_one_way_startDate);
        viewHoder.oneway_startTime = (TextView) v.findViewById(R.id.listview_tender_one_way_startTime);
        viewHoder.oneway_startPlace = (TextView) v.findViewById(R.id.listview_tender_one_way_startPlace);
        viewHoder.oneway_endPlace = (TextView) v.findViewById(R.id.listview_tender_one_way_endPlace);
        viewHoder.oneway_price = (TextView) v.findViewById(R.id.listview_tender_one_way_price);
        viewHoder.oneway_btnCancle = (LinearLayout) v.findViewById(R.id.listview_tender_one_way_cancelBtn);
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
        LinearLayout oneway_btnCancle, twoway_btnCancle;
    }

    public void showDialog(final Rental tender) {
        final Dialog_base_two_button dialog = new Dialog_base_two_button(context, "정말로 입찰 취소하시겠씁니까?");
        dialog.show();
        dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // 서버에서 지우는 작업
                activityView.deleteTender(tender.getRental_num());
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


    @Override
    public void registerDataSetObserver(DataSetObserver observer){ // DataSetObserver의 등록(연결)
        mDataSetObservable.registerObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer){ // DataSetObserver의 해제
        mDataSetObservable.unregisterObserver(observer);
    }

    @Override
    public void notifyDataSetChanged(){ // 위에서 연결된 DataSetObserver를 통한 변경 확인
        mDataSetObservable.notifyChanged();
    }

    public void clearTenderList(){
        this.tenderList = null;
    }

    public void addTenderList(Rental_List tender_list) {
        this.tenderList = tender_list;
    }
}
