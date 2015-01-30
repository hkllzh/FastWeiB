package com.hkllzh.fastweib.bean;

import java.util.Objects;

/**
 * 微博数据类
 * <p/>
 * 网页地址：http://open.weibo.com/wiki/常见返回对象数据结构#.E5.BE.AE.E5.8D.9A.EF.BC.88status.EF.BC.89
 * <p/>
 * <pre>
 * 返回值字段	字段类型	字段说明
 * created_at               string	微博创建时间
 * id	                    int64	微博ID
 * mid	                    int64	微博MID
 * idstr	                string	字符串型的微博ID
 * text	                    string	微博信息内容
 * source	                string	微博来源
 * favorited	            boolean	是否已收藏，true：是，false：否
 * truncated	            boolean	是否被截断，true：是，false：否
 * in_reply_to_status_id	string	（暂未支持）回复ID
 * in_reply_to_user_id	    string	（暂未支持）回复人UID
 * in_reply_to_screen_name	string	（暂未支持）回复人昵称
 * thumbnail_pic	        string	缩略图片地址，没有时不返回此字段
 * bmiddle_pic	            string	中等尺寸图片地址，没有时不返回此字段
 * original_pic	            string	原始图片地址，没有时不返回此字段
 * geo	                    object	地理信息字段 详细
 * user	                    object	微博作者的用户信息字段 详细
 * retweeted_status	        object	被转发的原微博信息字段，当该微博为转发微博时返回 详细
 * reposts_count	        int	    转发数
 * comments_count	        int	    评论数
 * attitudes_count	        int	    表态数
 * mlevel	                int	    暂未支持
 * visible	                object	微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
 * pic_ids	                object	微博配图ID。多图时返回多图ID，用来拼接图片url。用返回字段thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。
 * ad	                    object array	微博流内的推广微博ID*
 * </pre>
 * <p/>
 * *
 * lizheng -- 15/1/28
 * <p/>
 * FastWeiB
 */
public class StatusBean {
    public String created_at;
    public String id;
    public String mid;
    public String idstr;
    public String text;
    public String source;
    public boolean favorited;
    public boolean truncated;
    public String in_reply_to_status_id;
    public String in_reply_to_user_id;
    public String in_reply_to_screen_name;
    public String thumbnail_pic;
    public String bmiddle_pic;
    public String original_pic;
    public GeoBean geo;
    public UserBean user;
    public StatusBean retweeted_status;
    public String reposts_count;
    public String comments_count;
    public String attitudes_count;
    public String mlevel;
    public Objects visible;
    public Objects pic_ids;
    public Objects ad;
}
