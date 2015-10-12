package com.istore.common.core.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.User;
import com.istore.common.core.dao.UsersDao;
import com.istore.common.core.mapper.UsersMapper;

@Repository
public class UsersDaoImpl implements UsersDao{

	@Autowired
	private UsersMapper usersMapper;
	
	public List<User> getUserList(int startIndex, int endIndex, String storeId) {
		return usersMapper.getUserList(startIndex, endIndex, storeId);
	}

	public int getUserListSize(String storeId) {
		List<User> userList = usersMapper.getUserListSize(storeId);
		return userList.size();
	}

	public User getUserDetail(long users_id) {
		return usersMapper.getUserDetail(users_id);
	}

	public int getVerifyUserListSize(String storeId) {
		// TODO Auto-generated method stub

		return usersMapper.getVerifyUserListSize("P",storeId);
	}

	public List<User> getVerifyUserList(int startIndex, int endIndex,String storeId) {
		// TODO Auto-generated method stub
		return usersMapper.getVerifyUserList(startIndex, endIndex,storeId);
	}
   /**
    * 查询待审核会员详情
    */
	public User getVerifyUserDetail(long usersId) {
		// TODO Auto-generated method stub
		return usersMapper.getVerifyUserDetail(usersId);
	}
	
	public int getAdminListSize(String storeId){
		return usersMapper.getAdminListSize(storeId);
	}

	public int addApprovalUser(int usersId, int approveId,
			String statusBefore, String statusAfter, Timestamp createTime,
			String approveResult) {
		// TODO Auto-generated method stub
		
		return usersMapper.addApprovalUser(usersId, approveId,statusBefore,statusAfter,createTime,approveResult);
	}

	public int updateUserStatus(String users_id) {
		// TODO Auto-generated method stub
		return usersMapper.updateUserStatus(users_id);
	}

	public int refuseApprovalUser(int usersId, int approveId,
			String statusBefore, String statusAfter, Timestamp createTime,
			String approveResult) {
		// TODO Auto-generated method stub
		return usersMapper.refuseApprovalUser(usersId, approveId, statusBefore, statusAfter, createTime, approveResult);
	}

	public int updateRefuseUserStatus(String usersId) {
		// TODO Auto-generated method stub
		return usersMapper.updateRefuseUserStatus(usersId);
	}

}
