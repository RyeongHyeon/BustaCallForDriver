package com.bustacallfordriver.user.bustacallfordriver;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.model.Rental;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental_List;

/**
 * 메인 > 매물 리스트 어댑터
 * Created by user on 2016-11-07.
 */
public class RentalAdapter extends BaseAdapter {

    private Rental_List rentalList; //  렌탈리스트 가져오는 레트로핏 클래스
    ViewHoder viewHoder = null;
    Context context;
    DataSetObservable mDataSetObservable = new DataSetObservable();


    public RentalAdapter(Rental_List rental_list, Context context) {
        this.rentalList = rental_list;
        this.context = context;
    }

    public int getItemViewType_view(Rental rental) {
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
        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        View v = convertView;
        Rental rental = rentalList.getRental_list().get(pos);
        int viewType = getItemViewType_view(rental);

        if (v == null) {
            viewHoder = new ViewHoder();
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (viewType) {
                case 1: // 왕복 & 기본
                    v = vi.inflate(R.layout.listview_item_two_way, parent, false);
                    twoWayInit(v);
                    break;
                case 2: // 편도 & 기본
                    v = vi.inflate(R.layout.listview_item_one_way, parent, false);
                    oneWayInit(v);
                    break;
                case 3: // 편도 & 같이타기
                    v = vi.inflate(R.layout.listview_item_one_way_together, parent, false);
                    oneWayTogetherInit(v);
                    break;
            }

            v.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) v.getTag();
        }


        switch (viewType) {
            case 1: // 왕복 & 기본
                v.setBackgroundResource(R.drawable.listview_default_back_file);
                viewHoder.twoway_startDate.setText(rental.getDay_one());
                viewHoder.twoway_startTime.setText(rental.getTime_one());
                viewHoder.twoway_endDate.setText(rental.getDay_two());
                viewHoder.twoway_endTime.setText(rental.getTime_two());
                viewHoder.twoway_startPlace.setText(rental.getStart_point_one());
                viewHoder.twoway_endPlace.setText(rental.getEnd_point_one());
                viewHoder.twoway_goal.setText(rental.getRental_reason());
                viewHoder.twoway_userCnt.setText(rental.getUser_count());

                break;
            case 2: // 편도 & 기본
                v.setBackgroundResource(R.drawable.listview_default_back_file);
                viewHoder.oneway_startDate.setText(rental.getDay_one());
                viewHoder.oneway_startTime.setText(rental.getTime_one());
                viewHoder.oneway_startPlace.setText(rental.getStart_point_one());
                viewHoder.oneway_endPlace.setText(rental.getEnd_point_one());
                viewHoder.oneway_goal.setText(rental.getRental_reason());
                viewHoder.oneway_userCnt.setText(rental.getUser_count());
                break;
            case 3: // 편도 & 같이타기
                v.setBackgroundResource(R.drawable.listview_together_back_file);
                viewHoder.oneway_together_startDate.setText(rental.getDay_one());
                viewHoder.oneway_together_startTime.setText(rental.getTime_one());
                viewHoder.oneway_together_startPlace.setText(rental.getStart_point_one());
                viewHoder.oneway_together_endPlace.setText(rental.getEnd_point_one());
                viewHoder.oneway_together_goal.setText(rental.getRental_reason());
                viewHoder.oneway_together_price.setText(rental.getTogether().getMoney());
                break;
        }

        return v;
    }

    private void oneWayInit(View v) {
        viewHoder.oneway_startDate = (TextView) v.findViewById(R.id.listview_one_way_startDate);
        viewHoder.oneway_startTime = (TextView) v.findViewById(R.id.listview_one_way_startTime);
        viewHoder.oneway_startPlace = (TextView) v.findViewById(R.id.listview_one_way_startPlace);
        viewHoder.oneway_endPlace = (TextView) v.findViewById(R.id.listview_one_way_endPlace);
        viewHoder.oneway_goal = (TextView) v.findViewById(R.id.listview_one_way_goal);
        viewHoder.oneway_userCnt = (TextView) v.findViewById(R.id.listview_one_way_userCnt);
    }

    private void oneWayTogetherInit(View v) {
        viewHoder.oneway_together_startDate = (TextView) v.findViewById(R.id.listview_one_way_together_top_startDate);
        viewHoder.oneway_together_startTime = (TextView) v.findViewById(R.id.listview_one_way_together_top_startTime);
        viewHoder.oneway_together_startPlace = (TextView) v.findViewById(R.id.listview_one_way_together_top_startPlace);
        viewHoder.oneway_together_endPlace = (TextView) v.findViewById(R.id.listview_one_way_together_top_endPlace);
        viewHoder.oneway_together_goal = (TextView) v.findViewById(R.id.listview_one_way_together_top_goal);
        viewHoder.oneway_together_price = (TextView) v.findViewById(R.id.listview_one_way_together_bottom_price);
    }

    private void twoWayInit(View v) {
        viewHoder.twoway_startDate = (TextView) v.findViewById(R.id.listview_two_way_startDate);
        viewHoder.twoway_startTime = (TextView) v.findViewById(R.id.listview_two_way_startTime);
        viewHoder.twoway_endDate = (TextView) v.findViewById(R.id.listview_two_way_endDate);
        viewHoder.twoway_endTime = (TextView) v.findViewById(R.id.listview_two_way_endTime);
        viewHoder.twoway_startPlace = (TextView) v.findViewById(R.id.listview_two_way_startPlace);
        viewHoder.twoway_endPlace = (TextView) v.findViewById(R.id.listview_two_way_endPlace);
        viewHoder.twoway_goal = (TextView) v.findViewById(R.id.listview_two_way_goal);
        viewHoder.twoway_userCnt = (TextView) v.findViewById(R.id.listview_two_way_userCnt);
    }

    @Override
    public int getCount() {
        return rentalList.getRental_list().size();
    }

    @Override
    public Object getItem(int position) {
        return rentalList.getRental_list().get(position);
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
        TextView oneway_startDate, oneway_startTime, oneway_together_startDate, oneway_together_startTime; // 출발날짜, 출발시간 (출발할때)
        TextView twoway_startDate, twoway_startTime; // 출발날짜, 출발시간 (출발할때)
        TextView twoway_endDate, twoway_endTime; // 출발날짜, 출발시간 (돌아올때)
        TextView oneway_startPlace, oneway_endPlace, oneway_together_startPlace, oneway_together_endPlace; // 출발지, 도착지
        TextView twoway_startPlace, twoway_endPlace; // 출발지, 도착지
        TextView oneway_goal, oneway_together_goal, twoway_goal;// 목적
        TextView oneway_userCnt, twoway_userCnt; // 탑승자수
        TextView oneway_together_price; // 같이타기 가격
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


    public void clearRentalList(){
        this.rentalList = null;
    }

    public void addRentalList(Rental_List rental_list) {
        this.rentalList = rental_list;
    }
}
