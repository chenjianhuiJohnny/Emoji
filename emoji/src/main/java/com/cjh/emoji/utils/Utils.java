package com.cjh.emoji.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by chenjianhui on 2017/3/2.
 */

public class Utils {
    /**
     * Convert Dp to Pixel
     */
    public static int dpToPx(float dp, Resources resources){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }
}
