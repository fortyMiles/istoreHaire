package com.istore.common.b2b.dao;

import java.util.List;
import com.istore.common.b2b.entity.AdminUser;

public interface AdminUserDAO {

	/**
	 * 通过用户名查询
	 */
	public List<AdminUser> findByUsername(String username, String password);
}
