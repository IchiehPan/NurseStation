package com.pan.nurseStation.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.WindowManager;

import com.pan.nurseStation.R;


public class JAlertDialog extends AlertDialog {
    private static final String TAG = JAlertDialog.class.getSimpleName();
    int mResource;

    public JAlertDialog(Context context, int themeResId, @LayoutRes int resource) {
        super(context, themeResId);
        this.mResource = resource;
    }

//    public JAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//        this.mContext = context;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.dialog_custom_alert_patient, null);
        setContentView(view);

//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = (int) mWidth;
//        lp.height = (int) mHeight;
//        getWindow().setAttributes(lp);
    }


}
