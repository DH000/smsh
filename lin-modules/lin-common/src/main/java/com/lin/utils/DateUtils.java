package com.lin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 
 * @ClassName: DateUtils
 * @Description: 日期处理相关工具
 * @author xuelin
 * @date Jul 21, 2015 10:35:09 AM
 * @since 1.6
 */
public final class DateUtils {
	/**
	 * 周
	 * 
	 */
	public static final String[] WEEK = new String[] { "", "日", "一", "二", "三", "四", "五", "六" };

	/**
	 * 默认日期格式
	 * 
	 */
	public static final String DEFAULT_FORMAT_PATTERN = "yyyy-MM-dd";

	/**
	 * 日期格式化<br/>
	 * 默认日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DEFAULT_FORMAT_PATTERN);
	}
	
	/**
	 * 当前日期格式化<br/>
	 * 默认日期格式
	 * 
	 * @return
	 */
	public static String format() {
		return format(new Date(), DEFAULT_FORMAT_PATTERN);
	}

	/**
	 * 日期格式化<br/>
	 * 自定义日期格式
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 当前日期格式化<br/>
	 * 自定义日期格式
	 * 
	 * @param pattern
	 * @return
	 */
	public static String format(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	/**
	 * 日期转换<br/>
	 * 自定义日期格式
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dateStr, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 日期转换<br/>
	 * 默认日期格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, DEFAULT_FORMAT_PATTERN);
	}

	/**
	 * 转换成当天0点
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 转换成当天23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}

	/**
	 * 转换成当天23:59:59
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date getEndDate(String dateStr) {
		Date date = parseDate(dateStr);
		return getEndDate(date);
	}

	/**
	 * 判断日期时间是否相同
	 * 
	 * @param date
	 * @param otherDate
	 * @return
	 */
	public static boolean isSameDate(Date date, Date otherDate) {
		return date.equals(otherDate);
	}

	/**
	 * 判断日期是否相同
	 * 
	 * @param dateStr
	 * @param otherDateStr
	 * @return
	 */
	public static boolean isSameDay(String dateStr, String otherDateStr) {
		Date date = parseDate(dateStr);
		Date otherDate = parseDate(otherDateStr);

		return isSameDay(date, otherDate);
	}

	/**
	 * 判断日期是否相同
	 * 
	 * @param date
	 * @param otherDate
	 * @return
	 */
	public static boolean isSameDay(Date date, Date otherDate) {
		if (date.equals(otherDate)) {
			return true;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar otherCal = Calendar.getInstance();
		otherCal.setTime(otherDate);

		if (cal.get(Calendar.YEAR) != otherCal.get(Calendar.YEAR)) {
			return false;
		} else if (cal.get(Calendar.MONTH) != otherCal.get(Calendar.MONTH)) {
			return false;
		} else if (cal.get(Calendar.DAY_OF_MONTH) != otherCal.get(Calendar.DAY_OF_MONTH)) {
			return false;
		}

		return true;
	}

	/**
	 * 获取一周的下标
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekIndex(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取一周的下标
	 * 
	 * @param dateStr
	 * @return
	 */
	public static int getWeekIndex(String dateStr) {
		Date date = parseDate(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取一周具体的一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekStr(Date date) {
		int index = getWeekIndex(date);
		return WEEK[index];
	}

	/**
	 * 获取一周具体的一天
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getWeekStr(String dateStr) {
		int index = getWeekIndex(dateStr);
		return WEEK[index];
	}
}
