package com.istore.common.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

	
	@RequestMapping(value="/frame.html", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/logon.html", method = RequestMethod.GET)
	public String login(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("password");
		return "user/logon";
	}
	
	

}
