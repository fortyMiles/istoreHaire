package com.istore.common.core.dao;

import java.sql.Timestamp;
import java.util.List;

import com.istore.common.core.bean.OrderComment;

public interface OrderCommentDao {

	/**
	 * 获得评论数量 
	 * @param store_id
	 * @return
	 */
	public int getOrderCommentListSize(long store_id);

	/**
	 * 获得评论列表 
	 * @param store_id
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<OrderComment> getOrderCommentList(long store_id, int startIndex, int endIndex);
	
	/**
	 * 获取当前订单评论
	 * @param edit_comment_id
	 * @return
	 */
	public List<OrderComment> getOrderCommentListByID(long edit_comment_id);
	
	/**
	 * 保存评论回复
	 * @param comment_id
	 * @param replyName
	 * @param replyMessage
	 * @return
	 */
	public String editOrderCommentByID(long comment_id, String replyName, String replyMessage, Timestamp replyTime);
	
}
