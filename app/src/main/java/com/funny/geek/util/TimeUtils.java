package com.funny.geek.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Funny
 * Time: 2018/10/22
 * Description: This is DateUtils
 */
public class TimeUtils {

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    public static Date str2Date(String s) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            return df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
