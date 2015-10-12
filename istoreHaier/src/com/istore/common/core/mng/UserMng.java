package com.istore.common.core.mng;

import java.util.List;
import com.istore.common.core.bean.User;




public interface UserMng {
	
	//导出加盟商和门店的所有会员
	public List<User> exportMemberInfoForAdminAndShop(String type,String shopId);
	
}
