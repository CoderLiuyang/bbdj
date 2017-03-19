package com.bbdj8.sys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    public static Date format(String str, String pattern) throws ParseException {
    	if(str != null){
    		SimpleDateFormat df = new SimpleDateFormat(pattern);
    		return df.parse(str);
    	}
    	return null;
    }
    /**
     * 计算两个日期间的天数
     * @param sd 开始日期
     * @param ed 结束日期
     * @return 天数
     */
	public static int getMonthSpace(Date sd, Date ed) {
		int result = 0;
		result = (int) Math.floor((ed.getTime() - sd.getTime()) / (1000 * 3600 * 24));
		return result;
	}
	public static void main(String[] args) {
		try {
			System.out.println(getMonthSpace(format("2017-01-28 17:04:00", "yyyy-MM-dd"), new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
