package com.seta.killbillkit.framework;

import android.app.Application;
import android.os.Handler;

import com.seta.killbillkit.api.KApi;
import com.seta.setakits.logs.LogX;

/**
 * Created by SETA_WORK on 2016/11/15.
 */

public class BaseApplication extends Application {
    private static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        KApi.init(this);
        sHandler = new Handler();
        initCrashHandelr();
    }

    private void initCrashHandelr(){
        final Thread.UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LogX.e("发生崩溃 **********************************************\n"+ e);
                defaultHandler.uncaughtException(t,e);
            }
        });
    }


    public static Handler getHandler() {
        return sHandler;
    }
}
