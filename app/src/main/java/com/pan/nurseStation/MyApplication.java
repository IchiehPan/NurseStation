package com.pan.nurseStation;

import android.app.Application;
import android.util.Log;

import com.android.volley.toolbox.Volley;
import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.response.LevelResponseBean;
import com.pan.nurseStation.bean.response.LoginResponseBean;
import com.pan.nurseStation.business.DBHisBusiness;


public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        DBHisBusiness dbHisBusiness = new DBHisBusiness();
        DBHisBusiness.mQueue = Volley.newRequestQueue(this);


        dbHisBusiness.level(null, response -> {
            Log.i(TAG, "onCreate: response=" + response);
            LevelResponseBean responseBean = BeanKit.string2Bean(response, LevelResponseBean.class);
            Log.i(TAG, "onCreate: responseBean=" + responseBean);
            DBHisBusiness.levelDataList = responseBean.getData();
            Log.i(TAG, "onCreate: levelDataList=" + DBHisBusiness.levelDataList);
        }, error -> {
            Log.e(TAG, "onCreate: error=" + error, error);
        });

    }
}
