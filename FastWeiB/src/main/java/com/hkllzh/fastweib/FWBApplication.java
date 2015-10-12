package com.hkllzh.fastweib;

import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hkllzh.android.AppConfig;
import com.hkllzh.android.BaseApplication;
import com.hkllzh.android.util.log.LogInterface;

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
public class FWBApplication extends BaseApplication {

    private static final String TAG = "* FWBApplication * ";

    /**
     * true，正式版本、false，debug版本
     */
    public static final boolean isRelease = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("FastWB", "FWBApplication -> onCreate()");

//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(
//                                Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(
//                                Stetho.defaultInspectorModulesProvider(this))
//                        .build());
//
//        OkHttpClient client = new OkHttpClient();
//        client.networkInterceptors().add(new StethoInterceptor());

        Fresco.initialize(this);
        // 错误处理
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);

        // 数据库初始化
        // initDB();

        // 图片加载初始化
        // initImageLoader();
    }

    @Override
    protected AppConfig getAppConfig() {
        AppConfig.Builder builder = new AppConfig.Builder();
        builder.defaultSpFileName("sp_wei_b")
                .showLog(!isRelease)// 和当前版本相反。线上版本不打印、开发版本打印
                .showLogLevel(LogInterface.INFO);
        return builder.builder();
    }

//    private void initDB() {
//        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
//
//        // 增加数据库表
//        // configurationBuilder.addModelClass(UserLove.class);
//
//        ActiveAndroid.initialize(configurationBuilder.create());
//    }

//    private void initImageLoader() {
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        long cache = maxMemory / 10;// 使用程序可用内存的1/10左右图片加载的内存
//        log.e(TAG, "maxMemory:" + maxMemory + " -- use:" + cache + " -- " + (cache / 1024 / 1024) + "M");
//
//        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
//                .memoryCache(new LRULimitedMemoryCache((int) cache))
//                .discCacheFileNameGenerator(new Md5FileNameGenerator())
//                .discCacheSize(50 * 1024 * 1024)
//                .threadPoolSize(3)
//                .defaultDisplayImageOptions(ImageLoaderOptions.normalOptions())
//                        // .writeDebugLogs()
//                .build();
//
//        ImageLoader.getInstance().init(configuration);
//    }
}
