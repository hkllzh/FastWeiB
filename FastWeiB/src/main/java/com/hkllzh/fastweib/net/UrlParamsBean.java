package com.hkllzh.fastweib.net;

import android.os.StrictMode;

import com.loopj.android.http.RequestParams;

public class UrlParamsBean {
    public String url;
    public RequestParams params;

    public UrlParamsBean(String url, RequestParams params) {
        this.url = url;
        this.params = params;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());

        new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }
}