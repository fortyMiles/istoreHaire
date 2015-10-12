/**
 * @Project istoreHaier
 * @Package com.istore.common.core.dao.impl
 * @Title FtpDaoImpl.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-15
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */

package com.istore.common.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.User;
import com.istore.common.core.dao.GroupDao;
import com.istore.common.core.mapper.FindFtpMapper;
import com.istore.common.core.mapper.FindGroupMapper;

/**
 * @ClassName: GroupDaoImpl.java
 * @Description: TODO
 * @author Gaominquan
 * @time 2014-7-15下午1:20:05
 */
@Repository
public class GroupDaoImpl implements GroupDao {

	
	private FindGroupMapper findGroupMapper;
	
	public int deleteGroupById(String groupId) {
	
		// TODO Auto-generated method stub
		int sqlResult  = findGroupMapper.deleteGroupByName(groupId);
		return sqlResult;
	}

	public int addGroupByName(String name, String description, String storeId) {
		// TODO Auto-generated method stub
		int sqlResult = findGroupMapper.addGroupByName(name, description, storeId);
		return sqlResult;
	}
	
	
	//Added at 2014 - 7- 26 17:09 Gaominquan

	public int deleteGroupMemeber(String groupID, String memeberID, String storeID) {
		// TODO Auto-generated method stub
		int sqlResult = findGroupMapper.delelteMemberFromGroup(groupID, memeberID, storeID);
		return sqlResult;
	}

	public int addGroupMemeber(String groupID, String memeberID, String storeID) {
		// TODO Auto-generated method stub
		int sqlResult = findGroupMapper.addUserIntoMemebr(groupID, memeberID, storeID);
		return sqlResult;
	}
	
	public List<User> showGroupMember(int startIndex, int endIndex, String groupID, String storeID){
		return findGroupMapper.getGroupUsers(startIndex, endIndex, groupID, storeID);
	}


	public int getGroupMemberSize(String groupID, String storeID) {
		// TODO Auto-generated method stub
		return findGroupMapper.getGroupMemberSize(groupID, storeID);
	}

}
