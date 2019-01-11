package com.hisi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日起工具类
 * 
 * @author Ys 2017年10月19日 下午1:44:23
 */
public class DateUtil {

	public enum Pattern {
		/**
		 * 通用时间格式
		 */
		PATTERN_0("yyyy-MM-dd HH:mm:ss"),
		/**
		 * 数字型时间格式
		 */
		PATTERN_1("yyyyMMddHHmmss"),
		/**
		 * yyyyMMdd
		 */
		PATTERN_2("yyyyMMdd"),
		/**
		 * yyyy-MM-dd
		 */
		PATTERN_3("yyyy-MM-dd"),
		/**
		 * yyyy/MM/dd
		 */
		PATTERN_4("yyyy/MM/dd"),
		/**
		 * yyyyMM
		 */
		PATTERN_5("yyyyMM"),
		/**
		 * UTC时间格式
		 */
		PATTERN_UTC("yyyy-MM-dd'T'HH:mm:ss'Z'");

		private String value;

		Pattern(String v) {
			this.value = v;
		}

		public String getValue() {
			return value;
		}
	}

	public static String getSysTime(Pattern p) {
		SimpleDateFormat df = new SimpleDateFormat(p.value);
		Date curDate = new Date();
		String str = df.format(curDate);
		return str;
	}

	/**
	 * 获取相对UTC时间
	 * 
	 * @author Ys 2017年11月24日 下午4:50:03
	 * @param p
	 *            格式
	 * @return
	 */
	public static String getUTCTime(Pattern p) {
		// 1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		SimpleDateFormat format = new SimpleDateFormat(p.getValue());
		return format.format(cal.getTime());
	}

	/**
	 * 解析年的每月
	 * 
	 * @param year
	 * 
	 * 
	 *            2017
	 * @return 集合(1,2,3..)
	 * @throws ParseException
	 */
	public static List<String> parseMonthListByYear(String year)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		List<String> list = new ArrayList<String>();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(simpleDateFormat2.parse(year));
		int maxDay = calendar.getActualMaximum(Calendar.MONTH);
		int minDay = calendar.getActualMinimum(Calendar.MONTH);
		for (int i = minDay; i <= maxDay; i++) {
			calendar.set(Calendar.MONTH, i);
			list.add(simpleDateFormat.format(calendar.getTime()));
		}
		return list;
	}

	/**
	 * 解析月的每天
	 * 
	 * @param monthStr
	 *            201706
	 * @return 集合（1,2,3,4,5...）
	 * @throws ParseException
	 */
	public static List<String> parseDayListByMonth(String monthStr)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		List<String> list = new ArrayList<String>();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(simpleDateFormat2.parse(monthStr));
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		for (int i = minDay; i <= maxDay; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i);
			list.add(simpleDateFormat.format(calendar.getTime()));
		}
		return list;
	}

	/**
	 * 获取当前时间往前多少小时的每小时
	 * 
	 * @param amount
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static List<String> parseHourList(int amount) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, amount);
		for (int i = amount; i < 0; i++) {
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			list.add(simpleDateFormat.format(calendar.getTime()));
		}
		return list;
	}

	public static String getDateHour(String dateStr, int amount, Pattern p)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(p.getValue());
		Date date = sdf.parse(dateStr);
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date);
		calendar1.add(Calendar.HOUR_OF_DAY, amount);
		return sdf.format(calendar1.getTime());
	}

	public static String getDateMinute(String dateStr, int amount, Pattern p)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(p.getValue());
		Date date = sdf.parse(dateStr);
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date);
		calendar1.add(Calendar.MINUTE, amount);
		return sdf.format(calendar1.getTime());
	}

	/**
	 * 日期加减天
	 * 
	 * @param dateStr
	 *            20170612121540
	 * @param amount
	 *            -1
	 * @return 20170611121540
	 * @throws ParseException
	 */
	public static String getDateDay(String dateStr, int amount, Pattern p)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(p.getValue());
		Date date = sdf.parse(dateStr);
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date);
		calendar1.add(Calendar.DAY_OF_MONTH, amount);
		return sdf.format(calendar1.getTime());
	}

	/**
	 * 日期加减月
	 * 
	 * @param dateStr
	 *            20170612121540
	 * @param amount
	 *            -1
	 * @return 20170512121540
	 * @throws ParseException
	 */
	public static String getDateMonth(String dateStr, int amount, Pattern p)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(p.getValue());
		Date date = sdf.parse(dateStr);
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date);
		calendar1.add(Calendar.MONTH, amount);
		return sdf.format(calendar1.getTime());
	}

	/**
	 * 日期加减年
	 * 
	 * @param dateStr
	 *            20170612121540
	 * @param amount
	 *            -1
	 * @return 20160612121540
	 * @throws ParseException
	 */
	public static String getDateYear(String dateStr, int amount, Pattern p)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(p.getValue());
		Date date = sdf.parse(dateStr);
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date);
		calendar1.add(Calendar.YEAR, amount);
		return sdf.format(calendar1.getTime());
	}

	/**
	 * 转换时间格式
	 * 
	 * @author Ys 2017年10月10日 下午5:25:45
	 * @param pattern
	 *            原格式
	 * @param patternTo
	 *            新格式
	 * @param dateStr
	 *            时间字符串
	 * @return
	 * @throws ParseException
	 */
	public static String converDate(Pattern pattern, Pattern patternTo,
			String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue());
		Date parse = sdf.parse(dateStr);
		SimpleDateFormat sdf2 = new SimpleDateFormat(patternTo.getValue());
		return sdf2.format(parse);
	}

	/**
	 * 获取当前日期是星期几
	 * 
	 * @author 吴羽娟 2017年11月21日上午10:02:03
	 * @param dateStr
	 * @return
	 */
	public static String getWeekOfDate(String dateStr) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
}
