package com.seta.killbillkit.api.models;

import com.seta.setakits.db.DAOHelpable;
import com.seta.setakits.model.BaseContainer;

/**
 * Created by SETA_WORK on 2016/11/16.
 */

public class InoutContainer extends BaseContainer<Inout>{

    public InoutContainer(){
        super(InoutDAO.getInstance());
    }

    public InoutContainer(DAOHelpable<Inout> daoHelpable) {
        super(daoHelpable);
    }
}
