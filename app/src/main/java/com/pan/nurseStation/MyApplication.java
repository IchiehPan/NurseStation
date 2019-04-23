package com.pan.nurseStation;

import android.app.Application;

import com.android.volley.toolbox.Volley;
import com.pan.nurseStation.business.DBHisBusiness;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.setLogEnabled(true);
        /*
        注意：如果您已经在AndroidManifest.xml中配置过appkey和channel值，可以调用此版本初始化函数。
        */
//        参数1:上下文，必须的参数，不能为空。
//        参数2:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机。
//        参数3:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空。
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.reportError(this, "测试错误");

        String deviceInfo[] = UMConfigure.getTestDeviceInfo(this);

        DBHisBusiness.mQueue = Volley.newRequestQueue(this);
    }
}
