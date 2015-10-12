package com.istore.common.core.provider;

import java.util.Map;

import com.istore.common.core.util.DateUtils;

public class PreSaleSqlProvider {
	 
	public String findReport(Map<String, Object> parameters){
		String beginDate = parameters.get("beginDate")+"";
		String endDate = parameters.get("endDate")+"";
		String userid = parameters.get("userid")+"";
		String startNum = parameters.get("startNum")+"";
		String endNum = parameters.get("endNum")+"";
		
		int i = getNum(beginDate, endDate);
		
		StringBuffer sql  =  new StringBuffer("select * from ( select tt.*,rownum as oraclenum from ( ");
		sql.append(" select T.MATERIAL,   T.USERID, ");
		String dateStr = "";
		for (int j = 0; j < i; j++) {
			dateStr = DateUtils.getDateStr(DateUtils.getDateAddMonth(DateUtils.convertStrToDate(beginDate, "yyyy-MM"), j),"yyyy-MM");
//			String datestri = dateStr+i;
//			
//			String dateStrb = "\"" + dateStr + "\"";
//			datestri = "\"" + datestri + "\"";
//			if(j == i-1){
//				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS "+dateStrb+" , ");
//				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+datestri+"  ");//获取实际销售数量
//			}else{
//				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS "+dateStrb+", ");
//				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+datestri+", ");//获取实际销售数量
//			}
			
			if (j == 0){
				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS "+"date1"+", ");
				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+"date1sale"+", ");//获取实际销售数量
			}else if(j == 1 ){
				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS "+"date2"+", ");
				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+"date2sale"+", ");//获取实际销售数量
			}else if(j == 2 ){
				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS "+"date3"+", ");
				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+"date3sale"+"  ");//获取实际销售数量
			}
			
			
		}
		sql.append(" ,MAX(T.CREATETIME) AS CRTIME from XHAIERPREDICTDATESALE t where userid = '"+userid+"' and PREDICTDATE >= '"+beginDate+"' and PREDICTDATE < '"+endDate+"'");
		sql.append(" GROUP BY T.USERID, T.MATERIAL ORDER BY CRTIME DESC");
		sql.append(" ) tt) where oraclenum > = "+startNum+" and oraclenum < "+endNum );
		return sql.toString();
	} 
	public String findListCount(Map<String, Object> parameters){
		String beginDate = parameters.get("beginDate")+"";
		String endDate = parameters.get("endDate")+"";
		String userid = parameters.get("userid")+"";
		int i = getNum(beginDate, endDate);
		StringBuffer sql  =  new StringBuffer("");
		sql.append(" select count(*) as listcount from ( select T.MATERIAL,   T.USERID, ");
		String dateStr = "";
		for (int j = 0; j < i; j++) {
			dateStr = DateUtils.getDateStr(DateUtils.getDateAddMonth(DateUtils.convertStrToDate(beginDate, "yyyy-MM"), j),"yyyy-MM");
			String datestri = dateStr+i;
			String dateStrb = "\"" + dateStr + "\"";
			datestri = "\"" + datestri + "\"";
			dateStr = DateUtils.getDateStr(DateUtils.getDateAddMonth(DateUtils.convertStrToDate(beginDate, "yyyy-MM"), j),"yyyy-MM");
//			if(j == i-1){
//				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS"+dateStrb+" , ");
//				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+datestri+"  ");//获取实际销售数量
//			}
//			else
				if (j == 0){
				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS "+"date1"+", ");
				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+"date1sale"+", ");//获取实际销售数量
			}else if(j == 1 ){
				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS "+"date2"+", ");
				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+"date2sale"+", ");//获取实际销售数量
			}else if(j == 2 ){
				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS "+"date3"+", ");
				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+"date3sale"+"  ");//获取实际销售数量
			}
//			else{
//				sql.append(" SUM(DECODE(T.PREDICTDATE, '"+dateStr+"', AMOUNT, '0')) AS"+dateStrb+", ");
//				sql.append("f_get_haier_ordernum('"+dateStr+"',T.USERID,T.MATERIAL) AS "+datestri+", ");//获取实际销售数量
//			}
		}
		sql.append(" from XHAIERPREDICTDATESALE t  where userid = '"+userid+"' and PREDICTDATE >= '"+beginDate+"' and PREDICTDATE <  '"+endDate+"' ");
		sql.append(" GROUP BY T.USERID, T.MATERIAL ) ");
		return sql.toString();
	}
	
	public Integer getNum(String beginDate, String endDate) {
		Integer beginYear = DateUtils.getYear(beginDate, "yyyy-MM");
		Integer beginMonth = DateUtils.getMonth(beginDate, "yyyy-MM");
		Integer endYear = DateUtils.getYear(endDate, "yyyy-MM");
		Integer endMonth = DateUtils.getMonth(endDate, "yyyy-MM");
		int i = (endYear - beginYear)*12 + (endMonth - beginMonth);
		return i;
	}
}
