package com.hkllzh.fastweib.ui;

import android.os.Bundle;

import com.google.gson.JsonObject;
import com.hkllzh.fastweib.BaseActivity;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.net.RequestHandler;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        
        netRequest.get("http://www.baidu.com",null,new RequestHandler() {
            @Override
            public void start() {

            }

            @Override
            public void success(JsonObject jsonObject) {

            }

            @Override
            public void failure(String responseBody, Throwable error) {

            }

            @Override
            public void finish() {

            }
        });
        
    }
}
