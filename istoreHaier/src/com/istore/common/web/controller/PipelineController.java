package com.istore.common.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.istore.common.core.bean.Pager;
import com.istore.common.core.bean.Pipeline;
import com.istore.common.core.mng.PipelineMng;

@Controller
@RequestMapping("/pipelineView")
public class PipelineController {
	@Autowired 
	PipelineMng pipelinemng;
	
	
	@RequestMapping(value="/pipelineList.do", method = RequestMethod.GET)
	public String preList(HttpServletRequest request,Model model){
		return "pipeline/list";
	}
	
	
	
	@RequestMapping(value="/pipelineListJson.do", method = RequestMethod.POST)
	public String preListJson(HttpServletRequest request,int page,int rows,Model model){
		String href="";
		String pipeline_id = "";
		String orders_id = "";
		String userid = "";
		String sel_search = request.getParameter("sel_search")==null?"":request.getParameter("sel_search");
		String in_txt = request.getParameter("in_txt")==null?"":request.getParameter("in_txt");
		if(!in_txt.trim().equals("")){
			if(sel_search.equals("in_userid")){
				userid = in_txt;
			}else if(sel_search.equals("in_pipelineid")){
				pipeline_id = in_txt;
			}else if(sel_search.equals("in_orderid")){
				orders_id = in_txt;
			}
		}
		
		String store_id = (String) request.getSession().getAttribute("storeId");
		Pager pager = new Pager();
		pager.setCurrentPage(page);
		pager.setPageSize(rows);
		Pipeline pipeline =  pipelinemng.pipeLineViewListCount(userid, pipeline_id, orders_id, store_id);
		pager.setListCount(pipeline.getCount());
		
		List<Pipeline> list = pipelinemng.pipeLineViewList(userid, pipeline_id, orders_id, pager, store_id);
		model.addAttribute("pager",pager);
		model.addAttribute("pipelineList",list);
		href="pipeline/json";
		return href;
	}
	@RequestMapping(value="/pipelineEdit.do", method = RequestMethod.POST)
	@ResponseBody
	public String editPipeline(HttpServletRequest request){
		String store_id = (String) request.getSession().getAttribute("storeId");
		String pipeline_id = request.getParameter("pipelineid");
		String status = request.getParameter("status");
		String comment = request.getParameter("comment");
		String ordersid = request.getParameter("ordersid");
		int result = pipelinemng.modPipelineStatus(pipeline_id, status, ordersid, store_id,comment);
		return result+"";
	}
}
