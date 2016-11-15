package com.seta.killbillkit.api.models;

import java.util.Date;

/**
 * Created by SETA_WORK on 2016/11/15.
 * 存款所在处
 */

public class Pocket {
    private int dbId = -1;
    private String title;
    private Date billDay,repaymentDay;
    private Date AssumedRepaymentDay;
    private int balance = 0;

}
