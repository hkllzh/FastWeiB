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

    public abstract void success(JsonObject jsonObject);

    public abstract void failure(String responseBody, Throwable error);

    public abstract void finish();

    public void progress(int bytesWritten, int totalSize) {
    }

    public void customCacheData(String responseData) {
    }

    public void cancel() {
    }
}
