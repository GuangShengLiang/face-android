package com.face.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author smlzhixing@163.com
 * @date 6/15/21 4:57 PM
 * @describe TODO
 */
public class TimeUtils {
    static SimpleDateFormat sf;
    /* 时间戳转换成字符窜 */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }
}
