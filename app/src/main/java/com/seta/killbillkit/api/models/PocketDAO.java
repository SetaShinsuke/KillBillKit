package com.seta.killbillkit.api.models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SETA_WORK on 2016/11/15.
 */

public class PocketDAO {
    private final static String TABLENAME = "pocket";

    public static void createTable(SQLiteDatabase database) {
        String sql = "create table if not exists " + TABLENAME + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT " +
//                ", FORMAT TEXT  " +
//                ", FSIZE INT  " +
//                ", WIDTH INT  " +
//                ", HEIGHT INT  " +
//                ", UID TEXT  " +
//                ", MEDIUM_PATH TEXT  " +
//                ", FULL_PATH TEXT  " +
//                ", DESCRIPTION TEXT " +
                " );";
        database.execSQL(sql);
    }

    public static void updateTable(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
