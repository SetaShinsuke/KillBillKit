package com.seta.killbillkit.utils;

import com.seta.killbillkit.framework.BaseApplication;

/**
 * Created by SETA_WORK on 2017/3/17.
 */

public class ViewUtils {

    public static void post(Runnable task) {
        BaseApplication.getHandler().post(task);
    }

    public static void postDelayed(Runnable task, long delayMillis) {
        BaseApplication.getHandler().postDelayed(task, delayMillis);
    }

    public static void removeCallbacks(Runnable task) {
        BaseApplication.getHandler().removeCallbacks(task);
    }
}
