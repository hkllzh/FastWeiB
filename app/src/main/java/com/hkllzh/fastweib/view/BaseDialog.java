package com.hkllzh.fastweib.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.hkllzh.fastweib.R;


/**
 * User: lizheng<br>
 * Email: lizheng@v1.cn<br>
 * 一个默认弹框的实现,使用是可以通过调整layout，style。更改为适合各自项目的弹框
 */
public abstract class BaseDialog extends Dialog {

    /**
     * 用一个默认样式文件
     *
     * @param context     C
     * @param layoutResId 布局文件
     */
    public BaseDialog(Context context, int layoutResId) {
        super(context, R.style.BaseDialog);
        init(layoutResId);
    }

    /**
     * @param context     C
     * @param layoutResId 布局文件
     * @param styleId     样式文件
     */
    public BaseDialog(Context context, int layoutResId, int styleId) {
        super(context, styleId);
        init(layoutResId);
    }

    private void init(int layoutResId) {
        setContentView(layoutResId);
        setProperty();
        initView();
        initData();
        setListener();
    }


    private void setProperty() {
        Window window = getWindow();
        WindowManager.LayoutParams p = window.getAttributes();
        Display d = getWindow().getWindowManager().getDefaultDisplay();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point point = new Point();
            d.getSize(point);
            p.width = point.x;
            p.height = point.y;
        } else {
            p.height = d.getHeight();
            p.width = d.getWidth();
        }
        window.setAttributes(p);

    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();
}
