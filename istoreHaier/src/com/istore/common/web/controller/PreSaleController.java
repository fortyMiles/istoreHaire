package com.istore.common.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.istore.common.core.bean.Pager;
import com.istore.common.core.bean.Predictdatesale;
import com.istore.common.core.mng.PresaleMng;

@Controller
@RequestMapping("/presale")
public class PreSaleController {
	@Autowired 
	PresaleMng presaleMng ;
	
	@RequestMapping(value="/preList.do", method = RequestMethod.GET)
	public String preList(HttpServletRequest request,Model model){
		return "presale/list";
	}
	
	
	@RequestMapping(value="/preListJson.do", method = RequestMethod.POST)
	public String preListJson(HttpServletRequest request,int page,int rows,Model model){
		String href="";
		String userid = (String) request.getParameter("userid");
		String beginDate = (String) request.getParameter("beginDate");
		String endDate = (String) request.getParameter("endDate");
		Pager pager = new Pager();
		pager.setCurrentPage(page);
		pager.setEndNum(rows);
		Integer listCount =  presaleMng.findListCount(userid, beginDate, endDate);
		pager.setListCount(listCount);
		
		List<Predictdatesale> list = presaleMng.findReport(userid, beginDate, endDate, pager.getStartNum(), pager.getEndNum());
		model.addAttribute("pager",pager);
		model.addAttribute("list",list);
		href="presale/json";
		return href;
	}
	
}
