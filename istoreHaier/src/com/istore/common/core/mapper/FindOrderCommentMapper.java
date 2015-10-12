package com.istore.common.core.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.istore.common.core.bean.OrderComment;
import com.istore.common.core.provider.OrderCommentSqlProvider;

public interface FindOrderCommentMapper {

	/**
	 * 获得评论数量
	 * 
	 * @return
	 */
	@SelectProvider(type = OrderCommentSqlProvider.class, method = "getOrderCommentListCount")
	@Results(value = {
			@Result(column = "comment_id", property = "comment_id", id = true),
			@Result(column = "orders_id", property = "orders_id"),
			@Result(column = "store_id", property = "store_id"),
			@Result(column = "quality", property = "quality"),
			@Result(column = "service", property = "service"),
			@Result(column = "logistic", property = "logistic"),
			@Result(column = "comment_content", property = "comment_content"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "user_name", property = "user_name"),
			@Result(column = "replyName", property = "replyName"),
			@Result(column = "replyMessage", property = "replyMessage"),
			@Result(column = "replyTime", property = "replyTime"),
			@Result(column = "isReplied", property = "isReplied")})
	public List<OrderComment> getOrderCommentListCount(@Param("store_id") long store_id);
	
	
	public static final String GET_SEARCH_ORDERCOMMENT_LIST = "SELECT A.COMMENT_ID, A.ORDERS_ID, A.QUALITY, A.SERVICE, A.LOGISTIC, A.COMMENT_CONTENT, A.LASTUPDATE, A.USER_NAME, A.REPLYNAME, A.REPLYMESSAGE, A.REPLYTIME, A.ISREPLIED FROM (SELECT X.COMMENT_ID, X.ORDERS_ID, X.QUALITY, X.SERVICE, X.LOGISTIC, X.COMMENT_CONTENT, X.LASTUPDATE, X.USER_NAME, X.REPLYNAME, X.REPLYMESSAGE, X.REPLYTIME, X.ISREPLIED, ROWNUM ROWINDEX FROM (SELECT COMMENT_ID, ORDERS_ID, QUALITY, SERVICE, LOGISTIC, COMMENT_CONTENT, LASTUPDATE, USER_NAME, REPLYNAME, REPLYMESSAGE, REPLYTIME, ISREPLIED FROM XORDERCOMMENT WHERE STORE_ID = #{store_id}) X) A WHERE A.ROWINDEX > #{startIndex} AND A.ROWINDEX <= #{endIndex} ORDER BY A.LASTUPDATE DESC";

	/**
	 * 评论列表
	 * 
	 * @param xchannel_name
	 * @param tag
	 * @param type
	 * @param endIndex
	 * @param startIndex
	 * @return
	 */
	@Select(GET_SEARCH_ORDERCOMMENT_LIST)
	@Results(value = {
			@Result(column = "comment_id", property = "comment_id", id = true),
			@Result(column = "orders_id", property = "orders_id"),
			@Result(column = "store_id", property = "store_id"),
			@Result(column = "quality", property = "quality"),
			@Result(column = "service", property = "service"),
			@Result(column = "logistic", property = "logistic"),
			@Result(column = "comment_content", property = "comment_content"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "user_name", property = "user_name"),
			@Result(column = "replyName", property = "replyName"),
			@Result(column = "replyMessage", property = "replyMessage"),
			@Result(column = "replyTime", property = "replyTime"),
			@Result(column = "isReplied", property = "isReplied") })
	public List<OrderComment> getOrderCommentList(@Param("store_id") long store_id, @Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex);
	
	
	/**
	 * 获得当前编辑频道
	 * 
	 * @param xchannel_id
	 * @return
	 */
	@SelectProvider(type = OrderCommentSqlProvider.class, method = "getEditOrderCommentByID")
	@Results(value = {
			@Result(column = "comment_id", property = "comment_id", id = true),
			@Result(column = "orders_id", property = "orders_id"),
			@Result(column = "store_id", property = "store_id"),
			@Result(column = "quality", property = "quality"),
			@Result(column = "service", property = "service"),
			@Result(column = "logistic", property = "logistic"),
			@Result(column = "comment_content", property = "comment_content"),
			@Result(column = "lastupdate", property = "lastupdate"),
			@Result(column = "user_name", property = "user_name"),
			@Result(column = "replyName", property = "replyName"),
			@Result(column = "replyMessage", property = "replyMessage"),
			@Result(column = "replyTime", property = "replyTime") ,
			@Result(column = "isReplied", property = "isReplied") })
	public List<OrderComment> getEditOrderCommentListByID(@Param("comment_id") long comment_id);
	
	
	public static final String EDIT_ORDERCOMMENT_BY_ID = "UPDATE XORDERCOMMENT SET REPLYNAME=#{replyName}, REPLYMESSAGE=#{replyMessage},REPLYTIME=#{replyTime},ISREPLIED='Y' WHERE (COMMENT_ID = #{comment_id})";

	/**
	 * 保存评论
	 * @param comment_id
	 * @param replyName
	 * @param replyMessage
	 * @param replyTime
	 * @return
	 */
	@Update(EDIT_ORDERCOMMENT_BY_ID)
	public int editOrderCommentByID(@Param("comment_id") long comment_id, @Param("replyName") String replyName,@Param("replyMessage") String replyMessage,@Param("replyTime") Timestamp replyTime);
	
}
