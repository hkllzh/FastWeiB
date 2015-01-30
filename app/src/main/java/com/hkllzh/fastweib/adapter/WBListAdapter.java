package com.hkllzh.fastweib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hkllzh.fastweib.R;
import com.hkllzh.fastweib.bean.StatusBean;
import com.hkllzh.fastweib.util.LogUtil;
import com.hkllzh.fastweib.util.WBTimeParseUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 微博列表适配器
 * <p/>
 * lizheng -- 15/1/28
 * <p/>
 * FastWeiB
 */
public class WBListAdapter extends RecyclerView.Adapter<WBListAdapter.WBListViewHolder> {

    private static final String TAG = "* WBListAdapter * ";
    private static final boolean isShowLog = true;

    private ArrayList<StatusBean> statuses;

    @Override
    public WBListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item_wb_list, parent, false);
        return new WBListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WBListViewHolder holder, int position) {
        holder.tvName.setText(statuses.get(position).user.screen_name);
        holder.tvTime.setText(WBTimeParseUtil.parse(statuses.get(position).created_at).toString("hh:mm:ss"));
        ImageLoader.getInstance().displayImage(statuses.get(position).user.avatar_hd,holder.imavHeadPortrait);
    }

    @Override
    public int getItemCount() {
        if (null == statuses) {
            return 0;
        }
        return statuses.size();
    }

    public void setData(ArrayList<StatusBean> statuses) {
        this.statuses = statuses;
        this.notifyDataSetChanged();
    }

    class WBListViewHolder extends RecyclerView.ViewHolder {

        public ImageView imavHeadPortrait;
        public TextView tvName;
        public TextView tvTime;

        public WBListViewHolder(View itemView) {
            super(itemView);
            imavHeadPortrait = (ImageView) itemView.findViewById(R.id.imavHeadPortrait);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        }
    }

}
