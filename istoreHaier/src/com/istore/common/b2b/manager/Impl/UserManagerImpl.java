package com.istore.common.b2b.manager.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istore.common.b2b.dao.AdminUserDAO;
import com.istore.common.b2b.entity.AdminUser;
import com.istore.common.b2b.manager.UserManager;

@Service
public class UserManagerImpl implements UserManager{

	@Autowired
	private AdminUserDAO adminUserDao;
	
	public List<AdminUser> findByUsername(String username, String password) {
		return adminUserDao.findByUsername(username, password);
	}

	

	
}
