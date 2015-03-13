package com.hkllzh.fastweib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 微博列表里面的图片类<br>
 * 可以处理单张图片和多张图片的显示
 * <p/>
 * lizheng -- 15/3/12
 * <p/>
 * FastWeiB
 */
public class WBListImageView extends ViewGroup {

    public WBListImageView(Context context) {
        super(context);
    }

    public WBListImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WBListImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
