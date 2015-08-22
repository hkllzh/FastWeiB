package com.hkllzh.fastweib.net;

import com.google.gson.JsonObject;

/**
 * 请求返回管理
 * <p/>
 * lizheng -- 15/1/11
 * <p/>
 * FastWeiB
 */
public abstract class RequestHandler {
    public abstract void start();

    public abstract void success(String response);

    public abstract void failure(String responseBody, Throwable error);

    public abstract void finish();

    public void progress(long bytesWritten, long totalSize) {
    }

    public void customCacheData(String responseData) {
    }

    public void cancel() {
    }
}
