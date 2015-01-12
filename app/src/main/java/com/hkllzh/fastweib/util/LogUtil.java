package com.hkllzh.fastweib.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 日志工具类，此类需要在自定义的{@link android.app.Application}进行初始化，不初始化将使用默认属性
 *
 * @author lizheng
 */
public class LogUtil {

    private static String TAG = "FastWeiB";
    private static boolean mIsShowLog = true;

    public static void init(String tag, boolean isShowLog) {
        TAG = tag;
        mIsShowLog = isShowLog;
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (mIsShowLog) {
            msg = replaceNull(msg);
            Log.v(tag, msg);
        }
    }

    public static void v(Object tag, String msg) {
        v(tag.getClass().getName(), msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {

        if (mIsShowLog) {
            msg = replaceNull(msg);
            Log.d(tag, msg);
        }
    }

    public static void d(Object tag, String msg) {
        d(tag.getClass().getName(), msg);
    }

    public static void d(boolean isShow, String msg) {
        if (isShow) {
            d(msg);
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (mIsShowLog) {
            msg = replaceNull(msg);
            Log.i(tag, msg);
        }
    }

    public static void i(Object tag, String msg) {
        i(tag.getClass().getName(), msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (mIsShowLog) {
            msg = replaceNull(msg);
            Log.w(tag, msg);
        }
    }

    public static void w(Object tag, String msg) {
        w(tag.getClass().getName(), msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (mIsShowLog) {
            msg = replaceNull(msg);
            Log.e(tag, msg);
        }

    }

    public static void e(Object tag, String msg) {
        e(tag.getClass().getName(), msg);

    }

    public static void e(boolean isShow, String msg) {
        if (isShow) {
            e(msg);
        }
    }

    public static void str2File(Context context, String content, String fileName) {
        File file = null;
        try {
            file = new File(context.getExternalCacheDir(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 对null值进行替换。""，并不替换
     *
     * @param value
     * @return
     */
    private static String replaceNull(String value) {
        return null == value ? "null" : value;
    }
}
