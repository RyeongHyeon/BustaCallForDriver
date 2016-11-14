package com.example.user.bustacallfordriver;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-13.
 */

public class MyInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    AppController app;
    Context context;
    private ArrayList<String> profile = new ArrayList<>();

    public MyInfoAdapter(Context context, RecyclerView recyclerView, ArrayList<String> profile) {
        this.context = context;
        app = (AppController) context.getApplicationContext();
        this.profile = profile;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder userViewHolder = (ViewHolder) holder;
        Glide.with(context).load(profile.get(position)).into(userViewHolder.iv_image);
        //Glide.with(context).load(rental.getBus_list().get(position).getBus_url().get(0)).into(viewHoder.iv_profile);
    }

    @Override
    public int getItemCount() {
        return profile.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_image;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.recyclerview_item_iv_image);
        }
    }
}
