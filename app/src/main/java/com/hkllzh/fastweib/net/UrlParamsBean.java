package com.hkllzh.fastweib.net;

import com.loopj.android.http.RequestParams;

public class UrlParamsBean {
    public String url;
    public RequestParams params;

    public UrlParamsBean(String url, RequestParams params) {
        this.url = url;
        this.params = params;
    }
}