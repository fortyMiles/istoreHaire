/**
 * @Project istoreHaier
 * @Package com.istore.common.core.mng
 * @Title GroupMng.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-16
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mng;

import java.util.List;

import com.istore.common.core.bean.User;

/**
 * @ClassName: GroupMng.java
 * @Description: TODO
 * @author Gaominqua
 * @time 2014-7-16下午2:52:44
 */
public interface GrpMng {
	/**
	 * 获得GroupName
	 * 
	 * @param type
	 * @return
	 */
	
	/**
	 * Added by gaominquan 2014-7-25
	 * @param storeId 
	 */
	
	public boolean addGroupByName(String name,String store, String storeId);
	public boolean deleteGroupById(String groupId);
	
	//----added at 2014-7-26-17:00
	
	public boolean addGroupMember(String groupID,String memeberID, String storeID);
	public boolean deleteGroupMember(String groupID,String memeberID,String storeID);
	public int getGroupMemberSize(String groupID,String storeID);
	public List<User> showGroupMember(int startIndex, int endIndex,String groupID,String stroeID);
	
	/**
	 * Gaomiqnuan Add End
	 */
}
