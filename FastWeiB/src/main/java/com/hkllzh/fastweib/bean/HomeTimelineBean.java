package com.hkllzh.fastweib.bean;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 接口：https://api.weibo.com/2/statuses/home_timeline.json的数据类
 * <p/>
 * lizheng -- 15/1/29
 * <p/>
 * FastWeiB
 */
public class HomeTimelineBean {
    public ArrayList<StatusBean> statuses;
    // public ArrayList<Objects> advertises;
    // public ArrayList<Objects> ad;
    public boolean hasvisible;
    public String previous_cursor;
    public String next_cursor;
    public int total_number;
    public int interval;
    public int uve_blank;
    public String since_id;
    public String max_id;
    public int has_unread;
}
