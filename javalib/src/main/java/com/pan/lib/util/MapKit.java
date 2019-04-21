package com.pan.lib.util;

import com.pan.lib.bean.StatusBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapKit {
    public static Map<String, String> list2Map(List<StatusBean> list) {
        Map<String, String> resultMap = new HashMap<>();
        if (list == null) {
            return resultMap;
        }

        for (StatusBean statusBean : list) {
            if (statusBean == null) {
                continue;
            }

            resultMap.put(statusBean.getStatus(), statusBean.getValue());
        }

        return resultMap;
    }

}
