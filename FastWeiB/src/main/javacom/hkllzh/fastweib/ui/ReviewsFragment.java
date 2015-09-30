package com.hkllzh.fastweib.ui;

import android.widget.TextView;

import com.hkllzh.fastweib.BaseFragment;
import com.hkllzh.fastweib.R;

/**
 * 评论页面
 * <p/>
 * lizheng -- 2015/09/30
 */
public class ReviewsFragment extends BaseFragment {

    private TextView tvTest;

    @Override
    public int getContentViewId() {
        return R.layout.fr_reviews_page;
    }

    @Override
    protected void initView() {
        tvTest = findViewById(R.id.tvTest);
    }

    @Override
    protected void initData() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 1000; i++) {
            sb.append("lizheng").append(i);
        }
        tvTest.setText(sb.toString());
    }

    @Override
    protected void setListener() {

    }
}
