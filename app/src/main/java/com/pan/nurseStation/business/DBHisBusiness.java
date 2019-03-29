package com.pan.nurseStation.business;

import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.pan.anlib.util.VolleyKit;
import com.pan.nurseStation.R;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.request.BedListRequestBean;
import com.pan.nurseStation.bean.request.EnjoinDoInfoRequestBean;
import com.pan.nurseStation.bean.request.LevelRequestBean;
import com.pan.nurseStation.bean.request.LoginRequestBean;
import com.pan.nurseStation.bean.request.PatientDetailRequestBean;
import com.pan.nurseStation.bean.response.LevelResponseBean;
import com.pan.nurseStation.bean.response.LoginResponseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHisBusiness {
    public static RequestQueue mQueue;
    public static LoginResponseBean.Data loginBean = new LoginResponseBean.Data();
    public static List<LevelResponseBean.Data> levelDataList = new ArrayList<>();
    public static Map<String, Integer> bedTypeColorMap = new HashMap<>();

    private static final String TAG = DBHisBusiness.class.getSimpleName();

    public void login(LoginRequestBean requestBean, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        params.put("number", requestBean.getNumber());
        params.put("password", requestBean.getPassword());
        params.put(Constants.SERVICE_PARAM, Constants.LOGIN_SERVICE);
        StringRequest stringRequest = VolleyKit.newStringRequest(Request.Method.POST, Constants.URL, params, listener, errorListener);
        mQueue.add(stringRequest);
    }

    public void bedlist(BedListRequestBean requestBean, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        params.put("number", requestBean.getNumber());
        params.put("search", requestBean.getSearch());
        params.put("level", requestBean.getLevel());
        params.put("page", String.valueOf(requestBean.getPage()));
        params.put(Constants.SERVICE_PARAM, Constants.BED_LIST_SERVICE);
        StringRequest stringRequest = VolleyKit.newStringRequest(Request.Method.POST, Constants.URL, params, listener, errorListener);
        mQueue.add(stringRequest);
    }

    public void patientdetail(PatientDetailRequestBean requestBean, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        params.put("hos_number", requestBean.getHos_number());
        params.put(Constants.SERVICE_PARAM, Constants.PATIENT_DETAIL_SERVICE);
        StringRequest stringRequest = VolleyKit.newStringRequest(Request.Method.POST, Constants.URL, params, listener, errorListener);
        mQueue.add(stringRequest);
    }

    public void level(LevelRequestBean requestBean, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        params.put("department_id", requestBean.getDepartment_id());
        params.put(Constants.SERVICE_PARAM, Constants.LEVEL_SERVICE);
        StringRequest stringRequest = VolleyKit.newStringRequest(Request.Method.POST, Constants.URL, params, listener, errorListener);
        mQueue.add(stringRequest);
    }

    public void patientEnjoinDoInfo(EnjoinDoInfoRequestBean requestBean, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        params.put("hos_number", requestBean.getHos_number());
        params.put(Constants.SERVICE_PARAM, Constants.PATIENT_ENJOINDOINFO_SERVICE);
        StringRequest stringRequest = VolleyKit.newStringRequest(Request.Method.POST, Constants.URL, params, listener, errorListener);
        mQueue.add(stringRequest);
    }


    public static void initBedTypeColorMap(Context context) {
        // 将颜色和数据做绑定
        bedTypeColorMap.clear();
        int[] colors = context.getResources().getIntArray(R.array.bed_type_color);
        for (int index = 0; index < DBHisBusiness.levelDataList.size(); index++) {
            if (index < colors.length) {
                bedTypeColorMap.put(DBHisBusiness.levelDataList.get(index).getCode(), colors[index]);
            } else {
                bedTypeColorMap.put(DBHisBusiness.levelDataList.get(index).getCode(), context.getColor(R.color.bed_type_color_blue));
            }
        }
    }


}
