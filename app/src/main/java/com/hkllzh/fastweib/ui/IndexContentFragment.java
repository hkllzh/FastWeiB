package com.hkllzh.fastweib.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.hkllzh.fastweib.BaseFragment;
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

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewWbList;
    private WBListAdapter wbListAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.fr_index_content;
    }

    @Override
    protected void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayout);
        recyclerViewWbList = (RecyclerView) getView().findViewById(R.id.recyclerViewWbList);
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        wbListAdapter = new WBListAdapter();
        recyclerViewWbList.setLayoutManager(layoutManager);
        recyclerViewWbList.setAdapter(wbListAdapter);

        requestData();
    }

    private void requestData() {
        netRequest.get(WeiBoApi.StatusesHome_timeline(mAccessToken), new RequestHandler() {
            @Override
            public void start() {
                showLoading();
            }

            @Override
            public void success(String response) {
                HomeTimelineBean bean = new Gson().fromJson(response, HomeTimelineBean.class);
                wbListAdapter.setData(bean.statuses);

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
                requestData();
            }
        });
    }
}
