package com.seta.killbillkit.api;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.seta.setakits.db.DBHelper;
import com.seta.setakits.logs.LogX;

/**
 * Created by SETA_WORK on 2016/11/15.
 */

public class KApi {
    private static KApi api;
    private Context appContext;
    private DBHelper dbHelper;

    private KApi() {
    }

    /**
     * 返回一个API实例;
     *
     * @return {@link KApi};
     */
    public static KApi getApi() {
        if (api == null) {
            api = new KApi();
        }
        return api;
    }

    public static void init(Context context, String appId, boolean offlineMode) {
        api = getApi();
        api.appContext = context;

        //初始化API(数据库)
        api.dbHelper = new DBHelper(context);
        //initViews(context);
        boolean isDebuggable = (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
        if (isDebuggable) {
            LogX.logLevel = Log.VERBOSE;
        } else {
            LogX.logLevel = Log.WARN;
        }
        LogX.init(context);
    }

    public DBHelper getDbHelper(){
        return dbHelper;
    }
}
