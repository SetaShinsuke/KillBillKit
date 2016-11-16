package com.seta.killbillkit.api.models;

import com.seta.setakits.db.DBable;

/**
 * Created by SETA_WORK on 2016/11/15.
 * 存款所在处
 */

public class Pocket implements DBable{
    private Long dbId;
    private String id;
    private String name;
    private int billDay = 0;
    private int repayDay = 0;
    private int AssumedRepayDay;
    private int balance = 0;

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
}
