package com.seta.killbillkit.api.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.seta.killbillkit.api.KApi;
import com.seta.setakits.db.BaseSQLiteHelper;
import com.seta.setakits.db.DAOHelpable;
import com.seta.setakits.db.DAOHelper;
import com.seta.setakits.logs.LogX;

/**
 * Created by SETA_WORK on 2016/11/15.
 */

public class PocketDAO implements DAOHelpable<Pocket>{
    private final static String TABLENAME = "pocket";

    private final static String COLUMN_UID = "UID";
    private final static String COLUMN_NAME = "NAME";
    private final static String COLUMN_BILL_DAY = "BILL_DAY";
    private final static String COLUMN_REPAY_DAY = "REPAY_DAY";
    private final static String COLUMN_ASSUMED_DAY = "ASSUMED_DAY";
    private final static String COLUMN_BALANCE = "BALANCE";

    private static PocketDAO sPocketDAO;
    private DAOHelper<Pocket> mHelper;

    private PocketDAO(){
        this.mHelper = new DAOHelper<>(TABLENAME,this);
    }

    public static PocketDAO getInstance(){
        if(sPocketDAO==null){
            sPocketDAO = new PocketDAO();
        }
        return sPocketDAO;
    }

    public static void createTable(SQLiteDatabase database) {
        String sql = "create table if not exists " + TABLENAME + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", "+ COLUMN_UID +" TEXT  " +
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

    @Override
    public Pocket buildEntity() {
        return new Pocket();
    }

    @Override
    public Pocket buildUniqueById(String id) {
        return KApi.getApi().getPocketContainer().getUniqueTFromMem(id);
    }

    @Override
    public void inflate(Pocket entity, Cursor c) {
        for(String  name : c.getColumnNames()){
            switch (name){
                case "ID":
                    entity.setDbId(c.getLong(c.getColumnIndex(name)) );
                    break;
                case COLUMN_UID:
                    entity.setId(c.getString(c.getColumnIndex(name)));
                    break;
                case COLUMN_NAME:
                    entity.setName(c.getString(c.getColumnIndex(name)));
                    break;
                case COLUMN_BILL_DAY:
                    entity.setBillDay(c.getInt(c.getColumnIndex(name)));
                    break;
                case COLUMN_REPAY_DAY:
                    entity.setRepayDay(c.getInt(c.getColumnIndex(name)));
                    break;
                case COLUMN_ASSUMED_DAY:
                    entity.setAssumedRepayDay(c.getInt(c.getColumnIndex(name)));
                    break;
                case COLUMN_BALANCE:
                    entity.setBalance(c.getInt(c.getColumnIndex(name)));
                    break;
                default:
                    LogX.e("unknown filed " + name);
            }
        }
    }

    @Override
    public ContentValues getValues(Pocket obj) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_UID, String.valueOf(obj.getId()));
        values.put(COLUMN_NAME, String.valueOf(obj.getName()));
        values.put(COLUMN_BILL_DAY, obj.getBillDay());
        values.put(COLUMN_REPAY_DAY, obj.getRepayDay());
        values.put(COLUMN_ASSUMED_DAY, obj.getAssumedRepayDay());
        values.put(COLUMN_BALANCE, obj.getBalance());
        return values;
    }

    @Override
    public BaseSQLiteHelper getDB() {
        return KApi.getApi().getDatabase();
    }

    @Override
    public DAOHelper<Pocket> getHelper() {
        return mHelper;
    }
}
