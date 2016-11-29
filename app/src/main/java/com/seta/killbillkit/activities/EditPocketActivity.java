package com.seta.killbillkit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.seta.killbillkit.R;
import com.seta.killbillkit.api.KApi;
import com.seta.killbillkit.api.models.Pocket;
import com.seta.killbillkit.framework.BaseActivity;
import com.seta.killbillkit.presenters.EditPocketPresenter;
import com.seta.killbillkit.utils.UtilMethods;
import com.seta.killbillkit.viewsInterfaces.EditPocketView;
import com.seta.setakits.ViewUtils;

import static com.seta.killbillkit.api.models.Pocket.TYPE_CREDIT;
import static com.seta.killbillkit.api.models.Pocket.pocketTypes;

/**
 * Created by Seta.Driver on 2016/11/20.
 */

public class EditPocketActivity extends BaseActivity implements EditPocketView{
    private EditPocketPresenter mPresenter;
    private View mContentView;
    private Integer[] daysOfMonth;
    private Spinner mTypeSpinner, mBillDaySpinner, mRepayDaySpinner;
    private TextInputLayout mPocketNameText, mBalanceText;
    private Pocket mPocket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pocket);
        mContentView = findViewById(android.R.id.content);
        initViews();
        initData();
        initListeners();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        enableActionbarBack();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                finish();
            }
        });

        mPocketNameText = (TextInputLayout) findViewById(R.id.pocket_name);
        mBalanceText = (TextInputLayout) findViewById(R.id.balance);

        mTypeSpinner = (Spinner) findViewById(R.id.type_spinner);
        String[] types = pocketTypes;
        mTypeSpinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,types));
        daysOfMonth = UtilMethods.getDaysOfMonth();
        mBillDaySpinner = (Spinner) findViewById(R.id.bill_day_spinner);
        mRepayDaySpinner = (Spinner) findViewById(R.id.repay_day_spinner);
        ArrayAdapter<Integer> daysAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,daysOfMonth);
        mBillDaySpinner.setAdapter(daysAdapter);
        mRepayDaySpinner.setAdapter(daysAdapter);

        findViewById(R.id.bill_day_spinner_layout).setVisibility(View.GONE);
        findViewById(R.id.repay_day_spinner_layout).setVisibility(View.GONE);
    }

    private void initData() {
        if (getIntent().getExtras() != null) {
            String pocketId = getIntent().getExtras().getString("pocket_id");
            mPocket = KApi.getApi().getPocketContainer().getUniqueTFromMem(pocketId);
        }
        //TODO:初始化显示数据
        if(mPocket!=null){

        }
        mPresenter = new EditPocketPresenter(this);
        mPresenter.init(mPocket);
    }

    private void initListeners() {
        mTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = getPocketTypeEditing();
                findViewById(R.id.bill_day_spinner_layout).setVisibility(View.GONE);
                findViewById(R.id.repay_day_spinner_layout).setVisibility(View.GONE);
                if(type.equals(TYPE_CREDIT)) {
                    findViewById(R.id.bill_day_spinner_layout).setVisibility(View.VISIBLE);
                    findViewById(R.id.repay_day_spinner_layout).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        mTypeSpinner.getOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String type = getPocketTypeEditing();
//                mBillDaySpinner.setVisibility(View.GONE);
//                mRepayDaySpinner.setVisibility(View.GONE);
//                if(type.equals(TYPE_CREDIT)){
//                    mBillDaySpinner.setVisibility(View.VISIBLE);
//                    mRepayDaySpinner.setVisibility(View.VISIBLE);
//                }
//            }
//        });
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
        String[] types = Pocket.pocketTypes;
        int index = mTypeSpinner.getSelectedItemPosition();
        return types[index];
    }

    @Override
    public String getNameEditing() {
        return UtilMethods.getContent(mPocketNameText);
    }

    @Override
    public int getBalanceEditing() {
        String blcStr = UtilMethods.getContent(mBalanceText);
        if(blcStr!=null){
            return Integer.parseInt(blcStr);
        }
        return 0;
    }

    @Override
    public int getBillDayEditing() {
        return daysOfMonth[mBillDaySpinner.getSelectedItemPosition()];
    }

    @Override
    public int getRepaymentDayEditing() {
        return daysOfMonth[mRepayDaySpinner.getSelectedItemPosition()];
    }

    @Override
    public void onCommitSuccess() {
        ViewUtils.showToast(this,"提交成功!");
        finish();
    }

    @Override
    public void onCommitFail() {
        ViewUtils.showToast(this,"提交失败,请重试!");
    }
}
