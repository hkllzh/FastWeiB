package com.hkllzh.fastweib.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

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
    private Toolbar toolbar;
    private FrameLayout left_drawer;

    @Override
    public int getContentViewId() {
        return R.layout.ac_index;
    }

    @Override
    protected void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        left_drawer = (FrameLayout) findViewById(R.id.left_drawer);
    }

    @Override
    protected void initData() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_frame, new IndexContentFragment(), "content");
        ft.add(R.id.left_drawer, new IndexLeftDrawerFragment(), "leftDrawer");
        ft.commit();

        setSupportActionBar(toolbar);

        

        drawerLayout.setDrawerListener(new ActionBarDrawerToggle(mActivity, drawerLayout, toolbar, R.string.weibosdk_demo_feature_open_api, R.string.weibosdk_demo_share_video_desc) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void setListener() {
    }
    
    
}
