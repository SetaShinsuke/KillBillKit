package com.seta.killbillkit.mvpViews;

/**
 * Created by Seta.Driver on 2016/11/20.
 */

public interface EditPocketView {
    String getPocketTypeEditing();
    String getNameEditing();
    int getBalanceEditing();
    int getBillDayEditing();
    int getRepaymentDayEditing();

    void onCommitSuccess();
    void onCommitFail();
}
