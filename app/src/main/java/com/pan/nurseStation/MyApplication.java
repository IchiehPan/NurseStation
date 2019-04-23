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
import com.umeng.commonsdk.UMConfigure;

import java.util.HashMap;
import java.util.Map;


public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        DBHisBusiness.mQueue = Volley.newRequestQueue(this);

        /*
        注意：如果您已经在AndroidManifest.xml中配置过appkey和channel值，可以调用此版本初始化函数。
        */
//        参数1:上下文，必须的参数，不能为空。
//        参数2:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机。
//        参数3:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空。
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
    }
}
