package com.istore.common.core.mng.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istore.common.core.bean.User;
import com.istore.common.core.dao.UsersDao;
import com.istore.common.core.mng.UsersMng;

@Service
@Transactional
public class UsersMngImpl implements UsersMng{
	
	@Autowired
	public UsersDao usersDao;

	public List<User> getUserList(int startIndex, int endIndex, String storeId) {
		return usersDao.getUserList(startIndex, endIndex, storeId);
	}

	public int getUserListSize(String storeId) {
		return usersDao.getUserListSize(storeId);
	}
	
	public User getUserDetail(long users_id){
		return usersDao.getUserDetail(users_id);
	}

	public int getVerifyUserListSize(String storeId) {
		return usersDao.getVerifyUserListSize(storeId);
	}

	public List<User> getVerifyUserList(int startIndex, int endIndex,String storeId) {
		return usersDao.getVerifyUserList(startIndex, endIndex,storeId);
	}
	
	public int getAdminListSize(String storeId){
		return usersDao.getAdminListSize(storeId);
	}

	public User getVerifyUserDetail(long usersId) {
		// TODO Auto-generated method stub
		return usersDao.getVerifyUserDetail(usersId);
	}

	public int addApprovalUser(int usersId, int approveId,
			String statusBefore, String statusAfter, Timestamp createTime,
			String approveResult) {
		// TODO Auto-generated method stub
		return usersDao.addApprovalUser(usersId, approveId, statusBefore, statusAfter, createTime, approveResult);
	}

	public int updateUserStatus(String usersId) {
		// TODO Auto-generated method stub
		return usersDao.updateUserStatus(usersId);
	}

	public int refuseApprovalUser(int usersId, int approveId,
			String statusBefore, String statusAfter, Timestamp createTime,
			String approveResult) {
		// TODO Auto-generated method stub
		return usersDao.refuseApprovalUser(usersId, approveId, statusBefore, statusAfter, createTime, approveResult);
	}

	public int updateRefuseUserStatus(String usersId) {
		// TODO Auto-generated method stub
		return usersDao.updateRefuseUserStatus(usersId);
	}

}
