package com.pan.nurseStation.util;

import android.content.Context;
import android.util.TypedValue;

public class DensityKit {
    public static float dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }

    public static float px2dp(Context context, float pxValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics());
    }

    public static float sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale + 0.5f);
    }

    public static float px2sp(Context context, float pxValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pxValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 从资源文件中获取pix
     */
    public static int getPxByResId(Context context, int resourcesId) {
        return context.getResources().getDimensionPixelSize(resourcesId);
    }
}
