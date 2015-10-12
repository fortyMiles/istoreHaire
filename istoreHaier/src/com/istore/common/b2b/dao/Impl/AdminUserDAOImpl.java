package com.istore.common.b2b.dao.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.istore.common.b2b.Util.JdbcBaseTemplate;
import com.istore.common.b2b.dao.AdminUserDAO;
import com.istore.common.b2b.entity.AdminUser;
import com.istore.common.b2b.entity.mapper.AdminUserRowMapper;

@Repository
public class AdminUserDAOImpl extends JdbcBaseTemplate<AdminUser> implements AdminUserDAO{
	
	private static final Logger log = LoggerFactory.getLogger(AdminUserDAOImpl.class);

	@Override
	protected Class<AdminUser> getEntityClass() {
		return AdminUser.class;
	}

	public List<AdminUser> findByUsername(String username, String password) {
		String sql = "select u.LOGONID, x.STOREID, u.users_id from xusers x join userreg u on u.USERS_ID = x.USERS_ID where u.LOGONID = ? and x.PSWD = ?";
		Object[] args = {username, password};
		log.info(sql, args);
		return super.findByObject(sql, args, new AdminUserRowMapper());
	}

}
