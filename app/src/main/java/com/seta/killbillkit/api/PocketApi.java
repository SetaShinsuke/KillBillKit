package com.seta.killbillkit.api;

import com.seta.killbillkit.api.models.Pocket;
import com.seta.setakits.db.DAOHelpable;
import com.seta.setakits.model.BaseContainer;

/**
 * Created by SETA_WORK on 2017/3/17.
 */

public class PocketApi extends BaseContainer<Pocket> {

    public PocketApi(DAOHelpable<Pocket> daoHelpable) {
        super(daoHelpable);
    }
}
