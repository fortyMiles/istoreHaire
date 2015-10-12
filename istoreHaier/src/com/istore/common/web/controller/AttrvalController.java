package com.istore.common.web.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;

import com.istore.common.core.bean.Attr;
import com.istore.common.core.bean.XChannel;
import com.istore.common.core.mng.AttrManger;
import com.istore.common.web.util.BaseController;
import com.istore.common.web.util.JsonResult;


/***
 * 商品属性值
 */
@Controller
@RequestMapping("/attrval")
public class AttrvalController extends BaseController {
	

	String attrid=null;
	
	@Autowired
	HttpServletRequest request;
	

	@Autowired
    private	AttrManger attrmanger;
	
	
	
	
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String list_dingyi(){
		
		attrid =request.getParameter("attr_id");
		
		return "catentry/attrval/list";
	}
	
	
	/**
	 * 查询所有定义属性
	 */
	
	@RequestMapping(value="/json.do", method = RequestMethod.POST)
	public String dingyi() throws IOException{
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = attrmanger.getAttrvalListSize(attrid);

		List<Attr> AttrvalList = attrmanger.getAttrvalList(attrid,startIndex,endIndex);
		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", AttrvalList.size());
		request.setAttribute("AttrvalList", AttrvalList);
		
		return "catentry/attrval/json";
	}
	
	/**
	 * 属性值删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public String delete() {
		String attrval_id = request.getParameter("attrval_id");
		return attrmanger.deleteAttrvalByID(attrval_id);
	}
	/**
	 * 获取当前编辑属性值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editlist.do", method = RequestMethod.POST)
	public String editdesclist() {
		String attrvalid = request.getParameter("attrval_id");
		List<Attr> AttrvalList = attrmanger.getEditAttrvalListByID(attrvalid);
		request.setAttribute("AttrvalList", AttrvalList);
		return "catentry/attrval/edit";
	}
	
	
	
	/**
	 * 属性值修改保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	@ResponseBody
	public String edit() {
		String attrval_id = request.getParameter("attrval_id");
		String value = request.getParameter("value");
		String sq = request.getParameter("sq");
		return attrmanger.editAttrvalByID(attrval_id, value,sq);				
	}

	/**
	 * 添加属性值
	 * 
	 */
	
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	@ResponseBody
	public String add() {
		String value = request.getParameter("value");
		String sq= request.getParameter("sq");
	
		return  attrmanger.addAttrvalByName(value,sq, attrid);
	}
	
	
	
		
	
}
