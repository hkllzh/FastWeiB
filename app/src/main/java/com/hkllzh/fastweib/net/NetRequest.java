package com.hkllzh.fastweib.net;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.hkllzh.fastweib.util.ACache;
import com.hkllzh.fastweib.util.LogUtil;
import com.hkllzh.fastweib.util.MD5Util;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

/**
 * 网络请求类
 * <p/>
 * 此类再次对{@link com.loopj.android.http.AsyncHttpClient}进行了封装
 * <p/>
 * 可以对网络请求中的参数、返回值、耗时、文件上传过程进行打印
 * <p/>
 * 以后在此基础上还可以增加日志记录功能
 * <p/>
 * 可以支持get请求的本地缓存，默认缓存时间为{@link com.hkllzh.fastweib.net.NetRequest#DEFAULT_CACHE_TIME}
 * <p/>
 * <b>使用：</b>
 * <p/>
 * 在{@link android.app.Activity#onCreate(android.os.Bundle)}生成实例，
 * 随后直接调用相应的{@link com.hkllzh.fastweib.net.NetRequest#get(String, com.loopj.android.http.RequestParams, RequestHandler)}
 * 或者{@link com.hkllzh.fastweib.net.NetRequest#post(String, com.loopj.android.http.RequestParams, RequestHandler)}方法即可
 * <p/>
 * <p/>
 * <p/>
 * lizheng -- 15/1/11
 * <p/>
 * FastWeiB
 */
public class NetRequest {

    private static final String TAG = "* NetRequest * ";
    private static final boolean isShowLog = true;

    private static Context mContext;
    private AsyncHttpClient httpClient;

    private static final int DEFAULT_CACHE_TIME = 7 * 24 * 60 * 60;//一周

    private static NetRequest request;

    public static void init(Application _app) {
        mContext = _app;
    }

    public static NetRequest getInstance() {
        if (null == request) {
            request = new NetRequest();
        }
        return request;
    }

    private NetRequest() {
        httpClient = new AsyncHttpClient();
    }

    public void get(final String url, final RequestParams params, final RequestHandler handler, final boolean isUseCache, final int cacheTime) {
        if (isUseCache) {
            String cacheData = ACache.getInstance().getAsString(getCacheKey(url, params));
            if (!TextUtils.isEmpty(cacheData)) {
                handler.start();
                handler.success(cacheData);
                handler.finish();
                return;
            }
        }
        httpClient.get(mContext, url, params, new AsyncHttpResponseHandler() {

            long startTime = 0l;

            @Override
            public void onFinish() {
                printUrlAndTime(url, startTime);
                handler.finish();
            }

            @Override
            public void onStart() {
                startTime = System.currentTimeMillis();
                printUrlAndParams("get", url, params);
                handler.start();
            }

            @Override
            public void onCancel() {
                handler.cancel();
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                printUrlAndProgress(url, bytesWritten, totalSize);
                handler.progress(bytesWritten, totalSize);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String data = new String(responseBody);
                printUrlAndResponse(url, data);
                handler.customCacheData(data);
                if (isUseCache) {
                    ACache.getInstance().put(getCacheKey(url, params), data, cacheTime);
                }
                // 最后交给外面的程序进行处理
                handler.success(data);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String data = new String(responseBody);
                handler.failure(data, error);
            }
        });
    }

    public void get(final String url, final RequestParams params, final RequestHandler handler) {
        get(url, params, handler, false, 0);
    }

    public void get(final String url, final RequestParams params, final RequestHandler handler, final boolean isUseCache) {
        get(url, params, handler, isUseCache, DEFAULT_CACHE_TIME);
    }

    public void post(final String url, final RequestParams params, final RequestHandler handler) {
        httpClient.post(mContext, url, params, new AsyncHttpResponseHandler() {

            long startTime = 0l;

            @Override
            public void onFinish() {
                printUrlAndTime(url, startTime);
                handler.finish();

            }

            @Override
            public void onStart() {
                startTime = System.currentTimeMillis();
                printUrlAndParams("post", url, params);
                handler.start();
            }

            @Override
            public void onCancel() {
                handler.cancel();
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                printUrlAndProgress(url, bytesWritten, totalSize);
                handler.progress(bytesWritten, totalSize);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String data = new String(responseBody);
                printUrlAndResponse(url, data);
                handler.customCacheData(data);
                handler.success(data);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String data = new String(responseBody);
                handler.failure(data, error);
            }
        });
    }

    /**
     * 打印url和此上传数据的进度
     *
     * @param url          请求url
     * @param bytesWritten 完成的进度
     * @param totalSize    总大小
     */
    private void printUrlAndProgress(String url, int bytesWritten, int totalSize) {
        LogUtil.d(isShowLog, TAG + url + " - progress:" + String.format("Progress %d from %d (%2.0f%%)", bytesWritten, totalSize, (totalSize > 0) ? (bytesWritten * 1.0 / totalSize) * 100 : -1));
    }

    /**
     * 打印url和此请求的耗时
     *
     * @param url       请求url
     * @param startTime url启动时间
     */
    private void printUrlAndTime(String url, long startTime) {
        LogUtil.d(isShowLog, TAG + url + " - time:" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 打印url和此请求的返回值
     *
     * @param url          请求url
     * @param responseBody url的返回内容
     */
    private void printUrlAndResponse(String url, String responseBody) {
        LogUtil.d(isShowLog, TAG + url + " - response:" + responseBody);
    }

    /**
     * 打印url和此请求的参数
     *
     * @param method 请求方法
     * @param url    请求url
     * @param params 请求参数
     */
    private void printUrlAndParams(String method, String url, RequestParams params) {
        if (null == params) {
            params = new RequestParams();
        }
        LogUtil.d(isShowLog, TAG + method + " - " + url + " - params:" + params.toString());
    }


    /**
     * 得到缓存key值
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 缓存此请求数据的key
     */
    private String getCacheKey(String url, RequestParams params) {
        return MD5Util.generate(url + params.toString());
    }

}
