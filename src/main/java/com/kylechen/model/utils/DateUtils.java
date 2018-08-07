package com.kylechen.model.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 时间处理工具类
 * 
 * @author kylechen
 * 
 */
public class DateUtils implements BeanFactoryAware {


	/**
	 * 静态常量
	 */
	public static final String C_TIME_PATTON_DEFAULT = "yyyyMMddHHmmss";
	public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
	public static final String C_DATE_PATTON = "yyyyMMdd";

	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

	/*
	 * 获取时间
	 */public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 计算当前月份的最大天数
	 * 
	 * @return
	 */
	public static int findMaxDayInMonth() {
		return findMaxDayInMonth(0, 0);
	}

	/**
	 * 计算指定日期月份的最大天数
	 * 
	 * @param date
	 * @return
	 */
	public static int findMaxDayInMonth(Date date) {
		if (date == null) {
			return 0;
		}
		return findMaxDayInMonth(date2Calendar(date));
	}

	/**
	 * 计算指定日历月份的最大天数
	 * 
	 * @param calendar
	 * @return
	 */
	public static int findMaxDayInMonth(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}

		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 计算当前年某月份的最大天数
	 * 
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int month) {
		return findMaxDayInMonth(0, month);
	}

	/**
	 * 计算某年某月份的最大天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}

		if (month > 0) {
			calendar.set(Calendar.MONTH, month - 1);
		}

		return findMaxDayInMonth(calendar);
	}

	/**
	 * Calendar 转换为 Date
	 * 
	 * @param calendar
	 * @return
	 */
	public static Date calendar2Date(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.getTime();
	}

	/**
	 * Date 转换为 Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar date2Calendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 拿到默认格式的SimpleDateFormat
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return getSimpleDateFormat(null);
	}

	/**
	 * 拿到指定输出格式的SimpleDateFormat
	 * 
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat sdf;
		if (format == null) {
			sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
		} else {
			sdf = new SimpleDateFormat(format);
		}

		return sdf;
	}

	/**
	 * 转换当前时间为默认格式
	 * 
	 * @return
	 */
	public static String formatDate2Str() {
		return formatDate2Str(new Date());
	}

	/**
	 * 转换当前时间为默认格式
	 * 
	 * @return
	 */
	public static String formatDate2() {
		return formatDate2(new Date());
	}

	/**
	 * 转换指定时间为默认格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate2Str(Date date) {
		return formatDate2Str(date, C_TIME_PATTON_DEFAULT);
	}

	/**
	 * 转换指定时间为默认格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate2(Date date) {
		return formatDate2Str(date, C_DATE_PATTON);
	}

	/**
	 * 转换指定时间为指定格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate2Str(Date date, String format) {
		if (date == null) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 转换默认格式的时间为Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date formatStr2Date(String dateStr) {
		return formatStr2Date(dateStr, null);
	}

	/**
	 * 转换指定格式的时间为Date
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date formatStr2Date(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.parse(dateStr, new ParsePosition(0));
	}

	/**
	 * 转换默认格式的时间为指定格式时间
	 * 
	 * @param dateStr
	 * @param defineFormat
	 * @return
	 */
	public static String formatDefault2Define(String dateStr,
			String defineFormat) {
		return formatSource2Target(dateStr, C_TIME_PATTON_DEFAULT, defineFormat);
	}

	/**
	 * 转换源格式的时间为目标格式时间
	 * 
	 * @param dateStr
	 * @param sourceFormat
	 * @param targetFormat
	 * @return
	 */
	public static String formatSource2Target(String dateStr,
			String sourceFormat, String targetFormat) {
		Date date = formatStr2Date(dateStr, sourceFormat);
		return formatDate2Str(date, targetFormat);
	}

	/**
	 * 计算当天是该年中的第几周
	 * 
	 * @return
	 */
	public static int findWeeksNoInYear() {
		return findWeeksNoInYear(new Date());
	}

	/**
	 * 计算指定日期是该年中的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int findWeeksNoInYear(Date date) {
		if (date == null) {
			return 0;
		}
		return findWeeksNoInYear(date2Calendar(date));
	}

	/**
	 * 计算指定日历是该年中的第几周
	 * 
	 * @param calendar
	 * @return
	 */
	public static int findWeeksNoInYear(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 计算一年中的第几星期是几号
	 * 
	 * @param year
	 * @param weekInYear
	 * @param dayInWeek
	 * @return
	 */
	public static Date findDateInWeekOnYear(int year, int weekInYear,
			int dayInWeek) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}

		calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);
		calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);

		return calendar.getTime();
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param days
	 * @return
	 */
	public static Date dayBefore2Date(int days) {
		Date date = new Date();
		return dayBefore2Object(days, null, date);
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param days
	 * @return
	 */
	public static String dayBefore2Str(int days) {
		String string = formatDate2Str();
		return dayBefore2Object(days, null, string);
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0) 项目用（自己添加方法）
	 * 
	 * @param days
	 * @return
	 */
	public static String dayBefore2(int days) {
		String string = formatDate2();
		return dayBefore2Object(days, null, string);
	}

	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param days
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T dayBefore2Object(int days,
			String format, T instance) {
		Calendar calendar = Calendar.getInstance();
		if (days == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_DATE_PATTON;
		}

		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date defineDayBefore2Date(Date date, int days) {
		Date dateInstance = new Date();
		return defineDayBefore2Object(date, days, null, dateInstance);
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String defineDayBefore2Str(Date date, int days) {
		String stringInstance = formatDate2Str();
		return defineDayBefore2Object(date, days, null, stringInstance);
	}

	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * 
	 * @param <T>
	 * @param date
	 * @param days
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineDayBefore2Object(Date date,
			int days, String format, T instance) {
		if (date == null || days == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}

		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param months
	 * @return
	 */
	public static Date monthBefore2Date(int months) {
		Date date = new Date();
		return monthBefore2Object(months, null, date);
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param months
	 * @return
	 */
	public static String monthBefore2Str(int months) {
		String string = formatDate2Str();
		return monthBefore2Object(months, null, string);
	}

	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param <T>
	 * @param months
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T monthBefore2Object(int months,
			String format, T instance) {
		if (months == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, months);

		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}

		return null;
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date defineMonthBefore2Date(Date date, int months) {
		Date dateInstance = new Date();
		return defineMonthBefore2Object(date, months, null, dateInstance);
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static String defineMonthBefore2Str(Date date, int months) {
		String stringInstance = formatDate2Str();
		return defineMonthBefore2Object(date, months, null, stringInstance);
	}

	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * 
	 * @param <T>
	 * @param date
	 * @param months
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineMonthBefore2Object(Date date,
			int months, String format, T instance) {
		if (months == 0) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}

		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.MONTH, months);

		if (instance instanceof Date) {
			return (T) calendar.getTime();
		} else if (instance instanceof String) {
			return (T) formatDate2Str(calendar2Date(calendar), format);
		}

		return null;
	}

	/**
	 * 计算两个日期直接差的天数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int caculate2Days(Date firstDate, Date secondDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(secondDate);
		int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
		return Math.abs(dayNum1 - dayNum2);
	}

	/**
	 * 计算两个日期直接差的天数
	 * 
	 * @param firstCalendar
	 * @param secondCalendar
	 * @return
	 */
	public static int caculate2Days(Calendar firstCalendar,
			Calendar secondCalendar) {
		if (firstCalendar.after(secondCalendar)) {
			Calendar calendar = firstCalendar;
			firstCalendar = secondCalendar;
			secondCalendar = calendar;
		}

		long calendarNum1 = firstCalendar.getTimeInMillis();
		long calendarNum2 = secondCalendar.getTimeInMillis();
		return Math.abs((int) ((calendarNum1 - calendarNum2) / C_ONE_DAY));
	}

	/**
	 * 当前日期加减n天后的日期
	 * 
	 * @param firstCalendar
	 * @param secondCalendar
	 * @return
	 */
	public static Date nDaysAftertoday(Date d, short n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DAY_OF_MONTH, +n);
		return calendar.getTime();
	}

	/**
	 * 判断是否为合法的日期时间字符串
	 * 
	 * @param str
	 *            时间字符串
	 * @param rDateFormat
	 *            指定日期格式
	 * @return boolean;符合为true,不符合为false
	 */
	public static boolean isDate(String str, String rDateFormat) {
		if (str != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
			formatter.setLenient(false);
			try {
				formatter.format(formatter.parse(str));
				String year = str.substring(0, 4);
				String month = str.substring(4, 6);
				String day = str.substring(6, str.length());
				int maxDays = 31;
				if (Check(year) == false || Check(month) == false
						|| Check(day) == false) {

					return false;
				} else if (year.length() < 4) {

					return false;
				}
				int y = Integer.parseInt(year);
				int m = Integer.parseInt(month);
				int d = Integer.parseInt(day);
				if (m > 12 || m < 1) {

					return false;
				} else if (m == 4 || m == 6 || m == 9 || m == 11) {
					maxDays = 30;
				} else if (m == 2) {
					if (y % 4 > 0)
						maxDays = 28;
					else if (y % 100 == 0 && y % 400 > 0)
						maxDays = 28;
					else
						maxDays = 29;
				}
				if (d < 1 || d > maxDays) {

					return false;
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}

	}

	public static boolean Check(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str.trim());
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 操作月份
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonths(Date date, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(2, amount);
			return c.getTime();
		}
	}

	public static Date addDays(Date date, int days) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, days);
			return c.getTime();
		}
	}

	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		// TODO Auto-generated method stub

	}

	/**
	 * 判断两个日期与当天日期相差的天数 <li>如果两个日期都为空，返回true</li> <li>
	 * 如果其中一个不为空，判断不为空的日期与当天日期相差天数，如果>=32,返回 true,否则返回false</li> <li>
	 * 如果两个日期都不为空，且与当天日期相差天数都>=32,返回 true,否则返回false</li>
	 * 
	 */
	public static boolean checkDate(String today, String date1, String date2) {
		// String today = ServiceUtils.getBizDate();
		if (StringUtils.isBlank(date1) && StringUtils.isBlank(date2)) {
			return true;
		} else if (StringUtils.isBlank(date1)) {
			long days = getDateInterval(today, date2);
			return days >= 32;
		} else if (StringUtils.isBlank(date2)) {
			long days = getDateInterval(today, date1);
			return days >= 32;
		} else {
			long days1 = getDateInterval(today, date1);
			long days2 = getDateInterval(today, date2);
			return days1 >= 32 && days2 >= 32;
		}
	}

	// 取得两日期间隔天数:date1 - date2
	public static long getDateInterval(String date1, String date2) {
		// 将 JDBC 日期转义形式的字符串转换成 Date 值
		String yyyy1 = date1.substring(0, 4);
		String mm1 = date1.substring(4, 6);
		String dd1 = date1.substring(6, 8);
		String dateStr1 = yyyy1 + "-" + mm1 + "-" + dd1;
	
		Date dateFormat1 = java.sql.Date.valueOf(dateStr1);

		String yyyy2 = date2.substring(0, 4);
		String mm2 = date2.substring(4, 6);
		String dd2 = date2.substring(6, 8);
		String dateStr2 = yyyy2 + "-" + mm2 + "-" + dd2;
		
		Date dateFormat2 = java.sql.Date.valueOf(dateStr2);

		long days = 0;
		days = (dateFormat1.getTime() - dateFormat2.getTime())
				/ (24 * 60 * 60 * 1000);
		return days;
	}
}
