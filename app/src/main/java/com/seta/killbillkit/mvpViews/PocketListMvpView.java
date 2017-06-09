package com.seta.killbillkit.mvpViews;

import com.seta.killbillkit.api.models.Pocket;

import java.util.List;

/**
 * Created by SETA_WORK on 2017/3/21.
 */

public interface PocketListMvpView extends MvpView {
    void onPocketsLoaded(List<Pocket> pockets);
}
