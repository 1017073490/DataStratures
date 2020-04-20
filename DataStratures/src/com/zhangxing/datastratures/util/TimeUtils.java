package com.zhangxing.datastratures.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-7 10:21
 */
public class TimeUtils {
    public static void getTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
}
