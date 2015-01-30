package com.hkllzh.fastweib.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;

/**
 * 微博时间解析工具
 * <p/>
 * lizheng -- 15/1/29
 * <p/>
 * FastWeiB
 */
public class WBTimeParseUtil {

    private static DateTimeFormatter formatter;
    private static HashMap<String, String> months;

    static {
        formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        months = new HashMap<>();
        months.put("Jan", "1");
        months.put("Feb", "2");
        months.put("Mar", "3");
        months.put("Apr", "4");
        months.put("May", "5");
        months.put("Jun", "6");
        months.put("Jul", "7");
        months.put("Aug", "8");
        months.put("Sept", "9");
        months.put("Oct", "10");
        months.put("Nov", "11");
        months.put("Dec", "12");
    }

    /**
     * 解析微博返回的时间为{@link org.joda.time.DateTime}，方便使用
     * <pre>
     * <b>微博返回时间格式</b>
     * Thu Jan 29 11:21:39 +0800 2015
     * </pre>
     *
     * @param created_at 微博返回的时间值
     * @return {@link org.joda.time.DateTime}实例
     */
    public static DateTime parse(String created_at) {
        String[] ss = created_at.split(" ");
        /**
         *
         parse 0 = Thu
         parse 1 = Jan
         parse 2 = 29
         parse 3 = 11:43:36
         parse 4 = +0800
         parse 5 = 2015
         */
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ss[5]).append("-").append(months.get(ss[1])).append("-").append(ss[2])
                .append(" ").append(ss[3]);//.append(" ").append(ss[4]);

        return DateTime.parse(stringBuilder.toString(), formatter);
    }
}
