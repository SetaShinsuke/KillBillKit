package com.seta.killbillkit.api.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.seta.killbillkit.api.models.InoutDAO;
import com.seta.setakits.db.BaseSQLiteHelper;
import com.seta.setakits.logs.LogX;

/**
 * Created by SETA_WORK on 2016/11/17.
 */

public class Database extends BaseSQLiteHelper {

    public Database(Context context, String databaseName, boolean debugEnabled, int dbVersion) {
        super(context, databaseName, debugEnabled, dbVersion);
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        LogX.d("create tables . ");
        InoutDAO.createTable(db);
    }

    @Override
    public void updateTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogX.d("update tables .");
        InoutDAO.updateTable(db,oldVersion,newVersion);
    }
}
