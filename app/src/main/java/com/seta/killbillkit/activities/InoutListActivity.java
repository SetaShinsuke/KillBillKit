package com.seta.killbillkit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.seta.killbillkit.R;
import com.seta.killbillkit.adapters.InoutListAdapter;
import com.seta.killbillkit.api.models.Inout;
import com.seta.killbillkit.framework.BaseActivity;
import com.seta.killbillkit.mvpViews.InoutListMvpView;
import com.seta.killbillkit.presenters.InoutListPresenter;
import com.seta.setakits.logs.LogX;

import java.util.List;

/**
 * Created by SETA_WORK on 2017/3/14.
 */

public class InoutListActivity extends BaseActivity implements InoutListMvpView {
    private static final String LOG_TAG = "InoutListActivity";

    private RecyclerView mRecyclerView;
    private InoutListAdapter mAdapter;

    private InoutListPresenter mPresenter;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_single_recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar);
        toolbar.setTitle(R.string.action_manage_inouts);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new InoutListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new InoutListPresenter();
        mPresenter.attachView(this);
        mPresenter.getInouts(page);
    }


    @Override
    public void refreshList(List<Inout> inouts) {
        mAdapter.setInouts(inouts);
        LogX.d(LOG_TAG,"Inout size : " + inouts.size());
    }

    @Override
    public void loadMore(List<Inout> inouts) {
        mAdapter.addInouts(inouts);
    }
}
