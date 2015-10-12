//package com.hkllzh.fastweib.util;
//
//import android.graphics.Bitmap;
//
//import com.hkllzh.fastweib.R;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.assist.ImageScaleType;
//import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
//
///**
// * {@link com.nostra13.universalimageloader.core.ImageLoader}使用的{@link DisplayImageOptions}的集合
// * <p/>
// * lizheng -- 15/1/10
// * <p/>
// * FastWeiB
// */
//public class ImageLoaderOptions {
//    /**
//     * 返回默认的ImageLoader配置参数
//     *
//     * @return 默认配置
//     */
//    public static DisplayImageOptions normalOptions() {
//        return new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .cacheOnDisc(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .build();
//    }
//
//    public static DisplayImageOptions normalOptionsWithDisplayer() {
//        return new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .cacheOnDisc(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .displayer(new FadeInBitmapDisplayer(200))
//                .build();
//    }
//
//    public static DisplayImageOptions wbListImageOptions(){
//        return new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .cacheOnDisc(true)
//                .showImageOnLoading(R.mipmap.pic_listimage_default)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .displayer(new FadeInBitmapDisplayer(200))
//                .build();
//    }
//
//}
