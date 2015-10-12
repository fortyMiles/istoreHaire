package com.istore.common.web.util;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class ControllerInterceptor extends HandlerInterceptorAdapter {
	
	private String loginFormUrl;
	private Set<String> urls;

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url=request.getPathInfo();
		if(urls.contains(url)){
			return true;
		}else{
			HttpSession session = request.getSession();
			if(session.getAttribute("username") != null &&!"".equals(session.getAttribute("username"))){
				return true;
			}else{
				response.sendRedirect(this.getLoginFormUrl());
				return false;
			}
		}
	}
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		String servletPath = request.getServletPath();
		if (!servletPath.contains("/static/")) {
			response.setHeader("Cache-Control", "no-cache");
		}
	}
	
	public String getLoginFormUrl() {
		return loginFormUrl;
	}
	
	public void setLoginFormUrl(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}
	
	public Set<String> getUrls() {
		return urls;
	}
	
	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}
	

	

	
}
