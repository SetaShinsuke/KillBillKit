package com.seta.killbillkit.viewsInterfaces;

import java.util.ArrayList;

/**
 * Created by Seta.Driver on 2016/11/19.
 */

public interface MainView {
    void refreshTotalText(String amountString);
    void refreshInoutsAll(String allInouts);
    void refreshFab(boolean showReal);
    void refreshNavPocketMenu(ArrayList<String> menuItems);
}
