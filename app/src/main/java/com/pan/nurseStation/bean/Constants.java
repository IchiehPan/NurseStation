package com.pan.nurseStation.bean;

import com.pan.nurseStation.bean.response.LevelResponseBean;
import com.pan.nurseStation.bean.response.LoginResponseBean;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public final static long SLIDE_DURATION_MS = 500;

    public final static int MESSAGE_SUCCESS_CODE = 0;
    public final static int MESSAGE_FAIL_CODE = 1;

    public final static boolean ISDEBUG = true;

    //    public final static String URL = "http://192.168.8.104:8080/DyWeb_war_exploded/hello";
    public final static String URL = "http://192.168.10.71:88/dbhospital/";
    //    public final static String URL = "http://api.xiezn.com/dbhospital/?service=Login.index&number=011012&password=pwd";

    public final static String PATIENT_INFO_URL = "http://192.168.10.71:8689/index/ydhl/baseinfo/hos_number/";

    /**
     * 确认传参
     */
    public final static String SERVICE_PARAM = "service";
    /**
     * 登录验证接口服务
     */
    public final static String LOGIN_SERVICE = "Login.index";
    /**
     * 床位列表接口服务
     */
    public final static String BED_LIST_SERVICE = "Bedlist.index";
    /**
     * 病人详细信息接口服务
     */
    public final static String PATIENT_DETAIL_SERVICE = "Patient.index";
    /**
     * 护理级别接口服务
     */
    public final static String LEVEL_SERVICE = "Level.index";
    /**
     * 医嘱需执行信息接口服务
     */
    public final static String PATIENT_ENJOIN_DO_INFO_SERVICE = "Patient.EnjoinDoInfo";
    /**
     * 医嘱执行提交接口服务
     */
    public final static String PATIENT_ENJOIN_DO_SERVICE = "Patient.EnjoinDo";

    public final static int ORDER_STATUS_WAIT_MEDICAL = 0;
    public final static int ORDER_STATUS_WAIT_EXEC = 1;
    public final static int ORDER_STATUS_DO_EXEC = 2;
    public final static int ORDER_STATUS_DONE_EXEC = 3;
}
