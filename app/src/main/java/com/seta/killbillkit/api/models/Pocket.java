package com.seta.killbillkit.api.models;

import com.seta.setakits.db.DBable;
import com.seta.setakits.logs.LogX;

/**
 * Created by SETA_WORK on 2016/11/15.
 * 存款所在处
 */

public class Pocket implements DBable{

    public static final String TYPE_UNSPECIFIED = "Unspecified";
    public static final String TYPE_CREDIT = "Credit";
    public static final String TYPE_LOAN = "Loan";
    public static final String TYPE_EMERGENCEY = "Emergency";

    public static final String[] pocketTypes = {TYPE_UNSPECIFIED,TYPE_CREDIT,TYPE_LOAN,TYPE_EMERGENCEY};

//    public static final String TYPE_UNSPECIFIED = "unspecified";
//    public static final String TYPE_CASH        = "cash";
//    public static final String TYPE_CARD        = "card";
//    public static final String TYPE_CREDIT_CARD = "credit_card";
//    public static final String TYPE_PAY_APP     = "pay_app";
//    public static final String TYPE_OTHERS      = "others";

    private Long dbId;
    private String id;
    private String name;
    private String type = TYPE_UNSPECIFIED;
    private int billDay = -1;
    private int repayDay = -1;
    private int AssumedRepayDay = -1;
    private int balance = 0;

    private long createdAt = 0;

    Pocket(){

    }

    public int calculateBalance(int year , int month , int day){
        return getBalance();
    }

    public void save2DB(){
        PocketDAO.getInstance().getHelper().saveOne(this);
    }

    @Override
    public Long getDbId() {
        return dbId;
    }

    @Override
    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBillDay() {
        return billDay;
    }

    public void setBillDay(int billDay) {
        this.billDay = billDay;
    }

    public int getRepayDay() {
        return repayDay;
    }

    public void setRepayDay(int repayDay) {
        this.repayDay = repayDay;
    }

    public int getAssumedRepayDay() {
        return AssumedRepayDay;
    }

    public void setAssumedRepayDay(int assumedRepayDay) {
        AssumedRepayDay = assumedRepayDay;
    }

    public int getBalance() {
        return balance;
    }

    public int putBalance(int amount){
        LogX.d("put balance : " + amount);
        balance += amount;
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
