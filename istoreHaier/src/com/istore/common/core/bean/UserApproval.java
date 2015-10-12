/**
 * 
 */
package com.istore.common.core.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author wangyan
 *
 */
public class UserApproval implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	//被审核会员
	private int users_id;
	//审核人
	private int approverId;
	// 审核前状态
	private String statusBefore;
	//审核后状态
	private String statusAfter;
	//创建时间
	private Timestamp createTime;
	//审核内容
	private String approveResult;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getUsers_id() {
		return users_id;
	}
	public void setUsers_id(int usersId) {
		users_id = usersId;
	}
	public int getApproverId() {
		return approverId;
	}
	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}
	public String getStatusBefore() {
		return statusBefore;
	}
	public void setStatusBefore(String statusBefore) {
		this.statusBefore = statusBefore;
	}
	public String getStatusAfter() {
		return statusAfter;
	}
	public void setStatusAfter(String statusAfter) {
		this.statusAfter = statusAfter;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getApproveResult() {
		return approveResult;
	}
	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}
	
	
	
	
	

}
