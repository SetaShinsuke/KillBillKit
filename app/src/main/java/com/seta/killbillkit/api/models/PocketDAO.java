package com.seta.killbillkit.api.models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SETA_WORK on 2016/11/15.
 */

public class PocketDAO {
    private final static String TABLENAME = "pocket";

    private final static String COLUMN_NAME = "NAME";
    private final static String COLUMN_BILL_DAY = "BILL_DAY";
    private final static String COLUMN_REPAY_DAY = "REPAY_DAY";
    private final static String COLUMN_ASSUMED_DAY = "ASSUMED_DAY";
    private final static String COLUMN_BALANCE = "BALANCE";

    public static void createTable(SQLiteDatabase database) {
        String sql = "create table if not exists " + TABLENAME + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", "+COLUMN_NAME+" TEXT  " +
                ", "+COLUMN_BILL_DAY+" INT  " +
                ", "+COLUMN_REPAY_DAY+" INT  " +
                ", "+COLUMN_ASSUMED_DAY+" INT  " +
                ", "+COLUMN_BALANCE+" INT  " +
                " );";
        database.execSQL(sql);
    }

    public static void updateTable(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
