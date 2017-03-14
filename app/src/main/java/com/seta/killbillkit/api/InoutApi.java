package com.seta.killbillkit.api;

import com.seta.killbillkit.api.models.Inout;
import com.seta.killbillkit.interfaces.ResultHanlderInterface;

import java.util.List;

/**
 * Created by Seta.Driver on 2016/11/19.
 */

public class InoutApi {

    public void addInout(String name , int amount , String pocketId){

    }

    public void editInout(String name , int amount , String pocketId){

    }

    public void getInouts(int page , ResultHanlderInterface resultHanlderInterface){
        List<Inout> inouts = KApi.getApi().getInoutContainer().getTList();
        resultHanlderInterface.onSuccess(inouts);
    }
}
