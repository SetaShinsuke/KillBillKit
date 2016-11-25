package com.seta.killbillkit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.seta.killbillkit.R;
import com.seta.killbillkit.framework.BaseActivity;
import com.seta.killbillkit.presenters.EditPocketPresenter;
import com.seta.killbillkit.utils.UtilMethods;
import com.seta.killbillkit.viewsInterfaces.EditPocketView;
import com.seta.setakits.ViewUtils;

import static com.seta.setakits.logs.LogX.fastLog;

/**
 * Created by Seta.Driver on 2016/11/20.
 */

public class EditPocketActivity extends BaseActivity implements EditPocketView{
    private EditPocketPresenter mPresenter;
    private View mContentView;
    private Integer[] daysOfMonth;
    private Spinner mTypeSpinner, mBillDaySpinner, mRepayDaySpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pocket);
        mContentView = findViewById(android.R.id.content);
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
        enableActionbarBack();
        mTypeSpinner = (Spinner) findViewById(R.id.type_spinner);
        String[] types = getResources().getStringArray(R.array.pocket_types);
        mTypeSpinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,types));
        daysOfMonth = UtilMethods.getDaysOfMonth();
        mBillDaySpinner = (Spinner) findViewById(R.id.bill_day_spinner);
        mRepayDaySpinner = (Spinner) findViewById(R.id.repay_day_spinner);
        ArrayAdapter<Integer> daysAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,daysOfMonth);
        mBillDaySpinner.setAdapter(daysAdapter);
        mRepayDaySpinner.setAdapter(daysAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.commit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.commit){
            //TODO:提交
            mPresenter.commitPocketData();
            ViewUtils.hideInputMethod(mContentView);
            Snackbar.make(mContentView,"提交",Snackbar.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getPocketTypeEditing() {
        return null;
    }

    @Override
    public String getNameEditing() {
        return null;
    }

    @Override
    public int getBalanceEditing() {
        return 0;
    }

    @Override
    public int getBillDayEditing() {
        return 0;
    }

    @Override
    public int getRepaymentDayEditing() {
        return 0;
    }
}
