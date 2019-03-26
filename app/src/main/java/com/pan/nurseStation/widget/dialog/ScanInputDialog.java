package com.pan.nurseStation.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.pan.nurseStation.R;

import androidx.annotation.Nullable;

public class ScanInputDialog extends AlertDialog {

    private static final String TAG = ScanInputDialog.class.getSimpleName();
    int mResource;

    public ScanInputDialog(Context context, int themeResId, @LayoutRes int resource) {
        super(context, themeResId);
        this.mResource = resource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.dialog_custom_scan_input, null);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        setContentView(view);
    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(listener);
    }

}
