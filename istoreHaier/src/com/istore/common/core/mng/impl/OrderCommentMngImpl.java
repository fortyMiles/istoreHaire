package com.istore.common.core.mng.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.OrderComment;
import com.istore.common.core.dao.OrderCommentDao;
import com.istore.common.core.mng.OrderCommentMng;

@Service
@Transactional
public class OrderCommentMngImpl implements OrderCommentMng {

	@Autowired
	OrderCommentDao orderCommentDao;
	
	public int getOrderCommentListSize(long store_id) {
		return orderCommentDao.getOrderCommentListSize(store_id);
	}

	public List<OrderComment> getOrderCommentList(long store_id, int startIndex, int endIndex) {
		return orderCommentDao.getOrderCommentList(store_id, startIndex, endIndex);
	}

	public List<OrderComment> getOrderCommentListByID(long edit_comment_id) {
		return orderCommentDao.getOrderCommentListByID(edit_comment_id);
	}

	public String editOrderCommentByID(long comment_id, String replyName, String replyMessage, Timestamp replyTime) {
		return orderCommentDao.editOrderCommentByID(comment_id, replyName, replyMessage, replyTime);
	}

}
