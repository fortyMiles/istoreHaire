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
 * @ClassName: NewsSqlProvider.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:03:47
 */
public class NewsSqlProvider {

	/**
	 * 获得新闻数量
	 * 
	 * @return
	 */
	public String getNewsListCount(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XNEWS_ID, TITLE, FIRSTIMAGE, SUMMARY, DETAILS, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3")
				.FROM("XNEWS")
				.WHERE("STATUS != 'C' AND XCHANNEL_ID = "
						+ parameters.get("xchannel_id"));
		return sql.toString();
	}

	/**
	 * 获得创建时间新闻
	 * 
	 * @param parameters
	 * @return
	 */
	public String getNewsByCreatetime(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XNEWS_ID, TITLE, FIRSTIMAGE, SUMMARY, DETAILS, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3")
				.FROM("XNEWS")
				.WHERE("TO_CHAR(CREATETIME, 'YYYY-MM-DD HH24:MI:SS') = SUBSTR('"
						+ parameters.get("createtime")
						+ "', 0, 19) AND AUTHOR = " + parameters.get("author"));
		return sql.toString();
	}

	/**
	 * 获得当前编辑新闻
	 * 
	 * @param parameters
	 * @return
	 */
	public String getEditNewsListById(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XNEWS_ID, TITLE, FIRSTIMAGE, SUMMARY, DETAILS, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3")
				.FROM("XNEWS")
				.WHERE("XNEWS_ID = " + parameters.get("xnews_id"));
		return sql.toString();
	}

	/**
	 * 获得新闻搜索数量
	 * 
	 * @param parameters
	 * @return
	 */
	public String getSearchNewsListCount(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("XNEWS_ID, TITLE, FIRSTIMAGE, SUMMARY, DETAILS, CREATETIME, AUTHOR, XCHANNEL_ID, SEQ, XGROUP_ID, STATUS, FIELD1, FIELD2, FIELD3")
				.FROM("XNEWS")
				.WHERE("STATUS = 'C' AND TITLE LIKE " + parameters.get("title")
						+ " AND DETAILS  LIKE " + parameters.get("details"));
		return sql.toString();
	}

}
