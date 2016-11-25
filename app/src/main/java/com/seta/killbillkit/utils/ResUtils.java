package com.seta.killbillkit.utils;

import com.seta.killbillkit.api.KApi;

/**
 * Created by Seta.Driver on 2016/11/20.
 */

public class ResUtils {

    public static String getStringRes(int resourceId){
        return KApi.getApi().getAppContext().getString(resourceId);
    }
}
