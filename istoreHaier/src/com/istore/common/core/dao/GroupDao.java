/**
 * @Project istoreHaier
 * @Package com.istore.common.core.dao
 * @Title FtpDao.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-15
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.dao;

import java.util.List;

import com.istore.common.core.bean.User;

/**
 * @ClassName: GroupDao.java
 * @Description: TODO
 * @author Gaominquan
 * @time 2014-7-15下午1:18:06
 */
public interface GroupDao {

	public int deleteGroupById(String groupId) ;

	public int addGroupByName(String name, String description, String storeId);
	
	//---------added in 2014-7-26-17:05
	
	public int deleteGroupMemeber(String groupID, String memeberID,String storeID);
	public int addGroupMemeber(String groupID, String memeberID,String storeID);
	public List<User> showGroupMember(int startIndex, int endIndex,String groupID,String storeID);
	public int getGroupMemberSize(String groupID, String storeID);

}
