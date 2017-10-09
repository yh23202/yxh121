package com.yangxiaohe.yangshop.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/*
 *  @项目名：  Zhbj 
 *  @包名：    org.itheima19.zhbj.utils
 *  @文件名:   DensityUtils
 *  @创建者:   Administrator
 *  @创建时间:  2016/2/16 15:37
 *  @描述：    TODO
 */
public class DensityUtils {


    public static int dp2px(Context context, int dp) {
        //        > px = dp * (dpi / 160)


        DisplayMetrics metrics = context.getResources()
                                        .getDisplayMetrics();
        int dpi = metrics.densityDpi;

        return (int) (dp * dpi / 160f + 0.5f);
    }

    public static int px2dp(Context context, int px) {
        //        > dp = px * 160 / dpi
        DisplayMetrics metrics = context.getResources()
                                        .getDisplayMetrics();
        int dpi = metrics.densityDpi;

        return (int) (px * 160f / dpi + 0.5f);
    }
}
