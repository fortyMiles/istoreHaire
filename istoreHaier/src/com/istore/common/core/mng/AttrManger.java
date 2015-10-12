package com.istore.common.core.mng;



import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.istore.common.core.bean.Attr;
import com.istore.common.core.bean.XChannel;
import com.istore.common.web.util.JsonResult;


public interface AttrManger  {
	/**
	 * 定义属性
	 * @return
	 */
	public int getAttrListSize();
	public List<Attr> getAttrList(int startIndex, int endIndex);
	
	/**
	 * 描述属性
	 * @return
	 */
	public int getAttrdescListSize();
	public List<Attr> getAttrdescList(int startIndex, int endIndex);
	
	
	/**
	 * 删除属性
	 * @param attr_id
	 * @return
	 */
	public String deleteAttrByID(String attr_id);
	
	
	
	/**
	 * 获得当前编辑属性
	 * 
	 */
	public List<Attr> getEditAttrListByID(String attr_id);
	
	/**
	 * 保存属性
	 * 
	 */
	public String editAttrByID(String attr_id,String name, String description);
	
	/**
	 * 添加
	 */
	
	public String addAttrByName(String name,String description,String type);
	
	
	/**
	 * 属性值
	 * @return
	 */
	public int getAttrvalListSize(String id);
	public List<Attr> getAttrvalList(String id,int startIndex, int endIndex);
	
	/**
	 * 删除属性值
	 * @param attr_id
	 * @return
	 */
	public String deleteAttrvalByID(String attrval_id);
	
	/**
	 * 修改属性值
	 */
	
     public List<Attr> getEditAttrvalListByID(String attrval_id);

	public String editAttrvalByID(String attrval_id,String value, String sq);
	
	/**
	 * 添加属性值
	 */
	/**
	 * 添加
	 */
	
	public String addAttrvalByName(String value,String sq,String attrid);
	
	
	
	
}
