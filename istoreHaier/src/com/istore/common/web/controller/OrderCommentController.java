package com.istore.common.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.istore.common.core.bean.OrderComment;
import com.istore.common.core.mng.OrderCommentMng;
import com.istore.common.web.util.BaseController;

@Controller
@RequestMapping("/orderComment")
public class OrderCommentController extends BaseController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	OrderCommentMng orderCommentMng;
	
	/**
	 * 订单评论展示列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list() {
		return "orderComment/list";
	}
	
	/**
	 * 评论JSON数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/json.do", method = RequestMethod.POST)
	public String orderCommentList() {
		long store_id = Long.valueOf(request.getSession().getAttribute("storeId").toString());
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = orderCommentMng.getOrderCommentListSize(store_id);

		List<OrderComment> orderCommentList = orderCommentMng.getOrderCommentList(store_id, startIndex, endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", orderCommentList.size());
		request.setAttribute("orderCommentList", orderCommentList);
		return "orderComment/json";
	}
	
	
	/**
	 * 当前评论记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editlist.do", method = RequestMethod.POST)
	public String editlist() {
		long edit_comment_id = Long.valueOf(request.getParameter("comment_id"));
		List<OrderComment> orderCommentList = orderCommentMng.getOrderCommentListByID(edit_comment_id);
		request.setAttribute("orderCommentList", orderCommentList);
		return "orderComment/edit";
	}
	
	/**
	 * 评论回复保存
	 * @return
	 */
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	@ResponseBody
	public String edit() {
		long edit_comment_id = Long.valueOf(request.getParameter("comment_id"));
		String replyMessage = request.getParameter("replyContent");
		String replyName = request.getSession().getAttribute("username").toString();
		Timestamp replyTime = new Timestamp(new Date().getTime());
		return orderCommentMng.editOrderCommentByID(edit_comment_id, replyName, replyMessage, replyTime);
	}
}
