package com.seta.killbillkit.api;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.seta.killbillkit.api.db.Database;
import com.seta.killbillkit.api.models.InoutContainer;
import com.seta.killbillkit.api.models.PocketContainer;
import com.seta.killbillkit.api.models.User;
import com.seta.killbillkit.utils.Constants;
import com.seta.setakits.SetaUtils;
import com.seta.setakits.db.BaseSQLiteHelper;
import com.seta.setakits.logs.LogX;

/**
 * Created by SETA_WORK on 2016/11/15.
 */

public class KApi {
    private final static boolean DEBUGABLE = false;

    private static KApi api;
    private Context appContext;
    private Database mDatabase;

    private User mUser;

    //收支条目，只常存最近一周
    private static InoutContainer sInoutContainer = new InoutContainer();
    //所有 Pocket
    private static PocketContainer sPocketContainer = new PocketContainer();

    private InoutApi mInoutApi;

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
            api.setUser(new User());
        }
        return api;
    }

    public static void init(Context context) {
        api = getApi();
        api.appContext = context;
        SetaUtils.init(context);

        boolean isDebuggable = (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
        if (isDebuggable) {
            LogX.logLevel = Log.VERBOSE;
        } else {
            LogX.logLevel = Log.WARN;
        }
        LogX.init(context , Constants.LOG_FILE_NAME);

        //初始化API(数据库)
        api.mDatabase = new Database(context, Constants.DATABASE_NAME , DEBUGABLE, Constants.DATABASE_VERSION);
        sInoutContainer.restore();
        sPocketContainer.restore();
        api.getUser().restore();
        api.getUser().restorePockets();

        api.setInoutApi(new InoutApi());

        api.getDatabase().export();
    }


    public BaseSQLiteHelper getDatabase(){
        return mDatabase;
    }

    public InoutContainer getInoutContainer(){
        return sInoutContainer;
    }

    public PocketContainer getPocketContainer() {
        return sPocketContainer;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Context getAppContext() {
        return appContext;
    }

    public InoutApi getInoutApi() {
        return mInoutApi;
    }

    public void setInoutApi(InoutApi inoutApi) {
        mInoutApi = inoutApi;
    }
}
