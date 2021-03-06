package com.hkllzh.fastweib.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.bean.PicUrl;
import com.hkllzh.fastweib.util.MeasureUtil;

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
        int _100dp = MeasureUtil.dip2px(getContext(), 100);
        RelativeLayout.LayoutParams layoutParams = new LayoutParams(_100dp, _100dp);

        for (PicUrl s : pic_urls) {
            Uri smallUri = Uri.parse(s.getThumbnail_pic().replace("thumbnail", "small"));
            Uri bmiddleUri = Uri.parse(s.getThumbnail_pic().replace("thumbnail", "bmiddle"));
            SimpleDraweeView sv = new SimpleDraweeView(getContext());

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setLowResImageRequest(ImageRequest.fromUri(smallUri))
                    .setImageRequest(ImageRequest.fromUri(bmiddleUri))
                    .setAutoPlayAnimations(true)
                    .build();


            GenericDraweeHierarchyBuilder builder =
                    new GenericDraweeHierarchyBuilder(getResources());
            GenericDraweeHierarchy hierarchy = builder
                    // .setFadeDuration(1000)
                    .setPlaceholderImage(getResources().getDrawable(R.color.material_blue_50), ScalingUtils.ScaleType.FOCUS_CROP)
                    .build();

            sv.setHierarchy(hierarchy);
            sv.setController(controller);
            // sv.setImageURI();
            addView(sv, layoutParams);
        }

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
        // LogUtil.e(TAG + "onLayout - W:" + getWidth() + " H:" + getHeight());
        if (0 == getChildCount()) {
            return;
        }
        int w = getWidth() / 3;
        int _2dp = MeasureUtil.dip2px(getContext(), 2);
        int allViewCounts = getChildCount();

        for (int i = 0; i < allViewCounts; i++) {
//            final ImageView iv = (ImageView) getChildAt(i);
            final SimpleDraweeView iv = (SimpleDraweeView) getChildAt(i);


            // 0 -- iv.layout(0, 0, w, w);
            // 1 -- iv.layout(w, 0, w + w, w);
            // 2 -- iv.layout(w + w, 0, w + w + w, w);
            if (i < 3) { // 0 1 2
                iv.layout(w * i + _2dp, _2dp, w * (i + 1) - _2dp, w - _2dp);
            }

            // 3 -- iv.layout(w + w, 0, w + w + w, w);
            // 4 -- iv.layout(w + w, 0, w + w + w, w);
            // 5 -- iv.layout(w + w, 0, w + w + w, w);
            if (i > 2 && i < 6) { // 3 4 5
                iv.layout(w * (i - 3) + _2dp, w + _2dp, w * (i - 2) - _2dp, w + w - _2dp);
            }

            // 6 -- iv.layout(w + w, 0, w + w + w, w);
            // 7 -- iv.layout(w + w, 0, w + w + w, w);
            // 8 -- iv.layout(w + w, 0, w + w + w, w);
            if (i > 5 && i < 9) { // 6 7 8
                iv.layout(w * (i - 6) + _2dp, w + w + _2dp, w * (i - 5) - _2dp, w + w + w - _2dp);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // LogUtil.e(TAG + "onMeasure - widthMeasureSpec=" + widthMeasureSpec + " -- heightMeasureSpec=" + heightMeasureSpec);
        int measuredWidth = measure(widthMeasureSpec);
        // int measuredHeight = measure(heightMeasureSpec);

        int picCounts = 0;
        if (null != pic_urls && 0 != pic_urls.size()) {
            picCounts = pic_urls.size();
        }

        if (0 == picCounts) {
            setMeasuredDimension(0, 0);
            return;
        }

        if (picCounts < 4) { // 1 2 3
            setMeasuredDimension(measuredWidth, measuredWidth / 3);
            return;
        }

        if (picCounts < 7) { // 4 5 6
            setMeasuredDimension(measuredWidth, measuredWidth / 3 * 2);
            return;
        }

        if (picCounts < 10) { // 7 8 9
            setMeasuredDimension(measuredWidth, measuredWidth);
        }


    }

    private int measure(int measureSpec) {
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
