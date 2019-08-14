package com.sina.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 将date转换成字符串
     * @param date
     * @param patt
     * @return
     */
    public static String dateTostring(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 将字符串转换成date类型
     * @param str
     * @param patt
     * @return
     * @throws ParseException
     */
    public static Date stringTodate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }
}
