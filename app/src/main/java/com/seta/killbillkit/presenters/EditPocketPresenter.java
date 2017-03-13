package com.seta.killbillkit.presenters;

import com.seta.killbillkit.api.KApi;
import com.seta.killbillkit.api.models.Pocket;
import com.seta.killbillkit.api.models.User;
import com.seta.killbillkit.events.PocketEditEvent;
import com.seta.killbillkit.mvpViews.EditPocketView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Seta.Driver on 2016/11/20.
 */

public class EditPocketPresenter {
    private EditPocketView mEditPocketView;
    private Pocket mPocket;
    private User mUser;

    public EditPocketPresenter(EditPocketView editPocketView){
        this.mEditPocketView = editPocketView;
        this.mUser = KApi.getApi().getUser();
    }

    public void init(Pocket pocket){
        this.mPocket = pocket;
    }

    public void commitPocketData(){
        if(mPocket==null){
            mPocket = mUser.addPocket(mEditPocketView.getNameEditing(),mEditPocketView.getBalanceEditing());
        }
        mPocket.setType(mEditPocketView.getPocketTypeEditing());
        int billDay = mEditPocketView.getBillDayEditing();
        int repayDay = mEditPocketView.getRepaymentDayEditing();
        if( !mPocket.getType().equals(Pocket.TYPE_CREDIT) ){
            billDay = -1;
            repayDay = -1;
        }
        mPocket.setBillDay(billDay);
        mPocket.setRepayDay(repayDay);

        //TODO:更新数据库
//        mPocket.save2DB();
        mEditPocketView.onCommitSuccess();
        EventBus.getDefault().post(new PocketEditEvent());
    }
}
