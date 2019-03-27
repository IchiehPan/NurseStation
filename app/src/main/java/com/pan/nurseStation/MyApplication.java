package com.pan.nurseStation;

import android.app.Application;
import android.util.Log;

import com.android.volley.toolbox.Volley;
import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.request.LevelRequestBean;
import com.pan.nurseStation.bean.response.LevelResponseBean;
import com.pan.nurseStation.bean.response.LoginResponseBean;
import com.pan.nurseStation.business.DBHisBusiness;

import java.util.HashMap;
import java.util.Map;


public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        DBHisBusiness.mQueue = Volley.newRequestQueue(this);
    }
}
