package com.istore.common.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.istore.common.core.bean.User;
import com.istore.common.core.mng.UsersMng;
import com.istore.common.core.mng.GrpMng;
import com.istore.common.web.util.BaseController;

/**
 *	Group manager
 */
@Controller
@RequestMapping("/group")
public class UserGrpController extends BaseController{

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	GrpMng groupMng;
	
	@Autowired
	HttpSession session;
	
	
	String SUCCEE_FLAG = "success";
	/**
	 * 页面跳转
	 */
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	
	public String list_user(){
		return "users/list";
	}
	
	
	
	
	/**
	 *Add by gaominquan
	 * 
	 */
	
	/**
	 * To Add A Group
	 * 
	 */
	@RequestMapping(value="/add.do",method = RequestMethod.GET)
	@ResponseBody
	public String addGroup(){
		String groupName = String.valueOf(request.getParameter("groupName").toString()).trim();
		String groupDescribe = String.valueOf(request.getParameter("groupDes").toString()).trim();
		String storeId = (String)session.getAttribute("storeId");
		
		if(groupMng.addGroupByName(groupName, groupDescribe,storeId)){
			return "Succeed";
		}else{
			return "False";
		}
		
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	@ResponseBody
	public String deleteGroup(){
		String groupId = String.valueOf(request.getParameter("group_id").toString());
		if(groupMng.deleteGroupById(groupId)){
			return "Succeed";
		}else{
			return "Failed";
		}
		
	}
	
	
	//----------Add at 2014 - 7 - 26 - 17:00
	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	@ResponseBody
	public String insertGroupMember(){
		String groupId = String.valueOf(request.getParameter("group_id").toString());
		String userId = String.valueOf(request.getParameter("user_id").toString());
		String storeID = (String)session.getAttribute("storeId");
		
		boolean succeedExcute = groupMng.addGroupMember(groupId, userId, storeID);

		return succeedExcute?SUCCEE_FLAG:"failed";
	}
	
	@RequestMapping(value = "/deleteMember.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteGroupMember(){
		String groupId = String.valueOf(request.getParameter("group_id").toString());
		String userId = String.valueOf(request.getParameter("user_id").toString());
		String storeID = (String)session.getAttribute("storeId");
		
		boolean succeedExcute = groupMng.deleteGroupMember(groupId, userId,storeID);
		
		return succeedExcute?SUCCEE_FLAG:"failed";
	
	}
	
	@RequestMapping(value="/showMember.do", method = RequestMethod.POST)
	public String showgGroupMemeber(){
		String groupID = String.valueOf(request.getParameter("group_id").toString());
		
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;
		String storeID=request.getSession().getAttribute("storeId").toString();
		
		int listSize = groupMng.getGroupMemberSize(groupID,storeID);
		
		List<User> userList = groupMng.showGroupMember(startIndex,endIndex,groupID,storeID);
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
		
		return "users/showMember/groupMemberJson";
	
	
	}
	
	
	
	
	/**
	 * Gaominquan Add End
	 * 
	 * 
	 */
	
}
