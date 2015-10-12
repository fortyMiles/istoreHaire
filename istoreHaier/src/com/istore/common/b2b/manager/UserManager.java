package com.istore.common.b2b.manager;

import java.util.List;

import com.istore.common.b2b.entity.AdminUser;

public interface UserManager {

	/**
	 * 用户登录
	 */
	public List<AdminUser> findByUsername(String username, String password);
}
