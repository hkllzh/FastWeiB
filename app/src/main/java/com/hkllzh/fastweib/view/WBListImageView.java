package com.hkllzh.fastweib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hkllzh.fastweib.bean.PicUrl;
import com.hkllzh.fastweib.util.LogUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * 微博列表里面的图片类<br>
 * 可以处理单张图片和多张图片的显示
 * <p/>
 * lizheng -- 15/3/12
 * <p/>
 * FastWeiB
 */
public class WBListImageView extends RelativeLayout {

    private static final String TAG = "* WBListImageView * ";

    // 图片地址集合
    private ArrayList<PicUrl> pic_urls;

    public void setPic_urls(ArrayList<PicUrl> pic_urls) {
        removeAllViews();
        this.pic_urls = pic_urls;

        if (null == this.pic_urls || 0 == this.pic_urls.size()) {
            return;
        }

        LogUtil.e(TAG+"setPic_urls - pic_urls:"+pic_urls.toString());

        TextView t = new TextView(getContext());
        String showText = "";
        for (PicUrl s : this.pic_urls) {
            showText = showText + s.thumbnail_pic + "\n";
        }
        t.setText(showText);

        ImageView imageView = new ImageView(getContext());
        ImageLoader.getInstance().displayImage(pic_urls.get(0).thumbnail_pic,imageView);
        addView(imageView);

        requestLayout();
    }

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
        super.onLayout(changed, l, t, r, b);
        LogUtil.e(TAG+"onLayout - W:"+getWidth()+" H:"+getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        LogUtil.e(TAG + "onMeasure - widthMeasureSpec=" + widthMeasureSpec + " -- heightMeasureSpec=" + heightMeasureSpec);
        int measuredWidth = measure(widthMeasureSpec);
        int measuredHeight = measure(heightMeasureSpec);
        LogUtil.e(TAG+"onMeasure - W:"+measuredWidth+" H:"+measuredHeight);
        // int d = Math.min(measuredWidth, measuredHeight);
        // setMeasuredDimension(d, d);
        setMeasuredDimension(measuredWidth, measuredWidth);
    }

    private int measure(int measureSpec) {
        int result = 0;
        // Decode the measurement specifications.
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
//        LogUtil.e(TAG + "measure - specMode=" + specMode + " - specSize=" + specSize);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
//                LogUtil.e(TAG + "measure - specMode=UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
//                LogUtil.e(TAG + "measure - specMode=AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
//                LogUtil.e(TAG + "measure - specMode=EXACTLY");
                break;
            default:
//                LogUtil.e(TAG + "measure - specMode=switch default");
                break;
        }
        return specSize;
    }
}
