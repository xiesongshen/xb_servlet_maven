package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auth admin
 * @date 2020/3/18 12:04
 * @Description
 */
public class DateUtil {

    public static String getDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

}
