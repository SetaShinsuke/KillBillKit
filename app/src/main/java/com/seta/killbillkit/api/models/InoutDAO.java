package com.seta.killbillkit.api.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.seta.killbillkit.api.KApi;
import com.seta.setakits.db.DAOHelper;
import com.seta.setakits.db.DAOHelpable;
import com.seta.setakits.db.BaseSQLiteHelper;
import com.seta.setakits.logs.LogX;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by SETA_WORK on 2016/11/15.
 */

public class InoutDAO implements DAOHelpable<Inout> {
    private final static String TABLENAME = "inout";

    private final static String COLUMN_TITLE = "TITLE";
    private final static String COLUMN_UID = "UID";
    private final static String COLUMN_POCKET_ID = "POCKET_ID";
    private final static String COLUMN_OTHER_INFO = "OTHER_INFO";
    private final static String COLUMN_AMOUNT = "AMOUNT";
    private final static String COLUMN_CREATED_AT = "CREATED_AT";

    private static InoutDAO sInoutDAO;
    private DAOHelper<Inout> mHelper;

    private InoutDAO(){
        this.mHelper = new DAOHelper<>(TABLENAME,this);
    }

    public static InoutDAO getInstance(){
        if(sInoutDAO ==null){
            sInoutDAO = new InoutDAO();
        }
        return sInoutDAO;
    }

    public static void createTable(SQLiteDatabase database) {
        String sql = "create table if not exists " + TABLENAME + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", "+COLUMN_UID+" TEXT  " +
                ", "+COLUMN_TITLE+" TEXT  " +
                ", "+COLUMN_AMOUNT+" INT  " +
                ", "+COLUMN_POCKET_ID+" TEXT  " +
                ", "+COLUMN_OTHER_INFO+" TEXT  " +
                ", "+COLUMN_CREATED_AT+" LONG  " +
                " );";
        database.execSQL(sql);
    }

    public static void updateTable(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    //region 保存
    protected boolean save2DB(final Inout inout) {
        return mHelper.saveOne(inout);
    }

    /**
     * 批量保存
     */
    protected void saveInTx(Collection<Inout> objects) {
        mHelper.saveInTx(objects);
    }

    public Inout getUniqueInoutById(String uid){
        return mHelper.getUniqueObjById(uid);
    }

    protected ArrayList<Inout> findAll(){
        return mHelper.findAll();
    }

    @Override
    public BaseSQLiteHelper getDB() {
        return KApi.getApi().getDatabase();
    }

    @Override
    public DAOHelper<Inout> getHelper() {
        return mHelper;
    }

    @Override
    public Inout buildUniqueById(String id) {
        return KApi.getApi().getInoutApi().getUniqueTFromMem(id);
    }

    @Override
    public Inout buildEntity() {
        return new Inout();
    }


    @Override
    public ContentValues getValues(Inout obj) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_UID, String.valueOf(obj.getTitle()));
        values.put(COLUMN_TITLE, String.valueOf(obj.getTitle()));
        values.put(COLUMN_AMOUNT, obj.getAmount());
        values.put(COLUMN_POCKET_ID, obj.getPocketId());
        values.put(COLUMN_OTHER_INFO, obj.getOtherInfo());
        values.put(COLUMN_CREATED_AT, obj.getCreatedAt());
        return values;
    }

    /**
     * 从数据库中提取
     */
    @Override
    public void inflate(Inout entity, Cursor c) {
        for(String  name : c.getColumnNames()){
            switch (name){
                case "ID":
                    entity.setDbId(c.getLong(c.getColumnIndex(name)) );
                    break;
                case COLUMN_UID:
                    entity.setId(c.getString(c.getColumnIndex(name)));
                    break;
                case COLUMN_TITLE:
                    entity.setTitle(c.getString(c.getColumnIndex(name)));
                    break;
                case COLUMN_POCKET_ID:
                    entity.setPocketId(c.getString(c.getColumnIndex(name)));
                    break;
                case COLUMN_OTHER_INFO:
                    entity.setOtherInfo(c.getString(c.getColumnIndex(name)));
                    break;
                case COLUMN_AMOUNT:
                    entity.setAmount(c.getInt(c.getColumnIndex(name)));
                    break;
                case COLUMN_CREATED_AT:
                    entity.setCreatedAt(c.getLong(c.getColumnIndex(name)));
                    break;
                default:
                    LogX.e("unknown filed " + name);
            }
        }
    }
    //endregion

    //region删除
    protected static void deleteAll() {
    }
    //endregion
}