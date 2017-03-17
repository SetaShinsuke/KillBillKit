package com.seta.killbillkit.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.seta.killbillkit.R;
import com.seta.killbillkit.api.models.Inout;
import com.seta.setakits.DateUtils;

/**
 * Created by SETA_WORK on 2017/3/14.
 */

public class InoutItemHolder extends RecyclerView.ViewHolder {
    private TextView mTvCoverText,mTvTitle,mTvDate,mTvAmount;

    public InoutItemHolder(View itemView) {
        super(itemView);
        mTvCoverText = (TextView) itemView.findViewById(R.id.cover_text);
        mTvTitle = (TextView) itemView.findViewById(R.id.title);
        mTvDate = (TextView) itemView.findViewById(R.id.date);
        mTvAmount = (TextView) itemView.findViewById(R.id.amount);
    }

    public void loadData(Inout inout){
//        mTvCoverText.setText( inout.getPocketId() );
        mTvTitle.setText(inout.getTitle());
        mTvDate.setText(DateUtils.formatYYYYMMDD(inout.getCreatedAt()) );
        mTvAmount.setText( inout.getAmount() + "" );
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }
}
