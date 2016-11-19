package com.seta.killbillkit.framework;

import android.app.Application;

import com.seta.killbillkit.api.KApi;

/**
 * Created by SETA_WORK on 2016/11/15.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KApi.init(this);
    }
}
