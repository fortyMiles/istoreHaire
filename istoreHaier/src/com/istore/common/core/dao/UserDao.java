package com.istore.common.core.dao;

import java.util.List;
import com.istore.common.core.bean.User;



public interface UserDao {
	
	
	//查询当前店铺的所有会员列表
	public List<User> queryMemberListFromShop(String shopId, int index, int sum);
}
