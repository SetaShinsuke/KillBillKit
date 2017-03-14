package com.seta.killbillkit.interfaces;

/**
 * Created by SETA_WORK on 2017/3/14.
 */

public interface ResultHanlderInterface {
    void onSuccess(Object o);
    void onFail(Throwable t);
}
