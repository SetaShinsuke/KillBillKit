package com.seta.killbillkit.api.models;

import com.seta.killbillkit.api.KApi;

import java.util.ArrayList;

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


    public long getAllProperties(){
        long allProperties = 0;
        for(Pocket pocket:mPockets){
            allProperties += pocket.getBalance();
        }
        return allProperties;
    }
}
