package com.seta.killbillkit.views;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Seta.Driver on 2016/11/19.
 */

public interface Mainview {
    public void RefreshTotalText(String amountString);
    public void refreshFab(boolean showReal);
    public void refreshNavPocketMenu(ArrayList<String> menuItems);
}
