package com.istore.common.core.dao;

import java.sql.Timestamp;
import java.util.List;

import com.istore.common.core.bean.User;

public interface UsersDao {

	/**
	 * 获得会员数量
	 */
	
	public int getUserListSize(String storeId);
	
	/**
	 * 获得会员信息
	 */
	public List<User> getUserList(int startIndex, int endIndex, String storeId);
	
	/**
	 * 获得会员详情
	 */
	public User getUserDetail(long users_id);
	
	/**
	 * 查询出待审核会员的总数
	 */
   public int 	getVerifyUserListSize(String storeId);
   
   /**
 	 * 查询出待审核会员的列表
 	 */
 	public List<User> getVerifyUserList(int startIndex, int endIndex,String storeId);

 	
 	/**
	 * 查询待审核的user详情
	 */
	public User getVerifyUserDetail(long users_id);

 	
 	/**
 	 * 管理员列表总数
 	 */
 	public int getAdminListSize(String storeId);
 	
 	/**
	 * 审核会员
	 */
	public int addApprovalUser(int usersId,int  approveId,String statusBefore,String statusAfter,Timestamp createTime,String approveResult);
	
	/**
	 * 更新 会员状态
	 * 
	 */
	public int updateUserStatus(String  users_id);
	
	/**
	 * 拒绝会员
	 */
	public int refuseApprovalUser(int usersId,int approveId,String statusBefore,String statusAfter,Timestamp createTime,String approveResult);
	
	/**
	 * 拒绝更新 会员状态
	 * 
	 */
	public int updateRefuseUserStatus(String  users_id);
 	

}
