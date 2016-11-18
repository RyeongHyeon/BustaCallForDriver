package com.bustacallfordriver.user.bustacallfordriver.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.R;


/**
 * Created by user on 2016-11-05.
 */

public class DialogListviewAdapter extends BaseAdapter {
    CharSequence[] arrayChr;
    int array;

    public DialogListviewAdapter(int array) {
        this.array = array;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final int pos = position;
        final Context context = parent.getContext();

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.dialog_listview_item, null);
        }

        arrayChr = context.getResources().getTextArray(array);
        TextView item = (TextView) v.findViewById(R.id.dialog_listview_tv_item);
        item.setText(arrayChr[pos]);
        return v;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return arrayChr[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
