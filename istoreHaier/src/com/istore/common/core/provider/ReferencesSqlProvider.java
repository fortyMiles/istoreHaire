/**
 * @Project istoreHaier
 * @Package com.istore.common.core.provider
 * @Title ChannelSqlProvider.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName: ChannelSqlProvider.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:03:47
 */
public class ReferencesSqlProvider {
	/**
	 * 获得新闻数量
	 * 
	 * @return
	 */
	public String getReferencesListSize(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3")
				.FROM("XREFERENCES")
				.WHERE("STATUS != 'C' AND XCHANNEL_ID = "
						+ parameters.get("xchannel_id"));
		return sql.toString();
	}

	/**
	 * 获得创建时间样板工程
	 * 
	 * @param parameters
	 * @return
	 */
	public String getReferencesByCreatetime(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3")
				.FROM("XREFERENCES")
				.WHERE("TO_CHAR(CREATETIME, 'YYYY-MM-DD HH24:MI:SS') = SUBSTR('"
						+ parameters.get("createtime")
						+ "', 0, 19) AND AUTHOR = " + parameters.get("author"));
		return sql.toString();
	}

	/**
	 * 获得当前编辑样板工程
	 * 
	 * @param parameters
	 * @return
	 */
	public String getEditReferencesListByID(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, CATGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3")
				.FROM("XREFERENCES")
				.WHERE("XREFERENCES_ID = " + parameters.get("xreferences_id"));
		return sql.toString();
	}

	/**
	 * 获得样板工程搜索数量
	 * 
	 * @return
	 */
	public String getSearchReferencesListCount(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XREFERENCES_ID, SERIALNUMBER, COUNTRY, PROJECTPLACE, DESCRIPTION, TYPE, INSTALLTIME, INSTALLSERIES, INSTALLDETAILS, TOTALCAPACITY, KEYCAPACITY, PICTURES, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, CATGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3")
				.FROM("XREFERENCES")
				.WHERE("STATUS = 'C' AND SERIALNUMBER LIKE '"
						+ parameters.get("serialnumber")
						+ "' AND COUNTRY  LIKE '" + parameters.get("country")
						+ "' AND PROJECTPLACE  LIKE '"
						+ parameters.get("projectplace")
						+ "' AND DESCRIPTION  LIKE '"
						+ parameters.get("description") + "' AND TYPE  LIKE '"
						+ parameters.get("type") + "' AND INSTALLTIME  LIKE '"
						+ parameters.get("installtime")
						+ "' AND INSTALLSERIES  LIKE '"
						+ parameters.get("installseries")
						+ "' AND INSTALLDETAILS  LIKE '"
						+ parameters.get("installdetails")
						+ "' AND TOTALCAPACITY  LIKE '"
						+ parameters.get("totalcapacity")
						+ "' AND KEYCAPACITY  LIKE '"
						+ parameters.get("keycapacity") + "'");
		return sql.toString();
	}
}
