package com.pan.nurseStation.config;

import com.pan.lib.bean.StatusBean;
import com.pan.lib.util.MapKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignsDoConfig {
    public final static String TEMP_TYPE_MONTH = "1"; //口表
    public final static String TEMP_TYPE_UNDERARM = "2"; //掖表
    public final static String TEMP_TYPE_ANUS = "3"; //肛表
    public static List<StatusBean> TEMP_TYPE_LIST = null;
    public static Map<String, String> TEMP_TYPE_MAP = null;

    public final static String IS_PCOOL = "1"; //是
    public final static String IS_NOT_PCOOL = "0"; //否
    public static List<StatusBean> IS_PCOOL_LIST = null;
    public static Map<String, String> IS_PCOOL_MAP = null;

    public final static String PULSE_TYPE_PULSE = "1"; //脉率
    public final static String PULSE_TYPE_HEART = "2"; //心率
    public final static String PULSE_TYPE_ALL = "3"; //脉率+心率
    public static List<StatusBean> PULSE_TYPE_LIST = null;
    public static Map<String, String> PULSE_TYPE_MAP = null;

    public final static String BREATH_TYPE_NATURAL = "0"; //自然呼吸
    public final static String BREATH_TYPE_ASSISTED = "1"; //辅助呼吸
    public static List<StatusBean> BREATH_TYPE_LIST = null;
    public static Map<String, String> BREATH_TYPE_MAP = null;

    static {
        initTempTypeList();
        initTempTypeMap();

        initIsPcoolList();
        initIsPcoolMap();

        initPulseTypeList();
        initPulseTypeMap();

        initBreathTypeNaturalList();
        initBreathTypeNaturalMap();
    }

    private static void initBreathTypeNaturalMap() {
        BREATH_TYPE_MAP = MapKit.list2Map(BREATH_TYPE_LIST);
    }

    private static void initBreathTypeNaturalList() {
        BREATH_TYPE_LIST = new ArrayList<>();
        BREATH_TYPE_LIST.add(new StatusBean("自然呼吸", BREATH_TYPE_NATURAL));
        BREATH_TYPE_LIST.add(new StatusBean("辅助呼吸", BREATH_TYPE_ASSISTED));
    }

    private static void initPulseTypeMap() {
        PULSE_TYPE_MAP = MapKit.list2Map(PULSE_TYPE_LIST);
    }

    private static void initPulseTypeList() {
        PULSE_TYPE_LIST = new ArrayList<>();
        PULSE_TYPE_LIST.add(new StatusBean("脉率", PULSE_TYPE_PULSE));
        PULSE_TYPE_LIST.add(new StatusBean("心率", PULSE_TYPE_HEART));
        PULSE_TYPE_LIST.add(new StatusBean("脉率+心率", PULSE_TYPE_ALL));
    }

    private static void initIsPcoolMap() {
        IS_PCOOL_MAP = MapKit.list2Map(IS_PCOOL_LIST);
    }

    private static void initIsPcoolList() {
        IS_PCOOL_LIST = new ArrayList<>();
        IS_PCOOL_LIST.add(new StatusBean("物理降温", IS_PCOOL));
        IS_PCOOL_LIST.add(new StatusBean("非物理降温", IS_NOT_PCOOL));
    }

    private static void initTempTypeMap() {
        TEMP_TYPE_MAP = MapKit.list2Map(TEMP_TYPE_LIST);
    }

    private static void initTempTypeList() {
        TEMP_TYPE_LIST = new ArrayList<>();
        TEMP_TYPE_LIST.add(new StatusBean("口表", TEMP_TYPE_MONTH));
        TEMP_TYPE_LIST.add(new StatusBean("掖表", TEMP_TYPE_UNDERARM));
        TEMP_TYPE_LIST.add(new StatusBean("肛表", TEMP_TYPE_ANUS));
    }


}
