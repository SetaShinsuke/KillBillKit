package com.seta.killbillkit.utils;

import java.util.ArrayList;

/**
 * Created by Seta.Driver on 2016/11/20.
 */

public class UtilMethods {

    public static Integer[] getDaysOfMonth(){
        ArrayList<Integer> integers = new ArrayList<>();
        for(int i=0;i<31;i++){
            integers.add(i);
        }
        Integer[] result = new Integer[integers.size()];
        integers.toArray(result);
        return result;
    }
}
