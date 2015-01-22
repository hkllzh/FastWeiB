package com.hkllzh.fastweib.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hkllzh.fastweib.R;


/**
 * 加载数据弹框
 */
public class LoadingDialog extends BaseDialog {
    private TextView tvShowText;
    private RelativeLayout rlBG;

    public LoadingDialog(Context context) {
        super(context, R.layout.dia_loading, R.style.BaseDialog_Loading);
    }

    @Override
    protected void initView() {
        tvShowText = (TextView) findViewById(R.id.tvShowText);
        rlBG = (RelativeLayout) findViewById(R.id.rlBG);
    }

    @Override
    protected void initData() {
        tvShowText.setText("加载中...");
    }

    @Override
    protected void setListener() {
        rlBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setShowText(int resId) {
        tvShowText.setText(resId);
    }

    public void setShowText(String showText) {
        if (TextUtils.isEmpty(showText)){
            showText = "加载中...";
        }
        tvShowText.setText(showText);
    }
}
