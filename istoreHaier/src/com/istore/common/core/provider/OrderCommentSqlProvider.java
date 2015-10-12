package com.istore.common.core.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class OrderCommentSqlProvider {

	/**
	 * 获得评论数量
	 * 
	 * @return
	 */
	public String getOrderCommentListCount(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("COMMENT_ID, ORDERS_ID, STORE_ID, QUALITY, SERVICE, LOGISTIC, COMMENT_CONTENT, LASTUPDATE, USER_NAME, REPLYNAME, REPLYMESSAGE, REPLYTIME, ISREPLIED")
				.FROM("XORDERCOMMENT")
				.WHERE("STORE_ID = " + parameters.get("store_id"));
		return sql.toString();
	}

	
	/**
	 * 获得当前评论
	 * 
	 * @param parameters
	 * @return
	 */
	public String getEditOrderCommentByID(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT("ORDERS_ID, STORE_ID, QUALITY, SERVICE, LOGISTIC, COMMENT_CONTENT, LASTUPDATE, USER_NAME, REPLYNAME, REPLYMESSAGE, REPLYTIME, ISREPLIED")
				.FROM("XORDERCOMMENT")
				.WHERE("COMMENT_ID = " + parameters.get("comment_id"));
		return sql.toString();
	}
	
	
}
