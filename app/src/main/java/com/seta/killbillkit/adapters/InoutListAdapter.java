package com.seta.killbillkit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seta.killbillkit.R;
import com.seta.killbillkit.api.models.Inout;
import com.seta.killbillkit.viewHolders.InoutItemHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SETA_WORK on 2017/3/14.
 */

public class InoutListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Inout> inouts = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inout_list_item,parent,false);
        return new InoutItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        InoutItemHolder inoutItemHolder = (InoutItemHolder) holder;
        inoutItemHolder.loadData(inouts.get(inouts.size()-1-position));
    }

    @Override
    public int getItemCount() {
        return inouts.size();
    }

    public void setInouts(List<Inout> inouts) {
        this.inouts = inouts;
        notifyDataSetChanged();
    }

    public void addInouts(List<Inout> inouts){
        this.inouts.addAll(inouts);
        notifyDataSetChanged();
    }
}
