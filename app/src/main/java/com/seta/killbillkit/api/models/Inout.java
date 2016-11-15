package com.seta.killbillkit.api.models;

/**
 * Created by SETA_WORK on 2016/11/15.
 * 收支条目
 */

public class Inout {
    private int dbId = -1;
    private String title;
    private int amount;
    private String pocketId;
    private String otherInfo;

    private boolean isAssumed = false;
}
