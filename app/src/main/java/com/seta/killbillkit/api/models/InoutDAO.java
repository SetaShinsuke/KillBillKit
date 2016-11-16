package com.seta.killbillkit.api.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.seta.killbillkit.api.KApi;
import com.seta.setakits.db.BaseDAOHelper;
import com.seta.setakits.db.DAOHelpable;
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

    private BaseDAOHelper<Inout> mHelper;

    public InoutDAO(){
        this.mHelper = new BaseDAOHelper<Inout>(TABLENAME,this);
    }

    public static void createTable(SQLiteDatabase database) {
        String sql = "create table if not exists " + TABLENAME + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", "+COLUMN_UID+" TEXT  " +
                ", "+COLUMN_TITLE+" TEXT  " +
                ", "+COLUMN_AMOUNT+" INT  " +
                ", "+COLUMN_POCKET_ID+" TEXT  " +
                ", "+COLUMN_OTHER_INFO+" TEXT  " +
                " );";
        database.execSQL(sql);
    }

    public static void updateTable(SQLiteDatabase db, int oldVersion, int newVersion){
    }

    //region 保存
    protected boolean save2DB(final Inout inout) {
        return mHelper.saveOne(inout , KApi.getApi().getDbHelper());
    }

    /**
     * 批量保存
     */
    protected void saveInTx(Collection<Inout> objects) {
        mHelper.saveInTx(objects , KApi.getApi().getDbHelper() , true);
    }

    private Inout findInoutById(String id, boolean doClose){
        return mHelper.findObjById( KApi.getApi().getDbHelper().getWritableDatabase() , id , doClose );
    }
    //    protected static Inout findInoutById(String id, boolean doClose){
//        List list = find(  "UID=?", new String[]{String.valueOf(id)}, null, null, "1" , doClose);
//        if (list.isEmpty()) return null;
//        Object obj = list.get(0);
//        if(obj instanceof Inout){
//            return (Inout)obj;
//        }
//        return null;
//    }
    protected ArrayList<Inout> findAll(){
        return mHelper.findAll(KApi.getApi().getDbHelper().getWritableDatabase());
    }

    @Override
    public Inout buildEntity() {
        return new Inout();
    }


    @Override
    public ContentValues getValues(Inout obj) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, String.valueOf(obj.getTitle()));
        values.put(COLUMN_UID, String.valueOf(obj.getTitle()));
        values.put(COLUMN_AMOUNT, obj.getAmount());
        values.put(COLUMN_POCKET_ID, obj.getPocketId());
        values.put(COLUMN_OTHER_INFO, obj.getOtherInfo());
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
                    entity.setId(c.getString(c.getColumnIndex(name)));
                    break;
                case COLUMN_OTHER_INFO:
                    entity.setId(c.getString(c.getColumnIndex(name)));
                    break;
                case COLUMN_AMOUNT:
                    entity.setAmount(c.getInt(c.getColumnIndex(name)));
                    break;
                default:
                    LogX.e("unknown filed " + name);
            }
        }
    }
    //endregion

    //region删除
    protected static void deleteAll() {
//        SQLiteDatabase sqLiteDatabase = KApi.getApi().getDbHelper().getWritableDatabase();
//        sqLiteDatabase.delete(TABLENAME, null,null);
//        sqLiteDatabase.close();
    }
    //endregion
}