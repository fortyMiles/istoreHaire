package com.istore.common.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.istore.common.core.mng.UserMng;
import com.istore.common.core.util.SMSService;
import com.istore.common.web.util.BaseController;




@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	
	@Autowired
	UserMng userMng;
	
	@Autowired
	SMSService service;
	
	@RequestMapping(value="/logon.do", method = RequestMethod.POST)
	public void userLogon(HttpServletRequest request, HttpServletResponse response,Model model){
		HttpSession session = request.getSession();
		session.invalidate();
		PrintWriter out;
		try {
			out = response.getWriter();
			String tip = "success";
			out.print(tip);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String logonId = request.getParameter("logonId");
//		String password = request.getParameter("password");
//		Map<String, Object> map = userMng.userLogonById(logonId, password);
//		String strUrl = userMng.findUrlForCreateMember();
//		PrintWriter out = null;
//		String tip = "";
//		try {
//			out = response.getWriter();
//			if("ERRORCODE".equals(map.get("failure"))){
//				request.getSession().setAttribute("message", "您的登陆名或密码错误，请重新登陆！");
//				tip = "failure";
//			}else{
//				List<User> userBB = userMng.findPasswordForUser(logonId);
//				if(!userBB.isEmpty()){
//					List list = new ArrayList();
//					for(User uu : userBB){
//						String logonIdPass = uu.getPassword();
//						String decryptPassword =new String(new BASE64Decoder().decodeBuffer(logonIdPass));
//						list.add(decryptPassword);
//					}
//					if(list.contains(password)){
//						request.getSession().setAttribute("posId", logonId);  
//						if("1".equals(map.get("shopType"))){
//							request.getSession().setAttribute("shopId", map.get("shopidSap"));   //登陆人是店长
//							User user = userMng.findParameterForSend();
//							User us = userMng.queryUserCreateInfoForRemind();
//							String SmsUrl="";
//							String SpCode="";
//							String LoginName="";
//							String Password="";
//							String SerialNumber="";
//							String ScheduleTime="";
//							String SmsTimeOut="";
//							String Remindmessage="";
//							String sendUrl="";
//							String sendInfo="";
//							if(user != null){
//								SmsUrl = user.getSmsUrl();
//								SpCode = user.getSpCode();
//								LoginName = user.getLoginName();
//								Password = user.getSendPassword();
//								SerialNumber = user.getSerialNumber();
//								ScheduleTime = user.getScheduleTime();
//								SmsTimeOut = user.getSmsTimeOut();
//								Remindmessage = user.getCreateUserMessage();
//							}
//							if(us != null){
//								sendUrl=us.getSendurl();
//								sendInfo =us.getSendinfo();
//							}
//							request.getSession().setAttribute("SmsUrl", SmsUrl);   //用于创建用户时发送短信
//							request.getSession().setAttribute("SpCode", SpCode); 
//							request.getSession().setAttribute("LoginName", LoginName); 
//							request.getSession().setAttribute("Password", Password); 
//							request.getSession().setAttribute("SerialNumber", SerialNumber); 
//							request.getSession().setAttribute("ScheduleTime", ScheduleTime); 
//							request.getSession().setAttribute("SmsTimeOut", SmsTimeOut); 
//							request.getSession().setAttribute("Remindmessage", Remindmessage); 
//							request.getSession().setAttribute("sendUrl", sendUrl); 
//							request.getSession().setAttribute("sendInfo", sendInfo); 
//							tip =  "success";
//						}else{     							//登陆人是加盟商
//							if(profit != null){
//								double profitNum = profit.getTotalProfitAmount();
//								String status = profit.getStatus();
//								request.getSession().setAttribute("profitNum", profitNum);
//								request.getSession().setAttribute("status", status);
//								List<Map<String, Object>> listMap = userMng.shopForAdminUser(logonId);
//								StringBuffer buffer = new StringBuffer();
//								String str = "";
//								if(!listMap.isEmpty()){
//									for(Map<String, Object> mapInfo : listMap){
//										buffer.append(mapInfo.get("shopidSap")).append(",");
//										str = buffer.substring(0,buffer.length()-1);
//									}
//									request.getSession().setAttribute("shopId", str);      //加盟商所拥有的店铺
//									tip = "success";
//								}
//							}else{
//								request.getSession().setAttribute("message", "您未参与此项活动，请交费后登陆！");
//								tip = "failure";
//							}
//						 }
//						}else{
//							request.getSession().setAttribute("message", "您的密码错误，请重新登陆！");
//							tip = "failure";
//						}
//					}
//				request.getSession().setAttribute("logonId", map.get("shopidSap"));  //登陆人在x_sap表中的Id
//				request.getSession().setAttribute("password", password);
//				request.getSession().setAttribute("shopType", map.get("shopType"));  //判断登陆人是店长还是加盟商
//				request.getSession().setAttribute("strUrl", strUrl);
//		     }
//			request.getSession().setAttribute("logonType", "canUser");    //用来帮助其他Controller识别用户有没有登陆
//			out.print(tip);
//			out.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}
	
	@RequestMapping(value="/index.do", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String userList(){
		return "user/list";
	}
	
	
	
	
	@RequestMapping(value="/logout.do", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.removeAttribute("shopidSap");
		session.removeAttribute("message");
		session.removeAttribute("password");
		session.removeAttribute("logonType");
		session.removeAttribute("profitNum");
		session.removeAttribute("status");
		session.removeAttribute("logonId");
		session.removeAttribute("shopId");
		session.removeAttribute("shopType");
		session.invalidate();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("success");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/_logout.do", method = RequestMethod.GET)
	public String _logout(){
		return "user/logon";
	}
	

	
	
}



