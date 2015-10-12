package com.istore.common.b2b.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ibm.wsspi.sip.channel.exception.UnsupportedEncodingException;
import com.istore.common.b2b.entity.AdminUser;
import com.istore.common.b2b.manager.UserManager;
import com.istore.common.web.util.BaseController;

@Controller
@RequestMapping("/user")
public class LoginController extends BaseController{
	
	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	@ResponseBody
	public String logon(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, NoSuchAlgorithmException, UnsupportedEncodingException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<AdminUser> adminUsers = userManager.findByUsername(username, EncoderPwdByMd5(password));
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(adminUsers.size()>0){
			AdminUser adminUser=adminUsers.get(0);
//			redirect("/istore/servlet/b2c/logon.html", response);
			HttpSession session = request.getSession();
			session.setAttribute("users_id", adminUser.getUsers_id());
			session.setAttribute("storeId", adminUser.getStoreId());
			session.setAttribute("username", adminUser.getUsername());
			json.put("status", 1);
			out.print(json.toString());
			
		}else{
			json.put("message", "登录名或者密码错误!");
			json.put("status", 0);
			out.print(json.toString());
			}
		out.flush();
		out.close();
		return null;
	}
	
	
	
	public static final String EncoderPwdByMd5(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定计算方法
		StringBuffer buf = new StringBuffer(""); 
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(plainText.getBytes()); 
			byte b[] = md.digest(); 

			int i; 


			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if(i<0) i+= 256; 
				if(i<16) 
					buf.append("0"); 
				buf.append(Integer.toHexString(i)); 
			} 			
		} catch (NoSuchAlgorithmException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
		return buf.toString();
	}
}
