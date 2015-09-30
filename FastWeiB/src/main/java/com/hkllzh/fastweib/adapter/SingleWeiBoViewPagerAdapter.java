package com.hkllzh.fastweib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hkllzh.fastweib.ui.ReviewsFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 单条微博页面里面的左右滑动的适配器
 * <p/>
 * lizheng -- 2015/09/30
 */
public class SingleWeiBoViewPagerAdapter extends FragmentPagerAdapter {

    private Map<Integer, String> pageTitleMap;

    private static final int COUNT = 3;

    public SingleWeiBoViewPagerAdapter(FragmentManager fm) {
        super(fm);
        pageTitleMap = new HashMap<>(COUNT);
        pageTitleMap.put(0, "评论");
        pageTitleMap.put(1, "转发");
        pageTitleMap.put(2, "赞");
    }

    @Override
    public Fragment getItem(int position) {
        return new ReviewsFragment();
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitleMap.get(position);
    }


}
