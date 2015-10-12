package com.istore.common.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.istore.common.core.bean.Order;
import com.istore.common.core.mng.OrderMng;
import com.istore.common.web.util.BaseController;




@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	
	
	@Autowired
	OrderMng orderMng;
	
	
	@RequestMapping(value="/index.do", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/orderList.do", method = RequestMethod.GET)
	public String userList(){
		return "order/list";
	}
	
	@RequestMapping(value="/orderListJson.do", method = RequestMethod.POST)
	public String orderListJson(HttpServletRequest request,int page,int rows,Model model){
		String shopId = (String)request.getSession().getAttribute("shopId");
		String logonType = (String)request.getSession().getAttribute("logonType");
		int totalPage = 0;
		int totalRecord = 0;
		List<Order> listMap = null;
		String href="";
		if("canUser".equals(logonType)){
			totalRecord = orderMng.queryOrderCount(shopId);
			totalPage = totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows+ 1; 
			int index ;
			if(page==1){
				index = 1; 
			}else{
				index = (page-1) * rows + 1; 
			}
			int pageSize = rows; 
			int sum = index+pageSize;
			listMap = orderMng.queryOrderList(shopId, index, sum);
			model.addAttribute("records", totalRecord);
			model.addAttribute("total", totalPage);
			model.addAttribute("orderListJson", listMap);
			href="order/json";
		}else{
			href="user/logon";
		}
		return href;
		
	}
	
	@RequestMapping(value="/orderDetail.do", method = RequestMethod.GET)
	public String orderListJson(HttpServletRequest request,Model model){
		String orderId = request.getParameter("orderId");
		model.addAttribute("orderId", orderId);
		return "order/orderDetail";
		
	}
	
	/***
	 * 订单的详细信息
	 * @return
	 */
	@RequestMapping(value="/orderDetailList.do", method = RequestMethod.GET)
	public String viewUsers(HttpServletRequest request,int page,int rows,Model model){
		String logonType = (String)request.getSession().getAttribute("logonType");
		String orderId = request.getParameter("orderId");
		int totalPage = 0;
		int totalRecord = 0;
		List<Order> listMap = null;
		String href="";
		if("canUser".equals(logonType)){
			totalRecord = orderMng.queryOrderDetailCount(orderId);
			totalPage = totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows+ 1; 
			int index ;
			if(page==1){
				index = 1; 
			}else{
				index = (page-1) * rows + 1; 
			}
			int pageSize = rows; 
			int sum = index+pageSize;
			listMap = orderMng.queryOrderDetailList(orderId, index, sum);
			model.addAttribute("records", totalRecord);
			model.addAttribute("total", totalPage);
			model.addAttribute("orderDetailListJson", listMap);
			href="order/orderDetailJson";
		}else{
			href="user/logon";
		}
		return href;
	}
	
}



