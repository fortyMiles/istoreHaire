package com.istore.common.b2b.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.istore.common.b2b.Util.ColumnContent;
import com.istore.common.b2b.entity.AdminUser;

public class AdminUserRowMapper implements RowMapper<AdminUser>{

	public AdminUser mapRow(ResultSet rs, int i) throws SQLException{
		AdminUser amdinUser = new AdminUser();
		amdinUser.setUsername(rs.getString(ColumnContent._USER_USERNAME));
//		amdinUser.setPassword(rs.getString(ColumnContent._USER_PASSWORD));
		amdinUser.setStoreId(rs.getString(ColumnContent._USER_STOREID));
		amdinUser.setUsers_id(rs.getString(ColumnContent._USER_ID));
		return amdinUser;
	}
	
}
