package com.hkllzh.fastweib.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * 测量工具类
 *
 */
public class MeasureUtil {
    public final static int PX = TypedValue.COMPLEX_UNIT_PX;
    public final static int DIP = TypedValue.COMPLEX_UNIT_DIP;
    public final static int SP = TypedValue.COMPLEX_UNIT_SP;

//    public static int width;// 屏幕宽
//    public static int height;// 屏幕高

    /**
     *
     * @param unit
     *            单位 </br>0 px</br>1 dip</br>2 sp
     * @param value
     *            size 大小
     * @param context
     * @return
     */
    public static float getDimensionPixelSize(int unit, float value,
                                              Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        switch (unit) {
            case PX:
                return value;
            case DIP:
            case SP:
                return TypedValue.applyDimension(unit, value, metrics);
            default:
                throw new IllegalArgumentException("unknow unix");
        }
    }

//    /**
//     * 将px值转换为dip或dp值，保证尺寸大小不变
//     *
//     * @param pxValue
//     * @param scale
//     *            （DisplayMetrics类中属性density）
//     * @return
//     */
//    public static int px2dip(float pxValue, float scale) {
//        return (int) (pxValue / scale + 0.5f);
//    }
//
//    /**
//     * 将dip或dp值转换为px值，保证尺寸大小不变
//     *
//     * @param dipValue
//     * @param scale
//     *            （DisplayMetrics类中属性density）
//     * @return
//     */
//    public static int dip2px(float dipValue, float scale) {
//        return (int) (dipValue * scale + 0.5f);
//    }
//
//    /**
//     * 将px值转换为sp值，保证文字大小不变
//     *
//     * @param pxValue
//     * @param fontScale
//     *            （DisplayMetrics类中属性scaledDensity）
//     * @return
//     */
//    public static int px2sp(float pxValue, float fontScale) {
//        return (int) (pxValue / fontScale + 0.5f);
//    }
//
//    /**
//     * 将sp值转换为px值，保证文字大小不变
//     *
//     * @param spValue
//     * @param fontScale
//     *            （DisplayMetrics类中属性scaledDensity）
//     * @return
//     */
//    public static int sp2px(float spValue, float fontScale) {
//        return (int) (spValue * fontScale + 0.5f);
//    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }
}
