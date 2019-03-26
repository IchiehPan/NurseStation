package com.pan.anlib.util;

import android.support.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class VolleyKit {
    public static StringRequest newStringRequest(int method, String url, final Map<String, String> params, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(method, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        return stringRequest;
    }

}
