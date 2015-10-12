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


/***
 * 商品属性
 */
@Controller
@RequestMapping("/cantentry")
public class CatentryController extends BaseController {
	

	
	
	@Autowired
	HttpServletRequest request;
	

	@Autowired
    private	AttrManger attrmanger;
	/**
	 * 定义属性页面
	 */
	@RequestMapping(value="/dingyi/list.do", method = RequestMethod.GET)
	public String list_dingyi(){
		return "catentry/catentry/list";
	}
	
	
	/**
	 * 查询所有定义属性
	 */
	@RequestMapping(value="/dingyi/json.do", method = RequestMethod.POST)
	public String dingyi() throws IOException{

		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = attrmanger.getAttrListSize();

		List<Attr> AttrList = attrmanger.getAttrList(startIndex,
				endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", AttrList.size());
		request.setAttribute("AttrList", AttrList);
		return "catentry/catentry/json";
	}
	
	
	
	/**
	 * 描述属性页面
	 */
	@RequestMapping(value="/miaoshu/list.do", method = RequestMethod.GET)
	public String list_miaoshu(){
		return "catentry/descattr/list";
	}
	
	
	/**
	 * 查询所有描述性属性
	 */
	@RequestMapping(value="/miaoshu/json.do", method = RequestMethod.POST)
	public String miaoshu() throws IOException{

		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = attrmanger.getAttrdescListSize();

		List<Attr> AttrList = attrmanger.getAttrdescList(startIndex,
				endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", AttrList.size());
		request.setAttribute("AttrList", AttrList);
		return "catentry/descattr/json";
	}
	
	
	/**
	 * 获取当前编辑定义性属性
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editlist.do", method = RequestMethod.POST)
	public String editlist() {
		String attr_id = request.getParameter("attr_id");
		List<Attr> AttrList = attrmanger.getEditAttrListByID(attr_id);
		request.setAttribute("AttrList", AttrList);
		return "catentry/catentry/edit";
	}
	
	/**
	 * 获取当前编辑描述性属性
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editdesclist.do", method = RequestMethod.POST)
	public String editdesclist() {
		String attr_id = request.getParameter("attr_id");
		List<Attr> AttrList = attrmanger.getEditAttrListByID(attr_id);
		request.setAttribute("AttrList", AttrList);
		return "catentry/descattr/edit";
	}
	
	
	
	/**
	 * 属性修改保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	@ResponseBody
	public String edit() {
		String attr_id = request.getParameter("attr_id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		return attrmanger.editAttrByID(attr_id, name,
				description);
	}


	
	/**
	 * 属性删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public String delete() {
		String attr_id = request.getParameter("attr_id");
		return attrmanger.deleteAttrByID(attr_id);
	}
	/**
	 * 添加属性
	 * 
	 */
	
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	@ResponseBody
	public String add() {
		String name = request.getParameter("name");
		String description= request.getParameter("description");
		String type = request.getParameter("type");
		return  attrmanger.addAttrByName(name,description, type);
	}
	
}
