package com.seta.killbillkit.views.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.seta.killbillkit.R;
import com.seta.killbillkit.api.KApi;
import com.seta.killbillkit.api.models.Pocket;
import com.seta.killbillkit.events.InoutEvent;
import com.seta.setakits.logs.LogX;
import com.seta.setakits.views.BaseDialogFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import static com.seta.killbillkit.utils.ResUtils.getStringRes;

/**
 * Created by Seta.Driver on 2016/11/19.
 */

public class CreateInoutDialog extends BaseDialogFragment {

    private static final String TYPE = "type";
    private static final String TYPE_IN = "type_in"; //收入
    private static final String TYPE_OUT = "type_out"; //支出

    private View mainView;
    private TextInputLayout nameInputLayout,amountInputLayou;
    private Spinner mPocketSpinner;
    private String mType = TYPE_OUT;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    /**
     * 是否是支出
     */
    public void setType(boolean isPayment) {
//        Bundle bundle = getArguments();
//        if (bundle == null) {
//            bundle = new Bundle();
//        }
//        bundle.putString("type",TYPE_IN);
//        setArguments(bundle);
        mType = isPayment?TYPE_OUT:TYPE_IN;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mainView = inflater.inflate(R.layout.inout_create_dialog, null);
        nameInputLayout = (TextInputLayout) mainView.findViewById(R.id.name_input);
        amountInputLayou = (TextInputLayout) mainView.findViewById(R.id.amount_input);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final Button button = ((AlertDialog)getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setClickable(isValid());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        nameInputLayout.getEditText().addTextChangedListener(textWatcher);
        amountInputLayou.getEditText().addTextChangedListener(textWatcher);

        mPocketSpinner = (Spinner) mainView.findViewById(R.id.pocket_spinner);
        final ArrayList<Pocket> pockets = KApi.getApi().getUser().getPockets();
        String[] pocketNames = new String[pockets.size()];
        for(int i=0;i<pockets.size();i++){
            pocketNames[i] = pockets.get(i).getName();
        }
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mainView.getContext()
                , android.R.layout.simple_spinner_dropdown_item
                , pocketNames);
        //绑定 Adapter到控件
        mPocketSpinner.setAdapter(adapter);

        setCancelable(false);
        String title , hintName;
        if(mType.equals(TYPE_OUT)){
            title = getStringRes(R.string.add_payment);
            hintName = getStringRes(R.string.payment_name_hint);
        }else {
            title = getString(R.string.add_income);
            hintName = getStringRes(R.string.income_name_hint);
        }
        nameInputLayout.setHint(hintName);
        builder.setView(mainView)
                //.setMessage("Message")
                .setTitle(title)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Editable editableName = nameInputLayout.getEditText().getText();
                        Editable editableAmount = amountInputLayou.getEditText().getText();
                        if ( !isValid() ) {
                            Toast.makeText(mainView.getContext(), getString(R.string.invalid_name_amount), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String name = editableName.toString();
                        int amount = Integer.parseInt(editableAmount.toString());
                        if(mType.equals(TYPE_OUT)){ //支出
                            amount = 0-amount;
                        }
                        LogX.fastLog("新建Inout name : " + name + "\nAmount : " + amount + "\nPocket id : " + getSelectedPocket().getId());
                        KApi.getApi().getUser().addInout(name,amount,getSelectedPocket().getId());
                        EventBus.getDefault().post(new InoutEvent());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }

    private Pocket getSelectedPocket(){
        int index = mPocketSpinner.getSelectedItemPosition();
        return KApi.getApi().getUser().getPockets().get(index);
    }

    private boolean isValid(){
        boolean flag = true;
        Editable editableName = nameInputLayout.getEditText().getText();
        Editable editableAmount = amountInputLayou.getEditText().getText();
        if (getSelectedPocket()==null
                || editableName == null || editableAmount == null
                || editableName.length() == 0 || editableAmount.length() == 0) {
            flag = false;
        }
        return flag;
    }

}
