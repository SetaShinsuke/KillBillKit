package com.seta.killbillkit.api.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

import com.seta.setakits.logs.LogX;

/**
 * Created by ilike on 15/10/14.
 * Exposes methods to manage a SQLite database.
 */
public class CursorFactory implements SQLiteDatabase.CursorFactory {

        private boolean debugEnabled;

        public CursorFactory() {
            this.debugEnabled = false;
        }

        public CursorFactory(boolean debugEnabled){

            this.debugEnabled = debugEnabled;
        }

        @SuppressWarnings("deprecation")
        public Cursor newCursor(SQLiteDatabase sqLiteDatabase, SQLiteCursorDriver sqLiteCursorDriver, String editTable, SQLiteQuery sqLiteQuery) {

            if(debugEnabled){
                LogX.d("SQL Log", sqLiteQuery.toString());
            }

            return new SQLiteCursor(sqLiteDatabase, sqLiteCursorDriver, editTable, sqLiteQuery);
        }

}
