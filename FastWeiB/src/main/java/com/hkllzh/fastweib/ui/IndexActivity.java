package com.hkllzh.fastweib.ui;

import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hkllzh.android.net.APIInterface;
import com.hkllzh.android.util.toast.ToastUtil;
import com.hkllzh.fastweib.FWBBaseActivity;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.net.FastWBRequest;
import com.hkllzh.fastweib.net.api.UsersShowApi;
import com.hkllzh.fastweib.util.image.ImageUtil;

/**
 * 项目首页
 * <p/>
 *
 * @author lizheng -- li396858359@163.com
 */
public class IndexActivity extends FWBBaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private SimpleDraweeView sdvAvatar;
    private TextView tvName;
    private TextView tvDescription;

    @Override
    public int getContentViewId() {
        return R.layout.ac_index;
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        sdvAvatar = (SimpleDraweeView) findViewById(R.id.sdvAvatar);
        tvName = (TextView) findViewById(R.id.tvName);
        tvDescription = (TextView) findViewById(R.id.tvDescription);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white, null));
        } else {
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        }

        setSupportActionBar(toolbar);
        // getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.index_drawer_open, R.string.index_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    protected void initData() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_frame, new IndexContentFragment(), "content");
        ft.commit();

        // 个人信息
        FastWBRequest.getInstance().execute(new UsersShowApi(mAccessToken), this);
//        FastWBRequest.getInstance().execute(new UsersShowApi(mAccessToken), new ResponseInterface() {
//            @Override
//            public void start() {
//
//            }
//
//            @Override
//            public void failed(String errorInfo) {
//
//            }
//
//            @Override
//            public void success(final String response) {
//                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
//                ImageUtil.Companion.show(sdvAvatar, jsonObject.get("avatar_hd").getAsString());
//                tvName.setText(jsonObject.get("name").getAsString());
//                tvDescription.setText(jsonObject.get("description").getAsString());
//
//                for (Map.Entry<String, JsonElement> e : jsonObject.entrySet()) {
//                    log.e("IndexActivity 11 ", String.format("key=%s value=%s", e.getKey(), e.getValue().toString()));
//                }
//
//                Observable.from(jsonObject.entrySet())
//                        .map(new Func1<Map.Entry<String, JsonElement>, String>() {
//                            @Override
//                            public String call(Map.Entry<String, JsonElement> e) {
//                                return String.format("key=%s value=%s", e.getKey(), e.getValue().toString());
//                            }
//                        })
//                        .subscribe(new Action1<String>() {
//                            @Override
//                            public void call(String s) {
//                                log.e("IndexActivity 22 ", s);
//                            }
//                        });
//
////                        .subscribe(new Subscriber<Map.Entry<String, JsonElement>>() {
////                            @Override
////                            public void onCompleted() {
////
////                            }
////
////                            @Override
////                            public void onError(Throwable e) {
////
////                            }
////
////                            @Override
////                            public void onNext(Map.Entry<String, JsonElement> stringJsonElementEntry) {
////
////                            }
////                        });
//
////                        .subscribe(new Observer<Map.Entry<String, JsonElement>>() {
////                            @Override
////                            public void onCompleted() {
////
////                            }
////
////                            @Override
////                            public void onError(Throwable e) {
////
////                            }
////
////                            @Override
////                            public void onNext(Map.Entry<String, JsonElement> stringJsonElementEntry) {
////
////                            }
////                        });
//
////                        .subscribe(new Action1<Map.Entry<String, JsonElement>>() {
////                            @Override
////                            public void call(Map.Entry<String, JsonElement> e) {
////                                log.e("IndexActivity 22 ", String.format("key=%s value=%s", e.getKey(), e.getValue().toString()));
////                            }
////                        });
//
//            }
//
//            @Override
//            public void finish() {
//
//            }
//        });

    }

    @Override
    public void reqSuccess(APIInterface apiInterface, String response) {
        super.reqSuccess(apiInterface, response);
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        ImageUtil.Companion.show(sdvAvatar, jsonObject.get("avatar_hd").getAsString());
        tvName.setText(jsonObject.get("name").getAsString());
        tvDescription.setText(jsonObject.get("description").getAsString());
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
                IndexContentFragment indexContentFragment = (IndexContentFragment) getSupportFragmentManager().findFragmentByTag("content");
                indexContentFragment.actionMenuRefresh();
                break;
            case R.id.action_share:
                ToastUtil.show("action_share");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setListener() {
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else {

        }

        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
