package com.seta.killbillkit.presenters;

import com.seta.killbillkit.api.KApi;
import com.seta.killbillkit.api.models.Inout;
import com.seta.killbillkit.interfaces.ResultHanlderInterface;
import com.seta.killbillkit.mvpViews.InoutListMvpView;

import java.util.List;

/**
 * Created by SETA_WORK on 2017/3/14.
 */

public class InoutListPresenter extends BasePresenter<InoutListMvpView> {

    public void getInouts(final int page){
        KApi.getApi().getInoutApi().getInouts(page, new ResultHanlderInterface() {
            @Override
            public void onSuccess(Object object) {
                List<Inout> inouts = (List<Inout>)object;
                if(page==1) {
                    getMvpView().refreshList(inouts);
                }else {
                    getMvpView().loadMore(inouts);
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
