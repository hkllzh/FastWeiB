package com.hkllzh.fastweib;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter}的一个基础实现
 * <br/>
 * 增加了加载更多和设置{@link java.util.ArrayList}数据功能
 * <p/>
 * lizheng -- 15/1/30
 * <p/>
 * FastWeiB
 */
public abstract class BaseRVAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter {

    protected ArrayList<T> mData;

    public void setLoadMore(LoadMore loadMore) {
        this.loadMore = loadMore;
    }

    private LoadMore loadMore;

    public void setData(ArrayList<T> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    public void addMoreData(ArrayList<T> data) {
        if (null == this.mData) {
            setData(data);
        } else {
            this.mData.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        baseOnBindViewHolder((VH) holder, position);
        if (getItemCount() > 1 && getItemCount() - 2 == position) {
            if (null != loadMore) {
                loadMore.loadMore();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (null == mData) {
            return 0;
        }
        return mData.size();
    }

    public abstract void baseOnBindViewHolder(VH holder, int position);


    public interface LoadMore {
        public void loadMore();
    }
}
