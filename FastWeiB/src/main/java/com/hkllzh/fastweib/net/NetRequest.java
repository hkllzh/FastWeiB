//package com.hkllzh.fastweib.net;
//
//import android.app.Application;
//import android.content.Context;
//import android.text.TextUtils;
//
//import com.hkllzh.fastweib.util.ACache;
//import com.hkllzh.fastweib.util.LogUtil;
//import com.hkllzh.fastweib.util.MD5Util;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.MediaType;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.RequestInterface;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;
//
//import org.apache.http.Header;
//import org.apache.http.HttpStatus;
//import org.apache.http.protocol.HttpContext;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import okio.BufferedSink;
//
///**
// * 网络请求类
// * <p/>
// * 此类再次对{@link com.loopj.android.http.AsyncHttpClient}进行了封装
// * <p/>
// * 可以对网络请求中的参数、返回值、耗时、文件上传过程进行打印
// * <p/>
// * 以后在此基础上还可以增加日志记录功能
// * <p/>
// * 可以支持get请求的本地缓存，默认缓存时间为{@link com.hkllzh.fastweib.net.NetRequest#DEFAULT_CACHE_TIME}
// * <p/>
// * <b>使用：</b>
// * <p/>
// * 在{@link android.app.Activity#onCreate(android.os.Bundle)}生成实例，
// * 随后直接调用相应的{@link com.hkllzh.fastweib.net.NetRequest#get(String, com.loopj.android.http.RequestParams, RequestHandler)}
// * 或者{@link com.hkllzh.fastweib.net.NetRequest#post(String, com.loopj.android.http.RequestParams, RequestHandler)}方法即可
// * <p/>
// * <p/>
// * <p/>
// * lizheng -- 15/1/11
// * <p/>
// * FastWeiB
// */
//public class NetRequest {
//
//    private static final String TAG = "* NetRequest *";
//    private static final boolean isShowLog = true;
//
//    private static Context mContext;
//    private AsyncHttpClient httpClient;
//    private OkHttpClient okHttpClient;
//
//    private static final int DEFAULT_CACHE_TIME = 15 * 60;// 15分钟
//
//    private static NetRequest request;
//
//    public static void init(Application _app) {
//        mContext = _app;
//    }
//
//    public static NetRequest getInstance() {
//        if (null == request) {
//            request = new NetRequest();
//        }
//        return request;
//    }
//
//    private NetRequest() {
//        httpClient = new AsyncHttpClient();
//        okHttpClient = new OkHttpClient();
//        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
//    }
//
//    public void get_OkHttp(final String url, final RequestParams params, final RequestHandler handler, final boolean isUseCache, final int cacheTime) {
//        if (isUseCache) {
//            String cacheData = ACache.getInstance().getAsString(getCacheKey(url, params));
//            if (!TextUtils.isEmpty(cacheData)) {
//                printUseCache(url, params, cacheData);
//                handler.start();
//                handler.success(cacheData);
//                handler.finish();
//                return;
//            }
//        }
//
//        RequestBody requestBody = new RequestBody() {
//            @Override
//            public MediaType contentType() {
//                return null;
//            }
//
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//
//            }
//        };
//
//        RequestInterface request = new RequestInterface.Builder()
//                .post(requestBody)
//                .url(url)
//                .tag("")
//                .build();
//
//        okHttpClient.cancel("");
//
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(RequestInterface request, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//
//            }
//        });
//    }
//
//    public void get(final String url, final RequestParams params, final RequestHandler handler, final boolean isUseCache, final int cacheTime) {
//        if (isUseCache) {
//            String cacheData = ACache.getInstance().getAsString(getCacheKey(url, params));
//            if (!TextUtils.isEmpty(cacheData)) {
//                printUseCache(url, params, cacheData);
//                handler.start();
//                handler.success(cacheData);
//                handler.finish();
//                return;
//            }
//        }
//        httpClient.get(mContext, url, params, new AsyncHttpResponseHandler() {
//
//            long startTime = 0l;
//
//            @Override
//            public void onFinish() {
//                printUrlAndTime(url, startTime);
//                handler.finish();
//            }
//
//            @Override
//            public void onStart() {
//                startTime = System.currentTimeMillis();
//                printUrlAndParams("get", url, params);
//                handler.start();
//            }
//
//            @Override
//            public void onCancel() {
//                handler.cancel();
//            }
//
//            @Override
//            public void onProgress(long bytesWritten, long totalSize) {
//                printUrlAndProgress(url, bytesWritten, totalSize);
//                handler.progress(bytesWritten, totalSize);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                String data = new String(responseBody);
//                printUrlAndResponse(url, data);
//                handler.customCacheData(data);
//                if (isUseCache) {
//                    ACache.getInstance().put(getCacheKey(url, params), data, cacheTime);
//                }
//                // 最后交给外面的程序进行处理
//                handler.success(data);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                String data = new String(responseBody);
//                handler.failure(data, error);
//            }
//        });
//    }
//
//    public void get(final String url, final RequestParams params, final RequestHandler handler) {
//        get(url, params, handler, false, 0);
//    }
//
//    public void get(final String url, final RequestParams params, final RequestHandler handler, final boolean isUseCache) {
//        get(url, params, handler, isUseCache, DEFAULT_CACHE_TIME);
//    }
//
//    public void get(UrlParamsBean urlParamsBean, final RequestHandler handler, final boolean isUseCache, final int cacheTime) {
//        get(urlParamsBean.url, urlParamsBean.params, handler, isUseCache, cacheTime);
//    }
//
//    public void get(UrlParamsBean urlParamsBean, final RequestHandler handler) {
//        get(urlParamsBean, handler, false, 0);
//    }
//
//    public void get(UrlParamsBean urlParamsBean, final RequestHandler handler, final boolean isUseCache) {
//        get(urlParamsBean, handler, isUseCache, DEFAULT_CACHE_TIME);
//    }
//
//    public void post(final String url, final RequestParams params, final RequestHandler handler) {
//        httpClient.post(mContext, url, params, new AsyncHttpResponseHandler() {
//
//            long startTime = 0l;
//
//            @Override
//            public void onFinish() {
//                printUrlAndTime(url, startTime);
//                handler.finish();
//
//            }
//
//            @Override
//            public void onStart() {
//                startTime = System.currentTimeMillis();
//                printUrlAndParams("post", url, params);
//                handler.start();
//            }
//
//            @Override
//            public void onCancel() {
//                handler.cancel();
//            }
//
//            @Override
//            public void onProgress(long bytesWritten, long totalSize) {
//                printUrlAndProgress(url, bytesWritten, totalSize);
//                handler.progress(bytesWritten, totalSize);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                String data = new String(responseBody);
//                printUrlAndResponse(url, data);
//                handler.customCacheData(data);
//                handler.success(data);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                String response = new String(responseBody);
//                printUrlAndErrorInfo(url, statusCode, response);
//                handler.failure(response, error);
//            }
//        });
//    }
//
//    /**
//     * 打印错误信息
//     *
//     * @param url      请求url
//     * @param response 错误信息
//     */
//    private void printUrlAndErrorInfo(String url, int statusCode, String response) {
//        LogUtil.d(isShowLog, TAG + "\nurl:" + url + "\nstatusCode:" + statusCode + "\nerrorInfo:" + response);
//    }
//
//
//    /**
//     * 打印url和此上传数据的进度
//     *
//     * @param url          请求url
//     * @param bytesWritten 完成的进度
//     * @param totalSize    总大小
//     */
//    private void printUrlAndProgress(String url, long bytesWritten, long totalSize) {
//        if (1 == totalSize || 0 == totalSize) {
//            return;
//        }
//        LogUtil.d(isShowLog, TAG + "\nurl:" + url + "\nprogress:" + String.format("Progress %d from %d (%2.0f%%)", bytesWritten, totalSize, (totalSize > 0) ? (bytesWritten * 1.0 / totalSize) * 100 : -1));
//    }
//
//    /**
//     * 打印使用缓存数据
//     *
//     * @param url       请求url
//     * @param params    请求参数
//     * @param cacheData 缓存数据
//     */
//    private void printUseCache(String url, RequestParams params, String cacheData) {
//        LogUtil.d(isShowLog, TAG + "\nurl:" + url + "\nparams:" + params.toString() + "\ncache:" + cacheData);
//    }
//
//    /**
//     * 打印url和此请求的耗时
//     *
//     * @param url       请求url
//     * @param startTime url启动时间
//     */
//    private void printUrlAndTime(String url, long startTime) {
//        LogUtil.d(isShowLog, TAG + "\nurl:" + url + "\ntime:" + (System.currentTimeMillis() - startTime) + "ms");
//    }
//
//    /**
//     * 打印url和此请求的返回值
//     *
//     * @param url          请求url
//     * @param responseBody url的返回内容
//     */
//    private void printUrlAndResponse(String url, String responseBody) {
//        LogUtil.d(isShowLog, TAG + "\nurl:" + url + "\nresponse:" + responseBody);
//    }
//
//    /**
//     * 打印url和此请求的参数
//     *
//     * @param method 请求方法
//     * @param url    请求url
//     * @param params 请求参数
//     */
//    private void printUrlAndParams(String method, String url, RequestParams params) {
//        if (null == params) {
//            params = new RequestParams();
//        }
//        LogUtil.d(isShowLog, TAG + "\nurl:" + url + "\nmethod:" + method + "\nparams:" + params.toString());
//    }
//
//
//    /**
//     * 得到缓存key值
//     *
//     * @param url    请求url
//     * @param params 请求参数
//     * @return 缓存此请求数据的key
//     */
//    private String getCacheKey(String url, RequestParams params) {
//        return MD5Util.generate(url + params.toString());
//    }
//
//}
