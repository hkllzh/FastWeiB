package com.hkllzh.fastweib.ui;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hkllzh.android.util.toast.ToastUtil;
import com.hkllzh.fastweib.FWBBaseActivity;
import com.hkllzh.fastweib.R;

/**
 * 项目首页
 * <p/>
 *
 * @author lizheng -- li396858359@163.com
 */
public class IndexActivity extends FWBBaseActivity {

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

        setToolbar();
    }

    private void setToolbar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white, null));
        } else {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(mActivity, drawerLayout, toolbar, R.string.index_drawer_open, R.string.index_drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                ToastUtil.show("action_refresh");
                break;
            case R.id.action_share:
                ToastUtil.show("action_share");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setListener() {
    }


}
