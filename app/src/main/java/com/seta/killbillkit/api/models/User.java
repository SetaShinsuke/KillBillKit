package com.seta.killbillkit.api.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.seta.killbillkit.api.KApi;
import com.seta.killbillkit.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;

import static com.seta.killbillkit.utils.Constants.SHARED_PREFERENCE_TAG;
import static com.seta.killbillkit.utils.Constants.USER_POCKET_IDS;

/**
 * Created by SETA_WORK on 2016/11/17.
 */

public class User {
    private String userId;
    private ArrayList<Pocket> mPockets = new ArrayList<>();
    private ArrayList<Inout> mInouts = new ArrayList<>();
    private long lastSettleTime;

    public User(){

    }

    /**
     * 恢复关于用户的信息
     */
    public void restore(){
        lastSettleTime = System.currentTimeMillis();
    }

    /**
     * 从 SharedPreferences 中恢复 pockets
     */
    public void restorePockets(){
        mPockets.clear();

        SharedPreferences sharedPreferences = KApi.getApi().getAppContext().getSharedPreferences(SHARED_PREFERENCE_TAG,Context.MODE_PRIVATE);
        String mPocketIdString = sharedPreferences.getString(USER_POCKET_IDS,"");
        ArrayList<String> pocketIds = new ArrayList<>(Arrays.asList(mPocketIdString.split(",")));
        while(pocketIds.size()>0 && pocketIds.get(pocketIds.size()-1).length()==0){
            pocketIds.remove(pocketIds.size()-1);
        }
        for(String id: pocketIds){
            Pocket pocket = KApi.getApi().getPocketApi().getUniqueTFromMem(id);
            mPockets.add(pocket);
        }
        //初次使用，创建默认Pocket
        if(mPockets.size()==0){
            //会保存数据库
            addPocket(Constants.DEFAULT_POCKET_NAME);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Pocket> getPockets() {
        return mPockets;
    }

    protected void setPockets(ArrayList<Pocket> pockets) {
        mPockets = pockets;
    }

    public Pocket addPocket(String pocketName){
        return addPocket(pocketName,0);
    }

    /**
     * 更新持久化存储的 Pockets
     */
    private void updatePersistPockets(){
        StringBuilder sb = new StringBuilder();
        for(Pocket pocket : mPockets){
            sb.append(pocket.getId());
            sb.append(",");
        }
        SharedPreferences sharedPreferences = KApi.getApi().getAppContext().getSharedPreferences(SHARED_PREFERENCE_TAG,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_POCKET_IDS,sb.toString());
        editor.apply();
    }

    public Pocket addPocket(String pocketName , int balance){
        String pocketId = pocketName + System.currentTimeMillis();
        Pocket pocket = KApi.getApi().getPocketApi().getUniqueTFromMem(pocketId);
        pocket.setName(pocketName);
        pocket.setBalance(balance);
        if(!mPockets.contains(pocket)){
            mPockets.add(pocket);
            pocket.setCreatedAt(System.currentTimeMillis());
            //TODO:保存到数据库
        }
        pocket.save2DB();
        updatePersistPockets();
        return pocket;
    }

    public void removePocket(){

    }


    public int getTotalAccount(){
        int allProperties = 0;
        for(Pocket pocket:mPockets){
            allProperties += pocket.getBalance();
        }
        return allProperties;
    }

    //负债（欠钱时为正值...）
    public int getDebt(){
        int debt = 0;
        for(Inout inout:mInouts){
            Pocket pocket = KApi.getApi().getPocketApi().getUniqueTFromMem(inout.getPocketId());
            if(pocket.getType().equals(Pocket.TYPE_CREDIT)){ //超前消费，增加负债
                debt += inout.getAmount();
            }
        }
        return -debt;
    }

    //todo:根据出账日之类的计算
    //TODO: 每个 pocket 计算相应的 realAccount
    public int getRealAccount(){
        return getTotalAccount()-getDebt();
    }

    public void addInout(String title , int amount , String pocketId){
        //添加Inout
        String id = title + System.currentTimeMillis();
        Inout inout = KApi.getApi().getInoutApi().getUniqueTFromMem(id);
        inout.setTitle(title);
        inout.setAmount(amount);
        inout.setPocketId(pocketId);
        inout.setCreatedAt(System.currentTimeMillis());
        this.mInouts.add(inout);
        KApi.getApi().getInoutApi().updateTs2DB(inout);

        //更新Pocket余额
        Pocket pocket = KApi.getApi().getPocketApi().getUniqueTFromMem(pocketId);
        if( !pocket.getType().equals(Pocket.TYPE_CREDIT )){ //不是信用消费，直接扣除
            pocket.putBalance(amount);
            pocket.save2DB();
        }
    }

    public ArrayList<Inout> getInouts() {
        return mInouts;
    }

    public void setInouts(ArrayList<Inout> inouts) {
        mInouts = inouts;
        KApi.getApi().getInoutApi().updateAll(inouts);
    }
}
