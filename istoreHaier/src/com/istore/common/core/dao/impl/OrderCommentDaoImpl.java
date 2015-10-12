package com.istore.common.core.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.OrderComment;
import com.istore.common.core.dao.OrderCommentDao;
import com.istore.common.core.mapper.FindOrderCommentMapper;

@Repository
public class OrderCommentDaoImpl implements OrderCommentDao {

	@Autowired
	FindOrderCommentMapper findOrderCommentMapper;
	
	
	/*
	 * 获取评论数量
	 * @see com.istore.common.core.dao.OrderCommentDao#getOrderCommentListSize()
	 */
	public int getOrderCommentListSize(long store_id) {
		List<OrderComment> orderCommentList = findOrderCommentMapper.getOrderCommentListCount(store_id);
		return orderCommentList.size();
	}

	/*
	 * 获取评论分页
	 * @see com.istore.common.core.dao.OrderCommentDao#getOrderCommentList(int, int)
	 */
	public List<OrderComment> getOrderCommentList(long store_id, int startIndex, int endIndex) {
		return findOrderCommentMapper.getOrderCommentList(store_id, startIndex, endIndex);
	}

	/*
	 * 获取当前评论
	 * @see com.istore.common.core.dao.OrderCommentDao#getOrderCommentListByID(long)
	 */
	public List<OrderComment> getOrderCommentListByID(long edit_comment_id) {
		return findOrderCommentMapper.getEditOrderCommentListByID(edit_comment_id);
	}

	/*
	 * 保存评论回复
	 * @see com.istore.common.core.dao.OrderCommentDao#editOrderCommentByID(long, java.lang.String, java.lang.String)
	 */
	public String editOrderCommentByID(long comment_id, String replyName,String replyMessage, Timestamp replyTime) {
		int flag = 0;
		String sFlag = "failure";
		flag = findOrderCommentMapper.editOrderCommentByID(comment_id, replyName, replyMessage, replyTime);
		if(flag != 0){
			sFlag = "success";
		}
		return sFlag;
	}


}
