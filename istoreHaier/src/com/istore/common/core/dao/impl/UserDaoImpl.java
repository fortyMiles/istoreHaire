package com.istore.common.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.istore.common.core.bean.User;
import com.istore.common.core.dao.UserDao;
import com.istore.common.core.mapper.FindUserMapper;

@Repository
public class UserDaoImpl  implements UserDao {

	
	@Autowired
	private FindUserMapper findUserMapper;
	

	public List<User> queryMemberListFromShop(String shopId, int index, int sum) {
		// TODO Auto-generated method stub
		return null;
	}

}
