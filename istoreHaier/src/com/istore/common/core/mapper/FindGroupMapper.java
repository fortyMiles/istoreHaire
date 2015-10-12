/**
 * @Project istoreHaierP
 * @Package com.istore.common.core.mapper
 * @Title FindFtpMapper.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-15
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.istore.common.core.bean.User;
import com.istore.common.core.bean.XNews;
import com.istore.common.core.provider.GroupSqlProvider;

/**
 * @ClassName: FindGroupMapper.java
 * @Description: TODO
 * @author gaominquan
 * @time 2014-7-15下午1:27:42
 */
public interface FindGroupMapper {

	/**
	 * 获得GroupName
	 * 
	 * @param type
	 * @return
	 */
	//gaominquan added
	
	public static final String ADD_GROUP = "insert into xGROUP (xGROUP_ID, xGROUP_NAME, DESCRIPTION，STORE_ID ) values ((select max(xGROUP_ID)+1 from xGROUP), #{groupName},#{groupDescription},#{store_id})";
	@Insert(ADD_GROUP)
	public int addGroupByName(@Param("groupName") String groupName,@Param("groupDescription") String groupDescription, @Param("store_id") String stroe_id);

	
	
	public static final String DELETE_GROUP = "delete from xGROUP where xGROUP_ID= #{groupID}";
	@Update(DELETE_GROUP)
	public int deleteGroupByName(@Param("groupID") String groupId);
	//------------------------------------------
	//-----Part 2
	/**
	 * add a member to one group
	 */
	
	public static final String ADD_MEMBER_TO_GROUP = "insert into XUSERGROUP (XGROUP_ID,XUSER_ID,STORE_ID) values (#{groupID},#{userID},#{storeID}";
	@Insert(ADD_MEMBER_TO_GROUP)
	public int addUserIntoMemebr(@Param("groupID") String groupId,@Param("userID") String userID, @Param("storeID") String storeID);
	
	/**
	 * delete member from one group
	 */
	
	public static final String DELETE_MEMBER_FROM_GROUP = "delete from XUSERSGROUP where users_id = #{userID} and group_id = #{groupID} and store_id=#{store_id}";
	@Update(DELETE_MEMBER_FROM_GROUP)
	public int delelteMemberFromGroup(@Param("groupID") String groupID, @Param("userID") String userID,@Param("store_id") String storeID);

	/**
	 * Get the list of all members of one group
	 */
	
	
	public static final String SELECT_GROUP_MEMBER = "select u.users_id, ur.logonID, u.customer_name, u.TYPE, u.status, u.lastupdate from xusers u left join xusersgroup ug on u.users_id=ug.users_id left join userreg ur on u.users_id=ur.users_id where xgroup_id=？ and store_id= ？";
	@Select(SELECT_GROUP_MEMBER)
	@Results(value = {
			@Result(column = "users_id", property = "usersId", id = true),
			@Result(column = "logonid", property = "logonId"),
			@Result(column = "customer_name", property = "codeNum"),
			@Result(column = "status", property = "status"),
			@Result(column = "type", property = "type"),
			@Result(column = "lastUpdate", property = "lastUpdate"),})
	public List<User> getGroupUsers(@Param("startIndex") int startIndex, @Param("endIndex") int endIndex,@Param("groupID") String groupID, @Param("storeID") String storeID);

	/**
	 * get the number of one group
	 */
	public static final String GET_MEMBER_SIZE = "select count(users_id) from xusersgroup where store_id=#{storeID} and xgroup_id=#{groupID}";
	@Select(GET_MEMBER_SIZE)
	public int getGroupMemberSize(@Param("groupID") String groupID, @Param("storeID") String storeID);
	
	//gaominquan added end
}
