package com.seta.killbillkit.api.models;

import com.seta.setakits.db.DAOHelpable;
import com.seta.setakits.model.BaseContainer;

/**
 * Created by SETA_WORK on 2016/11/16.
 */

public class PocketContainer extends BaseContainer<Pocket> {

    public PocketContainer(){
        super(PocketDAO.getInstance());
    }

    public PocketContainer(DAOHelpable<Pocket> daoHelpable) {
        super(daoHelpable);
    }

//    public Pocket getTmpPocket(){
//        return getUniqueTFromMem("tmp_pocket");
//    }
}
