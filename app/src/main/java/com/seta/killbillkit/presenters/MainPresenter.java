package com.seta.killbillkit.presenters;

import com.seta.killbillkit.R;
import com.seta.killbillkit.api.KApi;
import com.seta.killbillkit.api.models.Inout;
import com.seta.killbillkit.api.models.Pocket;
import com.seta.killbillkit.api.models.User;
import com.seta.killbillkit.mvpViews.MainView;
import com.seta.killbillkit.utils.ViewUtils;
import com.seta.setakits.logs.LogX;
import com.seta.setakits.threadUtils.ThreadPoolManager;

import java.util.ArrayList;

import static com.seta.setakits.SetaUtils.getResString;

/**
 * Created by SETA_WORK on 2016/11/17.
 * 主界面presenter，用于处理数据和视图
 */

public class MainPresenter {
    private MainView mainview;
    private User mUser;

    private boolean showingReal = true;

    public MainPresenter(MainView mainview) {
        this.mainview = mainview;
    }

    public void init() {
        this.mUser = KApi.getApi().getUser();
        showingReal = true;
        loadInouts();
    }

    public void loadInouts(){
        if (showingReal) {
            mainview.refreshTotalText(getResString(R.string.total_balance_real) + mUser.getRealAccount());
        } else {
            mainview.refreshTotalText(getResString(R.string.total_balance_real) + mUser.getRealAccount() + "\n"
                    + getResString(R.string.total_balance) + mUser.getTotalAccount());
        }
        mainview.refreshFab(showingReal);

        //显示交易记录
        ThreadPoolManager.getDbThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Inout> inouts = KApi.getApi().getUser().getInouts();
                ViewUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        String content = "";
                        for (Inout inout : inouts) {
                            content += inout.getTitle() + " : ";
                            if (inout.getAmount() > 0) {
                                content += "+ ";
                            }
                            content += inout.getAmount() + "\n";
                        }
                        mainview.refreshInoutsAll(content);
                    }
                });
            }
        });
    }

    public ArrayList<String> getPocketNames() {
        ArrayList<Pocket> pockets = mUser.getPockets();
        ArrayList<String> menuTitles = new ArrayList<>();
        for (Pocket pocket : pockets) {
            menuTitles.add(pocket.getName() + " (" + pocket.getBalance() + ")");
        }
        return menuTitles;
    }

    public void onFabClick() {
        showingReal = !showingReal;
        loadInouts();
    }

    public void onPocketMenuItemClick(int order) {
        LogX.fastLog("click pocket : " + mUser.getPockets().get(order));
    }

    public void addPocket() {

    }
}
