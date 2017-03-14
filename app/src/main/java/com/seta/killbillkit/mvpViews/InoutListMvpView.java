package com.seta.killbillkit.mvpViews;

import com.seta.killbillkit.api.models.Inout;

import java.util.List;

/**
 * Created by SETA_WORK on 2017/3/14.
 */

public interface InoutListMvpView extends MvpView {

    void refreshList(List<Inout> inouts);
    void loadMore(List<Inout> inouts);
}
