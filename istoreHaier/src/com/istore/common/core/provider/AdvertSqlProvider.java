/**
 * 
 */
package com.istore.common.core.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author wangyan
 * 
 */
public class AdvertSqlProvider {


	
	public String getAdvertListSize(Map<String, Object> parameters)
	{
		SQL sql=new SQL().SELECT("COUNT(1)").FROM("x_espotimgrel").WHERE("TYPE="+parameters.get("advertType"));
		return sql.toString();
	}
	


	public String getFTPinfo() {

		SQL sql = new SQL().SELECT("HOST,PORT,USERNAME,PASSWORD,XPATH")
				.FROM("w_ftp").WHERE("type=1");
		return sql.toString();
	};

	public String findMaxPriority(String type) {
		SQL sql = new SQL().SELECT("MAX (priority)").FROM("x_espotimgrel")
				.WHERE("type=" + type);

		return sql.toString();
	};

	public String getAdvertById(int advertId) {
		SQL sql = new SQL().SELECT("ID,URL,IMG_ADDRESS,DESCPT,GROUPID")
				.FROM("x_espotimgrel").WHERE("ID=" + advertId);
		return sql.toString();

	}

	public String getImageMapByEId(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("ID, URL, IMG_ADDRESS, DESCPT, TYPE, PRIORITY").FROM("x_espotimgrel")
				.WHERE("TYPE = " + parameters.get("type") + " AND ID = "
						+ parameters.get("advertId")).ORDER_BY("PRIORITY");
		return sql.toString();
	}

}
