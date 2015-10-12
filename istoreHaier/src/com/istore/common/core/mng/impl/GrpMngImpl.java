/**
 * @Project Haieristore
 * @Package com.istore.common.core.mng.impl
 * @Title NewsMngImpl.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-12
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.User;
import com.istore.common.core.dao.GroupDao;
import com.istore.common.core.mng.GrpMng;

/**
 * @ClassName: GrpMngImple.java
 * @Description: TODO
 * @author Gaomiquan 	
 * @time 2014-7-12上午11:24:50
 */
@Service
@Transactional
public class GrpMngImpl implements GrpMng {

	@Autowired
	GroupDao groupDao;

	public boolean deleteGroupById(String groupId) {
		// TODO Auto-generated method stub
		return groupDao.deleteGroupById(groupId) == 1?true:false;
	}

	public boolean addGroupByName(String name, String store, String storeId) {
		// TODO Auto-generated method stub
		return groupDao.addGroupByName(name,store,storeId) == 1? true:false;
	}
	// added in 2014-7-26-17:08 Gaominquan
	
	public boolean addGroupMember(String groupID, String memeberID,
			String storeID) {
		// TODO Auto-generated method stub
		return groupDao.addGroupMemeber(groupID, memeberID, storeID) > 0 ?true:false;
	}

	public boolean deleteGroupMember(String groupID, String memeberID,String storeID) {
		// TODO Auto-generated method stub
		
		return groupDao.deleteGroupMemeber(groupID, memeberID,storeID) > 0 ? true:false;
	}

	public List<User> showGroupMember(int startIndex, int endIndex,String groupID,String storeID) {
		// TODO Auto-generated method stub
		return groupDao.showGroupMember(startIndex, endIndex, groupID,storeID);
	}

	public int getGroupMemberSize(String groupID, String storeID) {
		// TODO Auto-generated method stub
		return groupDao.getGroupMemberSize(groupID,storeID);
	}
	
	
}
