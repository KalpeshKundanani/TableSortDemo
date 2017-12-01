package com.example.android.tablesortdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kalpesh B Kundanani on 30-Nov-17.
 */

public class ScripListAdapter extends RecyclerView.Adapter<ScripListAdapter.ViewHolder> {


    ArrayList<Scrip> dataList;

    public ScripListAdapter(ArrayList<Scrip> dataList) {
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvScrip, tvClose, tvChg, tvVol;

        public ViewHolder(View itemView) {
            super(itemView);
            tvScrip = itemView.findViewById(R.id.tv_scrip);
            tvClose = itemView.findViewById(R.id.tv_close);
            tvChg = itemView.findViewById(R.id.tv_chg);
            tvVol = itemView.findViewById(R.id.tv_vol);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scrip_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Scrip scrip = dataList.get(position);
        holder.tvScrip.setText(scrip.getScrip());
        holder.tvClose.setText(String.valueOf(scrip.getClose()));
        holder.tvChg.setText(String.valueOf(scrip.getChange()));
        holder.tvVol.setText(String.valueOf(scrip.getVolume()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public ArrayList<Scrip> getDataList() {
        return dataList;
    }
}
