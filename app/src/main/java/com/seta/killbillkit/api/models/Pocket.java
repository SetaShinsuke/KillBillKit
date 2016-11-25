package com.seta.killbillkit.api.models;

import com.seta.setakits.db.DBable;

/**
 * Created by SETA_WORK on 2016/11/15.
 * 存款所在处
 */

public class Pocket implements DBable{

//    public static final String TYPE_UNSPECIFIED = "unspecified";
//    public static final String TYPE_CASH        = "cash";
//    public static final String TYPE_CARD        = "card";
//    public static final String TYPE_CREDIT_CARD = "credit_card";
//    public static final String TYPE_PAY_APP     = "pay_app";
//    public static final String TYPE_OTHERS      = "others";

    private Long dbId;
    private String id;
    private String name;
    private String type;
    private int billDay = -1;
    private int repayDay = -1;
    private int AssumedRepayDay = -1;
    private int balance = 0;

    private long createdAt = 0;

    Pocket(){

    }

    public int calculateBalance(){
        //默认当前 年月日
        return this.calculateBalance(0,0,0);
    }

    public int calculateBalance(int year , int month , int day){
        return getBalance();
    }

    protected void save2DB(){
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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public int getBillDay() {
        return billDay;
    }

    protected void setBillDay(int billDay) {
        this.billDay = billDay;
    }

    public int getRepayDay() {
        return repayDay;
    }

    protected void setRepayDay(int repayDay) {
        this.repayDay = repayDay;
    }

    public int getAssumedRepayDay() {
        return AssumedRepayDay;
    }

    protected void setAssumedRepayDay(int assumedRepayDay) {
        AssumedRepayDay = assumedRepayDay;
    }

    public int getBalance() {
        return balance;
    }

    protected void setBalance(int balance) {
        this.balance = balance;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
