package com.seta.killbillkit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.seta.killbillkit.R;
import com.seta.killbillkit.events.InoutEvent;
import com.seta.killbillkit.events.PocketEditEvent;
import com.seta.killbillkit.framework.BaseActivity;
import com.seta.killbillkit.mvpViews.MainView;
import com.seta.killbillkit.presenters.MainPresenter;
import com.seta.killbillkit.views.dialogs.CreateInoutDialog;
import com.seta.setakits.logs.LogX;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import static com.seta.setakits.logs.LogX.fastLog;


public class MainActivity extends BaseActivity
        implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private MainPresenter mMainPresenter;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFab;
    private TextView mTotalTextView;
    private DialogFragment mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeBackEnable(false);
        initViews();
        initData();

        EventBus.getDefault().register(this);
    }

    private void initViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTotalTextView = (TextView) findViewById(R.id.total_account);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Click",Snackbar.LENGTH_SHORT).show();
                mMainPresenter.onFabClick();
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initData(){
        this.mMainPresenter = new MainPresenter(this);
        this.mMainPresenter.init();
        refreshNavPocketMenu(mMainPresenter.getPocketNames());
    }

    @Override
    public void refreshTotalText(String amountString) {
        mTotalTextView.setText(amountString);
    }

    @Override
    public void refreshInoutsAll(String allInouts) {
        ((TextView)findViewById(R.id.inouts)).setText(allInouts);
    }

    @Override
    public void refreshFab(boolean showBoth) {
        FloatingActionButton.OnVisibilityChangedListener listener = new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(FloatingActionButton fab) {
                super.onShown(fab);
            }

            @Override
            public void onHidden(FloatingActionButton fab) {
                super.onHidden(fab);
                fab.show();
            }
        };
        mFab.hide(listener);
        if(showBoth){
            mFab.setImageResource(R.drawable.ic_sentiment_neutral_white_48dp);
            mFab.setBackgroundResource(R.color.colorPrimary);
        }else {
            mFab.setImageResource(R.drawable.ic_sentiment_satisfied_white_48dp);
            mFab.setBackgroundResource(R.color.colorAccent);
        }
    }

//    @Override
    public void refreshNavPocketMenu(ArrayList<String> titles) {
        Menu navMenu = mNavigationView.getMenu();
        int groupId = R.id.pocket_menu_group;
        Menu pocketListMenu = navMenu.getItem(0).getSubMenu();
        pocketListMenu.clear();
        for(int i=0;i<titles.size();i++){
            pocketListMenu.add(groupId,i,i,titles.get(i));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PocketEditEvent event){
        refreshNavPocketMenu(mMainPresenter.getPocketNames());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(InoutEvent inoutEvent){
        mMainPresenter.loadInouts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        fastLog("Click menu : " + id);

        if(item.getGroupId()==R.id.pocket_menu_group){
            mMainPresenter.onPocketMenuItemClick(item.getOrder());
            return true;
        }

        //TODO:添加/移除 pocket
        if(id == R.id.action_add_payment){ //支出
            CreateInoutDialog dialog = new CreateInoutDialog();
            mDialog = dialog;
            dialog.setType(true);
            dialog.show(this);
            return true;
        }else if(id == R.id.action_add_income) {
            CreateInoutDialog dialog = new CreateInoutDialog();
            mDialog = dialog;
            dialog.setType(false);
            dialog.show(this);
            return true;
        }else if(id==R.id.action_add_budget) {
            return true;
        }else if (id == R.id.action_add_pockets) {
            Intent intent = new Intent(this,EditPocketActivity.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.action_remove_pockets){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        LogX.fastLog("click menu id : " + id
                        + "\nOrder : " + item.getOrder());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        switch (id){
            case R.id.manage_inouts:
                Intent intent = new Intent(this,InoutListActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
