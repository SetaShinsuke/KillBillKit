package com.seta.killbillkit.presenters;

import com.seta.killbillkit.R;
import com.seta.killbillkit.api.models.Pocket;
import com.seta.killbillkit.api.models.User;
import com.seta.killbillkit.views.Mainview;
import com.seta.setakits.SetaUtils;

import java.util.ArrayList;

import static com.seta.setakits.SetaUtils.getResString;

/**
 * Created by SETA_WORK on 2016/11/17.
 * 主界面presenter，用于处理数据和视图
 */

public class MainPresenter {
    private Mainview mainview;
    private User mUser;

    private boolean showingReal = true;

    public MainPresenter(Mainview mainview,User user){
        this.mainview = mainview;
        this.mUser = user;
    }

    public void init(){
        showingReal = true;
        mainview.RefreshTotalText( getResString(R.string.total_balance_real) + mUser.getRealAccount());
        mainview.refreshFab(showingReal);
        ArrayList<Pocket> pockets = mUser.getPockets();
        ArrayList<String> pocketNames = new ArrayList<>();
        for(Pocket pocket:pockets){
            pocketNames.add(pocket.getName());
        }
        mainview.refreshNavPocketMenu(pocketNames);
    }

    public void onFabClick(){
        showingReal = !showingReal;
        if(showingReal){
            mainview.RefreshTotalText(getResString(R.string.total_balance_real) + mUser.getRealAccount());
        }else {
            mainview.RefreshTotalText(getResString(R.string.total_balance_real) + mUser.getRealAccount() + "\n"
                                        + getResString(R.string.total_balance) + mUser.getTotalAccount());
        }
        mainview.refreshFab(showingReal);
    }

}
