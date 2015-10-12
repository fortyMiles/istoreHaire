package com.istore.common.core.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.Attr;
import com.istore.common.core.bean.XChannel;
import com.istore.common.core.dao.AttrDao;
import com.istore.common.core.mapper.FindAttrMapper;
import com.istore.common.core.mapper.FindChannelMapper;

@Repository
public class AttrDaoImpl implements AttrDao {

	@Autowired
	private FindAttrMapper findAttrMapper;

	/**
	 * 定义属性
	 */
	public int getAttrListSize() {
		List<Attr> xAttrList = findAttrMapper.getAttrListCount();
		return xAttrList.size();
	}

	public List<Attr> getAttrList(int startIndex, int endIndex) {

		return findAttrMapper.getAttrList(startIndex, endIndex);
	}

	/**
	 * 描述属性
	 */
	public int getAttrdescListSize() {
		List<Attr> xAttrList = findAttrMapper.getAttrdescListCount();
		return xAttrList.size();
	}

	public List<Attr> getAttrdescList(int startIndex, int endIndex) {

		return findAttrMapper.getAttrdescList(startIndex, endIndex);
	}

	/**
	 * 删除属性
	 */
	public String deleteAttrByID(String attrId) {
		String flag = "false";
		try {
			long attrid = Long.valueOf(attrId);
			int result = findAttrMapper.deleteAttrByID(attrid);
			if (result == 1) {
				flag = "success";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	/**
	 * 获取要修改的属性
	 */
	public List<Attr> getEditAttrListByID(String attrId) {

		long attrid = Long.valueOf(attrId);
		return findAttrMapper.getEditAttrListByID(attrid);
	}

	/*
	 * 保存当前编辑属性
	 */
	public String editAttrByID(String attrId, String name, String description) {
		String flag = "false";
		try {
			long attr_id = Long.valueOf(attrId);
			int result = findAttrMapper.editAttrByID(attr_id, name, description);
			if (result == 1) {
				flag = "success";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return flag;
	}
/**
 * 添加
 */
	public String addAttrByName(String name, String description, String type) {
		
			String flag = "false";
			try {
				int attruseage=Integer.parseInt(type);
				//System.out.println(attruseage);
                Long attr_id=findAttrMapper.selectmaxid()+1;
                String identy=attr_id+"";
               int find= findAttrMapper.addAttr(attr_id,identy,attruseage);
          	   int result = findAttrMapper.addAttrByName(attr_id,name,description);
				if (result == 1&&find==1) {
					flag = "success";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}

public List<Attr> getAttrvalList(String id, int startIndex, int endIndex) {
	long attrid=Long.parseLong(id);
	return findAttrMapper.getAttrvalList(attrid,startIndex, endIndex);
}

public int getAttrvalListSize(String id) {
	long attrid=Long.parseLong(id);
	List<Attr> AttrvalList = findAttrMapper.getAttrvalListCount(attrid);
	return AttrvalList.size();
}
/**
 * 删除属性值
 */
public String deleteAttrvalByID(String attrvalId) {
	String flag = "false";
	try {
		long attrval_Id = Long.valueOf(attrvalId);
		int result = findAttrMapper.deleteAttrvalByID(attrval_Id);
		if (result == 1) {
			flag = "success";
		}
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}
	return flag;
}
/**
 * 修改属性值
 */
public String editAttrvalByID(String attrvalId, String value, String sq) {
	String flag = "false";
	try {
		int squ=Integer.parseInt(sq);
		long attrval_id = Long.valueOf(attrvalId);
		int result = findAttrMapper.editAttrvalByID(attrval_id, value, squ);
		if (result == 1) {
			flag = "success";
		}
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}
	return flag;
}

public List<Attr> getEditAttrvalListByID(String attrvalId) {
  long attrval_id=Long.parseLong(attrvalId);
  return findAttrMapper.getEditAttrvalListByID(attrval_id);
}
	/**
	 * 添加属性
	 */

public String addAttrvalByName(String value, String sq, String attrid) {

		
		String flag = "false";
		try {
			int squ=Integer.parseInt(sq);
            long attrval_id=findAttrMapper.selectvalmaxid()+1;
            String identy=attrval_id+"";
            long attr_id=Long.parseLong(attrid);
            int find=findAttrMapper.addAttrval(attrval_id,identy,attrid);
            
			int result = findAttrMapper.addAttrvaldesc(attrval_id,attr_id,value,squ);
			if (result == 1 && find == 1) {
				flag = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	
}

	
}
