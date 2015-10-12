package com.istore.common.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.icu.text.SimpleDateFormat;
import com.istore.common.core.bean.User;
import com.istore.common.core.mng.UsersMng;
import com.istore.common.web.util.BaseController;

/**
 *	待审核会员列表
 */
@Controller
@RequestMapping("/userVerifyList")
public class UserVerifyController extends BaseController{

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UsersMng usersMng;
	
	@Autowired
	HttpSession session;
	

	
	
	
	/**
	 * 待审核会员列表页面跳转
	 */
	
	@RequestMapping(value="/waitVerifyList.do", method = RequestMethod.GET)
	public String waitVerifyList(){
		
		return "users/verifyUser/waitVerifyList";
	}
	
	/**
	 * 获取待审核会员列表
	 */
	
		@RequestMapping(value="/verifyJson.do", method = RequestMethod.POST)
		public String getVerifyUsersList(){
			int page = Integer.valueOf(request.getParameter("page").toString());
			int rows = Integer.valueOf(request.getParameter("rows").toString());
			int startIndex = (page - 1) * rows;
			int endIndex = startIndex + rows;
			String storeId=request.getSession().getAttribute("storeId").toString();
			
			int listSize = usersMng.getVerifyUserListSize(storeId);
			
			List<User> userList = usersMng.getVerifyUserList(startIndex, endIndex,storeId);
			int total = 0;
			if (listSize % rows == 0) {
				total = listSize / rows;
			} else {
				total = listSize / rows + 1;
			}
			request.setAttribute("total", total);
			request.setAttribute("page", page);
			request.setAttribute("records", userList.size());
			request.setAttribute("userList", userList);
			
			return "users/verifyUser/vuserJson";
		}
		
	
		
		
		/**
		 * 待审核会员详情信息
		 */
		@RequestMapping(value="/verifyDetailJson.do",method = RequestMethod.POST)
		public String getVerifyUserDetail(){
			String users_id = request.getParameter("users_id");
			long usersId = Long.parseLong(users_id);
		
			
			User user = usersMng.getVerifyUserDetail(usersId);
	
			request.setAttribute("user", user);
			return "users/verifyUser/vuserDetail";
		}
		
		/**
		 * 
		 * 审核会员
		 * @return
		 */
		@RequestMapping(value="/verifyUser.do",method = RequestMethod.POST)
		@ResponseBody
		public String verifyUser(){
		String users_id = request.getParameter("users_id");
		String approveResult=request.getParameter("approveResult");
		if(approveResult==null||approveResult.equals(""))
		{
			approveResult="";
		}
		String usersId=	request.getSession().getAttribute("users_id").toString();
		Timestamp createTime = new Timestamp(new Date().getTime());
		
			int i=usersMng.addApprovalUser(Integer.valueOf(users_id),Integer.valueOf(usersId),"P","A",createTime,approveResult);
			int j=usersMng.updateUserStatus(users_id);
			String message;
			if(Integer.valueOf(i)>0&&Integer.valueOf(j)>0)
			{
				message="success";
			}
			else
			{
				message="fail";
			}
			
			
			return message;
		}
		//refuseUser.do
		
		/**
		 * 
		 * 拒绝会员
		 * @return
		 */
		@RequestMapping(value="/refuseUser.do",method = RequestMethod.POST)
		@ResponseBody
		public String refuseUser(){
		String users_id = request.getParameter("users_id");
		String approveResult=request.getParameter("approveResult");
		if(approveResult==null||approveResult.equals(""))
		{
			approveResult="";
		}
		String approveId=	request.getSession().getAttribute("users_id").toString();
		Timestamp createTime = new Timestamp(new Date().getTime());
		
			int i=usersMng.refuseApprovalUser(Integer.valueOf(users_id),Integer.valueOf(approveId),"P","R",createTime,approveResult);
			int j=usersMng.updateRefuseUserStatus(users_id);
			
			String message;
			if(Integer.valueOf(i)>0&&Integer.valueOf(j)>0)
			{
				message="success";
			}
			else
			{
				message="fail";
			}
			
			
			return message;
		}

}
