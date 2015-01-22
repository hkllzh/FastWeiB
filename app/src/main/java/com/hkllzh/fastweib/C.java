package com.hkllzh.fastweib;

/**
 * 常量类
 * <p/>
 * lizheng -- 15/1/10
 * <p/>
 * FastWeiB
 */
public final class C {
    public static final class SP {
        // 屏幕宽（px）
        public static final String SCREEN_WIDTH = "sp_screen_width";
        // 屏幕高（px）
        public static final String SCREEN_HEIGHT = "sp_screen_height";
    }

    public static final class WeiBo {
        public static final String APP_KEY = "2045436852"; // 应用的APP_KEY 
        public static final String REDIRECT_URL = "http://www.sina.com";// 应用的回调页
        // 应用申请的高级权限
        public static final String SCOPE =
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write";


    }
}
