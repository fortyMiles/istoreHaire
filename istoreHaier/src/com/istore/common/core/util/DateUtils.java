package com.istore.common.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期转换类  yyyy年MM月dd日HH时mm分ss秒
 * yyyy-MM-dd HH:mm:ss
 * @author whitebird520
 *
 */
public class DateUtils {

	/**
	 * 获得本月的第一天的日期
	 * @return 日期
	 */
	

	public static Date getCurrMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		String s=(getYear(cal))+"-"+(getMonth(cal))+"-01";
		return convertStrToDate(s,"yyyy-MM-dd");
	}

	/**
	 * 获得本月的最后一天的日期
	 * @return 日期
	 */
	public static Date getCurrMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		String s=(getYear(cal))+"-"+(getMonth(cal))+"-"+getDays(cal);
		return convertStrToDate(s,"yyyy-MM-dd");
	}
	/**
	 * 获得指定月的第一天的日期
	 * @param cal 日历
	 * @return 日期
	 */
	public static Date getCurrMonthFirstDay(Calendar cal) {
		String s=(getYear(cal))+"-"+(getMonth(cal))+"-01";
		return convertStrToDate(s,"yyyy-MM-dd");
	}
	/**
	 * 获得指定月的最后一天的日期
	 * @param cal 日历
	 * @return 日期
	 */
	public static Date getCurrMonthLastDay(Calendar cal) {
		String s=(getYear(cal))+"-"+(getMonth(cal))+"-"+getDays(cal);
		return convertStrToDate(s,"yyyy-MM-dd");
	}
	/**
	 * 获得给定日历的年
	 * @param cal 日历
	 * @return 年
	 */
	public static int getYear(Calendar cal) {
		return cal.get(Calendar.YEAR);
	}

	/**
	 *  获得给定日期的年
	 * @param date 日期
	 * @return 年
	 */
	public static int getYear(Date date) {
		return convertDateToCal(date).get(Calendar.YEAR);
	}

	/**
	 * 获得给定日期字符串对应的年
	 * @param date_str 日期
	 * @param type 格式
	 * @return 年
	 */
	public static int getYear(String date_str, String type) {
		return (convertStrToCal(date_str, type).get(Calendar.YEAR));
	}

	/**
	 * 获得给定日历的月
	 * @param cal 日历
	 * @return 月
	 */
	public static int getMonth(Calendar cal) {
		return (cal.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获得给定日期字符串对应的月
	 * @param date 日期
	 * @return 月
	 */
	public static int getMonth(Date date) {
		return (convertDateToCal(date).get(Calendar.MONTH) + 1);
	}

	/**
	 * 获得给定日期字符串对应的月
	 * @param date_str 日期
	 * @param type 类型
	 * @return 月
	 */
	public static int getMonth(String date_str, String type) {
		return (convertStrToCal(date_str, type).get(Calendar.MONTH) + 1);
	}

	/**
	 * 获得给定日期的当天
	 * @param cal 日历
	 * @return 天数
	 */
	public static int getDay(Calendar cal) {
		return (cal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获得给定日期的当天
	 * @param date 日期
	 * @return 天数
	 */
	public static int getDay(Date date) {
		return (convertDateToCal(date).get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获得给定日期的当天
	 * @param date_str 字符串日期
	 * @param type 类型
	 * @return 天数
	 */
	public static int getDay(String date_str, String type) {
		return (convertStrToCal(date_str, type).get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获得给定日期当月的天数
	 * @param cal 日历
	 * @return 天数
	 */
	public static int getDays(Calendar cal) {
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得给定日期当月的天数
	 * @param date 日期
	 * @return 天数
	 */
	public static int getDays(Date date) {
		return (convertDateToCal(date).getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获得给定日期当月的天数
	 * @param date_str 字符串日期
	 * @param type 格式
	 * @return 天数
	 */
	public static int getDays(String date_str, String type) {
		return (convertStrToCal(date_str, type)
				.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获得当前日期
	 * @return 日期
	 */
	public static Date getCurrDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();

	}

	/**
	 * 获得当前年
	 * @return 年
	 */
	public static int getCurrYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得当前月
	 * @return 月
	 */
	public static int getCurrMonth() {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获得当前天
	 * @return 天数
	 */
	public static int getCurrDay() {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 日期转换字符(动态格式转换)
	 * @param date 日期	
	 * @param type 类型
	 * @return 字符串时间
	 */
	public static String convertDateToStr(Date date, String type) {
		SimpleDateFormat dateformat = new SimpleDateFormat(type);
		return dateformat.format(date);
	}

	/**
	 * 字符转换日期(动态格式转换)
	 * @param date_str 字符串时间
	 * @param type 类型
	 * @return 时间
	 */
	public static Date convertStrToDate(String date_str, String type) {
		SimpleDateFormat dateformat = new SimpleDateFormat(type);
		try {
			return dateformat.parse(date_str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符转换日历(动态格式转换) 
	 * @param date_str 字符串式日期
	 * @param type 类型
	 * @return 日历
	 */
	public static Calendar convertStrToCal(String date_str, String type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertStrToDate(date_str, type));
		return cal;
	}

	/**
	 * 日期转日历
	 * @param date 日期
	 * @return 日历
	 */
	public static Calendar convertDateToCal(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}


	/**
	 * 判断日期格式是否正确
	 * @param date_str 字符串时间
	 * @param type 格式字符串
	 * @return 是否正确
	 */
	public static boolean isDate(String date_str,String type) {
		SimpleDateFormat dateformat = new SimpleDateFormat(type);
		try {
			dateformat.parse(date_str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	  /**
	   * 获得在此时间后几天的时间
	   * @param amount 天数
	   * @param type 类型
	   * @return 时间
	   */
	  public Date getCurDateAddDate( int amount,int type) {
		  	Calendar cal = Calendar.getInstance();
		  	cal.add(Calendar.DATE, amount);
		    return cal.getTime();
	  }
	  
	  /**
	   * 获得指定时间后几天的时间
	   * @param date 指定时间
	   * @param type 类型
	   * @param amount 天数
	   * @return 时间
	   */
	  public static Date getDateAddDate(Date date,int amount){
		  	Calendar cal = Calendar.getInstance();
		  	cal.clear();
		  	cal.setTime(date);
		  	cal.add(Calendar.DATE, amount);
		    return cal.getTime();
	  }
	  /**
	   * 得到指定时间后添加的月份
	   * @param date
	   * @param months
	   * @return
	   */
	  public static Date getDateAddMonth(Date date,int months){
		  Calendar cal = Calendar.getInstance();
		  cal.clear();
		  cal.setTime(date);
		  cal.add(Calendar.MONTH, months);
		  return cal.getTime();
	  }
	  /**
	   * 将指定的时间增加指定的年
	   * @param date
	   * @param years
	   * @return
	   */
	  public static Date getDateAddYears(Date date,int years){
		  Calendar cal = Calendar.getInstance();
		  cal.clear();
		  cal.setTime(date);
		  cal.add(Calendar.YEAR, years);
		  return cal.getTime();
	  }
	  /**
	   * 将指定日期增加几小时
	   * @param date
	   * @return
	   */
	  public static Date getDateAddHours(Date date, int hours){
		  Calendar cal = Calendar.getInstance();
		  cal.clear();
		  cal.setTime(date);
		  cal.add(Calendar.HOUR, hours);
		  return cal.getTime();
	  }
	  /**
	   * 格式化时间
	   * @param date
	   * @param type
	   * @return
	   */
	  public static Date getformatdate(Date date,String type){
		  SimpleDateFormat format = new SimpleDateFormat(type);
		  String nowtime1 = format.format(date);
			Date SSmsdate = null;
			try {
				SSmsdate = format.parse(nowtime1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SSmsdate;
	  }
	  /**
	   * 取得时间序列字符串
	   * @return
	   * @exception 
	   * 2009-4-10
	   * TODO
	   */
	  public static String getdatestr(){
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date data = new Date();
			return format.format(data);
	  }
	  
	  /**
		 * 日期转换字符(动态格式转换)
		 * @param date 日期	
		 * @param type 类型
		 * @return 字符串时间
		 */
		public static String getDateStr(Date date, String type) {
			SimpleDateFormat dateformat = new SimpleDateFormat(type);
			return dateformat.format(date);
		}
		
	/**
	 * 得到当前时间的上一个月
	 * @return
	*/
	public static String getprievMoth() {
		GregorianCalendar gr = new GregorianCalendar();
	    gr.add(GregorianCalendar.MONTH, -1);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String nowtime = format.format(gr.getTime());
		return nowtime;
	}

	
	/**
	 * 根据字符串年月+数字天 得出日期
	 * @param date_str
	 * @param type
	 * @param n
	 * @return
	 * @exception 
	 * 2009-5-7
	 * TODO
	 */
	public static Date getdateaddN(String date_str, String type,int n){
		Calendar cal = convertStrToCal(date_str, type);
		Calendar calt = Calendar.getInstance();
		calt.clear();
		calt.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), n);
		Date date = calt.getTime();
		return date;
	}

	/**
	 * 将long数据转换成hh:mm:ss形式显示
	 * @param time
	 * @return
	 */
	public static String getHhmmss(long time){
		StringBuffer result = new StringBuffer();
		long hour = (time / 60) / 60 ;
		long min = (time/60)%60 ;
		long sec = time % 60 ;
		if(hour < 10){//小时
			result.append("0".concat(String.valueOf(hour)).concat(":"));
		}else{
			result.append(String.valueOf(hour).concat(":"));
		}
		if(min<10){//分
			result.append("0".concat(String.valueOf(min)).concat(":"));
		}else{
			result.append(String.valueOf(min).concat(":"));
		}
		if(sec<10){//秒
			result.append("0".concat(String.valueOf(sec)));
		}else{
			result.append(String.valueOf(sec));
		}
		return result.toString();
	}
	
	/**
	 * 星期的加减操作
	 * @param DAY_OF_WEEK 
	 * @param difference 星期的差值
	 * @param type //类型
	 * @return string
	 */
	public static String getWeekaddWeek(int DAY_OF_WEEK,int difference,String type) {
		GregorianCalendar gr = new GregorianCalendar();
	    gr.add(DAY_OF_WEEK, difference);
	    SimpleDateFormat format = new SimpleDateFormat(type);
		String nowtime = format.format(gr.getTime());
		return nowtime;
	}
}
