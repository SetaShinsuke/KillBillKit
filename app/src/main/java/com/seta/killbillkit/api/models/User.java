package com.seta.killbillkit.api.models;

import com.seta.killbillkit.api.KApi;

import java.util.ArrayList;

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

    public void restorePockets(){
        mPockets.clear();
        addPocket("Default");
        addPocket("余额宝");
        addPocket("支付宝");
        addPocket("现金");
        addPocket("招行银行卡",371);
        addPocket("招行信用卡",0);
        addPocket("QQ钱包");
        addPocket("微信钱包");
//        addPocket("现金");
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

    public Pocket addPocket(String pocketName , int balance){
        String pocketId = pocketName + System.currentTimeMillis();
        Pocket pocket = KApi.getApi().getPocketContainer().getUniqueTFromMem(pocketId);
        pocket.setName(pocketName);
        pocket.setBalance(balance);
        if(!mPockets.contains(pocket)){
            mPockets.add(pocket);
            pocket.setCreatedAt(System.currentTimeMillis());
            //TODO:保存到数据库
//            pocket.save2DB();
        }
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

    public int getDebt(){
        int debt = 0;
        for(Inout inout:mInouts){
            debt += inout.getAmount();
        }
        return -debt;
    }

    //todo:根据出账日之类的计算
    //TODO: 每个 pocket 计算相应的 realAccount
    public int getRealAccount(){
        return getTotalAccount()-getDebt();
    }

    public void addInout(String title , int amount , String pocketId){
        String id = title + System.currentTimeMillis();
        Inout inout = KApi.getApi().getInoutContainer().getUniqueTFromMem(id);
        inout.setTitle(title);
        inout.setAmount(amount);
        inout.setPocketId(pocketId);
        inout.setCreatedAt(System.currentTimeMillis());
        this.mInouts.add(inout);
    }

    public ArrayList<Inout> getInouts() {
        return mInouts;
    }

    public void setInouts(ArrayList<Inout> inouts) {
        mInouts = inouts;
    }
}
