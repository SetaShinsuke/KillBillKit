package com.seta.killbillkit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.seta.killbillkit.R;
import com.seta.killbillkit.framework.BaseActivity;
import com.seta.killbillkit.presenters.EditPocketPresenter;
import com.seta.killbillkit.viewsInterfaces.EditPocketView;

/**
 * Created by Seta.Driver on 2016/11/20.
 */

public class EditPocketActivity extends BaseActivity implements EditPocketView{
    private EditPocketPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pocket);
        initViews();
        initData();
    }

    private void initData() {
        mPresenter = new EditPocketPresenter(this);
        mPresenter.init();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner typeSpinner = (Spinner) findViewById(R.id.type_spinner);
        String[] types = getResources().getStringArray(R.array.pocket_types);
        typeSpinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,types));
    }

}
