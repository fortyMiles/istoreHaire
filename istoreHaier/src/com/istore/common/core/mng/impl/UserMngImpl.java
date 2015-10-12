package com.istore.common.core.mng.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.istore.common.core.bean.User;
import com.istore.common.core.dao.UserDao;
import com.istore.common.core.mng.UserMng;

@Service
@Transactional
public class UserMngImpl implements UserMng{

	@Autowired
	UserDao userDao;

	public List<User> exportMemberInfoForAdminAndShop(String type, String shopId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
