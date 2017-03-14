package com.seta.killbillkit.presenters;

import com.seta.killbillkit.mvpViews.MvpView;

/**
 * Created by liujia on 2016/6/haha.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
