package com.pan.nurseStation.business;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pan.anlib.util.VolleyKit;
import com.pan.lib.util.BeanKit;
import com.pan.lib.util.StringKit;
import com.pan.nurseStation.BedListActivity;
import com.pan.nurseStation.LoginActivity;
import com.pan.nurseStation.R;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.request.BedListRequestBean;
import com.pan.nurseStation.bean.request.LevelRequestBean;
import com.pan.nurseStation.bean.request.LoginRequestBean;
import com.pan.nurseStation.bean.request.PatientDetailRequestBean;
import com.pan.nurseStation.bean.response.LevelResponseBean;
import com.pan.nurseStation.bean.response.LoginResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DBHisBusiness {
    public static RequestQueue mQueue;
    public static LoginResponseBean loginResponseBean;
    public static List<LevelResponseBean.Data> levelDataList = new ArrayList<>();

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
        params.put("number", loginResponseBean.getData().getNumber());
        params.put("search", requestBean.getSearch());
        params.put("level", requestBean.getLevel());
        params.put("page", String.valueOf(requestBean.getPage()));
        params.put(Constants.SERVICE_PARAM, Constants.BEDLIST_SERVICE);
        StringRequest stringRequest = VolleyKit.newStringRequest(Request.Method.POST, Constants.URL, params, listener, errorListener);
        mQueue.add(stringRequest);
    }

    public void patientdetail(PatientDetailRequestBean requestBean, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        params.put("hos_number", requestBean.getHos_number());
        params.put(Constants.SERVICE_PARAM, Constants.PATIENTDETAIL_SERVICE);
        StringRequest stringRequest = VolleyKit.newStringRequest(Request.Method.POST, Constants.URL, params, listener, errorListener);
        mQueue.add(stringRequest);
    }

    public void level(LevelRequestBean requestBean, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.SERVICE_PARAM, Constants.LEVEL_SERVICE);
        StringRequest stringRequest = VolleyKit.newStringRequest(Request.Method.POST, Constants.URL, params, listener, errorListener);
        mQueue.add(stringRequest);
    }


}
