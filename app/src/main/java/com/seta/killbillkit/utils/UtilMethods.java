package com.seta.killbillkit.utils;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Seta.Driver on 2016/11/20.
 */

public class UtilMethods {

    public static Integer[] getDaysOfMonth(){
        ArrayList<Integer> integers = new ArrayList<>();
        for(int i=0;i<31;i++){
            integers.add(i+1);
        }
        Integer[] result = new Integer[integers.size()];
        integers.toArray(result);
        return result;
    }

    public static String getContent(EditText editText){
        if(editText.getText() == null || editText.getText().length()==0){
            return null;
        }
        return editText.getText().toString();
    }

    public static String getContent(TextInputLayout textInputLayout){
        if(textInputLayout.getEditText().getText() == null || textInputLayout.getEditText().getText().length()==0){
            return null;
        }
        return textInputLayout.getEditText().getText().toString();
    }
}
