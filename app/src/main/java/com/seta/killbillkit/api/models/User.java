package com.seta.killbillkit.api.models;

import com.seta.killbillkit.api.KApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by SETA_WORK on 2016/11/17.
 */

public class User {
    private String userId;
    private ArrayList<Pocket> mPockets = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Pocket> getPockets() {
        for(int i=0;i<7;i++){
            Pocket pocket = new Pocket();
            String name = "pocket_"+i;
            pocket.setId(name);
            pocket.setName(name);
        }
        return mPockets;
    }

    protected void setPockets(ArrayList<Pocket> pockets) {
        mPockets = pockets;
    }

    public void addPocket(String pocketName){
        String pocketId = pocketName;
        Pocket pocket = KApi.getApi().getPocketContainer().getUniqueTFromMem(pocketId);
        if(!mPockets.contains(pocket)){
            mPockets.add(pocket);
            pocket.save2DB();
        }
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
        return 100;
    }

    //todo:根据出账日之类的计算
    public int getRealAccount(){
        return getTotalAccount()-getDebt();
    }
}
