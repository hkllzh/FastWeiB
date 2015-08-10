package com.hkllzh.fastweib.ui;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.hkllzh.fastweib.BaseFragment;
import com.hkllzh.fastweib.BaseRVAdapter;
import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.adapter.WBListAdapter;
import com.hkllzh.fastweib.bean.HomeTimelineBean;
import com.hkllzh.fastweib.net.RequestHandler;
import com.hkllzh.fastweib.net.WeiBoApi;

/**
 * 首页的内容部分
 * <p/>
 * lizheng -- 15/1/27
 * <p/>
 * FastWeiB
 */
public class IndexContentFragment extends BaseFragment {

    private static final String TAG = "* IndexContentFragment * ";
    private static final boolean isShowLog = true;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewWbList;
    private WBListAdapter wbListAdapter;


    private String mMax_id;
    
    public IndexActivity parentActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IndexActivity){
            parentActivity = (IndexActivity) activity;
        }
            
    }

    @Override
    public int getContentViewId() {
        return R.layout.fr_index_content;
    }

    @Override
    protected void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout);
        recyclerViewWbList = (RecyclerView) getView().findViewById(R.id.recyclerViewWbList);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_color_1, R.color.refresh_color_2,
                R.color.refresh_color_3, R.color.refresh_color_4);

    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        wbListAdapter = new WBListAdapter();
        recyclerViewWbList.setLayoutManager(layoutManager);
        recyclerViewWbList.setAdapter(wbListAdapter);

        requestData("");
    }

    private void requestData(final String max_id) {
        netRequest.get(WeiBoApi.StatusesHome_timeline(mAccessToken, max_id), new RequestHandler() {
            @Override
            public void start() {
                if (TextUtils.isEmpty(max_id)) {
                    showLoading();
                }
            }

            @Override
            public void success(String response) {
                HomeTimelineBean bean = new Gson().fromJson(response, HomeTimelineBean.class);
                if (TextUtils.isEmpty(max_id)) {
                    wbListAdapter.setData(bean.statuses);
                } else {
                    wbListAdapter.addMoreData(bean.statuses);
                }
                mMax_id = bean.max_id;
            }

            @Override
            public void failure(String responseBody, Throwable error) {

            }

            @Override
            public void finish() {
                dismissLoading();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData("");
            }
        });
        wbListAdapter.setLoadMore(new BaseRVAdapter.LoadMore() {
            @Override
            public void loadMore() {
                requestData(mMax_id);
            }
        });
    }
}