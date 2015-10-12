package com.istore.common.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.istore.common.core.bean.User;
import com.istore.common.core.mng.UsersMng;
import com.istore.common.web.util.BaseController;

/**
 *	会员列表
 */
@Controller
@RequestMapping("/userList")
public class UserMsgController extends BaseController{

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UsersMng usersMng;
	
	@Autowired
	HttpSession session;
	
	
	/**
	 * 页面跳转
	 */
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String list_user(){
		return "users/list";
	}
	
	
	
	/**
	 * 查看所有人员列表
	 */
	@RequestMapping(value="/json.do", method = RequestMethod.POST)
	public String getUsersList(){
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;
		
		String storeId = (String) session.getAttribute("storeId");
		int listSize = usersMng.getUserListSize(storeId);
		List<User> userList = usersMng.getUserList(startIndex, endIndex, storeId);
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
		
		return "users/userJson";
	}
	
	
	/**
	 * 会员详情信息
	 */
	@RequestMapping(value="/detailJson.do",method = RequestMethod.POST)
	public String getUserDetail(){
		String users_id = request.getParameter("users_id");
		long users = Long.parseLong(users_id);
		User user = usersMng.getUserDetail(users);
		request.setAttribute("user", user);
		return "users/userDetail";
	}
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
		 * 管理员页面 
		 */
		@RequestMapping(value="/adminList.do", method = RequestMethod.GET)
		public String adminList(){
			return "users/createAdmin/list";
		}
		
		/**
		 * 查询管理员列表
		 */
		@RequestMapping(value="/adminListJson.do", method = RequestMethod.POST)
		public String getAdminList(){
			int page = Integer.valueOf(request.getParameter("page").toString());
			int rows = Integer.valueOf(request.getParameter("rows").toString());
			int startIndex = (page - 1) * rows;
			int endIndex = startIndex + rows;
			String storeId = (String) session.getAttribute("storeId");
			int listSize = usersMng.getAdminListSize(storeId);
			System.out.println("listSize"+listSize);
			List<User> userList = usersMng.getUserList(startIndex, endIndex, storeId);
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
			return "users/createAdmin/userJson";
		}
		
	

}
