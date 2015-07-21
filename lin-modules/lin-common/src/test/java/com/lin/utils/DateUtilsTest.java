package com.lin.utils;

import java.util.Date;

import org.junit.Test;

/**
 * 
 * 
 * @ClassName: DateUtilsTest 
 * @Description: 日期工具测试
 * @author xuelin 
 * @date Jul 21, 2015 11:57:32 AM 
 *
 */
public class DateUtilsTest {

	@Test
	public void formatTest(){
		System.out.println(DateUtils.format());
		System.out.println(DateUtils.format("yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateUtils.format(new Date()));
		System.out.println(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void parseDateTest(){
		System.out.println(DateUtils.parseDate("2015-01-12 05:20:00").toLocaleString());
		System.out.println(DateUtils.parseDate("2015-01-12 05:20:00", "yyyy-MM-dd HH:mm:ss").toLocaleString());
	}
	
	@Test
	public void getStartDateTest(){
		System.out.println(DateUtils.getStartDate(new Date()).toLocaleString());
	}
	
	@Test
	public void getEndDateTest(){
		System.out.println(DateUtils.getEndDate(new Date()).toLocaleString());
		System.out.println(DateUtils.getEndDate("2015-02-16 05").toLocaleString());
	}
	
	@Test
	public void isSameDateTest(){
		Date date = new Date();
		Date otherDate = new Date();
		System.out.println(DateUtils.isSameDate(date, date));
		System.out.println(DateUtils.isSameDate(date, otherDate));
		System.out.println(date.toLocaleString());
		System.out.println(otherDate.toLocaleString());
	}
	
	@Test
	public void isSameDayTest(){
		Date date = new Date();
		Date otherDate = new Date();
		System.out.println(DateUtils.isSameDay(date, otherDate));
		System.out.println(DateUtils.isSameDay("2015-6-18", "2015-06-18"));
		System.out.println(DateUtils.isSameDay("2015-6-16", "2015-06-18"));
	}
	
	@Test
	public void getWeekIndexTest(){
		System.out.println(DateUtils.getWeekIndex(new Date()));
		System.out.println(DateUtils.getWeekIndex("2015-07-21"));
	}
	
	@Test
	public void getWeekStrTest(){
		System.out.println(DateUtils.getWeekStr(new Date()));
		System.out.println(DateUtils.getWeekStr("2015-07-21"));
	}
}
