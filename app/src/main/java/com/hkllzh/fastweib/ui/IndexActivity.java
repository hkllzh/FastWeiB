package com.hkllzh.fastweib.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;

import com.hkllzh.fastweib.BaseActivity;
import com.hkllzh.fastweib.R;

/**
 * 项目首页
 * <p/>
 *
 * @author lizheng -- li396858359@163.com
 */
public class IndexActivity extends BaseActivity {

    private DrawerLayout drawerLayout;

    @Override
    public int getContentViewId() {
        return R.layout.ac_index;
    }

    @Override
    protected void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    }

    @Override
    protected void initData() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_frame, new IndexContentFragment(), "content");
        ft.add(R.id.left_drawer, new IndexLeftDrawerFragment(), "leftDrawer");
        ft.commit();
    }

    @Override
    protected void setListener() {

    }
}
