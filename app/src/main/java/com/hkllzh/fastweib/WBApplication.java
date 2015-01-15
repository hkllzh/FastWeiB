package com.hkllzh.fastweib;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.hkllzh.fastweib.util.ACache;
import com.hkllzh.fastweib.util.ImageLoaderOptions;
import com.hkllzh.fastweib.util.LogUtil;
import com.hkllzh.fastweib.util.SPUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 此程序自定义的Application
 * <br/>
 * 程序的初始化在此类进行，那么应该进行哪些初始化呢？
 * <br/>
 * 图片、缓存、网络、配置、数据库、错误处理、日志输出。。。
 * <br/>
 * 初始化的意义在于在程序的其他地方使用的时候可以直接得到实例<br/>
 * 一、保证单个实例<br/>
 * 二、保证所使用的{@link android.content.Context}为{@link android.app.Application}，
 * 避免某一个Activity长时间未被释放的问题
 * <p/>
 * lizheng -- 15/1/10
 * <p/>
 * FastWeiB
 */
public class WBApplication extends Application {

    private static final String TAG = "* WBApplication * ";
    private static final boolean isShowLog = true;

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化和是否为正式版本有关的配置
        isRelease(false);

        // 错误处理
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

        // 数据库初始化
        // initDB();

        // 图片加载初始化
        initImageLoader();

        // 缓存初始化
        ACache.init(this);

        // 全局配置
        SPUtil.init(this, "sp_wp");

    }

    private void initDB() {
        Configuration.Builder configurationBuilder = new Configuration.Builder(this);

        // 增加数据库表
        // configurationBuilder.addModelClass(UserLove.class);

        ActiveAndroid.initialize(configurationBuilder.create());
    }

    private void initImageLoader() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long cache = maxMemory / 10;// 使用程序可用内存的1/10左右图片加载的内存
        LogUtil.e(isShowLog, TAG + "maxMemory:" + maxMemory + " -- use:" + cache + " -- " + (cache / 1024 / 1024) + "M");

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LRULimitedMemoryCache((int) cache))
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .discCacheSize(50 * 1024 * 1024)
                .threadPoolSize(3)
                .defaultDisplayImageOptions(ImageLoaderOptions.normalOptions())
                        // .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(configuration);
    }

    /**
     * 是否为正式发布版本
     * true，正式版本、false，debug版本
     *
     * @param isRelease 是否为正式发布版本
     */
    public void isRelease(boolean isRelease) {
        LogUtil.init("FastWB", !isRelease);
    }
}
