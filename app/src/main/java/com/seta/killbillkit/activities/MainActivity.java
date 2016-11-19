package com.seta.killbillkit.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.seta.killbillkit.R;
import com.seta.killbillkit.api.KApi;
import com.seta.killbillkit.api.models.User;
import com.seta.killbillkit.presenters.MainPresenter;
import com.seta.killbillkit.views.Mainview;
import com.seta.setakits.logs.LogX;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements Mainview , NavigationView.OnNavigationItemSelectedListener {

    private MainPresenter mMainPresenter;
    private User mUser;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFab;
    private TextView mTotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBindingUtil.setContentView(this,R.layout.activity_main);
        initViews();
        initData();
    }

    private void initViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTotalTextView = (TextView) findViewById(R.id.total_account);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        this.mUser = KApi.getApi().getUser();
        this.mMainPresenter = new MainPresenter(this, mUser);
        this.mMainPresenter.init();
    }

    @Override
    public void RefreshTotalText(String amountString) {
        mTotalTextView.setText(amountString);
    }

    @Override
    public void refreshFab(boolean showBoth) {
        if(showBoth){
            mFab.setImageResource(R.drawable.ic_sentiment_neutral_white_48dp);
            mFab.setBackgroundResource(R.color.colorPrimary);
        }else {
            mFab.setImageResource(R.drawable.ic_sentiment_satisfied_white_48dp);
            mFab.setBackgroundResource(R.color.colorAccent);
        }
    }

    @Override
    public void refreshNavPocketMenu(ArrayList<String> titles) {
        Menu navMenu = mNavigationView.getMenu();
        int groupId = R.id.pocket_list_menu;
        Menu pocketListMenu = navMenu.getItem(0).getSubMenu();
        pocketListMenu.clear();
        for(int i=0;i<titles.size();i++){
            navMenu.add(groupId,i,i,titles.get(i));
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

        //TODO:添加/移除 pocket
        if (id == R.id.action_add_pockets) {
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

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
