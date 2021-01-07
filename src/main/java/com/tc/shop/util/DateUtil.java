package com.tc.wx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	private static SimpleDateFormat sdf = null;
	private static Date date = null;
	private static Integer todayOfMonth = null;
	private static Calendar calendar = null;

	public static Date daysLater(Integer dayNum) {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date=new Date();
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long today = calendar.getTime().getTime();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, todayOfMonth + dayNum);
		Long dayLater = calendar.getTime().getTime();
		Date date =null;
		try {
			date = sdf.parse(dayLater.toString());
			return date;
		} catch (ParseException e) {
		}
		return null;

	}
	public static long sevenDaysAgo() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long today = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(today));
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, todayOfMonth - 7);
		long recentSevenDay = calendar.getTime().getTime();
		//System.out.println("7天前：" + sdf.format(recentSevenDay));
		return recentSevenDay;

	}

	public static long fifteenDaysAgo() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long today = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(today));
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, todayOfMonth - 15);
		long recentSevenDay = calendar.getTime().getTime();
		//System.out.println("15天前：" + sdf.format(recentSevenDay));
		return recentSevenDay;

	}

	public static long thirtyDaysAgo() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long today = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(today));
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, todayOfMonth - 30);
		long thirtyDayao = calendar.getTime().getTime();
		//System.out.println("30天前：" + sdf.format(thirtyDayao));
		return thirtyDayao;

	}

	public static long thisDayBegin() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long today = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(today));
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		long recentSevenDay = calendar.getTime().getTime();
		//System.out.println("今天开始：" + sdf.format(recentSevenDay));
		return recentSevenDay;
	}

	public static long lastdayBegin() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long today = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(today));
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date time = calendar.getTime();
		//System.out.println("昨天开始：" + sdf.format(time));
		return time.getTime();
	}

	public static long thisMonthBegin() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long today = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(today));
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		long thisMonthBegin = calendar.getTime().getTime();
		//System.out.println("这个月开始：" + sdf.format(thisMonthBegin));
		return thisMonthBegin;
	}

	public static long thisWeekBegin() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long todayLong = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(todayLong));

		// 一周第一天是否为星期天
		boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		// 获取周几
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		// 若一周第一天为星期天，则-1
		if (isFirstSunday) {
			weekDay = weekDay - 1;
			if (weekDay == 0) {
				weekDay = 7;
			}
		}
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, today - weekDay + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		long time = calendar.getTime().getTime();
		//System.out.println("这周开始：" + sdf.format(time));
		return time;

	}
	public static long lastWeekBegin() {

		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long todayLong = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(todayLong));

		// 一周第一天是否为星期天
		boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		// 获取周几
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		// 若一周第一天为星期天，则-1
		if (isFirstSunday) {
			weekDay = weekDay - 1;
			if (weekDay == 0) {
				weekDay = 7;
			}
		}
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, today - weekDay -6);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		long time = calendar.getTime().getTime();
		//System.out.println("上周开始：" + sdf.format(time));
		return time;

	}
	public static String DayOfWeek(Date date) {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();// 取时间
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long today = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(today));
		calendar.setTime(date);
		// 一周第一天是否为星期天
		boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		// 获取周几
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		// 若一周第一天为星期天，则-1
		if (isFirstSunday) {
			weekDay = weekDay - 1;
			if (weekDay == 0) {
				weekDay = 7;
			}
		}
		String whatDay = "";
		switch (weekDay) {
			case 1:
				whatDay = "星期一";
				break;
			case 2:
				whatDay = "星期二";
				break;
			case 3:
				whatDay = "星期三";
				break;
			case 4:
				whatDay = "星期四";
				break;
			case 5:
				whatDay = "星期五";
				break;
			case 6:
				whatDay = "星期六";
				break;
			case 7:
				whatDay = "星期日";
				break;
			default:
				break;
		}
		return whatDay;

	}


	public static String thisDayBeginNew() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = new Date();
		calendar = new GregorianCalendar();
		todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		long todayLong = calendar.getTime().getTime();
		//System.out.println("现在：" + sdf.format(todayLong));
		date=new Date();
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		long thisDayBegin = calendar.getTime().getTime();
		//System.out.println("今天开始：" + sdf.format(thisDayBegin));
		return sdf.format(thisDayBegin);
	}


	public static Date parse(String time) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
		try {
			Date date = sdf.parse(time);
			return date;
		} catch (ParseException e) {

		}
		return null;
	}
}
