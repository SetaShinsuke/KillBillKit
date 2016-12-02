package com.seta.killbillkit.api.models;

import com.seta.setakits.db.DBable;

/**
 * Created by SETA_WORK on 2016/11/15.
 * 收支条目
 */

public class Inout implements DBable{
    private Long dbId;
    private String id;
    private String title;
    private int amount;
    private String pocketId;
    private String otherInfo;
    private long createdAt = 0;

    private boolean isAssumed = false;

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

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    protected void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPocketId() {
        return pocketId;
    }

    protected void setPocketId(String pocketId) {
        this.pocketId = pocketId;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    protected void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public boolean isAssumed() {
        return isAssumed;
    }

    protected void setAssumed(boolean assumed) {
        isAssumed = assumed;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
