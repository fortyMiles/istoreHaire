package com.istore.common.core.mng.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.Attr;
import com.istore.common.core.bean.XChannel;
import com.istore.common.core.dao.AttrDao;
import com.istore.common.core.dao.ChannelDao;
import com.istore.common.core.mng.AttrManger;
import com.istore.common.web.util.JsonResult;
@Service
@Transactional
public class AttrMangerImpl implements AttrManger {
	@Autowired
	AttrDao attrDao;
	/**
	 * 获得定义数量
	 * 
	 * @return
	 */
	public int getAttrListSize() {
		return attrDao.getAttrListSize();
	}

	/**
	 * 获得定义列表
	 * 
	 * @param endIndex
	 * @param startIndex
	 * 
	 * @return
	 */
	public List<Attr> getAttrList(int startIndex, int endIndex) {
		return attrDao.getAttrList(startIndex, endIndex);
	}

	
	
	
	
	
	/**
	 * 获得描述
	 * 
	 * @return
	 */
	
	public List<Attr> getAttrdescList(int startIndex, int endIndex) {
		return attrDao.getAttrdescList(startIndex, endIndex);
	}
	/**
	 * 获得描述数量
	 * 
	 * @return
	 */
	public int getAttrdescListSize() {
		
		return attrDao.getAttrdescListSize();
	}
	
	/**
	 * 删除
	 * 
	 */
	public String deleteAttrByID(String attrId) {
		return attrDao.deleteAttrByID(attrId);
	}
/**
 * 获取属性
 */
	public List<Attr> getEditAttrListByID(String attrId) {
		return attrDao.getEditAttrListByID(attrId);
	}

public String editAttrByID(String attrId, String name, String description) {
	 
	return attrDao.editAttrByID(attrId, name, description);
}

public String addAttrByName(String name, String description, String type) {
	// TODO Auto-generated method stub
	return attrDao.addAttrByName(name,description, type);
}

public List<Attr> getAttrvalList(String id, int startIndex, int endIndex) {
	return attrDao.getAttrvalList(id,startIndex, endIndex);
}

public int getAttrvalListSize(String id) {
	return attrDao.getAttrvalListSize(id);
}
/**
 * 删除属性值
 */
public String deleteAttrvalByID(String attrvalId) {
  return attrDao.deleteAttrvalByID(attrvalId);
}
/**
 * 修改属性值
 */
public String editAttrvalByID(String attrvalId, String value, String sq) {
	return attrDao.editAttrvalByID(attrvalId, value,sq);
}

public List<Attr> getEditAttrvalListByID(String attrvalId) {
	return attrDao.getEditAttrvalListByID(attrvalId);
}
/**
 * 属性值添加
 */

public String addAttrvalByName(String value, String sq, String attrid) {
	
	return attrDao.addAttrvalByName(value,sq,attrid);
}
	

	

}
