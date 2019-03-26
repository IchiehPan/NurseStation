package com.bben.ydcf.scandome.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.bben.ydcf.scandome.CommonView;

public class ScanResultReceiver extends BroadcastReceiver {
    private String TAG = ScanResultReceiver.class.getSimpleName();

    private CommonView mView;

    public ScanResultReceiver(CommonView mView) {
        this.mView = mView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String scanResult = intent.getStringExtra("data");
        Log.i(TAG, "scanResult = " + scanResult);
        mView.setScan(scanResult);
    }
}
