package com.istore.common.core.dao;

import java.util.List;

import com.istore.common.core.bean.Attr;
import com.istore.common.core.bean.XChannel;

public interface AttrDao {

	/**
	 * 获得定义数量
	 * 
	 * @return
	 */
	public int getAttrListSize();

	/**
	 * 获得定义列表
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	public List<Attr> getAttrList(int startIndex, int endIndex);
	
	
	/**
	 * 获得描述数量
	 * 
	 * @return
	 */
	public int getAttrdescListSize();

	/**
	 * 获得描述列表
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	public List<Attr> getAttrdescList(int startIndex, int endIndex);
	
	
	
	
	/**
	 * 删除属性
	 */
	public String deleteAttrByID(String attrId);
	
	/**
	 * 获取属性
	 */
	public List<Attr> getEditAttrListByID(String attrId);
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
	 * 获得属性数量
	 * 
	 * @return
	 */
	
	public int getAttrvalListSize(String id);
	/**
	 * 获得属性zhi列表
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	public List<Attr> getAttrvalList(String id,int startIndex, int endIndex);
	
	/**
	 * 删除值
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
	public String addAttrvalByName(String value,String sq,String attrid);
	
	
	
	
}
