package com.pan.nurseStation.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.pan.nurseStation.R;

public class ScanErrorDialog extends AlertDialog {

    private static final String TAG = ScanErrorDialog.class.getSimpleName();
    int mResource;

    public ScanErrorDialog(Context context, int themeResId, @LayoutRes int resource) {
        super(context, themeResId);
        this.mResource = resource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.dialog_custom_scan_error, null);
        setContentView(view);
    }
}
