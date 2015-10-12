package com.istore.common.web.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;

@Controller
public class BaseController {

	public String json(JsonResult jsonResult){
		 return JSON.toJSONString(jsonResult);
	}
	
	/***
	 * 上下文
	 */
	@ModelAttribute("base")
	public String basePath(HttpServletRequest request) {
		return request.getContextPath();
	}

	
	
	
}
